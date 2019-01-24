package com.ceiba.estacionamiento.domain.impl;

import java.util.Calendar;

import com.ceiba.estacionamiento.controller.error.EstacionamientoExcepcion;

public class ItinerarioDiasHabilesImpl {

	public void validarIngresoVehiculosByA(String placa , int diaRestringido, int diaRrestringidoDos) {
		Calendar fechaActual = Calendar.getInstance();
		int diaActual = fechaActual.get(Calendar.DAY_OF_WEEK);
		if(placa.startsWith("a") && (diaActual == diaRestringido || diaActual == diaRrestringidoDos)) {
			throw new EstacionamientoExcepcion("No es un dia habil para este vehiculo");
		}
	}
}
