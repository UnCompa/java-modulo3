package com.krakedev.inventario.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
            return Response.ok("Proveedor insertado: " + pedido.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
