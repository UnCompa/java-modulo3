package com.krakedev.inventario.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.krakedev.inventario.entidades.Proveedor;
import com.krakedev.inventario.utils.ConexionBDD;

public class ProveedoresBDD {
	public ArrayList<Proveedor> buscar(String subcadena) {
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		Connection conexion = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conexion = ConexionBDD.conectar();
			ps = conexion.prepareStatement("select * from proveedores "
					+ "where upper(nombre) like ? ");
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				String identificador = rs.getString("identificador");
				String tipoDocumento = rs.getString("tipo_documento");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("telefono");
				String direccion = rs.getString("direccion");
			Proveedor pro = new Proveedor(identificador, tipoDocumento, nombre,telefono,correo,direccion);
				proveedores.add(pro);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proveedores;
	}

}
