package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.drogaria.dao.FactoryDAO;
import br.com.drogaria.domain.Factory;

@ManagedBean
@ViewScoped
public class FactoryBean implements Serializable {

	private static final long serialVersionUID = 2624763985179260833L;
	private Factory factory;
	private FactoryDAO dao;
	private List<Factory> factorys;
	
	public void init() {
		dao= new FactoryDAO();
//		factorys= dao.getAll();
		fillFactorysService();
	}
	
	private void fillFactorysService() {
		try {
			Client client= ClientBuilder.newClient();
			WebTarget webTarget = client.target("http://127.0.0.1:8080/Drogaria/rest/factory");
			String json = webTarget.request().get(String.class);
			
			Gson gson= new Gson();
			Factory[] factories = gson.fromJson(json, Factory[].class); //Há outras formas construindo uma class
			factorys= Arrays.asList(factories);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
//			dao.merge(factory);
			System.out.println("Chamando save");
			Client client= ClientBuilder.newClient();
			WebTarget webTarget = client.target("http://127.0.0.1:8080/Drogaria/rest/factory");
			webTarget.request().post(Entity.json(new Gson().toJson(factory)));
			
			fillFactorysService();
			clearFields();
			Messages.addGlobalInfo("Saving via Service");
		} catch (RuntimeException e) {
			Messages.addGlobalInfo("System temporarily unavailable");
			e.printStackTrace();
		}
	}
	
	public void delete(Factory f) {
		try {
//			dao.delete(f);
			System.out.println("Deletando");
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target("http://127.0.0.1:8080/Drogaria/rest/factory");
			WebTarget webTarget2 = webTarget.path("{id}").resolveTemplateFromEncoded("id", f.getId()); //resolve converter " p um valor válido
			webTarget2.request().delete();
			init();
			Messages.addGlobalInfo("Successfully");
		} catch (RuntimeException e) {
			Messages.addGlobalInfo("System temporarily unavailable");
			e.printStackTrace();
		}
	}
	
	public void clearFields() {
		factory= new Factory();
	}
	
	public Factory getFactory() {
		if(factory == null) {
			factory= new  Factory();
		}
		return factory;
	}
	
	public void setFactory(Factory factory) {
		this.factory = factory;
	}
	
	public List<Factory> getFactorys() {
		return factorys;
	}
	
	public void setFactorys(List<Factory> factorys) {
		this.factorys = factorys;
	}
}
