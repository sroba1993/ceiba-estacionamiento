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
	private Date duracion; 
	private int tarifaHoraMoto = 500;
	private int tarifaDiaMoto = 4_000;
	private int tarifaExcedenteCc = 2_000;
	private int CcExcedente = 500;
	private int tarifaHoraCarro = 1_000;
	private int tarifaDiaCarro = 8_000;
	

	public Factura() {

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

	public Date getDuracion() {
		return duracion;
	}

	public void setDuracion(Date duracion) {
		this.duracion = duracion;
	}
	
	public int calcularPrecioCarro(int cantidadHoras, int cantidadDias) {
		int precioTotal;
		if (cantidadDias > 0) {
			precioTotal = cantidadDias * tarifaDiaCarro;
			if (cantidadHoras > 0) {
				precioTotal += cantidadHoras * tarifaHoraCarro;
			}
		} 
		else {
			precioTotal = cantidadHoras * tarifaHoraCarro;
		}
		return precioTotal;
	}
	
	public int calcularPrecioMoto(int cantidadHoras, int cantidadDias, int cilindraje) {
		int precioTotal;
		if (cantidadDias > 0) {
			precioTotal = cantidadDias * tarifaDiaMoto;
			if (cantidadHoras > 0) {
				precioTotal += cantidadHoras * tarifaHoraMoto;
			}
		} 
		else {
			precioTotal = cantidadHoras * tarifaHoraMoto;
		}
		if (cilindraje > CcExcedente) {
			precioTotal += tarifaExcedenteCc;
		}
		return precioTotal;
	}
	
}
