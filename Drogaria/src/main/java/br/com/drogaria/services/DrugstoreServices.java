package br.com.drogaria.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("drugstore")
public class DrugstoreServices {

	@GET
	public String teste() {
		return "Teste";
	}
	
}
