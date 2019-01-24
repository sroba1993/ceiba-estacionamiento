package com.ceiba.estacionamiento.domain.impl.unitarias;

import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import com.ceiba.estacionamiento.domain.impl.CalculoPrecioDomainImpl;
import com.ceiba.estacionamiento.model.Vehiculo;

public class CalculoPrecioDomainImplTestUnitaria {
	
	final static String CARRO = "carro";
	final static String MOTO = "moto";
	private CalculoPrecioDomainImpl nuevoCobro = new CalculoPrecioDomainImpl();
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Vehiculo vehiculo = new Vehiculo(); 
	 
	@Test 
	public void validarCalculoTiempoEstacionamientoMotoHoras() throws ParseException {
		Date fechaEntrada = format.parse("2018-01-18 06:00:00"); 
		Date fechaSalida = format.parse("2018-01-18 12:40:00"); 
		correrPruebacalculoTiempoEstacionamiento(MOTO,fechaEntrada, fechaSalida,125, 3_500);
	}
	
	@Test 
	public void validarCalculoTiempoEstacionamientoMotoDias() throws ParseException {
		Date fechaEntrada = format.parse("2018-01-14 12:00:00"); 
		Date fechaSalida = format.parse("2018-01-18 12:00:00"); 
		correrPruebacalculoTiempoEstacionamiento(MOTO,fechaEntrada, fechaSalida,125, 16_000);
	}
	
	@Test 
	public void validarCalculoTiempoEstacionamientoMotoDiasHoras() throws ParseException {
		Date fechaEntrada = format.parse("2018-01-16 08:00:00"); 
		Date fechaSalida = format.parse("2018-01-18 12:00:00"); 
		correrPruebacalculoTiempoEstacionamiento(MOTO,fechaEntrada, fechaSalida,125, 10_000);
	}
	
	@Test 
	public void validarCalculoTiempoEstacionamientoMotoCilindraje() throws ParseException {
		Date fechaEntrada = format.parse("2018-01-16 08:00:00"); 
		Date fechaSalida = format.parse("2018-01-18 12:00:00"); 
		correrPruebacalculoTiempoEstacionamiento(MOTO,fechaEntrada, fechaSalida,650, 12_000);
	}

	@Test 
	public void validarCalculoTiempoEstacionamientoCarroDias() throws ParseException {
		Date fechaEntrada = format.parse("2018-01-16 01:00:00"); 
		Date fechaSalida = format.parse("2018-01-18 12:34:00"); 
		correrPruebacalculoTiempoEstacionamiento(CARRO,fechaEntrada, fechaSalida,0, 25_000);
	}
	
	@Test 
	public void validarCalculoTiempoEstacionamientoCarroHoras() throws ParseException {
		Date fechaEntrada = format.parse("2018-01-18 09:00:00"); 
		Date fechaSalida = format.parse("2018-01-18 09:40:00"); 
		correrPruebacalculoTiempoEstacionamiento(CARRO,fechaEntrada, fechaSalida,0, 1_000);
	}
	
	@Test 
	public void validarCalculoTiempoEstacionamientoCarroHorasDias() throws ParseException {
		Date fechaEntrada = format.parse("2018-01-05 09:00:00"); 
		Date fechaSalida = format.parse("2018-01-18 14:40:00"); 
		correrPruebacalculoTiempoEstacionamiento(CARRO,fechaEntrada, fechaSalida,0, 110_000);
	}
	
	public void correrPruebacalculoTiempoEstacionamiento(String tipoVehiculo, Date fechaEntrada, Date fechaSalida,int cilindraje, int cobroEsperado) {
		vehiculo.setTipoVehiculo(tipoVehiculo);
		vehiculo.setCilindraje(cilindraje);
		vehiculo.setFechaEntrada(fechaEntrada); 
		vehiculo.setFechaSalida(fechaSalida);
		Vehiculo vehiculoCalculado = nuevoCobro.calcularTiempoEstacionamiento(vehiculo);
		assertEquals(vehiculoCalculado.getTotalPagar(), cobroEsperado);
	}
	
	@Test
	public void calcularPrecioParqueoCarroHoras() {
		int precioParqueoEsperado = 3_000;
		int precioParqueoCalculado = nuevoCobro.calcularPrecioCarro(3,0);
		assertEquals(precioParqueoCalculado,precioParqueoEsperado);
	}
	
	@Test
	public void calcularPrecioParqueoCarroDias() { 
		int precioParqueoEsperado = 16_000;
		int precioParqueoCalculado = nuevoCobro.calcularPrecioCarro(0,2);		
		assertEquals(precioParqueoCalculado,precioParqueoEsperado);
	}
	
	@Test
	public void calcularPrecioParqueoCarroDiasHoras() {
		int precioParqueoEsperado = 29_000;
		int precioParqueoCalculado = nuevoCobro.calcularPrecioCarro(5,3);		
		assertEquals(precioParqueoCalculado,precioParqueoEsperado);
	}
	
	@Test
	public void calcularPrecioParqueoMotoHoras() {
		int precioParqueoEsperado = 2_500;
		int precioParqueoCalculado = nuevoCobro.calcularPrecioMoto(5,0,125);		
		assertEquals(precioParqueoCalculado,precioParqueoEsperado);
	}
	
	@Test
	public void calcularPrecioParqueoMotoDias() {
		int precioParqueoEsperado = 12_000;
		int precioParqueoCalculado = nuevoCobro.calcularPrecioMoto(0,3,125);		
		assertEquals(precioParqueoCalculado,precioParqueoEsperado);
	}
	
	@Test
	public void calcularPrecioParqueoMotoDiasHoras() {
		int precioParqueoEsperado = 7_000;
		int precioParqueoCalculado = nuevoCobro.calcularPrecioMoto(6,1,125);
		assertEquals(precioParqueoCalculado,precioParqueoEsperado);
	}
	
	@Test
	public void calcularPrecioParqueoMotoCilindrajeAlto() {
		int precioParqueoEsperado = 7_500;
		int precioParqueoCalculado = nuevoCobro.calcularPrecioMoto(3,1,650);
		assertEquals(precioParqueoCalculado,precioParqueoEsperado);
	}
}



