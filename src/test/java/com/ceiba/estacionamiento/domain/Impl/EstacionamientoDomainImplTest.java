package com.ceiba.estacionamiento.domain.Impl;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import com.ceiba.estacionamiento.domain.impl.EstacionamientoDomainImpl;
import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.repository.impl.EstacionamientoRepositoryImpl;

public class EstacionamientoDomainImplTest {
	
	final static String PLACA_MOTO = "lwq12t";
	final static String TIPO_VEHICULO_MOTO = "moto";
	final static String PLACA_CARRO = "tyo346";
	final static String TIPO_VEHICULO_CARRO = "carro";
	final static String PLACA_BY_A = "att987";
	private EstacionamientoDomainImpl estacionamientoDomain = new EstacionamientoDomainImpl();
	private EstacionamientoRepositoryImpl nuevoRepositorio = new EstacionamientoRepositoryImpl();
	private Vehiculo vehiculo = new Vehiculo(); 
	
	@Test
	public void validarIngresoVehiculoRegistroVehiculoMoto() {
		vehiculo.setPlaca(PLACA_MOTO);
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_MOTO);
		String respuestaEsperada = estacionamientoDomain.ingresarVehiculo(vehiculo);
		assertTrue(respuestaEsperada.equals("Vehiculo registrado"));
		assertFalse(respuestaEsperada.equals("Parqueadero lleno"));
		assertFalse(respuestaEsperada.equals("No es un día hábil para ese vehiculo"));
	}
	
	@Test
	public void validarIngresoVehiculoRegistroVehiculoCarro() {
		vehiculo.setPlaca(PLACA_CARRO);
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
		String respuestaEsperada = estacionamientoDomain.ingresarVehiculo(vehiculo);
		assertTrue(respuestaEsperada.equals("Vehiculo registrado"));
		assertFalse(respuestaEsperada.equals("Parqueadero lleno"));
		assertFalse(respuestaEsperada.equals("No es un día hábil para ese vehiculo"));
	}

	@Test
	public void validarIngresoVehiculoDiaNoHabilplacasA() {
		vehiculo.setPlaca(PLACA_BY_A);
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
		String respuestaEsperada = estacionamientoDomain.ingresarVehiculo(vehiculo);
		assertTrue(respuestaEsperada.equals("Vehiculo registrado"));
		assertFalse(respuestaEsperada.equals("Parqueadero lleno"));
		assertFalse(respuestaEsperada.equals("No es un día hábil para ese vehiculo"));
	} 
	
	@Test
	public void validarPuestosDisponiblesMotos() {
		Boolean respuestEsperadaEstacionamientoMotos = estacionamientoDomain.validarPuestosDisponibles(TIPO_VEHICULO_MOTO);
		assertTrue(respuestEsperadaEstacionamientoMotos);
	}
	
	@Test
	public void validarPuestosDisponiblesCarros() {
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarPuestosDisponibles(TIPO_VEHICULO_CARRO);
		assertTrue(respuestEsperadaEstacionamientoCarros);
	}
	
	@Test
	public void validarCantPuestosMotos() {
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarCantPuestosVehiculos(5, TIPO_VEHICULO_MOTO);
		assertTrue(respuestEsperadaEstacionamientoCarros);
	}
	
	@Test
	public void validarCantPuestosCarros() {
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarCantPuestosVehiculos(15, TIPO_VEHICULO_CARRO);
		assertTrue(respuestEsperadaEstacionamientoCarros);
	}
	
	@Test
	public void validarIngresoVehiculosByA() {
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarIngresoVehiculosByA(PLACA_BY_A);
		assertTrue(respuestEsperadaEstacionamientoCarros);
	}
	
	@Test
	public void validarObtencionListaVehiculos() {
		List<Vehiculo> listaVehiculos = nuevoRepositorio.obtenerVehiculosDB();		
		List<VehiculoDTO> listaVehiculosDTOs = estacionamientoDomain.obtenerListaVehiculos();
		int apuntador = 0;
		for (Vehiculo vehiculo : listaVehiculos) {
				VehiculoDTO vehiculoDTO =listaVehiculosDTOs.get(apuntador);
				assertTrue(vehiculo.getPlaca().equals(vehiculoDTO.getPlaca()));
				assertTrue(vehiculo.getTipoVehiculo().equals(vehiculoDTO.getTipoVehiculo()));
				assertTrue(vehiculo.getFechaEntrada().equals(vehiculoDTO.getFechaEntrada()));
				apuntador += 1;
		}
	}
	
	@Test
	public void validarObtencionVehiculoByPlaca() {
		List<Vehiculo> listaVehiculoRepository = nuevoRepositorio.obtenerVehiculoPorPlacaDB(PLACA_MOTO);
		List<Vehiculo> listaVehiculoEsperado = estacionamientoDomain.obtenerVehiculoByPlaca(PLACA_MOTO);
		Vehiculo vehiculoRepository = listaVehiculoRepository.get(0);
		Vehiculo vehiculoEsperado = listaVehiculoEsperado.get(0);
			assertTrue(vehiculoRepository.getPlaca().equals(vehiculoEsperado.getPlaca()));
			assertTrue(vehiculoRepository.getTipoVehiculo().equals(vehiculoEsperado.getTipoVehiculo()));
			assertTrue(vehiculoRepository.getFechaEntrada().equals(vehiculoEsperado.getFechaEntrada()));
	}
}
