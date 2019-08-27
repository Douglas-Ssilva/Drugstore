package br.com.drogaria.services;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.drogaria.dao.FactoryDAO;
import br.com.drogaria.domain.Factory;

@Path("factory")
public class FactoryServices {
	
	@GET
	public String getFactorys(){
		System.out.println("Consumindo fabricantes");
		Gson gson= new Gson();
		String json = gson.toJson(new FactoryDAO().getAllOrdered("name"));
		return json;
	}
	
	@GET
	@Path("{id}")
	public String getId(@PathParam("id")Long id) { //pathparam faz a amarra entre a variável e @Path
		return new Gson().toJson(new FactoryDAO().findById(id));
	}
	
	@POST
	public String save(String json) {
		Gson gson = new Gson();
		Factory factory = gson.fromJson(json, Factory.class);
		new FactoryDAO().save(factory);
		String jsonFac= gson.toJson(factory);
		return jsonFac;
	}
	
	@PUT //protocolo de chamada faz a distinção de qual método chamar
	public String update(String json) {
		Gson gson = new Gson();
		Factory f= (Factory) gson.fromJson(json, Factory.class);
		new FactoryDAO().update(f);
		String jsonFactory= gson.toJson(f);
		return jsonFactory;
	}
	
	@DELETE
	public void delete(String json) {
		Gson gson= new Gson();
		Factory factory = gson.fromJson(json, Factory.class);
		new FactoryDAO().delete(factory);
	}
	
	

}





























