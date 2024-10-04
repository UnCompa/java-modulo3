package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.bdd.CategoriaBDD;
import com.krakedev.inventario.entidades.Categoria;

@Path("categorias")
public class ServicioCategoria {
    @Path("registrar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Categoria categoria) {
        CategoriaBDD categoriaBDD = new CategoriaBDD();
        try {
            categoriaBDD.insertar(categoria);
            return Response.ok("Categoria insertada: " + categoria.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
    @Path("actualizar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recibir(Categoria categoria) {
        CategoriaBDD categoriaBDD = new CategoriaBDD();
        try {
            categoriaBDD.actualizar(categoria);
            return Response.ok("Categoria actualizado: " + categoria.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
    @Path("buscar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarProveedor() {
        CategoriaBDD categoriaBDD = new CategoriaBDD();
        try {
            ArrayList<Categoria> categorias = categoriaBDD.buscar();
            return Response.ok(categorias).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
