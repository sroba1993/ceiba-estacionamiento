package com.ceiba.estacionamiento.controller.error;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class EstacionamientoExcepcion extends WebApplicationException {

	private static final long serialVersionUID = 1L;
	  /**
	  * Create a HTTP 40O (BAD REQUEST) exception.
	  * @param message the String that is the entity of the 400 response.
	  */
	public EstacionamientoExcepcion(String message) {
		super(message, Response.status(400).entity(message).type(MediaType.APPLICATION_JSON).build());
	}
}
 
