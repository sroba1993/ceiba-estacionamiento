package com.ceiba.estacionamiento.domain.impl.unitarias;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ceiba.estacionamiento.domain.impl.ItinerarioDiasHabilesImpl;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.repository.impl.EstacionamientoRepositoryImpl;

@RunWith(MockitoJUnitRunner.class)
public class ItinerarioDiasHabilesImplTest {
	
	final static String PLACA_MOTO = "lwq12t";
	final static String TIPO_VEHICULO_MOTO = "moto";
	final static String PLACA_CARRO = "tyo346";
	final static String TIPO_VEHICULO_CARRO = "carro";
	final static String PLACA_BY_A = "att987";
	private Vehiculo vehiculo = new Vehiculo(); 
	
	@InjectMocks
	private ItinerarioDiasHabilesImpl validacion = new ItinerarioDiasHabilesImpl();
	
	@Mock
	private EstacionamientoRepositoryImpl nuevoRepositorio;
	
	@Test
	public void validarIngresoVehiculosByA() {
		Calendar fechaActual = Calendar.getInstance();
		int diaActual = fechaActual.get(Calendar.DAY_OF_WEEK);
		try {
			validacion.validarIngresoVehiculosByA(PLACA_BY_A,diaActual,diaActual);
		} catch (RuntimeException e) {	
			assertTrue(e.getMessage().equals("No es un dia habil para este vehiculo"));
		}	
	} 
}
