package com.ceiba.estacionamiento.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FacturaTest {
	
	private Factura factura = new Factura();
	
	@Test
	public void calcularPrecioParqueoCarroHoras() {
		int precioParqueoEsperado = factura.calcularPrecioCarro(3,0);		
		assertEquals(precioParqueoEsperado,3_000);
	}
	
	@Test
	public void calcularPrecioParqueoCarroDias() {
		int precioParqueoEsperado = factura.calcularPrecioCarro(0,2);		
		assertEquals(precioParqueoEsperado,16_000);
	}
	
	@Test
	public void calcularPrecioParqueoCarroDiasHoras() {
		int precioParqueoEsperado = factura.calcularPrecioCarro(5,3);		
		assertEquals(precioParqueoEsperado,29_000);
	}
	
	@Test
	public void calcularPrecioParqueoMotoHoras() {
		int precioParqueoEsperado = factura.calcularPrecioMoto(5,0,125);		
		assertEquals(precioParqueoEsperado,2_500);
	}
	
	@Test
	public void calcularPrecioParqueoMotoDias() {
		int precioParqueoEsperado = factura.calcularPrecioMoto(0,3,125);		
		assertEquals(precioParqueoEsperado,12_000);
	}
	
	@Test
	public void calcularPrecioParqueoMotoDiasHoras() {
		int precioParqueoEsperado = factura.calcularPrecioMoto(6,1,125);		
		assertEquals(precioParqueoEsperado,7_000);
	}
	
	@Test
	public void calcularPrecioParqueoMotoCilindrajeAlto() {
		int precioParqueoEsperado = factura.calcularPrecioMoto(3,1,650);		
		assertEquals(precioParqueoEsperado,7_500);
	}
	
}



