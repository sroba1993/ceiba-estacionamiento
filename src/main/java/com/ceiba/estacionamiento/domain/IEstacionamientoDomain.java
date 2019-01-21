package com.ceiba.estacionamiento.domain;

import java.util.List;

import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.util.MensajeRespuesta;

public interface IEstacionamientoDomain {
	
	MensajeRespuesta ingresarVehiculo(Vehiculo vehiculo);
	Boolean validarPuestosDisponibles(String tipoVehiculo);
	Boolean validarIngresoVehiculosByA(String placa , int diaRestringido, int diaRrestringidoDos);
	List<VehiculoDTO> obtenerListaVehiculos();
	List<Vehiculo> obtenerVehiculoByPlaca(String placa);
	Vehiculo registrarSalidaVehiculo(String placa);
}
