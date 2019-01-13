package com.ceiba.estacionamiento.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("vehiculos")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class VehiculoService {

	@GET
	public Response sayHello() {
		return Response.ok("Hello World desde el API REST",MediaType.APPLICATION_JSON).build();
	}
}
