package com.ceiba.estacionamiento.repository.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.repository.IEstacionamientoRepository;
import com.ceiba.estacionamiento.util.HibernateUtil;

public class EstacionamientoRepositoryImpl implements IEstacionamientoRepository{
	
	@Override
	public void registrarVehiculoDB(Vehiculo vehiculo){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(vehiculo);
		session.getTransaction().commit();  
		session.close();
	} 
	
	@Override
	public List<Vehiculo> obtenerVehiculosDB(){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query<Vehiculo> queryVehiculos = session.createQuery("from Vehiculo"); 
		List<Vehiculo> listaVehiculos = queryVehiculos.getResultList();
		session.getTransaction().commit();  
		session.close();
		return listaVehiculos;
	}
	
	@Override
	public List<Vehiculo> obtenerVehiculoPorPlacaDB(String placa){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		Query<Vehiculo> queryVehiculo = session.createQuery("from Vehiculo where placa = :placa"); 
		queryVehiculo.setParameter("placa", placa);
		List<Vehiculo> vehiculo = queryVehiculo.getResultList();
		session.getTransaction().commit();  
		session.close();
		return vehiculo;
	}
	
	@Override
	public void actualizarVehiculoDB(Vehiculo vehiculo){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(vehiculo);
		session.getTransaction().commit();  
		session.close();
	} 
}
