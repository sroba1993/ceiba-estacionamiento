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
	private int cantPuestosMotos;
	private int cantPuestosCarros;
	private int tarifaHoraCarro;
	private int tarifaHoraMoto;
	private int tarifaDiaCarro;
	private int tarifaDiaMoto;
	private int tarifaExcedenteCc;
	
	public Estacionamiento() {

	}
	
	public Estacionamiento(int cantPuestosMotos, int cantPuestosCarros, int tarifaHoraCarro, int tarifaHoraMoto,
			int tarifaDiaCarro, int tarifaDiaMoto, int tarifaExcedenteCc) {
		
		this.cantPuestosMotos = cantPuestosMotos;
		this.cantPuestosCarros = cantPuestosCarros;
		this.tarifaHoraCarro = tarifaHoraCarro;
		this.tarifaHoraMoto = tarifaHoraMoto;
		this.tarifaDiaCarro = tarifaDiaCarro;
		this.tarifaDiaMoto = tarifaDiaMoto;
		this.tarifaExcedenteCc = tarifaExcedenteCc;
	}


	public boolean calcularPuestoLibreMoto() {
		return cantPuestosMotos > 10 ;
	}
	
	public boolean calcularPuestoLibreCarro() {
		return cantPuestosCarros > 20 ;
	}

	public int getCantParqueaderosMotos() {
		return cantPuestosMotos;
	}

	public void setCantParqueaderosMotos(int cantParqueaderosMotos) {
		this.cantPuestosMotos = cantParqueaderosMotos;
	}

	public int getCantParqueaderosCarros() {
		return cantPuestosCarros;
	}

	public void setCantParqueaderosCarros(int cantParqueaderosCarros) {
		this.cantPuestosCarros = cantParqueaderosCarros;
	}

	public int getTarifaHoraCarro() {
		return tarifaHoraCarro;
	}

	public void setTarifaHoraCarro(int tarifaHoraCarro) {
		this.tarifaHoraCarro = tarifaHoraCarro;
	}

	public int getTarifaHoraMoto() {
		return tarifaHoraMoto;
	}

	public void setTarifaHoraMoto(int tarifaHoraMoto) {
		this.tarifaHoraMoto = tarifaHoraMoto;
	}

	public int getTarifaDiaCarro() {
		return tarifaDiaCarro;
	}

	public void setTarifaDiaCarro(int tarifaDiaCarro) {
		this.tarifaDiaCarro = tarifaDiaCarro;
	}

	public int getTarifaDiaMoto() {
		return tarifaDiaMoto;
	}

	public void setTarifaDiaMoto(int tarifaDiaMoto) {
		this.tarifaDiaMoto = tarifaDiaMoto;
	}

	public int getExcedenteCcMoto() {
		return tarifaExcedenteCc;
	}

	public void setExcedenteCcMoto(int excedenteCcMoto) {
		this.tarifaExcedenteCc = excedenteCcMoto;
	}
	
}
