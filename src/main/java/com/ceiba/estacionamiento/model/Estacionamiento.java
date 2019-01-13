package com.ceiba.estacionamiento.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Estacionamiento")
public class Estacionamiento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEstacionamiento;
	private int cantEstacionamientoMotos = 10;
	private int cantEstacionamientoCarros = 20;
	
	@OneToMany(mappedBy = "estacionamiento")
	private Set <Vehiculo> vehiculoSet;
	
	public Estacionamiento() {

	}	
	
	public Estacionamiento(int cantEstacionamientoMotos, int cantEstacionamientoCarros) {
		this.cantEstacionamientoMotos = cantEstacionamientoMotos;
		this.cantEstacionamientoCarros = cantEstacionamientoCarros;
	}
	

	public int getCantEstacionamientoMotos() {
		return cantEstacionamientoMotos;
	}

	public void setCantEstacionamientoMotos(int cantEstacionamientoMotos) {
		this.cantEstacionamientoMotos = cantEstacionamientoMotos;
	}

	public int getCantEstacionamientoCarros() {
		return cantEstacionamientoCarros;
	}

	public void setCantEstacionamientoCarros(int cantEstacionamientoCarros) {
		this.cantEstacionamientoCarros = cantEstacionamientoCarros;
	}

	public Set<Vehiculo> getVehiculoSet() {
		return vehiculoSet;
	}

	public void setVehiculoSet(Set<Vehiculo> vehiculoSet) {
		this.vehiculoSet = vehiculoSet;
	}
	
	public Boolean validarCantPuestosMoto(int cantidadMotos) {
		if (cantidadMotos < cantEstacionamientoMotos) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean validarCantPuestosCarro(int cantidadCarros) {
		if (cantidadCarros < cantEstacionamientoCarros) {
			return true;
		}
		else {
			return false;
		}
	}
}
