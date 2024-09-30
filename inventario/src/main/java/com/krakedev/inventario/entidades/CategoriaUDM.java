package com.krakedev.inventario.entidades;

public class CategoriaUDM {
    private int codigoUDM;
    private String nombre;

    public CategoriaUDM() {
    }

    public CategoriaUDM(int codigoUDM, String nombre) {
        this.codigoUDM = codigoUDM;
        this.nombre = nombre;
    }

    public int getCodigoUDM() {
        return codigoUDM;
    }

    public void setCodigoUDM(int codigoUDM) {
        this.codigoUDM = codigoUDM;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "CategoriaUDM [codigoUDM=" + codigoUDM + ", nombre=" + nombre + "]";
    }

}
