package com.ceiba.estacionamiento.domain.impl.unitarias;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.InjectMocks;

import com.ceiba.estacionamiento.controller.error.EstacionamientoExcepcion;
import com.ceiba.estacionamiento.domain.IEstacionamientoDomain;
import com.ceiba.estacionamiento.domain.impl.EstacionamientoDomainImpl;
import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.repository.IEstacionamientoRepository;
import com.ceiba.estacionamiento.repository.impl.EstacionamientoRepositoryImpl;


@RunWith(MockitoJUnitRunner.class)
public class EstacionamientoDomainImplTestUnitaria {
	
	final static String PLACA_MOTO = "lwq12t"; 
	final static String TIPO_VEHICULO_MOTO = "moto";
	final static String PLACA_CARRO = "tyo346";
	final static String TIPO_VEHICULO_CARRO = "carro";
	final static String PLACA_BY_A = "att987"; 
	  
	@InjectMocks
	private EstacionamientoDomainImpl estacionamientoDomain;

	@Mock
	private IEstacionamientoRepository estacionamientoRepository;

	@Before
	public void setUp() {
		estacionamientoDomain = new EstacionamientoDomainImpl(estacionamientoRepository);
	}
	 
	private Vehiculo vehiculo = new Vehiculo();  
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	@Test
	public void validarIngresoMoto() {
		correrValidacionIngresoVehiculos(TIPO_VEHICULO_MOTO, PLACA_MOTO);
	}
	
	@Test
	public void validarIngresoCarro() {
		correrValidacionIngresoVehiculos(TIPO_VEHICULO_CARRO, PLACA_CARRO);
	}
	
