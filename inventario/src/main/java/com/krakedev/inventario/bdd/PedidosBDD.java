package com.krakedev.inventario.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventario.entidades.DetallePedido;
import com.krakedev.inventario.entidades.Pedido;
import com.krakedev.inventario.utils.ConexionBDD;

public class PedidosBDD {
    public void insertar(Pedido pedido) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        PreparedStatement psDet = null;
        ResultSet rsClave = null;
        Date fechaHoy = new Date();
        java.sql.Date fechaSQL = new java.sql.Date(fechaHoy.getTime());
        int codigoGenerado = 0;
        try {
            conexion = ConexionBDD.conectar();
            ps = conexion.prepareStatement("INSERT INTO cabecera_pedido (proveedor, fecha, estado_fk) " +
                    "VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pedido.getProveedor().getidentificador());
            ps.setDate(2, fechaSQL);
            ps.setString(3, "S");
            ps.executeUpdate();

            rsClave = ps.getGeneratedKeys();

            if (rsClave.next()) {
                codigoGenerado = rsClave.getInt(1);
            }
            ArrayList<DetallePedido> detalles = pedido.getDetalles();
            for (DetallePedido detallePedido : detalles) {

                psDet = conexion.prepareStatement(
                        "INSERT INTO detalle_pedido (cabecera_pedido_fk, producto, cantidad_solicitada, subtotal, cantidad_recibida) "
                                +
                                "VALUES (?,?,?,?,?);");

                psDet.setInt(1, codigoGenerado);
                psDet.setInt(2, detallePedido.getProducto().getCodigoProducto());
                psDet.setBigDecimal(3, detallePedido.getCantidadSolicitada());
                BigDecimal pv = detallePedido.getProducto().getPrecioVenta();
                BigDecimal subtotal = pv.multiply(detallePedido.getCantidadSolicitada());
                psDet.setBigDecimal(4, subtotal);
                psDet.setInt(5, 0);

                psDet.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al crear un producto");
        }
    }
}
