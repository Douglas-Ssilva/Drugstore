package br.com.drogaria.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.drogaria.dao.CityDAO;
import br.com.drogaria.domain.City;

@Path("city")
public class CityService {
	
	@GET
	@Path("{id}")
	public String getAll(@PathParam("id") Long idState){
		List<City> citys = new CityDAO().findByState(idState);
		return new Gson().toJson(citys).toString();
	}

}
