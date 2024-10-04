package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.bdd.PedidosBDD;
import com.krakedev.inventario.entidades.Pedido;

@Path("pedidos")
public class ServicioPedido {
    @Path("registrar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Pedido pedido) {
        PedidosBDD pedidoBDD = new PedidosBDD();
        try {
            pedidoBDD.insertar(pedido);
            return Response.ok("Pedido insertado: " + pedido.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
    @Path("recibir")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recibir(Pedido pedido) {
        PedidosBDD pedidoBDD = new PedidosBDD();
        try {
            pedidoBDD.recibir(pedido);
            return Response.ok("Pedido actualizado: " + pedido.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
    @Path("buscar/{proveedor}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarProveedor(@PathParam("proveedor") String proveedor) {
        PedidosBDD pedidoBDD = new PedidosBDD();
        try {
            ArrayList<Pedido> pedidos = pedidoBDD.buscarPorProveedor(proveedor);
            return Response.ok(pedidos).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
