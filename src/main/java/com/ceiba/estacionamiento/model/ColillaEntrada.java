package com.ceiba.estacionamiento.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ColillaEntrada")
public class ColillaEntrada {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idColilla;
	private String placa;
	private Date fechaEntrada;
	
	@OneToOne
	@JoinColumn(name = "idVehiculo")
	private Vehiculo vehiculo;
	
	public ColillaEntrada() {

	}

	public ColillaEntrada(String placa, Date fechaEntrada) {
		this.placa = placa;
		this.fechaEntrada = fechaEntrada;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
}
