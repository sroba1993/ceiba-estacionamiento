package com.ceiba.estacionamiento.domain.pruebasunitarias;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import com.ceiba.estacionamiento.domain.impl.EstacionamientoDomainImpl;
import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.repository.impl.EstacionamientoRepositoryImpl;


@RunWith(MockitoJUnitRunner.class)
public class EstacionamientoDomainImplTestUnitaria {
	
	final static String PLACA_MOTO = "lwq12t";
	final static String TIPO_VEHICULO_MOTO = "moto";
	final static String PLACA_CARRO = "tyo346";
	final static String TIPO_VEHICULO_CARRO = "carro";
	final static String PLACA_BY_A = "att987";
	 
	@InjectMocks
	private EstacionamientoDomainImpl estacionamientoDomain = new EstacionamientoDomainImpl();
	
	@Mock
	private EstacionamientoRepositoryImpl nuevoRepositorio = new EstacionamientoRepositoryImpl();
	
	private Vehiculo vehiculo = new Vehiculo();  
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	@Test
	public void validarIngresoMoto() {
		List<Vehiculo> listaVacia = new ArrayList<>();
		String respuestaIdeal = "Vehiculo registrado";
		correrValidacionIngresoVehiculos(respuestaIdeal, TIPO_VEHICULO_MOTO, PLACA_MOTO, listaVacia);
	}
	
	@Test
	public void validarIngresoCarro() {
		List<Vehiculo> listaVacia = new ArrayList<>();
		String respuestaIdeal = "Vehiculo registrado";
		correrValidacionIngresoVehiculos(respuestaIdeal, TIPO_VEHICULO_CARRO, PLACA_CARRO, listaVacia);
	}
	
	@Test
	public void validarIngresoCarroEstacionamientoLleno() {
		List<Vehiculo> listaLlena = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			listaLlena.add(vehiculo);
		}
		String respuestaIdeal = "Parqueadero lleno";
		correrValidacionIngresoVehiculos(respuestaIdeal, TIPO_VEHICULO_CARRO, PLACA_CARRO, listaLlena);
	}
	
	@Test
	public void validarIngresoMotoEstacionamientoLleno() {
		List<Vehiculo> listaLlena = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			listaLlena.add(vehiculo);
		}
		String respuestaIdeal = "Parqueadero lleno";
		correrValidacionIngresoVehiculos(respuestaIdeal, TIPO_VEHICULO_MOTO, PLACA_MOTO, listaLlena);
	}

	public void correrValidacionIngresoVehiculos(String respuestaIdeal, String tipovehiculo, String placa, List<Vehiculo> listaVehiculos) {
		vehiculo.setPlaca(placa);
		vehiculo.setTipoVehiculo(tipovehiculo);
		when(nuevoRepositorio.obtenerVehiculosDB()).thenReturn(listaVehiculos);
		assertTrue(estacionamientoDomain.ingresarVehiculo(vehiculo).equals(respuestaIdeal));
	}
	
	@Test
	public void validarIngresoVehiculoDiaNoHabilplacasA() {
		List<Vehiculo> listaVacia = new ArrayList<>();
		String respuestaIdeal = "No es un día hábil para ese vehiculo";
		vehiculo.setPlaca(PLACA_BY_A);
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
		when(nuevoRepositorio.obtenerVehiculosDB()).thenReturn(listaVacia);
		assertFalse(estacionamientoDomain.ingresarVehiculo(vehiculo).equals(respuestaIdeal));
	}
	
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
		when(nuevoRepositorio.obtenerVehiculosDB()).thenReturn(listaVehiculos);
		assertTrue(estacionamientoDomain.validarPuestosDisponibles(tipoVehiculo));
	}
	
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
		when(nuevoRepositorio.obtenerVehiculosDB()).thenReturn(listaVehiculos);
		//si hay puestos disponibles retorna un true
		assertFalse(estacionamientoDomain.validarPuestosDisponibles(tipoVehiculo));
	}
	
	@Test
	public void validarCantPuestosMotos() {
		//no necesita mock
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarCantPuestosVehiculos(5, TIPO_VEHICULO_MOTO);
		assertTrue(respuestEsperadaEstacionamientoCarros);
	}
	
	@Test
	public void validarCantPuestosCarros() {
		//no necesita mock
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarCantPuestosVehiculos(15, TIPO_VEHICULO_CARRO);
		assertTrue(respuestEsperadaEstacionamientoCarros);
	}
	
	@Test
	public void validarCantPuestosMotosLlenos() {
		//no necesita mock, me retorna false por estar lleno
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarCantPuestosVehiculos(10, TIPO_VEHICULO_MOTO);
		assertFalse(respuestEsperadaEstacionamientoCarros);
	}
	
	@Test
	public void validarCantPuestosCarrosLLenos() {
		//no necesita mock, me retorna false por estar lleno
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarCantPuestosVehiculos(20, TIPO_VEHICULO_CARRO);
		assertFalse(respuestEsperadaEstacionamientoCarros);
	}
	
	@Test
	public void validarIngresoVehiculosByA() {
		int diaActualParaPrueba = Calendar.SATURDAY;
		int dia = Calendar.MONDAY;
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarIngresoVehiculosByA(PLACA_BY_A,diaActualParaPrueba,dia);
		//retorna true si no es dia habil o la placa no inicia por a
		assertFalse(respuestEsperadaEstacionamientoCarros);
	}
	
	@Test
	public void validarIngresoVehiculosNoIniciaByA() {
		int diaActualParaPrueba = Calendar.SATURDAY;
		int dia = Calendar.MONDAY;
		Boolean respuestEsperadaEstacionamientoCarros = estacionamientoDomain.validarIngresoVehiculosByA(PLACA_CARRO,diaActualParaPrueba,dia);
		//retorna true si no es dia habil o la placa no inicia por a
		assertTrue(respuestEsperadaEstacionamientoCarros);
	}
	
	@Test
	public void validarObtencionListaVehiculos() {
		List<Vehiculo> listaLlena = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			vehiculo.setPlaca(PLACA_CARRO);
			vehiculo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
			listaLlena.add(vehiculo);
		}
		when(nuevoRepositorio.obtenerVehiculosDB()).thenReturn(listaLlena);
		List<Vehiculo> listaVehiculos = nuevoRepositorio.obtenerVehiculosDB();		
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
		when(nuevoRepositorio.obtenerVehiculoPorPlacaDB(PLACA_MOTO)).thenReturn(lista);
		List<Vehiculo> listaVehiculoRepository = nuevoRepositorio.obtenerVehiculoPorPlacaDB(PLACA_MOTO);
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
		when(nuevoRepositorio.obtenerVehiculosDB()).thenReturn(lista);
		//solo hay una moto, la placa del carro no existe retorna un vehiculo nulo
		Vehiculo vehiculoEsperado = estacionamientoDomain.registrarSalidaVehiculo(PLACA_CARRO);
		assertFalse(vehiculo.equals(vehiculoEsperado));
	}
	
	/*
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