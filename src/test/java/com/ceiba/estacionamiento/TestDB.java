package com.ceiba.estacionamiento;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ceiba.estacionamiento.domain.*;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.util.HibernateUtil;

public class TestDB {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		
		//Estacionamiento estacionamiento = new Estacionamiento(10,20);
		//session.save(estacionamiento);
		
		Date date = new Date();
		Vehiculo vehiculo = new Vehiculo("rze97e", "moto", 125, date);
		//vehiculo.setEstacionamiento(estacionamiento);
		session.save(vehiculo);
		
		Query<Vehiculo> queryVehiculos = session.createQuery("from Vehiculo"); 
		List<Vehiculo> listaVehiculos = queryVehiculos.getResultList();
		System.out.println(listaVehiculos.toString());
		
		//para realizar los cambios, siempre va de ultimo
		session.getTransaction().commit();  
		session.close();
	}
}
