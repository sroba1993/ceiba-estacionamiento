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
}
