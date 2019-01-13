package com.ceiba.estacionamiento.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class EstacionamientoTest {
	
	private Estacionamiento estacionamiento = new Estacionamiento();

	@Test
	public void calcularCantidadPuestoMotoMinima() {
		Boolean puestoEstacionamientoMoto = estacionamiento.validarCantPuestosMoto(0);		
		assertEquals(puestoEstacionamientoMoto,true);
	}
	
	@Test
	public void calcularCantidadPuestoMotoMaxima() {
		Boolean puestoEstacionamientoMoto = estacionamiento.validarCantPuestosMoto(10);		
		assertEquals(puestoEstacionamientoMoto,false);
	}
	
	@Test
	public void calcularCantidadPuestoCarroMinima() {
		Boolean puestoEstacionamientoCarro = estacionamiento.validarCantPuestosCarro(0);		
		assertEquals(puestoEstacionamientoCarro,true);
	}
	
	@Test
	public void calcularCantidadPuestoCarroMaxima() {
		Boolean puestoEstacionamientoCarro = estacionamiento.validarCantPuestosCarro(20);		
		assertEquals(puestoEstacionamientoCarro,false);
	}

}
