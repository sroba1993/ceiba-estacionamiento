package com.ceiba.estacionamiento.domain.impl;

import java.util.Date;
import java.util.List;

import com.ceiba.estacionamiento.domain.IEstacionamientoDomain;
import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.repository.impl.EstacionamientoRepositoryImpl;

public class EstacionamientoDomainImpl implements IEstacionamientoDomain{
	
	@Override
	public void ingresarVehiculo(Vehiculo vehiculo){
		Date fechaEntrada = new Date();
		vehiculo.setFechaEntrada(fechaEntrada);
		
		EstacionamientoRepositoryImpl nuevoRepositorio = new EstacionamientoRepositoryImpl();
		nuevoRepositorio.registrarVehiculoDB(vehiculo);	
		//LOGICA PARA PODER INGRESAR UN VEHICULO
	}

	public static List<VehiculoDTO> obtenerListaVehiculos(){
		EstacionamientoRepositoryImpl nuevoRepositorio = new EstacionamientoRepositoryImpl();
		List<Vehiculo> listaVehiculos = nuevoRepositorio.obtenerVehiculosDB();
		return VehiculoDTO.vehiculoDTO(listaVehiculos);
	}
	
	public static List<Vehiculo> obtenerVehiculo(String placa){
		EstacionamientoRepositoryImpl nuevoRepositorio = new EstacionamientoRepositoryImpl();
		return nuevoRepositorio.obtenerVehiculoPorPlacaDB(placa);
	}
}
