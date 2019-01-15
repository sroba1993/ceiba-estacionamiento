package com.ceiba.estacionamiento.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ceiba.estacionamiento.dto.VehiculoDTO;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.util.HibernateUtil;

public class VehiculoRepository {

	public static void crearVehiculo(Vehiculo vehiculo){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Date date = new Date();
		vehiculo.setFechaEntrada(date);
		session.save(vehiculo);
		session.getTransaction().commit();  
		session.close();
	}
	
	public static List<VehiculoDTO> obtenerListaVehiculos(){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query<Vehiculo> queryVehiculos = session.createQuery("from Vehiculo"); 
		List<Vehiculo> listaVehiculos = queryVehiculos.getResultList();
		List<VehiculoDTO> vehiculoDTOs = new ArrayList<VehiculoDTO>();
		vehiculoDTOs = VehiculoDTO.vehiculoDTO(listaVehiculos);
		session.getTransaction().commit();  
		session.close();
		
		return vehiculoDTOs;
	}
	
	public static List<Vehiculo> obtenerVehiculoPorPlaca(String placa){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		Query<Vehiculo> queryVehiculo = session.createQuery("from Vehiculo where placa = :placa"); 
		queryVehiculo.setParameter("placa", placa);
		List<Vehiculo> vehiculos = queryVehiculo.getResultList();
		session.getTransaction().commit();  
		session.close();
		
		return vehiculos;
	}
}
