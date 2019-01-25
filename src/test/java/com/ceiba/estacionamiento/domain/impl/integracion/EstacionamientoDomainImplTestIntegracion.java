package com.ceiba.estacionamiento.domain.impl.integracion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.estacionamiento.EstacionamientoSpringApplication;
import com.ceiba.estacionamiento.controller.error.EstacionamientoExcepcion;
import com.ceiba.estacionamiento.domain.IEstacionamientoDomain;
import com.ceiba.estacionamiento.domain.impl.EstacionamientoDomainImpl;
import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.repository.IEstacionamientoRepository;
import com.ceiba.estacionamiento.repository.impl.EstacionamientoRepositoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EstacionamientoSpringApplication.class)
@Transactional
public class EstacionamientoDomainImplTestIntegracion {
	
	final static String PLACA_MOTO = "lwq12t";
	final static String TIPO_VEHICULO_MOTO = "moto";
	final static String PLACA_CARRO = "tyo346";
	final static String TIPO_VEHICULO_CARRO = "carro"; 
	final static String PLACA_BY_A = "att987";
	private Vehiculo vehiculoTest = new Vehiculo();  
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private IEstacionamientoRepository estacionamientoRepository;
	private IEstacionamientoDomain estacionamientoDomain;
	
	@Before
	public void setUp() {
		estacionamientoDomain = new EstacionamientoDomainImpl(estacionamientoRepository);
	}
	
	@Test
	@Rollback(true)
	public void validarIngresoMotoBasesDatosLlena() {
		Vehiculo vehiculo = new Vehiculo();
		for (int i = 0; i < 10; i++) {
			vehiculo.setPlaca(PLACA_MOTO);
			vehiculo.setTipoVehiculo(TIPO_VEHICULO_MOTO);
			estacionamientoRepository.registrarVehiculoDB(vehiculo);
		}
		correrValidacionIngresoVehiculos(PLACA_MOTO, TIPO_VEHICULO_MOTO);
	}
	
	@Test
	@Rollback(true)
	public void validarIngresoCarroBasesDatosLlena() {
		Vehiculo vehiculo = new Vehiculo();
		for (int i = 0; i < 20; i++) {
			vehiculo.setPlaca(PLACA_CARRO);
			vehiculo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
			estacionamientoRepository.registrarVehiculoDB(vehiculo);
		}
		correrValidacionIngresoVehiculos(PLACA_CARRO, TIPO_VEHICULO_CARRO);
	}
	
	public void correrValidacionIngresoVehiculos(String placa, String tipovehiculo) {
		try {
			vehiculoTest.setPlaca(placa);
			vehiculoTest.setTipoVehiculo(tipovehiculo);
			estacionamientoDomain.ingresarVehiculo(vehiculoTest);
		} catch (RuntimeException e) {	
			assertTrue(e.getMessage().equals("Estacionamiento lleno"));
		}
	}
	
	/*
	@Test
	public void validarIngresoCarroBasesDatosLlena() {
		
		try {
			estacionamientoDomain.validarPuestosDisponibles(TIPO_VEHICULO_CARRO);
		} catch (RuntimeException e) {	
			assertFalse(e.getMessage().equals("Estacionamiento lleno"));
		}
	}	
	
	@Test
	@Transactional
	public void validarIngresoMotoBasesDatosVacia() {
		correrValidacionIngresoVehiculos(TIPO_VEHICULO_MOTO, PLACA_MOTO);
	}
	
	@Test
	@Transactional
	public void validarIngresoCarroBasesDatosVacia() {
		correrValidacionIngresoVehiculos(TIPO_VEHICULO_CARRO, PLACA_CARRO);
	}
	
	public void correrValidacionIngresoVehiculos(String tipovehiculo, String placa) {
		vehiculoTest.setPlaca(placa);
		vehiculoTest.setTipoVehiculo(tipovehiculo);
		estacionamientoDomain.ingresarVehiculo(vehiculoTest);
		List<Vehiculo> vehiculoRegistrado = estacionamientoRepository.obtenerVehiculoPorPlacaDB(placa);
		assertTrue(vehiculoRegistrado.get(0).getPlaca().equals(placa));
		//assertTrue(respuestaEsperada.equals(respuestaFalsa));
	}
	
	/*
	
	@Test
	public void validarIngresoVehiculoDiaNoHabilplacasA() {
		String respuestaIdeal = "No es un día hábil para ese vehiculo";
		correrValidacionIngresoVehiculos(respuestaIdeal, "", TIPO_VEHICULO_CARRO, PLACA_BY_A);
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
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarIngresoVehiculosByA(PLACA_BY_A,Calendar.SATURDAY,Calendar.MONDAY);
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
	 
	@Test
	public void probarValidacionRegistroSalidaVehiculoPlacaNoExistente() {
		//vehiculo nulo
		Vehiculo vehiculoEsperado = estacionamientoDomain.registrarSalidaVehiculo(PLACA_CARRO);
		assertFalse(vehiculo.equals(vehiculoEsperado));
	}
	
	@Test
	public void probarValidacionRegistroSalidaCarro() {
		//En la base de datos registrar este vehiculo
		vehiculo.setPlaca("eds123");
		vehiculo.setTipoVehiculo("carro");
		try {
			vehiculo.setFechaEntrada(format.parse("2019-01-18 14:57:38"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		correrTestResgistroSalidaVehiculos("eds123", vehiculo);
	}
	
	@Test
	public void probarValidacionRegistroSalidaMoto() {
		//En la base de datos registrar este vehiculo
		vehiculo.setPlaca("mxl34j");
		vehiculo.setTipoVehiculo("moto");
		try {
			vehiculo.setFechaEntrada(format.parse("2019-01-18 14:58:40"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		correrTestResgistroSalidaVehiculos("mxl34j", vehiculo);
	}
				
	public void correrTestResgistroSalidaVehiculos(String placaAverificar, Vehiculo vehiculoModelo) { 
		Vehiculo vehiculoEsperado = estacionamientoDomain.registrarSalidaVehiculo(placaAverificar);
		assertTrue(vehiculoModelo.getPlaca().equals(vehiculoEsperado.getPlaca()));
	}
	*/
}

