package com.krakedev.inventario.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.krakedev.inventario.entidades.TipoDocumento;
import com.krakedev.inventario.utils.ConexionBDD;

public class TipoDocumentosBDD {
    public ArrayList<TipoDocumento> buscar() {
        ArrayList<TipoDocumento> documentos = new ArrayList<TipoDocumento>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ConexionBDD.conectar();
            ps = conexion.prepareStatement("select * from tipo_documento");
            rs = ps.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String descripcion = rs.getString("descripcion");
                TipoDocumento pro = new TipoDocumento(codigo,descripcion);
                documentos.add(pro);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return documentos;
    }

}
