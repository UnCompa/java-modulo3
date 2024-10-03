package com.krakedev.inventario.entidades;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private int numero_pedido;
    private Proveedor proveedor;
    private Date fecha;
    private EstadoPedido estado;

    public Pedido(Proveedor proveedor, Date fecha, EstadoPedido estado) {
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Pedido() {
    }

    private ArrayList<DetallePedido> detalles;

    public int getNumero_pedido() {
        return numero_pedido;
    }

    public void setNumero_pedido(int numero_pedido) {
        this.numero_pedido = numero_pedido;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public ArrayList<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Pedido [numero_pedido=" + numero_pedido + ", proveedor=" + proveedor + ", fecha=" + fecha + ", estado="
                + estado + ", detalles=" + detalles + "]";
    }
}
