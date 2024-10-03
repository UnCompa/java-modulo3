package com.krakedev.inventario.entidades;

import java.math.BigDecimal;

public class Producto {
    private int codigoProducto;
    private String nombre;
    private UnidadDeMedida udm;
    private BigDecimal precioVenta;
    private boolean tieneIva;
    private BigDecimal coste;
    private Categoria categoria;
    private int stock;

    public Producto() {
    }

    public Producto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Producto(int codigoProducto, String nombre, UnidadDeMedida udm, BigDecimal precioVenta, boolean tieneIva,
            BigDecimal coste, Categoria categoria, int stock) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.udm = udm;
        this.precioVenta = precioVenta;
        this.tieneIva = tieneIva;
        this.coste = coste;
        this.categoria = categoria;
        this.stock = stock;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UnidadDeMedida getUdm() {
        return udm;
    }

    public void setUdm(UnidadDeMedida udm) {
        this.udm = udm;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public boolean isTieneIva() {
        return tieneIva;
    }

    public void setTieneIva(boolean tieneIva) {
        this.tieneIva = tieneIva;
    }

    public BigDecimal getCoste() {
        return coste;
    }

    public void setCoste(BigDecimal coste) {
        this.coste = coste;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto [codigoProducto=" + codigoProducto + ", nombre=" + nombre + ", udm=" + udm + ", precioVenta="
                + precioVenta + ", tieneIva=" + tieneIva + ", coste=" + coste + ", categoria=" + categoria + ", stock="
                + stock + "]";
    }

}
