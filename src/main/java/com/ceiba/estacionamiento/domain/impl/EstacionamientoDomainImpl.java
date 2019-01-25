package com.ceiba.estacionamiento.domain.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ceiba.estacionamiento.controller.error.EstacionamientoExcepcion;
import com.ceiba.estacionamiento.domain.IEstacionamientoDomain;
import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Estacionamiento;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.repository.IEstacionamientoRepository;

@Service
public class EstacionamientoDomainImpl implements IEstacionamientoDomain {
	
	private static final String MOTO = "moto"; 
	private static final String CARRO = "carro";  
	private static final int LUNES = Calendar.MONDAY; 
	private static final int DOMINGO = Calendar.SUNDAY; 
	private Estacionamiento estacionamiento = new Estacionamiento();
	private ItinerarioDiasHabilesImpl validacionDiasHabiles = new ItinerarioDiasHabilesImpl();
	
	@Autowired
	private final IEstacionamientoRepository estacionamientoRepository;
	
	public EstacionamientoDomainImpl(IEstacionamientoRepository estacionamientoRepository) {
		this.estacionamientoRepository = estacionamientoRepository;
	}

	@Transactional
	public Vehiculo ingresarVehiculo(Vehiculo vehiculo){ 
		Date fechaEntrada = new Date();
		vehiculo.setFechaEntrada(fechaEntrada); 
		validarPlacaExistenteEstacionamiento(vehiculo.getPlaca());
		validacionDiasHabiles.validarIngresoVehiculosByA(vehiculo.getPlaca(),LUNES,DOMINGO);
		validarPuestosDisponibles(vehiculo.getTipoVehiculo());
		estacionamientoRepository.registrarVehiculoDB(vehiculo);
		List<Vehiculo> vehiculoDB = estacionamientoRepository.obtenerVehiculoPorPlacaDB(vehiculo.getPlaca());
		return vehiculoDB.get(0); 
	}  

	@Transactional
	public List<VehiculoDTO> obtenerListaVehiculos(){
		List<Vehiculo> listaVehiculosFiltrados = new ArrayList<>();
		List<Vehiculo> listaVehiculos = estacionamientoRepository.obtenerVehiculosDB();
		for (Vehiculo vehiculo : listaVehiculos) {
			if(vehiculo.getTotalPagar() == 0  && vehiculo.getFechaSalida() == null) {
				listaVehiculosFiltrados.add(vehiculo);
			}		
		}
		return VehiculoDTO.vehiculoDTO(listaVehiculosFiltrados);
	}
	 
	@Transactional
	public Vehiculo registrarSalidaVehiculo(String placa) {
		List<Vehiculo> listaVehiculosActivos = estacionamientoRepository.obtenerVehiculosDB();
		for (Vehiculo vehiculo : listaVehiculosActivos) {
			if (vehiculo.getPlaca().equals(placa) && vehiculo.getFechaSalida() == null) {
				Date fechaSalida = new Date();
				vehiculo.setFechaSalida(fechaSalida);
				Vehiculo vehiculoParaSalir = new CalculoPrecioDomainImpl().calcularTiempoEstacionamiento(vehiculo);
				estacionamientoRepository.actualizarVehiculoDB(vehiculoParaSalir);
				return vehiculoParaSalir;
			}
		} 	 
		throw new EstacionamientoExcepcion("Ese vehiculo No se encuentra en el parqueadero");
	}
	
	@Transactional
	public List<Vehiculo> obtenerVehiculoByPlaca(String placa){
		return estacionamientoRepository.obtenerVehiculoPorPlacaDB(placa);
	}
	
	public void validarPuestosDisponibles(String tipoVehiculo) {
		List<Vehiculo> listaVehiculos = estacionamientoRepository.obtenerVehiculosDB();
		int cantidadVehiculos = 0;
		for (Vehiculo vehiculo : listaVehiculos) {
			if(vehiculo.getTipoVehiculo().equals(tipoVehiculo)  && vehiculo.getFechaSalida() == null) {
				cantidadVehiculos += 1;
			}		
		} 
		if (!(cantidadVehiculos < estacionamiento.getCantEstacionamientoCarros() && tipoVehiculo.equals(CARRO)
				|| (cantidadVehiculos < estacionamiento.getCantEstacionamientoMotos() && tipoVehiculo.equals(MOTO)))) {
			throw new EstacionamientoExcepcion("Estacionamiento lleno");
		}
	}  
	
	public void validarPlacaExistenteEstacionamiento(String placa) {
		List<Vehiculo> listaVehiculosDB = estacionamientoRepository.obtenerVehiculosDB();
		for (Vehiculo vehiculoDB : listaVehiculosDB) {
			if(vehiculoDB.getPlaca().equals(placa) && vehiculoDB.getFechaSalida() == null) {
				throw new EstacionamientoExcepcion("Ese vehiculo ya aparece activo en el estacionamiento");
			}
		}
	}
}