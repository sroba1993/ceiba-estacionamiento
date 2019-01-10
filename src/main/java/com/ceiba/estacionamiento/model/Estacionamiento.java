package com.ceiba.estacionamiento.model;

public class Estacionamiento {
	
	private int cantParqueaderosMotos;
	private int cantParqueaderosCarros;
	private int tarifaHoraCarro;
	private int tarifaHoraMoto;
	private int tarifaDiaCarro;
	private int tarifaDiaMoto;
	private int excedenteCcMoto;
	
	
	public Estacionamiento() {

	}
	
	public boolean calcularPuestoLibreMoto() {
		return cantParqueaderosMotos > 10 ;
	}
	
	public boolean calcularPuestoLibreCarro() {
		return cantParqueaderosCarros > 20 ;
	}

	public int getCantParqueaderosMotos() {
		return cantParqueaderosMotos;
	}

	public void setCantParqueaderosMotos(int cantParqueaderosMotos) {
		this.cantParqueaderosMotos = cantParqueaderosMotos;
	}

	public int getCantParqueaderosCarros() {
		return cantParqueaderosCarros;
	}

	public void setCantParqueaderosCarros(int cantParqueaderosCarros) {
		this.cantParqueaderosCarros = cantParqueaderosCarros;
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
		return excedenteCcMoto;
	}

	public void setExcedenteCcMoto(int excedenteCcMoto) {
		this.excedenteCcMoto = excedenteCcMoto;
	}
	
}
