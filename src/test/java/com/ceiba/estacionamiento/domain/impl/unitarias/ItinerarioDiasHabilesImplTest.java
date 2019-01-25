package com.ceiba.estacionamiento.domain.impl.unitarias;

import static org.junit.Assert.assertTrue;
import java.util.Calendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import com.ceiba.estacionamiento.domain.impl.ItinerarioDiasHabilesImpl;


@RunWith(MockitoJUnitRunner.class)
public class ItinerarioDiasHabilesImplTest {
	
	final static String PLACA_BY_A = "att987";
	
	@Autowired
	private ItinerarioDiasHabilesImpl validacion = new ItinerarioDiasHabilesImpl();
	
	
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
