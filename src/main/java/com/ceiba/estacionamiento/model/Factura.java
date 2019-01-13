package com.ceiba.estacionamiento.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Factura")
public class Factura {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idFactura;
	private String placa;
	private Date fechaEntrada;
	private Date fechaSalida;
	private String duracion; 
	private int totalPagar;
	

	public Factura() {

	}
	
	
	public Factura(String placa, Date fechaEntrada, Date fechaSalida, String duracion) {
		this.placa = placa;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.duracion = duracion;
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

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	
	public int calcularPrecioCarro(int cantidadHoras, int cantidadDias) {
		
		int tarifaHoraCarro = 1_000;
		int tarifaDiaCarro = 8_000;
		
		if (cantidadDias > 0) {
			totalPagar = cantidadDias * tarifaDiaCarro;
			if (cantidadHoras > 0) {
				totalPagar += cantidadHoras * tarifaHoraCarro;
			}
		} 
		else {
			totalPagar = cantidadHoras * tarifaHoraCarro;
		}
		return totalPagar;
	}
	
	public int calcularPrecioMoto(int cantidadHoras, int cantidadDias, int cilindraje) {
		
		int tarifaHoraMoto = 500;
		int tarifaDiaMoto = 4_000;
		int tarifaExcedenteCc = 2_000;
		int CcExcedente = 500;
		
		if (cantidadDias > 0) {
			totalPagar = cantidadDias * tarifaDiaMoto;
			if (cantidadHoras > 0) {
				totalPagar += cantidadHoras * tarifaHoraMoto;
			}
		} 
		else {
			totalPagar = cantidadHoras * tarifaHoraMoto;
		}
		if (cilindraje > CcExcedente) {
			totalPagar += tarifaExcedenteCc;
		}
		return totalPagar;
	}
	
}
