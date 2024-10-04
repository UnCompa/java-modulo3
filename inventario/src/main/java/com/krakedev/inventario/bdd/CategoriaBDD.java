package com.krakedev.inventario.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.krakedev.inventario.entidades.Categoria;
import com.krakedev.inventario.utils.ConexionBDD;

public class CategoriaBDD {
    public void insertar(Categoria categoria) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        try {
            conexion = ConexionBDD.conectar();
            if (categoria.getCategoriaPadre() == null) {
                ps = conexion.prepareStatement("INSERT INTO categorias (nombre) VALUES (?);");
                ps.setString(1, categoria.getNombre());
            } else {
                ps = conexion.prepareStatement("INSERT INTO categorias (nombre, categoria_padre) VALUES (?,?);");
                ps.setString(1, categoria.getNombre());
                ps.setInt(2, categoria.getCategoriaPadre().getCodigoCat());
            }
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al crear una categoria");
        }
    }

    public void actualizar(Categoria categoria) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        try {
            conexion = ConexionBDD.conectar();
            ps = conexion.prepareStatement(
                    "UPDATE categorias set nombre = ?, categoria_padre = ? WHERE codigo_cat = ?");
            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getCategoriaPadre().getCodigoCat());
            ps.setInt(3, categoria.getCodigoCat());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar una categoria");
        }
    }

    public ArrayList<Categoria> buscar() {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        Connection conexion = null;
        PreparedStatement ps = null;
        PreparedStatement psSub = null;
        ResultSet rs = null;
        ResultSet rsSub = null;
        try {
            conexion = ConexionBDD.conectar();
            ps = conexion.prepareStatement("select * from categorias");
            rs = ps.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("codigo_cat");
                String nombre = rs.getString("nombre");
                int codigoCategoriaPadre = rs.getInt("categoria_padre");
                Categoria categoriaPadre = null;
                if (codigoCategoriaPadre > 0) {
                    psSub = conexion.prepareStatement("select * from categorias WHERE codigo_cat = ?");
                    psSub.setInt(1, codigoCategoriaPadre);
                    rsSub = psSub.executeQuery();
                    if (rsSub.next()) {
                        int codigoPadre = rsSub.getInt("codigo_cat");
                        String nombrePadre = rsSub.getString("nombre");
                        int codigoCategoriaPadrePadre = rsSub.getInt("categoria_padre");
                        categoriaPadre = new Categoria(codigoPadre, nombrePadre, codigoCategoriaPadrePadre);
                    }
                }
                Categoria categoria = new Categoria(codigo, nombre, categoriaPadre);
                categorias.add(categoria);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorias;
    }
}
