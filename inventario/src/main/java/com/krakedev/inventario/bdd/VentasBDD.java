package com.krakedev.inventario.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventario.entidades.DetalleVenta;
import com.krakedev.inventario.entidades.Venta;
import com.krakedev.inventario.utils.ConexionBDD;

public class VentasBDD {
    public void guardar(Venta venta) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        PreparedStatement psDet = null;
        PreparedStatement psCab = null;
        PreparedStatement psHis = null;
        ResultSet rsClave = null;
        Date fechaActual = new Date();
        Timestamp fechaTimestamp = new Timestamp(fechaActual.getTime());
        int codigoGenerado = 0;
        try {
            conexion = ConexionBDD.conectar();
            ps = conexion.prepareStatement(
                    "INSERT INTO cabecera_ventas (fecha, total_sin_iva, iva, total) VALUES (?,?,?,?);",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, fechaTimestamp);
            ps.setInt(2, 0);
            ps.setInt(3, 0);
            ps.setInt(4, 0);
            ps.executeUpdate();

            rsClave = ps.getGeneratedKeys();

            if (rsClave.next()) {
                codigoGenerado = rsClave.getInt(1);
            }

            BigDecimal totalSinIva = new BigDecimal(0);
            BigDecimal iva = new BigDecimal(0);
            BigDecimal total = new BigDecimal(0);

            ArrayList<DetalleVenta> detalles = venta.getDetalles();
            for (DetalleVenta detallePedido : detalles) {
                psDet = conexion.prepareStatement(
                        "INSERT INTO detalle_venta (producto_fk,cantidad, precio_venta, subtotal, subtotal_iva, cabecera_ventas_fk) VALUES (?,?,?,?,?,?);");

                psDet.setInt(1, detallePedido.getProducto().getCodigoProducto());
                psDet.setInt(2, detallePedido.getCantidad());
                psDet.setBigDecimal(3, detallePedido.getProducto().getPrecioVenta());
                BigDecimal pv = detallePedido.getProducto().getPrecioVenta();
                BigDecimal subtotal = pv.multiply(new BigDecimal(detallePedido.getCantidad()));
                psDet.setBigDecimal(4, subtotal);
                if (detallePedido.getProducto().isTieneIva()) {
                    BigDecimal totalIva = subtotal.multiply(new BigDecimal(1.12));
                    iva.subtract(pv);
                    psDet.setBigDecimal(5, totalIva);
                } else {
                    psDet.setBigDecimal(5, subtotal);
                }
                psDet.setInt(6, codigoGenerado);

                totalSinIva.add(subtotal);
                psDet.executeUpdate();

                psHis = conexion.prepareStatement(
                        "INSERT INTO historial_stock (fecha, referencia, producto_fk,cantidad) VALUES (?,?,?,?);");
                psHis.setTimestamp(1, fechaTimestamp);
                psHis.setString(2, "VENTA " + codigoGenerado);
                psHis.setInt(3, detallePedido.getProducto().getCodigoProducto());
                BigDecimal cantidad = new BigDecimal(detallePedido.getCantidad());
                psHis.setBigDecimal(4, cantidad.multiply(new BigDecimal(-1)));

                psHis.executeUpdate();
            }
            total = totalSinIva.add(iva);
            psCab = conexion.prepareStatement(
                    "UPDATE cabecera_ventas set total_sin_iva = ?, iva = ?, total = ? where codigo = ?");
            psCab.setBigDecimal(1, totalSinIva);
            psCab.setBigDecimal(2, iva);
            psCab.setBigDecimal(3, total);
            psCab.setInt(4, codigoGenerado);

            psCab.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al crear un producto");
        }
    }
}
