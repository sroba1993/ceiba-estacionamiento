package com.ceiba.estacionamiento.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.repository.VehiculoRepository;

@Path("vehiculos")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class VehiculoService {
	
	@GET
	public Response findAllVehicles() {
		return Response.ok(VehiculoRepository.obtenerListaVehiculos()).build();	
	}
	
	@GET
	@Path("/{placa}")
	public Response findVehicle(@PathParam("placa") String placa) {
		return Response.ok(VehiculoRepository.obtenerVehiculoPorPlaca(placa)).build();
	}
	
	@POST
	public Response addVehicle(Vehiculo vehiculo) {
		VehiculoRepository nuevoVehiculoRepository = new VehiculoRepository();
		nuevoVehiculoRepository.crearVehiculo(vehiculo);
		VehiculoDTO vehiculoDTO = new VehiculoDTO();
		vehiculoDTO.setPlaca(vehiculo.getPlaca());
		vehiculoDTO.setTipoVehiculo(vehiculo.getTipoVehiculo());
		vehiculoDTO.setFechaEntrada(vehiculo.getFechaEntrada());
		return Response.status(Response.Status.CREATED).entity(vehiculoDTO).build();
	}	
	
	@DELETE
	@Path("/{placa}")
	public Response deleteVehicle(@PathParam("placa") String placa) {
		VehiculoRepository vehiculoRepository = new VehiculoRepository();
		List<VehiculoDTO> listadoVehiculos = vehiculoRepository.obtenerListaVehiculos();
		for (VehiculoDTO vehiculoDTO : listadoVehiculos) {
			if (vehiculoDTO.getPlaca().equals(placa)) {
				VehiculoRepository.borrarVehiculo(placa);
				return Response.noContent().build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();		
	}
}
