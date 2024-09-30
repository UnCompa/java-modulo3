package com.krakedev.inventario.entidades;

public class UnidadDeMedida {
    private String nombre;
    private String descripcion;
    private CategoriaUDM categoriaUDM;

    public UnidadDeMedida() {
    }

    public UnidadDeMedida(String nombre) {
        this.nombre = nombre;
    }

    public UnidadDeMedida(String nombre, String descripcion, CategoriaUDM categoriaUDM) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaUDM = categoriaUDM;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaUDM getCategoriaUDM() {
        return categoriaUDM;
    }

    public void setCategoriaUDM(CategoriaUDM categoriaUDM) {
        this.categoriaUDM = categoriaUDM;
    }

}
