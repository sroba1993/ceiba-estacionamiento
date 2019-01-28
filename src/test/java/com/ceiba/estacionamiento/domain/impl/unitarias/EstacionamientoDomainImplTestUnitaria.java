package com.ceiba.estacionamiento.domain.impl.unitarias;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import com.ceiba.estacionamiento.controller.error.EstacionamientoExcepcion;
import com.ceiba.estacionamiento.domain.impl.EstacionamientoDomainImpl;
import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.repository.IEstacionamientoRepository;

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
		correrValidacionIngresoVehiculos(TIPO_VEHICULO_MOTO, PLACA_MOTO, 125);
	}
	
	@Test
	public void validarIngresoCarro() {
		correrValidacionIngresoVehiculos(TIPO_VEHICULO_CARRO, PLACA_CARRO, 1200);
	}
	
	public void correrValidacionIngresoVehiculos(String tipovehiculo, String placa, int cilindraje) {
		vehiculo.setPlaca(placa);
		vehiculo.setTipoVehiculo(tipovehiculo);
		vehiculo.setCilindraje(cilindraje);
		when(estacionamientoRepository.findVehicleByPlaca(placa)).thenReturn(vehiculo);
		Vehiculo vehiculoIngresado = estacionamientoDomain.ingresarVehiculo(vehiculo);
		assertTrue(vehiculoIngresado.equals(vehiculo));
	}
	
	@Test
	public void validarIngresoCarroEstacionamientoLleno() {
		List<Vehiculo> listaLlena = new ArrayList<>();
		Vehiculo vehiculoTest = new Vehiculo();
		for (int i = 0; i < 20; i++) {
			vehiculoTest.setPlaca("eee000");
			vehiculoTest.setTipoVehiculo(TIPO_VEHICULO_CARRO);
			listaLlena.add(vehiculoTest);
		}
		correrValidacionIngresoVehiculosEstacionamientoLleno(TIPO_VEHICULO_CARRO, PLACA_CARRO, 1300, listaLlena);
	}
	
	@Test
	public void validarIngresoMotoEstacionamientoLleno() {
		List<Vehiculo> listaLlena = new ArrayList<>();
		Vehiculo vehiculoTest = new Vehiculo();
		for (int i = 0; i < 10; i++) {
			vehiculoTest.setPlaca("eee00e");
			vehiculoTest.setTipoVehiculo(TIPO_VEHICULO_MOTO);
			listaLlena.add(vehiculoTest);
		}
		correrValidacionIngresoVehiculosEstacionamientoLleno(TIPO_VEHICULO_MOTO, PLACA_MOTO,650 , listaLlena);
	}
	
	public void correrValidacionIngresoVehiculosEstacionamientoLleno(String tipovehiculo, String placa, int cilindraje, List<Vehiculo> listaVehiculos) {
		vehiculo.setPlaca(placa);
		vehiculo.setTipoVehiculo(tipovehiculo);
		vehiculo.setCilindraje(cilindraje);
		when(estacionamientoRepository.findListVehicles()).thenReturn(listaVehiculos);
		try {
			estacionamientoDomain.ingresarVehiculo(vehiculo);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Estacionamiento lleno"));
		}
	}
	
	@Test
	public void ValidarIngresoVehiculosPlacaRepetida() {
		List<Vehiculo> listaVehiculos = new ArrayList<>();
		vehiculo.setPlaca(PLACA_CARRO);
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
		listaVehiculos.add(vehiculo);
		when(estacionamientoRepository.findListVehicles()).thenReturn(listaVehiculos);
		try {
			estacionamientoDomain.validarPlacaExistenteEstacionamiento(PLACA_CARRO);
		} catch (EstacionamientoExcepcion e) {
			assertTrue(e.getMessage().equals("Ese vehiculo ya aparece activo en el estacionamiento"));
		}
	}
	
	@Test
	public void validarIngresoVehiculosByA() {
		Calendar fechaActual = Calendar.getInstance();
		int diaActual = fechaActual.get(Calendar.DAY_OF_WEEK);
		try {
			estacionamientoDomain.validarIngresoVehiculosByA(PLACA_BY_A,diaActual,diaActual);
		} catch (RuntimeException e) {	
			assertTrue(e.getMessage().equals("No es un dia habil para este vehiculo"));
		}	
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
		when(estacionamientoRepository.findListVehicles()).thenReturn(listaVehiculos);
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
		when(estacionamientoRepository.findListVehicles()).thenReturn(listaLlena);
		List<Vehiculo> listaVehiculos = estacionamientoRepository.findListVehicles();		
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
		vehiculo.setPlaca(PLACA_MOTO);
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_MOTO);
		when(estacionamientoRepository.findVehicleByPlaca(PLACA_MOTO)).thenReturn(vehiculo);
		Vehiculo vehiculoEsperado = estacionamientoDomain.obtenerVehiculoByPlaca(PLACA_MOTO);
		assertTrue(vehiculo.getPlaca().equals(vehiculoEsperado.getPlaca()));
		assertTrue(vehiculo.getTipoVehiculo().equals(vehiculoEsperado.getTipoVehiculo()));
	}
	
	@Test
	public void probarValidacionRegistroSalidaVehiculoPlacaNoExistente() {
		when(estacionamientoRepository.findVehicleByPlaca(PLACA_CARRO)).thenReturn(null);
		try {
			estacionamientoDomain.registrarSalidaVehiculo(PLACA_CARRO);
		} catch (EstacionamientoExcepcion e) {
			assertTrue(e.getMessage().equals("Ese vehiculo No se encuentra en el parqueadero"));
		}	
	}

	@Test
	public void probarValidacionRegistroSalidaCarro() {
		Vehiculo vehiculoModelo = new Vehiculo();
		vehiculoModelo.setPlaca("lkj345");
		vehiculoModelo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
		try {
			vehiculoModelo.setFechaEntrada(format.parse("2019-01-16 12:58:40"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		correrTestResgistroSalidaVehiculos("lkj345", vehiculoModelo);
	}
	
	@Test
	public void probarValidacionRegistroSalidaMoto() {
		Vehiculo vehiculoModelo = new Vehiculo();
		vehiculoModelo.setPlaca("mxl34j");
		vehiculoModelo.setTipoVehiculo(TIPO_VEHICULO_MOTO);
		try {
			vehiculoModelo.setFechaEntrada(format.parse("2019-01-18 14:58:40"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		correrTestResgistroSalidaVehiculos("mxl34j", vehiculoModelo);
	}
	
	public void correrTestResgistroSalidaVehiculos(String placaAverificar, Vehiculo vehiculoModelo) { 
		when(estacionamientoRepository.findVehicleByPlaca(placaAverificar)).thenReturn(vehiculoModelo);
		Vehiculo vehiculoEsperado = estacionamientoDomain.registrarSalidaVehiculo(placaAverificar);
		assertTrue(vehiculoModelo.getPlaca().equals(vehiculoEsperado.getPlaca()));
	}
	
	@Test
	public void probarDatosIncompletoFaltaCampoTipoVehiculo() {
		vehiculo.setPlaca(PLACA_CARRO);
		vehiculo.setTipoVehiculo("");
		vehiculo.setCilindraje(1200);
		try {
			estacionamientoDomain.validarDatosIngresadosVehiculos(vehiculo);
		} catch (EstacionamientoExcepcion e) {
			assertTrue(e.getMessage().equals("Ingrese un tipo de vehiculo válido"));
		}	
	}
	
	@Test
	public void probarDatosIncompletosFaltaCilindraje() {
		vehiculo.setPlaca(PLACA_CARRO);
		vehiculo.setTipoVehiculo(TIPO_VEHICULO_CARRO);
		try {
			estacionamientoDomain.validarDatosIngresadosVehiculos(vehiculo);
		} catch (EstacionamientoExcepcion e) {
			assertTrue(e.getMessage().equals("Ingrese el cilindraje del vehiculo"));
		}	
	}
}
