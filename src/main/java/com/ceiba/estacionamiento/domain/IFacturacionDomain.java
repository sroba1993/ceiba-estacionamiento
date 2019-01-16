package com.ceiba.estacionamiento.domain;

public interface IFacturacionDomain {
	
	int calcularPrecioCarro(int cantidadHoras, int cantidadDias);
	int calcularPrecioMoto(int cantidadHoras, int cantidadDias, int cilindraje);
}
