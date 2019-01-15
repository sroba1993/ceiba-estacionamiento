package com.ceiba.estacionamiento.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ceiba.estacionamiento.model.Vehiculo;

public class VehiculoDTO {

	private String placa;
	private String tipoVehiculo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEntrada;

	public VehiculoDTO() {

	}

	public VehiculoDTO(String placa, String tipoVehiculo, Date fechaEntrada) {
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
		this.fechaEntrada = fechaEntrada;
	}

	public static List<VehiculoDTO> vehiculoDTO(List<Vehiculo> vehiculos){
		List<VehiculoDTO> vehiculoDTOs = new ArrayList();
		
		for (Vehiculo vehiculo : vehiculos) {
			VehiculoDTO vehiculoDTO = new VehiculoDTO();
			vehiculoDTO.setPlaca(vehiculo.getPlaca());
			vehiculoDTO.setTipoVehiculo(vehiculo.getTipoVehiculo());
			vehiculoDTO.setFechaEntrada(vehiculo.getFechaEntrada());
			vehiculoDTOs.add(vehiculoDTO);
		}
			return vehiculoDTOs;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
}
