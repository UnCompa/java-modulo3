package com.krakedev.inventario.entidades;

import java.math.BigDecimal;

public class DetallePedido {
    private int codigo;
    private int cebecera;
    private Producto producto;
    private BigDecimal cantidadSolicitada;
    private BigDecimal subtotal;
    private BigDecimal cantidadRecibida;

    public DetallePedido(int cebecera, Producto producto, BigDecimal cantidadSolicitada, BigDecimal subtotal) {
        this.cebecera = cebecera;
        this.producto = producto;
        this.cantidadSolicitada = cantidadSolicitada;
        this.subtotal = subtotal;
    }

    public DetallePedido(int codigo, int cebecera, Producto producto, BigDecimal cantidadSolicitada,
            BigDecimal subtotal, BigDecimal cantidadRecibida) {
        this.codigo = codigo;
        this.cebecera = cebecera;
        this.producto = producto;
        this.cantidadSolicitada = cantidadSolicitada;
        this.subtotal = subtotal;
        this.cantidadRecibida = cantidadRecibida;
    }

    public DetallePedido() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCebecera() {
        return cebecera;
    }

    public void setCebecera(int cebecera) {
        this.cebecera = cebecera;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public BigDecimal getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(BigDecimal cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(BigDecimal cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    @Override
    public String toString() {
        return "DetallePedido [codigo=" + codigo + ", cebecera=" + cebecera + ", producto=" + producto
                + ", cantidadSolicitada=" + cantidadSolicitada + ", subtotal=" + subtotal + ", cantidadRecibida="
                + cantidadRecibida + "]";
    }

}
