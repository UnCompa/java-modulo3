package com.krakedev.inventario.entidades;

import java.math.BigDecimal;

public class DetalleVenta {
    private int codigo;
    private int cebeceraVentasFK;
    private Producto producto;
    private int cantidad;
    private BigDecimal precioVenta;
    private BigDecimal subtotal;
    private BigDecimal subtotalIva;

    public DetalleVenta() {
    }

    public DetalleVenta(int codigo, int cebeceraVentasFK, Producto producto, int cantidad, BigDecimal precioVenta,
            BigDecimal subtotal, BigDecimal subtotalIva) {
        this.codigo = codigo;
        this.cebeceraVentasFK = cebeceraVentasFK;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.subtotal = subtotal;
        this.subtotalIva = subtotalIva;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCebeceraVentasFK() {
        return cebeceraVentasFK;
    }

    public void setCebeceraVentasFK(int cebeceraVentasFK) {
        this.cebeceraVentasFK = cebeceraVentasFK;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getSubtotalIva() {
        return subtotalIva;
    }

    public void setSubtotalIva(BigDecimal subtotalIva) {
        this.subtotalIva = subtotalIva;
    }

    @Override
    public String toString() {
        return "DetalleVenta [codigo=" + codigo + ", cebeceraVentasFK=" + cebeceraVentasFK + ", producto=" + producto
                + ", cantidad=" + cantidad + ", precioVenta=" + precioVenta + ", subtotal=" + subtotal
                + ", subtotalIva=" + subtotalIva + "]";
    }

}
