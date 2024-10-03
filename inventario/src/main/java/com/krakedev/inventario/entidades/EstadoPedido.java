package com.krakedev.inventario.entidades;

public class EstadoPedido {
    private int codigo;
    private int descripcion;

    public EstadoPedido(int codigo) {
        this.codigo = codigo;
    }

    public EstadoPedido() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(int descripcion) {
        this.descripcion = descripcion;
    }

}
