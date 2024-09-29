package com.krakedev.inventario.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.krakedev.inventario.entidades.Proveedor;
import com.krakedev.inventario.entidades.TipoDocumento;
import com.krakedev.inventario.utils.ConexionBDD;

public class ProveedoresBDD {
	public ArrayList<Proveedor> buscar(String subcadena) {
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		Connection conexion = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conexion = ConexionBDD.conectar();
			ps = conexion.prepareStatement(
					"select pro.identificador, pro.nombre, pro.telefono, pro.correo, pro.direccion, td.codigo, td.descripcion "
							+ //
							"from proveedores as pro, tipo_documento as td " + //
							"where pro.tipo_documento = td.codigo and upper(pro.nombre) like ?;");
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				String identificador = rs.getString("identificador");
				String codigoTipoDocumento = rs.getString("codigo");
				String descripcionTipoDocumento = rs.getString("descripcion");
				TipoDocumento tipoDocumento = new TipoDocumento(codigoTipoDocumento, descripcionTipoDocumento);
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("telefono");
				String direccion = rs.getString("direccion");
				Proveedor pro = new Proveedor(identificador, tipoDocumento, nombre, telefono, correo, direccion);
				proveedores.add(pro);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return proveedores;
	}

	public void insertar(Proveedor proveedor) throws Exception {
		Connection conexion = null;
		PreparedStatement ps = null;
		try {
			conexion = ConexionBDD.conectar();
			ps = conexion.prepareStatement(
					"insert into proveedores (identificador,tipo_documento, nombre, telefono, correo, direccion) " +
							"values (?,?,?,?,?,?)");
			ps.setString(1, proveedor.getIdentifficador());
			ps.setString(2, proveedor.getTipoDocumento().getCodigo());
			ps.setString(3, proveedor.getNombre());
			ps.setString(4, proveedor.getTelefono());
			ps.setString(5, proveedor.getCorreo());
			ps.setString(6, proveedor.getDireccion());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al insertar");
		}
	}
}
