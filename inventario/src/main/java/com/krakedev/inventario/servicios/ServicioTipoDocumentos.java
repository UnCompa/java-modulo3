package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.bdd.TipoDocumentosBDD;
import com.krakedev.inventario.entidades.TipoDocumento;

@Path("tiposdocumento")
public class ServicioTipoDocumentos {
    @Path("recuperar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar() {
        TipoDocumentosBDD doc = new TipoDocumentosBDD();
        try {
            ArrayList<TipoDocumento> proveedores = doc.buscar();
            return Response.ok(proveedores).build();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
