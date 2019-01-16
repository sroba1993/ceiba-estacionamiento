package com.ceiba.estacionamiento.domain;

import com.ceiba.estacionamiento.model.Vehiculo;

public interface IEstacionamientoDomain {

	public void ingresarVehiculo(Vehiculo vehiculo);
	public Boolean contarVehiculosByTipo(String tipoVehiculo);
	//public Vehiculo generarFacturaVehiculo(Vehiculo vehiculo);
	//validarExisteVehiculo
}
