package com.ceiba.estacionamiento.domain.impl.integracion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
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


@RunWith(SpringRunner.class)
@SpringBootTest(classes = EstacionamientoSpringApplication.class)
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
	public void validarIngresoMotoBasesDatosVacia() {
		correrValidacionIngresoVehiculosEstacionamientoVacio(TIPO_VEHICULO_MOTO, PLACA_MOTO);
	}
	
	@Test
	public void validarIngresoCarroBasesDatosVacia() {
		correrValidacionIngresoVehiculosEstacionamientoVacio(TIPO_VEHICULO_CARRO, PLACA_CARRO);
	}
	
	public void correrValidacionIngresoVehiculosEstacionamientoVacio(String tipovehiculo, String placa) {
		vehiculoTest.setPlaca(placa);
		vehiculoTest.setTipoVehiculo(tipovehiculo);
		Vehiculo vehiculoRegistrado = estacionamientoDomain.ingresarVehiculo(vehiculoTest);
		estacionamientoRepository.delete(vehiculoTest);
		assertTrue(vehiculoRegistrado.getPlaca().equals(vehiculoTest.getPlaca()));	
	}
	/*
	@Test
	public void validarIngresoMotoBasesDatosLlena() {
		int contador = 100000;
		for (int i = 0; i < 10; i++) {
			vehiculoTest.setIdVehiculo(contador);
			vehiculoTest.setPlaca(PLACA_MOTO + contador);
			vehiculoTest.setTipoVehiculo(TIPO_VEHICULO_MOTO);
			estacionamientoRepository.save(vehiculoTest);
			contador += 1;
		}
		correrValidacionIngresoVehiculosEstacionamientoLleno(PLACA_MOTO, TIPO_VEHICULO_CARRO);
	}
	
	@Test
	public void validarIngresoCarroBasesDatosLlena() {
		int contador = 200000;
		for (int i = 0; i < 20; i++) {
			vehiculoTest.setIdVehiculo(contador);
			vehiculoTest.setPlaca(PLACA_CARRO + contador);
			vehiculoTest.setTipoVehiculo(TIPO_VEHICULO_CARRO);
			estacionamientoRepository.save(vehiculoTest);
			contador += 1;
		}
		correrValidacionIngresoVehiculosEstacionamientoLleno(PLACA_CARRO, TIPO_VEHICULO_CARRO);
	}
	
	public void correrValidacionIngresoVehiculosEstacionamientoLleno(String placa, String tipovehiculo) {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca("m");
		vehiculo.setTipoVehiculo(tipovehiculo);
		try {
			estacionamientoDomain.ingresarVehiculo(vehiculo);
		} catch (RuntimeException e) {
			estacionamientoRepository.deleteAll();
			assertTrue(e.getMessage().equals("Estacionamiento lleno"));
		}
	}
	*/
	  
	@Test
	public void validarPlacasByAdiaNoHabil() { 
		Calendar fechaActual = Calendar.getInstance();
		int diaActual = fechaActual.get(Calendar.DAY_OF_WEEK);
		try {
			estacionamientoDomain.validarIngresoVehiculosByA(PLACA_BY_A, diaActual, diaActual);
		} catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("No es un dia habil para este vehiculo"));
		}
	}
	/*
	@Test
	public void validarPlacasRepetidas() {
		vehiculoTest.setPlaca(PLACA_CARRO);
		vehiculoTest.setTipoVehiculo(PLACA_CARRO);
		estacionamientoDomain.ingresarVehiculo(vehiculoTest);
		try {
			estacionamientoDomain.validarPlacaExistenteEstacionamiento(PLACA_CARRO);
		} catch (RuntimeException e) {
			estacionamientoRepository.delete(vehiculoTest);
			assertTrue(e.getMessage().equals("Ese vehiculo ya aparece activo en el estacionamiento"));
		}
	}
	*/
	
	@Test
	public void probarValidacionRegistroSalidaCarro() {
		//En la base de datos registrar este vehiculo
		vehiculoTest.setPlaca("eds123");
		vehiculoTest.setTipoVehiculo("carro");
		correrTestResgistroSalidaVehiculos("eds123", vehiculoTest);
	}
	
	@Test
	public void probarValidacionRegistroSalidaMoto() {
		//En la base de datos registrar este vehiculo
		vehiculoTest.setPlaca("mxl34j");
		vehiculoTest.setTipoVehiculo("moto");
		correrTestResgistroSalidaVehiculos("mxl34j", vehiculoTest);
	}
			
	public void correrTestResgistroSalidaVehiculos(String placaAverificar, Vehiculo vehiculoModelo) { 
		estacionamientoDomain.ingresarVehiculo(vehiculoModelo);
		Vehiculo vehiculoEsperado = estacionamientoDomain.registrarSalidaVehiculo(placaAverificar);
		estacionamientoRepository.delete(vehiculoModelo);
		assertTrue(vehiculoModelo.getPlaca().equals(vehiculoEsperado.getPlaca()));
	}

}

