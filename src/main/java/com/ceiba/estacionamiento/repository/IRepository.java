package com.ceiba.estacionamiento.repository;

import java.util.List;
import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Vehiculo;

public interface IRepository {
	
	public void crearVehiculoDB(Vehiculo vehiculo);
	public void borrarVehiculoDB(String placa);
	public List<Vehiculo> obtenerVehiculosDB();
	public List<Vehiculo> obtenerVehiculoPorPlaca(String placa);
}
