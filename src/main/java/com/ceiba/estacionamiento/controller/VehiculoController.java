package com.ceiba.estacionamiento.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class VehiculoController {

	@GET
	public Response sayHello() {
		return Response.ok("Hello World desde el API REST",MediaType.APPLICATION_JSON).build();
	}
}
