package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.bdd.ProveedoresBDD;
import com.krakedev.inventario.entidades.Proveedor;

@Path("proveedores")
public class ServicioProveedores {

	@Path("buscar/{subcadena}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("subcadena") String subcadena) {
		ProveedoresBDD pro = new ProveedoresBDD();
		try {
			ArrayList<Proveedor> proveedores = pro.buscar(subcadena);
			return Response.ok(proveedores).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Proveedor proveedor) {
		System.out.println(proveedor);
		ProveedoresBDD pro = new ProveedoresBDD();
		try {
			pro.insertar(proveedor);
			return Response.ok("Proveedor insertado: " + proveedor.toString()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
