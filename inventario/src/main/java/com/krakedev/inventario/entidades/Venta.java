package com.krakedev.inventario.entidades;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Venta {
    private int codigo;
    private Timestamp fecha;
    private BigDecimal totalSinIva;
    private BigDecimal iva;
    private BigDecimal total;

    private ArrayList<DetalleVenta> detalles;

    public Venta() {
    }

    public Venta(int codigo, Timestamp fecha, BigDecimal totalSinIva, BigDecimal iva, BigDecimal total) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.totalSinIva = totalSinIva;
        this.iva = iva;
        this.total = total;
    }

    public Venta(int codigo, Timestamp fecha, BigDecimal totalSinIva, BigDecimal iva, BigDecimal total,
            ArrayList<DetalleVenta> detalles) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.totalSinIva = totalSinIva;
        this.iva = iva;
        this.total = total;
        this.detalles = detalles;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotalSinIva() {
        return totalSinIva;
    }

    public void setTotalSinIva(BigDecimal totalSinIva) {
        this.totalSinIva = totalSinIva;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Venta [codigo=" + codigo + ", fecha=" + fecha + ", totalSinIva=" + totalSinIva + ", iva=" + iva
                + ", total=" + total + ", detalles=" + detalles + "]";
    }

    public ArrayList<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleVenta> detalles) {
        this.detalles = detalles;
    }


}
