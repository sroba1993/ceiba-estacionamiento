package com.ceiba.estacionamiento.domain.Impl;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.ceiba.estacionamiento.domain.impl.FacturacionDomainImpl;

public class FacturacionDomainImplTest {
	
	private FacturacionDomainImpl nuevoCobro = new FacturacionDomainImpl();
	
	@Test
	public void calcularPrecioParqueoCarroHoras() {
		int precioParqueoEsperado = nuevoCobro.calcularPrecioCarro(3,0);
		assertEquals(precioParqueoEsperado,3_000);
	}
	
	@Test
	public void calcularPrecioParqueoCarroDias() {
		int precioParqueoEsperado = nuevoCobro.calcularPrecioCarro(0,2);		
		assertEquals(precioParqueoEsperado,16_000);
	}
	
	@Test
	public void calcularPrecioParqueoCarroDiasHoras() {
		int precioParqueoEsperado = nuevoCobro.calcularPrecioCarro(5,3);		
		assertEquals(precioParqueoEsperado,29_000);
	}
	
	@Test
	public void calcularPrecioParqueoMotoHoras() {
		int precioParqueoEsperado = nuevoCobro.calcularPrecioMoto(5,0,125);		
		assertEquals(precioParqueoEsperado,2_500);
	}
	
	@Test
	public void calcularPrecioParqueoMotoDias() {
		int precioParqueoEsperado = nuevoCobro.calcularPrecioMoto(0,3,125);		
		assertEquals(precioParqueoEsperado,12_000);
	}
	
	@Test
	public void calcularPrecioParqueoMotoDiasHoras() {
		int precioParqueoEsperado = nuevoCobro.calcularPrecioMoto(6,1,125);		
		assertEquals(precioParqueoEsperado,7_000);
	}
	
	@Test
	public void calcularPrecioParqueoMotoCilindrajeAlto() {
		int precioParqueoEsperado = nuevoCobro.calcularPrecioMoto(3,1,650);		
		assertEquals(precioParqueoEsperado,7_500);
	}
	
}



