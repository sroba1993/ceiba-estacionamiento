package com.ceiba.estacionamiento;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationEstacionamiento {

	@ApplicationPath("/")
	public class RestApplication extends ResourceConfig {
		
	}
}
