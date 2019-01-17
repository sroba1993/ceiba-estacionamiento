package com.ceiba.estacionamiento.domain;

import com.ceiba.estacionamiento.model.Vehiculo;

public interface ICalculoprecioDomain {
	
	Vehiculo calcularTiempoEstacionamiento(Vehiculo vehiculo);
	int calcularPrecioCarro(int cantidadHoras, int cantidadDias);
	int calcularPrecioMoto(int cantidadHoras, int cantidadDias, int cilindraje);
}
