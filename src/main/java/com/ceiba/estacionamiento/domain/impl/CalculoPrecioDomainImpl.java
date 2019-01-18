package com.ceiba.estacionamiento.domain.impl;

import java.util.Date;
import com.ceiba.estacionamiento.domain.ICalculoprecioDomain;
import com.ceiba.estacionamiento.model.Vehiculo;

public class CalculoPrecioDomainImpl implements ICalculoprecioDomain{
	
	static final int TARIFA_HORA_CARRO = 1_000;
	static final int TARIFA_DIA_CARRO = 8_000;
	static final int TARIFA_HORA_MOTO = 500;
	static final int TARIFA_DIA_MOTO = 4_000;
	static final int TARIFA_CC_EXCEDENTE = 2_000;
	static final int CC_EXCEDENTE = 500;
	private static final String MOTO = "moto";
	private static final String CARRO = "carro";
	int valorPagar; 
	
	@Override
	public Vehiculo calcularTiempoEstacionamiento(Vehiculo vehiculo) {
		Date fechaEntrada = vehiculo.getFechaEntrada();
		Date fechaSalida = vehiculo.getFechaSalida();
		long  diferenciatiempo = fechaSalida.getTime() - fechaEntrada.getTime();
		int minutos = (int) (diferenciatiempo/(60 * 1000) % 60); 
		int horas = (int) (diferenciatiempo/(60 * 60 * 1000) % 24); 
		int dias= (int) (diferenciatiempo/(24 * 60 * 60 * 1000));  
		if(horas > 9){
			dias += 1;
			horas = 0;
			if(minutos > 0) {
				horas = 1;
			}
		}
		else if(minutos > 0) {
			horas += 1;
		}

		if(vehiculo.getTipoVehiculo().equals(CARRO)) {
			int totalApagar = calcularPrecioCarro(horas, dias);
			vehiculo.setTotalPagar(totalApagar);
		}
		else if(vehiculo.getTipoVehiculo().equals(MOTO)) {
			int totalApagar = calcularPrecioMoto(horas, dias,vehiculo.getCilindraje());
			vehiculo.setTotalPagar(totalApagar);
		}
		return vehiculo;
	}

	@Override
	public int calcularPrecioCarro(int cantidadHoras, int cantidadDias) {
		if (cantidadDias > 0) {
			valorPagar = cantidadDias * TARIFA_DIA_CARRO;
			if (cantidadHoras > 0) {
				valorPagar += cantidadHoras * TARIFA_HORA_CARRO;
			}
		} 
		else {
			valorPagar = cantidadHoras * TARIFA_HORA_CARRO;
		}
		return valorPagar;
	}
	
	@Override
	public int calcularPrecioMoto(int cantidadHoras, int cantidadDias, int cilindraje) {
		if (cantidadDias > 0) {
			valorPagar = cantidadDias * TARIFA_DIA_MOTO;
			if (cantidadHoras > 0) {
				valorPagar += cantidadHoras * TARIFA_HORA_MOTO;
			}
		} 
		else {
			valorPagar = cantidadHoras * TARIFA_HORA_MOTO;
		}
		if (cilindraje > CC_EXCEDENTE) {
			valorPagar += TARIFA_CC_EXCEDENTE;
		}
		return valorPagar;
	}
}
