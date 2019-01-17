package com.ceiba.estacionamiento.domain.Impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.constraints.AssertFalse;

import org.junit.Test;
import com.ceiba.estacionamiento.domain.impl.EstacionamientoDomainImpl;
import com.ceiba.estacionamiento.model.Vehiculo;

public class EstacionamientoDomainImplTest {
	
	private EstacionamientoDomainImpl estacionamientoDomain = new EstacionamientoDomainImpl();
	private Vehiculo vehiculo = new Vehiculo(); 
	final static String PLACA_MOTO = "lwq12t";
	final static String TIPO_VEHICULO_MOTO = "moto";
	final static String PLACA_CARRO = "tyo346";
	final static String TIPO_VEHICULO_CARRO = "carro";
	final static String PLACA_BY_A = "att987";
	
	@Test
	public void validarIngresoVehiculoRegistroVehiculoMoto() {
		vehiculo.setPlaca(PLACA_MOTO);
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_MOTO);
		String respuestaEsperada = estacionamientoDomain.ingresarVehiculo(vehiculo);
		assertTrue(respuestaEsperada.equals("Vehiculo registrado"));
		assertFalse(respuestaEsperada.equals("Parqueadero lleno"));
		assertFalse(respuestaEsperada.equals("No es un día hábil para ese vehiculo"));
	}
	
	@Test
	public void validarIngresoVehiculoRegistroVehiculoCarro() {
		vehiculo.setPlaca(PLACA_CARRO);
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
		String respuestaEsperada = estacionamientoDomain.ingresarVehiculo(vehiculo);
		assertTrue(respuestaEsperada.equals("Vehiculo registrado"));
		assertFalse(respuestaEsperada.equals("Parqueadero lleno"));
		assertFalse(respuestaEsperada.equals("No es un día hábil para ese vehiculo"));
	}

	@Test
	public void validarIngresoVehiculoDiaNoHabilplacasA() {
		vehiculo.setPlaca(PLACA_BY_A);
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
		String respuestaEsperada = estacionamientoDomain.ingresarVehiculo(vehiculo);
		assertTrue(respuestaEsperada.equals("Vehiculo registrado"));
		assertFalse(respuestaEsperada.equals("Parqueadero lleno"));
		assertFalse(respuestaEsperada.equals("No es un día hábil para ese vehiculo"));
	} 
	
	@Test
	public void validarPuestosDisponiblesMotos() {
		Boolean respuestEsperadaEstacionamientoMotos = estacionamientoDomain.validarPuestosDisponibles(TIPO_VEHICULO_MOTO);
		assertFalse(respuestEsperadaEstacionamientoMotos);
	}
	
	@Test
	public void validarPuestosDisponiblesCarros() {
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarPuestosDisponibles(TIPO_VEHICULO_CARRO);
		assertFalse(respuestEsperadaEstacionamientoCarros);
	}
	
	@Test
	public void validarCantPuestosMotos() {
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarCantPuestosVehiculos(5, TIPO_VEHICULO_MOTO);
		assertTrue(respuestEsperadaEstacionamientoCarros);
	}
	
	@Test
	public void validarCantPuestosCarros() {
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarCantPuestosVehiculos(15, TIPO_VEHICULO_CARRO);
		assertTrue(respuestEsperadaEstacionamientoCarros);
	}
	
	@Test
	public void validarIngresoMotosByA() {
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarIngresoVehiculosByA(PLACA_BY_A);
		assertTrue(respuestEsperadaEstacionamientoCarros);
	}
	
}
