package com.ceiba.estacionamiento.domain.impl;

import com.ceiba.estacionamiento.domain.IFacturacionDomain;

public class FacturacionDomainImpl implements IFacturacionDomain{
	
	static final int TARAIFA_HORA_CARRO = 1_000;
	static final int TARIFA_DIA_CARRO = 8_000;
	static final int TARIFA_HORA_MOTO = 500;
	static final int TARIFA_DIA_MOTO = 4_000;
	static final int TARIFA_CC_EXCEDENTE = 2_000;
	static final int CC_EXCEDENTE = 500;
	int totalPagar;
	
	@Override
	public int calcularPrecioCarro(int cantidadHoras, int cantidadDias) {
		if (cantidadDias > 0) {
			totalPagar = cantidadDias * TARIFA_DIA_CARRO;
			if (cantidadHoras > 0) {
				totalPagar += cantidadHoras * TARAIFA_HORA_CARRO;
			}
		} 
		else {
			totalPagar = cantidadHoras * TARAIFA_HORA_CARRO;
		}
		return totalPagar;
	}
	
	@Override
	public int calcularPrecioMoto(int cantidadHoras, int cantidadDias, int cilindraje) {
		if (cantidadDias > 0) {
			totalPagar = cantidadDias * TARIFA_DIA_MOTO;
			if (cantidadHoras > 0) {
				totalPagar += cantidadHoras * TARIFA_HORA_MOTO;
			}
		} 
		else {
			totalPagar = cantidadHoras * TARIFA_HORA_MOTO;
		}
		if (cilindraje > CC_EXCEDENTE) {
			totalPagar += TARIFA_CC_EXCEDENTE;
		}
		return totalPagar;
	}
}
