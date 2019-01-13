package com.ceiba.estacionamiento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Estacionamiento")
public class Estacionamiento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEstacionamiento;
	private Vehiculo vehiculo;
	private int cantPuestosMotos = 10;
	private int cantPuestosCarros = 20;
	
	public Estacionamiento() {

	}	
	
	public Boolean validarCantPuestosMoto(int cantidadMotos) {
		if (cantidadMotos < cantPuestosMotos) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean validarCantPuestosCarro(int cantidadCarros) {
		if (cantidadCarros < cantPuestosCarros) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}
