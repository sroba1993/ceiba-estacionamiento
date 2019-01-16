package com.ceiba.estacionamiento.domain.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ceiba.estacionamiento.domain.IEstacionamientoDomain;
import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Estacionamiento;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.repository.impl.EstacionamientoRepositoryImpl;

public class EstacionamientoDomainImpl implements IEstacionamientoDomain{
	
	private Estacionamiento estacionamiento = new Estacionamiento();
	private static final String MOTO = "moto";
	private static final String CARRO = "carro";
	
	@Override
	public Boolean ingresarVehiculo(Vehiculo vehiculo){
		Date fechaEntrada = new Date();
		vehiculo.setFechaEntrada(fechaEntrada);
		if (validarPuestosDisponibles(vehiculo.getTipoVehiculo()) && validarIngresoVehiculosByA(vehiculo.getPlaca())) {
			EstacionamientoRepositoryImpl nuevoRepositorio = new EstacionamientoRepositoryImpl();
			nuevoRepositorio.registrarVehiculoDB(vehiculo);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public Boolean validarPuestosDisponibles(String tipoVehiculo) {
		EstacionamientoRepositoryImpl nuevoRepositorio = new EstacionamientoRepositoryImpl();
		List<Vehiculo> listaVehiculos = nuevoRepositorio.obtenerVehiculosDB();
		int contadorPuestosOcupados = 0;
		for (Vehiculo vehiculo : listaVehiculos) {
			if(vehiculo.getTipoVehiculo().equals(tipoVehiculo) && vehiculo.getTotalPagar() == 0  && vehiculo.getFechaSalida() == null) {
				contadorPuestosOcupados += 1;
			}		
		}
		if (validarCantPuestosVehiculos(contadorPuestosOcupados, tipoVehiculo)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean validarCantPuestosVehiculos(int cantidadVehiculos, String tipoVehiculo) {
		if (cantidadVehiculos < estacionamiento.getCantEstacionamientoCarros() && tipoVehiculo.equals(CARRO)) {
			return true;
		}
		else if (cantidadVehiculos < estacionamiento.getCantEstacionamientoMotos() && tipoVehiculo.equals(MOTO)) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public Boolean validarIngresoVehiculosByA(String placa) {
		Calendar calendar = Calendar.getInstance();
		int fechaActual = calendar.DAY_OF_WEEK;
		
		if(placa.substring(0,1).equals("a") && fechaActual == Calendar.MONDAY) {
			return false;		
		}
		else {
			return true;
		}	
	}

	@Override
	public List<VehiculoDTO> obtenerListaVehiculos(){
		List<Vehiculo> listaVehiculosFiltrados = new ArrayList<>();
		EstacionamientoRepositoryImpl nuevoRepositorio = new EstacionamientoRepositoryImpl();
		List<Vehiculo> listaVehiculos = nuevoRepositorio.obtenerVehiculosDB();
		for (Vehiculo vehiculo : listaVehiculos) {
			if(vehiculo.getTotalPagar() == 0  && vehiculo.getFechaSalida() == null) {
				listaVehiculosFiltrados.add(vehiculo);
			}		
		}
		return VehiculoDTO.vehiculoDTO(listaVehiculosFiltrados);
	}
	
	@Override
	public List<Vehiculo> obtenerVehiculoByPlaca(String placa){
		EstacionamientoRepositoryImpl nuevoRepositorio = new EstacionamientoRepositoryImpl();
		return nuevoRepositorio.obtenerVehiculoPorPlacaDB(placa);
	}
}
