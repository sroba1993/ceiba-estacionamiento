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
	
	private static final String MOTO = "moto"; 
	private static final String CARRO = "carro";
	private static final int LUNES = Calendar.MONDAY; 
	private static final int DOMINGO = Calendar.SUNDAY; 
	private EstacionamientoRepositoryImpl nuevoRepositorio = new EstacionamientoRepositoryImpl();
	private Estacionamiento estacionamiento = new Estacionamiento();
	
	@Override
	public String ingresarVehiculo(Vehiculo vehiculo){ 
		Date fechaEntrada = new Date();
		vehiculo.setFechaEntrada(fechaEntrada); 
		if (validarIngresoVehiculosByA(vehiculo.getPlaca(),LUNES,DOMINGO)) {
			if(validarPuestosDisponibles(vehiculo.getTipoVehiculo())) {
				nuevoRepositorio.registrarVehiculoDB(vehiculo);
				return "Vehiculo registrado";
			}
			else {
				return "Parqueadero lleno";
			}
			
		}
		else {
			return "No es un día hábil para ese vehiculo";
		}
	}
	
	@Override
	public Boolean validarPuestosDisponibles(String tipoVehiculo) {
		Boolean validacion;
		List<Vehiculo> listaVehiculos = nuevoRepositorio.obtenerVehiculosDB();
		int contadorPuestosOcupados = 0;
		for (Vehiculo vehiculo : listaVehiculos) {
			if(vehiculo.getTipoVehiculo().equals(tipoVehiculo)  && vehiculo.getFechaSalida() == null) {
				contadorPuestosOcupados += 1;
			}		
		} 
		if (validarCantPuestosVehiculos(contadorPuestosOcupados, tipoVehiculo)) {
			validacion = true;
		}
		else {
			validacion = false;
		}
		return validacion;
	}
	 
	public Boolean validarCantPuestosVehiculos(int cantidadVehiculos, String tipoVehiculo) {
		Boolean validacion;
		if (cantidadVehiculos < estacionamiento.getCantEstacionamientoCarros() && tipoVehiculo.equals(CARRO)
				|| (cantidadVehiculos < estacionamiento.getCantEstacionamientoMotos() && tipoVehiculo.equals(MOTO))) {
			validacion = true;
		}
		else {
			validacion = false;
		}
		return validacion;
	}
	
	@Override
	public Boolean validarIngresoVehiculosByA(String placa , int diaRestringido, int diaRrestringidoDos) {
		Boolean validacion;
		Calendar fechaActual = Calendar.getInstance();
		int diaActual = fechaActual.get(Calendar.DAY_OF_WEEK);
		if(placa.startsWith("a") && (diaActual == diaRestringido || diaActual == diaRrestringidoDos)) {
			validacion = false;		
		}
		else {
			validacion = true;
		}
		return validacion;
	}

	@Override
	public List<VehiculoDTO> obtenerListaVehiculos(){
		List<Vehiculo> listaVehiculosFiltrados = new ArrayList<>();
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
		return nuevoRepositorio.obtenerVehiculoPorPlacaDB(placa);
	}
	
	@Override
	public Vehiculo registrarSalidaVehiculo(String placa) {
		List<Vehiculo> listaVehiculosActivos = nuevoRepositorio.obtenerVehiculosDB();
		for (Vehiculo vehiculo : listaVehiculosActivos) {
			if (vehiculo.getPlaca().equals(placa) && vehiculo.getFechaSalida() == null) {
				Date fechaSalida = new Date();
				vehiculo.setFechaSalida(fechaSalida);
				Vehiculo vehiculoParaSalir = new CalculoPrecioDomainImpl().calcularTiempoEstacionamiento(vehiculo);
				nuevoRepositorio.actualizarVehiculoDB(vehiculoParaSalir);
				return vehiculoParaSalir;
			}
		}	
		return null;
	} 
}
