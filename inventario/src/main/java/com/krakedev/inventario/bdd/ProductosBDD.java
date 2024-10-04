package com.krakedev.inventario.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.krakedev.inventario.entidades.Producto;
import com.krakedev.inventario.entidades.Categoria;
import com.krakedev.inventario.entidades.UnidadDeMedida;
import com.krakedev.inventario.utils.ConexionBDD;

public class ProductosBDD {
	public ArrayList<Producto> buscar(String subcadena) {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Connection conexion = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conexion = ConexionBDD.conectar();
			ps = conexion.prepareStatement("select pr.codigo_producto, pr.nombre as nombre_producto, " +
					"pr.udm as unidad_medida, udm.descripcion,cast(pr.precio_venta as decimal(6,2)), pr.tiene_iva, " +
					"cast(pr.coste as decimal(5,4)), cat.codigo_cat as categoria, cat.nombre as nombre_categoria ,pr.stock "
					+
					"from productos as pr, unidades_medida as udm, categorias as cat " +
					"where pr.udm = udm.codigo_udm " +
					"and cat.codigo_cat = pr.categoria " +
					"and upper(pr.nombre) like ?");
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				int codigoProducto = rs.getInt("codigo_producto");
				String nombreProducto = rs.getString("nombre_producto");
				String udmCodigo = rs.getString("unidad_medida");
				BigDecimal precioVenta = rs.getBigDecimal("precio_venta");
				UnidadDeMedida udm = new UnidadDeMedida(udmCodigo);
				boolean tieneIva = rs.getBoolean("tiene_iva");
				BigDecimal coste = rs.getBigDecimal("coste");
				int codigoCategoria = rs.getInt("categoria");
				String nombreCategoria = rs.getString("nombre_categoria");
				Categoria categoria = new Categoria(codigoCategoria, nombreCategoria);
				int stock = rs.getInt("stock");
				Producto producto = new Producto(codigoProducto, nombreProducto, udm, precioVenta, tieneIva, coste,
						categoria, stock);
				productos.add(producto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productos;
	}

	public void insertar(Producto producto) throws Exception {
		Connection conexion = null;
		PreparedStatement ps = null;
		try {
			conexion = ConexionBDD.conectar();
			ps = conexion.prepareStatement(
					"INSERT INTO productos (nombre,udm,precio_venta,tiene_iva,coste,categoria,stock)" +
							"VALUES (?,?,?,?,?,?,?)");
			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getUdm().getNombre());
			ps.setBigDecimal(3, producto.getPrecioVenta());
			ps.setBoolean(4, producto.isTieneIva());
			ps.setBigDecimal(5, producto.getCoste());
			ps.setInt(6, producto.getCategoria().getCodigoCat());
			ps.setInt(7, producto.getStock());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al crear un producto");
		}
	}

	public void actualizar(Producto producto) throws Exception {
		Connection conexion = null;
		PreparedStatement ps = null;
		try {
			conexion = ConexionBDD.conectar();
			ps = conexion.prepareStatement(
					"UPDATE productos SET nombre = ?, udm = ?, precio_venta = ?, tiene_iva = ?, coste = ?, categoria = ?, stock = ? WHERE codigo_producto = ?");
			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getUdm().getNombre());
			ps.setBigDecimal(3, producto.getPrecioVenta());
			ps.setBoolean(4, producto.isTieneIva());
			ps.setBigDecimal(5, producto.getCoste());
			ps.setInt(6, producto.getCategoria().getCodigoCat());
			ps.setInt(7, producto.getStock());
			ps.setInt(8, producto.getCodigoProducto());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al crear un producto");
		}
	}
}
