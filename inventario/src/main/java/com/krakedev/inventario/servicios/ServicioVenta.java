package com.krakedev.inventario.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.krakedev.inventario.bdd.VentasBDD;
import com.krakedev.inventario.entidades.Venta;

@Path("ventas")
public class ServicioVenta {
@Path("guardar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Venta venta) {
        VentasBDD ventasBDD = new VentasBDD();
        try {
            ventasBDD.guardar(venta);
            return Response.ok("Venta insertado: " + venta.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
