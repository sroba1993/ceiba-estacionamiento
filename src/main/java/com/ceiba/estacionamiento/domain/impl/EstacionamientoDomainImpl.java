package com.ceiba.estacionamiento.domain.impl;

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
	
	@Autowired
	private final IEstacionamientoRepository estacionamientoRepository;
	
	public EstacionamientoDomainImpl(IEstacionamientoRepository estacionamientoRepository) {
		this.estacionamientoRepository = estacionamientoRepository;
	} 
	
	@Transactional
	public Vehiculo ingresarVehiculo(Vehiculo vehiculo){ 
		Date fechaEntrada = new Date();
		vehiculo.setFechaEntrada(fechaEntrada); 
		validarDatosIngresadosVehiculos(vehiculo);
		validarPlacaExistenteEstacionamiento(vehiculo.getPlaca());
		validarIngresoVehiculosByA(vehiculo.getPlaca(),LUNES,DOMINGO);
		validarPuestosDisponibles(vehiculo.getTipoVehiculo());
		estacionamientoRepository.save(vehiculo);
		return estacionamientoRepository.findVehicleByPlaca(vehiculo.getPlaca());
	}  
	
	@Transactional
	public List<VehiculoDTO> obtenerListaVehiculos(){
		List<Vehiculo> listaVehiculos = estacionamientoRepository.findListVehicles();
		return VehiculoDTO.vehiculoDTO(listaVehiculos);
	}
	
	@Transactional
	public Vehiculo registrarSalidaVehiculo(String placa) {
		Vehiculo vehiculo = estacionamientoRepository.findVehicleByPlaca(placa);
		if(vehiculo == null) {
			throw new EstacionamientoExcepcion("Ese vehiculo No se encuentra en el parqueadero");
		}
		Date fechaSalida = new Date();
		vehiculo.setFechaSalida(fechaSalida);
		Vehiculo vehiculoParaSalir = new CalculoPrecioDomainImpl().calcularTiempoEstacionamiento(vehiculo);
		estacionamientoRepository.save(vehiculoParaSalir);
		return vehiculoParaSalir;			
	}
	
	@Transactional
	public Vehiculo obtenerVehiculoByPlaca(String placa){
		return estacionamientoRepository.findVehicleByPlaca(placa);
	}
	
	@Transactional
	public void validarPlacaExistenteEstacionamiento(String placa) {
		List<Vehiculo> listaVehiculosDB = estacionamientoRepository.findListVehicles();
		for (Vehiculo vehiculoDB : listaVehiculosDB) {
			if(vehiculoDB.getPlaca().equals(placa)) {
				throw new EstacionamientoExcepcion("Ese vehiculo ya aparece activo en el estacionamiento");
			}
		}
	}
	
	@Transactional
	public void validarIngresoVehiculosByA(String placa , int primerDiaRestringido, int segundoDiaRestringido) {
		Calendar fechaActual = Calendar.getInstance();
		int diaActual = fechaActual.get(Calendar.DAY_OF_WEEK);
		if(placa.startsWith("a") && (diaActual == primerDiaRestringido || diaActual == segundoDiaRestringido)) {
			throw new EstacionamientoExcepcion("No es un dia habil para este vehiculo");
		}
	} 
	
	@Transactional
	public void validarPuestosDisponibles(String tipoVehiculo) {
		List<Vehiculo> listaVehiculos = estacionamientoRepository.findListVehicles();
		int cantidadVehiculos = 0;
		for (Vehiculo vehiculo : listaVehiculos) {
			if(vehiculo.getTipoVehiculo().equals(tipoVehiculo)) {
				cantidadVehiculos += 1;
			}		
		} 
		if (!((cantidadVehiculos < estacionamiento.getCantEstacionamientoCarros() && tipoVehiculo.equals(CARRO))
				|| (cantidadVehiculos < estacionamiento.getCantEstacionamientoMotos() && tipoVehiculo.equals(MOTO)))) {
			throw new EstacionamientoExcepcion("Estacionamiento lleno");
		}
	}   
	 
	@Transactional
	public void validarDatosIngresadosVehiculos(Vehiculo vehiculo) {
		if(vehiculo.getTipoVehiculo().equals("")) {
			throw new EstacionamientoExcepcion("Ingrese un tipo de vehiculo válido");
		}	
		else if(vehiculo.getCilindraje() == 0) {
			throw new EstacionamientoExcepcion("Ingrese el cilindraje del vehiculo");
		}
	} 
} 