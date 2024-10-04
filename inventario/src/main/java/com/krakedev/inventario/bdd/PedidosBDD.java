package com.krakedev.inventario.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventario.entidades.DetallePedido;
import com.krakedev.inventario.entidades.EstadoPedido;
import com.krakedev.inventario.entidades.Pedido;
import com.krakedev.inventario.entidades.Producto;
import com.krakedev.inventario.entidades.Proveedor;
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

    public void recibir(Pedido pedido) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        PreparedStatement psDet = null;
        PreparedStatement psHis = null;
        Date fechaActual = new Date();
        Timestamp fechaTimestamp = new Timestamp(fechaActual.getTime());
        try {
            conexion = ConexionBDD.conectar();
            ps = conexion.prepareStatement("UPDATE cabecera_pedido set estado_fk = ? " +
                    "WHERE numero_pedido = ?;");
            ps.setString(1, "R");
            System.out.println(pedido.getNumero_pedido());
            ps.setInt(2, pedido.getNumero_pedido());

            ps.executeUpdate();

            ArrayList<DetallePedido> detalles = pedido.getDetalles();
            for (DetallePedido detallePedido : detalles) {

                psDet = conexion.prepareStatement(
                        "UPDATE detalle_pedido set subtotal = ?, cantidad_recibida = ? "
                                +
                                "WHERE codigo = ?");

                BigDecimal pv = detallePedido.getProducto().getPrecioVenta();
                BigDecimal subtotal = pv.multiply(detallePedido.getCantidadRecibida());
                psDet.setBigDecimal(1, subtotal);
                psDet.setBigDecimal(2, detallePedido.getCantidadRecibida());
                psDet.setInt(3, detallePedido.getCodigo());

                psDet.executeUpdate();

                psHis = conexion.prepareStatement(
                        "INSERT INTO historial_stock (fecha, referencia, producto_fk,cantidad) VALUES (?,?,?,?);");
                psHis.setTimestamp(1, fechaTimestamp);
                psHis.setString(2, "PEDIDO " + pedido.getNumero_pedido());
                psHis.setInt(3, detallePedido.getCodigo());
                psHis.setBigDecimal(4, detallePedido.getCantidadRecibida());

                psHis.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al crear un producto");
        }
    }

    public ArrayList<Pedido> buscarPorProveedor(String codigoProveedor) throws Exception {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement ps = null;
        PreparedStatement psDet = null;
        ResultSet rsCab = null;
        ResultSet rsDet = null;

        try {
            conexion = ConexionBDD.conectar();
            ps = conexion.prepareStatement("SELECT * FROM cabecera_pedido WHERE proveedor = ?;");
            ps.setString(1, codigoProveedor);
            rsCab = ps.executeQuery();

            while (rsCab.next()) {
                int numeroPedido = rsCab.getInt("numero_pedido");
                Date fecha = rsCab.getDate("fecha");
                String estado = rsCab.getString("estado_fk");

                ArrayList<DetallePedido> detalles = new ArrayList<>();
                psDet = conexion.prepareStatement(
                        "SELECT * FROM detalle_pedido WHERE cabecera_pedido_fk = ?;");
                psDet.setInt(1, numeroPedido);
                rsDet = psDet.executeQuery();

                while (rsDet.next()) {
                    int codigo = rsDet.getInt("codigo");
                    int codigoProducto = rsDet.getInt("producto");
                    BigDecimal cantidadSolicitada = rsDet.getBigDecimal("cantidad_solicitada");
                    BigDecimal subtotal = rsDet.getBigDecimal("subtotal");
                    BigDecimal cantidadRecibida = rsDet.getBigDecimal("cantidad_recibida");
                    Producto producto = new Producto(codigoProducto);
                    DetallePedido detallePedido = new DetallePedido(codigo, numeroPedido, producto, cantidadSolicitada,
                            subtotal, cantidadRecibida);
                    detalles.add(detallePedido);
                }

                Proveedor proveedor = new Proveedor(codigoProveedor);
                EstadoPedido estadoPedido = new EstadoPedido(Integer.parseInt(estado));
                Pedido pedidoRecuperado = new Pedido(numeroPedido, proveedor, fecha, estadoPedido, detalles);
                pedidos.add(pedidoRecuperado);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar pedidos por proveedor", e);
        }
        return pedidos;
    }

}