	public void correrValidacionIngresoVehiculos(String tipovehiculo, String placa) {
		List<Vehiculo> listaVacia = new ArrayList<>();
		vehiculo.setPlaca(placa);
		vehiculo.setTipoVehiculo(tipovehiculo);
		when(estacionamientoRepository.obtenerVehiculosDB()).thenReturn(listaVacia);
		estacionamientoDomain.ingresarVehiculo(vehiculo);
		assertTrue();
	}
	/*
	@Test
	public void validarIngresoCarroEstacionamientoLleno() {
		List<Vehiculo> listaLlena = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			listaLlena.add(vehiculo);
		}
		mensaje.setMensaje("Parqueadero lleno");
		correrValidacionIngresoVehiculos(mensaje, TIPO_VEHICULO_CARRO, PLACA_CARRO, listaLlena);
	}
	
	@Test
	public void validarIngresoMotoEstacionamientoLleno() {
		List<Vehiculo> listaLlena = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			listaLlena.add(vehiculo);
		}
		mensaje.setMensaje("Parqueadero lleno");
		correrValidacionIngresoVehiculos(mensaje, TIPO_VEHICULO_MOTO, PLACA_MOTO, listaLlena);
	}
	
	public void correrValidacionIngresoVehiculos(MensajeRespuesta mensaje, String tipovehiculo, String placa, List<Vehiculo> listaVehiculos) {
		vehiculo.setPlaca(placa);
		vehiculo.setTipoVehiculo(tipovehiculo);
		when(nuevoRepositorio.obtenerVehiculosDB()).thenReturn(listaVehiculos);
		assertTrue(estacionamientoDomain.ingresarVehiculo(vehiculo).getMensaje().equals(mensaje.getMensaje()));
	}
	
	@Test
	public void validarIngresoVehiculoDiaNoHabilplacasA() {
		List<Vehiculo> listaVacia = new ArrayList<>();
		vehiculo.setPlaca(PLACA_BY_A);
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
		when(nuevoRepositorio.obtenerVehiculosDB()).thenReturn(listaVacia);
		when(nuevoRepositorio.obtenerVehiculosDB()).thenReturn(listaVacia);
		assertTrue(estacionamientoDomain.ingresarVehiculo(vehiculo).getMensaje().equals(mensaje.getMensaje()));
	}
	*/
	/*
	@Test
	public void validarPuestosDisponiblesMotos() {
		List<Vehiculo> listaVacia = new ArrayList<>();
		correrValidacionPuestosDisponibles(listaVacia, TIPO_VEHICULO_MOTO);
	}
	 
	@Test
	public void validarPuestosDisponiblesCarros() {
		List<Vehiculo> listaVacia = new ArrayList<>();
		correrValidacionPuestosDisponibles(listaVacia, TIPO_VEHICULO_CARRO);
	}
	
	public void correrValidacionPuestosDisponibles(List<Vehiculo> listaVehiculos, String tipoVehiculo) {
		when(estacionamientoRepository.obtenerVehiculosDB()).thenReturn(listaVehiculos);
		assertTrue(estacionamientoDomain.validarPuestosDisponibles(tipoVehiculo));
	}
	*/
	@Test
	public void validarPuestosLlenosMotos() {
		List<Vehiculo> listaLlena = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			vehiculo.setTipoVehiculo(TIPO_VEHICULO_MOTO);
			listaLlena.add(vehiculo);
		}
		correrValidacionPuestosLlenos(listaLlena, TIPO_VEHICULO_MOTO);
	}
	
	@Test
	public void validarPuestosLlenosCarros() {
		List<Vehiculo> listaLlena = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			vehiculo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
			listaLlena.add(vehiculo);
		}
		correrValidacionPuestosLlenos(listaLlena, TIPO_VEHICULO_CARRO);
	}
	
	public void correrValidacionPuestosLlenos(List<Vehiculo> listaVehiculos, String tipoVehiculo) {
		when(estacionamientoRepository.obtenerVehiculosDB()).thenReturn(listaVehiculos);
		try {
			estacionamientoDomain.validarPuestosDisponibles(tipoVehiculo);
		} catch (RuntimeException e) {	
			assertTrue(e.getMessage().equals("Estacionamiento lleno"));
		}
	}
	
	@Test
	public void validarObtencionListaVehiculos() {
		List<Vehiculo> listaLlena = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			vehiculo.setPlaca(PLACA_CARRO);
			vehiculo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
			listaLlena.add(vehiculo);
		}
		when(estacionamientoRepository.obtenerVehiculosDB()).thenReturn(listaLlena);
		List<Vehiculo> listaVehiculos = estacionamientoRepository.obtenerVehiculosDB();		
		List<VehiculoDTO> listaVehiculosDTOs = estacionamientoDomain.obtenerListaVehiculos();
		int apuntador = 0;
		for (Vehiculo vehiculo : listaVehiculos) {
			VehiculoDTO vehiculoDTO =listaVehiculosDTOs.get(apuntador);
			assertTrue(vehiculo.getPlaca().equals(vehiculoDTO.getPlaca()));
			assertTrue(vehiculo.getTipoVehiculo().equals(vehiculoDTO.getTipoVehiculo()));
			apuntador += 1;
		}
	} 
	
	@Test
	public void validarObtencionVehiculoByPlaca() { 
		List<Vehiculo> lista = new ArrayList<>();
		vehiculo.setPlaca(PLACA_MOTO);
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_MOTO);
		lista.add(vehiculo);
		when(estacionamientoRepository.obtenerVehiculoPorPlacaDB(PLACA_MOTO)).thenReturn(lista);
		List<Vehiculo> listaVehiculoRepository = estacionamientoRepository.obtenerVehiculoPorPlacaDB(PLACA_MOTO);
		List<Vehiculo> listaVehiculoEsperado = estacionamientoDomain.obtenerVehiculoByPlaca(PLACA_MOTO);
		Vehiculo vehiculoRepository = listaVehiculoRepository.get(0);
		Vehiculo vehiculoEsperado = listaVehiculoEsperado.get(0);
		assertTrue(vehiculoRepository.getPlaca().equals(vehiculoEsperado.getPlaca()));
		assertTrue(vehiculoRepository.getTipoVehiculo().equals(vehiculoEsperado.getTipoVehiculo()));
	}

	@Test
	public void probarValidacionRegistroSalidaVehiculoPlacaNoExistente() {
		List<Vehiculo> lista = new ArrayList<>();
		vehiculo.setPlaca(PLACA_MOTO);
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_MOTO);
		lista.add(vehiculo);
		when(estacionamientoRepository.obtenerVehiculosDB()).thenReturn(lista);
		try {
			estacionamientoDomain.registrarSalidaVehiculo(PLACA_CARRO);
		} catch (EstacionamientoExcepcion e) {
			assertTrue(e.getMessage().equals("Ese vehiculo No se encuentra en el parqueadero"));
		}	
	}

	@Test
	public void probarValidacionRegistroSalidaCarro() {
		List<Vehiculo> listaVehiculos= new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			vehiculo.setPlaca(PLACA_CARRO);
			vehiculo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
			listaVehiculos.add(vehiculo);
		}
		vehiculo.setPlaca("gds123");
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
		try {
			vehiculo.setFechaEntrada(format.parse("2019-01-18 14:57:38"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		correrTestResgistroSalidaVehiculos("gds123", vehiculo, listaVehiculos);
	}
	
	@Test
	public void probarValidacionRegistroSalidaMoto() {
		List<Vehiculo> listaVehiculos= new ArrayList<>();
		listaVehiculos.add(vehiculo);
		for (int i = 0; i < 5; i++) {
			vehiculo.setPlaca(PLACA_MOTO);
			vehiculo.setTipoVehiculo(TIPO_VEHICULO_MOTO);
			listaVehiculos.add(vehiculo);
		}
		vehiculo.setPlaca("mxl34j");
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_MOTO);
		try {
			vehiculo.setFechaEntrada(format.parse("2019-01-18 14:58:40"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		correrTestResgistroSalidaVehiculos("mxl34j", vehiculo, listaVehiculos);
	}
			
	public void correrTestResgistroSalidaVehiculos(String placaAverificar, Vehiculo vehiculoModelo, List<Vehiculo> listaVehiculos) { 
		when(estacionamientoRepository.obtenerVehiculosDB()).thenReturn(listaVehiculos);
		Vehiculo vehiculoEsperado = estacionamientoDomain.registrarSalidaVehiculo(placaAverificar);
		assertTrue(vehiculoModelo.getPlaca().equals(vehiculoEsperado.getPlaca()));
	}

}
