package com.ceiba.estacionamiento.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.ceiba.estacionamiento.domain.IEstacionamientoDomain;
import com.ceiba.estacionamiento.model.Vehiculo;

@Controller
@Path(value = "/estacionamiento-ceiba")
@Consumes(value= "application/json")
@Produces(value = "application/json")
public class EstacionamientoController {
	
	@Autowired 
	private IEstacionamientoDomain estacionamientoDomain;
	 
	@GET
	public Response findAllVehicles() {	
		return Response.ok(estacionamientoDomain.obtenerListaVehiculos()).build();	
	}

	@GET
	@Path("/{placa}")
	public Response findVehicle(@PathParam("placa") String placa) {
		return Response.ok(estacionamientoDomain.obtenerVehiculoByPlaca(placa)).build();
	}
	
	@POST
	public Response addVehicle(Vehiculo vehiculo) {
		return Response.ok(estacionamientoDomain.ingresarVehiculo(vehiculo)).build();
	}	
	
	@PUT
	@Path("/{placa}")
	public Response updateVehicle(@PathParam("placa") String placa){
		return Response.ok(estacionamientoDomain.registrarSalidaVehiculo(placa)).build();	
	}
} 
