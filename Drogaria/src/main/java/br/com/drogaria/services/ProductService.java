package br.com.drogaria.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.com.drogaria.dao.ProductDAO;
import br.com.drogaria.domain.Product;

@Path("product")
public class ProductService {
	
	@GET
	public String getAll() {
		return new Gson().toJson(new ProductDAO().getAll());
	}
	
	@POST
	public String save(String json) {
		Gson gson = new Gson();
		Product product = gson.fromJson(json, Product.class);
		Product product2 = new ProductDAO().merge(product);
		String json2 = gson.toJson(product2);
		return json2;
	}

}
