package com.ceiba.estacionamiento.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	
	private EstacionamientoDomainImpl estacionamientoDomainImpl =new EstacionamientoDomainImpl();
	
	@GET
	public Response findAllVehicles() {	
		return Response.ok(estacionamientoDomainImpl.obtenerListaVehiculos()).build();	
	}
	
	@GET
	@Path("/{placa}")
	public Response findVehicle(@PathParam("placa") String placa) {
		return Response.ok(estacionamientoDomainImpl.obtenerVehiculoByPlaca(placa)).build();
	}
	
	@POST
	public String addVehicle(Vehiculo vehiculo) {
		return estacionamientoDomainImpl.ingresarVehiculo(vehiculo);
	}	
	
	@PUT
	@Path("{placa}")
	public Response updateVehicle(@PathParam("placa") String placa) {
		Vehiculo vehiculo = estacionamientoDomainImpl.registrarSalidaVehiculo(placa);
		if (vehiculo.getPlaca().equals(placa)) {
			return Response.ok(vehiculo).build();
		}
		return Response.noContent().build();
	}
}
