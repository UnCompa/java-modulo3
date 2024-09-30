package com.krakedev.inventario.servicios;

import java.util.ArrayList;

//import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.bdd.ProductosBDD;
import com.krakedev.inventario.entidades.Producto;

@Path("productos")
public class ServicioProductos {

	@Path("buscar/{subcadena}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("subcadena") String subcadena) {
		ProductosBDD pro = new ProductosBDD();
		try {
			ArrayList<Producto> productos = pro.buscar(subcadena);
			return Response.ok(productos).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

}
