package com.ceiba.estacionamiento;

import java.util.Date;

import org.hibernate.Session;
import com.ceiba.estacionamiento.model.*;
import com.ceiba.estacionamiento.repository.HibernateUtil;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;

import com.ceiba.estacionamiento.repository.HibernateUtil;


public class Test {

	public Test() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		//Crear una instancia de todos los objetos
		Vehiculo vehiculo = new Vehiculo("OJM20u","moto",200);
		Vehiculo vehiculo1 = new Vehiculo("LJH20u","moto",200);
		Vehiculo vehiculo2 = new Vehiculo("KDF023","carro",2000);
		Vehiculo vehiculo3 = new Vehiculo("QWL963","carro",1300);
		
		Date date = new Date();
		ColillaEntrada colillaEntrada = new ColillaEntrada("KHM20u",  new Timestamp(date.getTime()));
		//Factura factura = new Factura(new Timestamp(date.getTime()) , new Timestamp(date.getTime()), new Timestamp(date.getTime()));
		
		//salvar el objeto
		session.save(vehiculo);
		session.save(vehiculo1);
		session.save(vehiculo2);
		session.save(vehiculo3);
	    session.save(colillaEntrada);
		//session.save(factura);
		
		
		session.getTransaction().commit();  //para realizar los cambios, siempre va de ultimo
		session.close();
	}
}
