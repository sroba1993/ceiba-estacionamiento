package com.ceiba.estacionamiento.main;

import org.hibernate.Session;
import com.ceiba.estacionamiento.repository.HibernateUtil;
import com.ceiba.estacionamiento.model.Vehiculo;

public class Main {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		//Crear una instancia del objeto
		Vehiculo vehiculo = new Vehiculo("hgm123","carro",125);
		
		//salvar el objeto
		session.save(vehiculo);
		
		session.getTransaction().commit();  //para realizar los cambios, siempre va de ultimo
		session.close();
	}
}
