package com.ceiba.estacionamiento.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VehiculoTest {
	
	final int PRECIO = 3_000;
	private Vehiculo vehiculo = new Vehiculo();
	
	@Test
	public void calcularPrecioParqueoCarroHoras() {
		int precioParqueoEsperado = vehiculo.calcularPrecioCarro(3,0);
		assertEquals(precioParqueoEsperado,PRECIO);
	}
	
	@Test
	public void calcularPrecioParqueoCarroDias() {
		int precioParqueoEsperado = vehiculo.calcularPrecioCarro(0,2);		
		assertEquals(precioParqueoEsperado,16_000);
	}
	
	@Test
	public void calcularPrecioParqueoCarroDiasHoras() {
		int precioParqueoEsperado = vehiculo.calcularPrecioCarro(5,3);		
		assertEquals(precioParqueoEsperado,29_000);
	}
	
	@Test
	public void calcularPrecioParqueoMotoHoras() {
		int precioParqueoEsperado = vehiculo.calcularPrecioMoto(5,0,125);		
		assertEquals(precioParqueoEsperado,2_500);
	}
	
	@Test
	public void calcularPrecioParqueoMotoDias() {
		int precioParqueoEsperado = vehiculo.calcularPrecioMoto(0,3,125);		
		assertEquals(precioParqueoEsperado,12_000);
	}
	
	@Test
	public void calcularPrecioParqueoMotoDiasHoras() {
		int precioParqueoEsperado = vehiculo.calcularPrecioMoto(6,1,125);		
		assertEquals(precioParqueoEsperado,7_000);
	}
	
	@Test
	public void calcularPrecioParqueoMotoCilindrajeAlto() {
		int precioParqueoEsperado = vehiculo.calcularPrecioMoto(3,1,650);		
		assertEquals(precioParqueoEsperado,7_500);
	}
	
}



