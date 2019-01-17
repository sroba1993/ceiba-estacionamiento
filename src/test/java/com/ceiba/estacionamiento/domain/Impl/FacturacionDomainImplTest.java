package com.ceiba.estacionamiento.domain.Impl;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.ceiba.estacionamiento.domain.impl.CalculoPrecioDomainImpl;

public class FacturacionDomainImplTest {
	
	private CalculoPrecioDomainImpl nuevoCobro = new CalculoPrecioDomainImpl();
	
	@Test
	public void calcularPrecioParqueoCarroHoras() {
		int precioCalculado = 3_000;
		int precioParqueoEsperado = nuevoCobro.calcularPrecioCarro(3,0);
		assertEquals(precioParqueoEsperado,precioCalculado);
	}
	
	@Test
	public void calcularPrecioParqueoCarroDias() {
		int precioCalculado = 16_000;
		int precioParqueoEsperado = nuevoCobro.calcularPrecioCarro(0,2);		
		assertEquals(precioParqueoEsperado,precioCalculado);
	}
	
	@Test
	public void calcularPrecioParqueoCarroDiasHoras() {
		int precioCalculado = 29_000;
		int precioParqueoEsperado = nuevoCobro.calcularPrecioCarro(5,3);		
		assertEquals(precioParqueoEsperado,precioCalculado);
	}
	
	@Test
	public void calcularPrecioParqueoMotoHoras() {
		int precioCalculado = 2_500;
		int precioParqueoEsperado = nuevoCobro.calcularPrecioMoto(5,0,125);		
		assertEquals(precioParqueoEsperado,precioCalculado);
	}
	
	@Test
	public void calcularPrecioParqueoMotoDias() {
		int precioCalculado = 12_000;
		int precioParqueoEsperado = nuevoCobro.calcularPrecioMoto(0,3,125);		
		assertEquals(precioParqueoEsperado,precioCalculado);
	}
	
	@Test
	public void calcularPrecioParqueoMotoDiasHoras() {
		int precioCalculado = 7_000;
		int precioParqueoEsperado = nuevoCobro.calcularPrecioMoto(6,1,125);
		assertEquals(precioParqueoEsperado,precioCalculado);
	}
	
	@Test
	public void calcularPrecioParqueoMotoCilindrajeAlto() {
		int precioCalculado = 7_500;
		int precioParqueoEsperado = nuevoCobro.calcularPrecioMoto(3,1,650);
		assertEquals(precioParqueoEsperado,precioCalculado);
	}
}



