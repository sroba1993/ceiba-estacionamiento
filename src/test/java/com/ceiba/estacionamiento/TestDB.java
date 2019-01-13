package com.ceiba.estacionamiento;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;

import com.ceiba.estacionamiento.model.ColillaEntrada;
import com.ceiba.estacionamiento.model.Estacionamiento;
import com.ceiba.estacionamiento.model.Factura;
import com.ceiba.estacionamiento.model.Vehiculo;
import com.ceiba.estacionamiento.repository.HibernateUtil;

public class TestDB {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		
		Estacionamiento estacionamiento = new Estacionamiento(10,20);
		session.save(estacionamiento);
		
		Vehiculo vehiculo = new Vehiculo("OJM20u","moto",200);
		vehiculo.setEstacionamiento(estacionamiento);
		session.save(vehiculo);
		
		Date date = new Date();
		ColillaEntrada colillaEntrada = new ColillaEntrada("KHM20u",  new Timestamp(date.getTime()));
		colillaEntrada.setVehiculo(vehiculo);
		session.save(colillaEntrada);
		
		Factura factura = new Factura("QWL963",new Timestamp(date.getTime()) , new Timestamp(date.getTime()), "02:31:06");
		factura.setVehiculo(vehiculo);
		session.save(factura);
		
		
		session.getTransaction().commit();  //para realizar los cambios, siempre va de ultimo
		session.close();
	}
}
