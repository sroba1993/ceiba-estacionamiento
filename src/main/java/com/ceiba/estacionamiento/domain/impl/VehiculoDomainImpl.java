package com.ceiba.estacionamiento.domain.impl;

import java.util.Date;
import java.util.List;

import com.ceiba.estacionamiento.domain.IVehiculoDomain;
import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.repository.VehiculoRepository;

public class VehiculoDomainImpl implements IVehiculoDomain{
	
	@Override
	public void ingresarVehiculo(Vehiculo vehiculo){
		Date date = new Date();
		vehiculo.setFechaEntrada(date);
		VehiculoRepository nuevoRepositorio = new VehiculoRepository();
		nuevoRepositorio.crearVehiculoDB(vehiculo);	
		//LOGICA PARA PODER INGRESAR UN VEHICULO
	}

	public static List<VehiculoDTO> obtenerListaVehiculos(){
		VehiculoRepository nuevoRepositorio = new VehiculoRepository();
		List<Vehiculo> listaVehiculos = nuevoRepositorio.obtenerVehiculosDB();
		return VehiculoDTO.vehiculoDTO(listaVehiculos);
	}
	
	public static List<Vehiculo> obtenerVehiculo(String placa){
		VehiculoRepository nuevoRepositorio = new VehiculoRepository();
		return nuevoRepositorio.obtenerVehiculoPorPlaca(placa);
	}
}
