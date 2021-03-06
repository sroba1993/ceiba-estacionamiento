package com.ceiba.estacionamiento.domain;

import java.util.List;
import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Vehiculo;

public interface IEstacionamientoDomain {
	
	Vehiculo ingresarVehiculo(Vehiculo vehiculo);
	List<VehiculoDTO> obtenerListaVehiculos();
	Vehiculo obtenerVehiculoByPlaca(String placa);
	Vehiculo registrarSalidaVehiculo(String placa);
	void validarPlacaExistenteEstacionamiento(String placa);
	void validarIngresoVehiculosByA(String placa , int primerDiaRestringido, int segundoDiaRestringido);
	void validarPuestosDisponibles(String tipoVehiculo);
}
