package com.ceiba.estacionamiento.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Vehiculo")
public class Vehiculo {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVehiculo;
	private String placa;
	private String tipoVehiculo;
	private int cilindraje;
	private Date fechaEntrada;
	private Date fechaSalida;
	private int totalPagar;
	
	@ManyToOne
	@JoinColumn(name = "idEstacionamiento")
	private Estacionamiento estacionamiento;

	public Vehiculo() {

	}

	

	public Vehiculo(String placa, String tipoVehiculo, int cilindraje, Date fechaEntrada) {
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = cilindraje;
		this.fechaEntrada = fechaEntrada;
	}

	public Vehiculo(String placa, String tipoVehiculo, int cilindraje, Date fechaEntrada, Date fechaSalida,
			int totalPagar) {
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = cilindraje;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.totalPagar = totalPagar;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public Estacionamiento getEstacionamiento() {
		return estacionamiento;
	}

	public void setEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamiento = estacionamiento;
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

	public int getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(int totalPagar) {
		this.totalPagar = totalPagar;
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
	
	@Override
	public String toString() {
		return "Vehiculo [idVehiculo=" + idVehiculo + ", placa=" + placa + ", tipoVehiculo=" + tipoVehiculo
				+ ", cilindraje=" + cilindraje + ", estacionamiento=" + estacionamiento + "]";
	}
	
}
