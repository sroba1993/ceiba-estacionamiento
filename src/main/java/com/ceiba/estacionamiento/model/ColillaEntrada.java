package com.ceiba.estacionamiento.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ColillaEntrada")
public class ColillaEntrada {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idColilla;
	private String placa;
	private Date fechaEntrada;
	
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
}
