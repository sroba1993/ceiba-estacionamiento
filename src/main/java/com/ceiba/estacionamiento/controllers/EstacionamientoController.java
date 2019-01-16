package com.ceiba.estacionamiento.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ceiba.estacionamiento.domain.impl.EstacionamientoDomainImpl;
import com.ceiba.estacionamiento.model.Vehiculo;


@Path("vehiculos")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class EstacionamientoController {
	
	@GET
	public Response findAllVehicles() {
		return Response.ok(EstacionamientoDomainImpl.obtenerListaVehiculos()).build();	
	}
	
	@GET
	@Path("/{placa}")
	public Response findVehicle(@PathParam("placa") String placa) {
		return Response.ok(EstacionamientoDomainImpl.obtenerVehiculo(placa)).build();
	}
	
	@POST
	public Response addVehicle(Vehiculo vehiculo) {
		EstacionamientoDomainImpl nuevoIngresoVehiculo = new EstacionamientoDomainImpl();
		nuevoIngresoVehiculo.ingresarVehiculo(vehiculo);
		return Response.status(Response.Status.CREATED).build();
	}	
}
