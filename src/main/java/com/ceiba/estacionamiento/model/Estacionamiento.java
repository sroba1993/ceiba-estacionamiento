package com.ceiba.estacionamiento.model;

public class Estacionamiento {
	
	private int cantPuestosMotos;
	private int cantPuestosCarros;
	private int tarifaHoraCarro;
	private int tarifaHoraMoto;
	private int tarifaDiaCarro;
	private int tarifaDiaMoto;
	private int tarifaExcedenteCc;
	
	
	public Estacionamiento() {

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
