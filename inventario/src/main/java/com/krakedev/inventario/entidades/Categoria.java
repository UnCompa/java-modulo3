package com.krakedev.inventario.entidades;

public class Categoria {
    private int codigoCat;
    private String nombre;
    private Categoria categoriaPadre;

    public Categoria() {
    }

    public Categoria(int codigoCat) {
        this.codigoCat = codigoCat;
    }
    public Categoria(int codigoCat, String nombre) {
        this.codigoCat = codigoCat;
        this.nombre = nombre;
    }

    public Categoria(int codigoCat, String nombre, Categoria categoriaPadre) {
        this.codigoCat = codigoCat;
        this.nombre = nombre;
        this.categoriaPadre = categoriaPadre;
    }
    public Categoria(int codigoCat, String nombre, int categoriaPadre) {
        this.codigoCat = codigoCat;
        this.nombre = nombre;
        this.categoriaPadre = new Categoria(categoriaPadre);
    }

    public int getCodigoCat() {
        return codigoCat;
    }

    public void setCodigoCat(int codigoCat) {
        this.codigoCat = codigoCat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoriaPadre() {
        return categoriaPadre;
    }

    public void setCategoriaPadre(Categoria categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }

    @Override
    public String toString() {
        return "Categoria [codigoCat=" + codigoCat + ", nombre=" + nombre + ", categoriaPadre=" + categoriaPadre + "]";
    }

}
