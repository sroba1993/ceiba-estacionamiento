package com.ceiba.estacionamiento.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;

public class Configuration {

	@ApplicationPath("/")
	public static class RestApplication extends Application {
		
	}
}
