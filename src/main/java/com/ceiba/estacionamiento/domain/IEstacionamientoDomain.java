package com.ceiba.estacionamiento.domain;

import java.util.List;
import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Vehiculo;

public interface IEstacionamientoDomain {
	
	String ingresarVehiculo(Vehiculo vehiculo);
	Boolean validarPuestosDisponibles(String tipoVehiculo);
	Boolean validarIngresoVehiculosByA(String placa);
	List<VehiculoDTO> obtenerListaVehiculos();
	List<Vehiculo> obtenerVehiculoByPlaca(String placa);
}
