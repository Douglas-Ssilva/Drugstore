package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.CityDAO;
import br.com.drogaria.dao.StateDAO;
import br.com.drogaria.domain.City;
import br.com.drogaria.domain.State;

@ManagedBean
@ViewScoped
public class CityBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private City city;
	private CityDAO dao;
	private StateDAO statesDAO;
	private List<City> citys;
	private List<State> states;
	
	
	public void init() {
		try {
			dao= new CityDAO();
			citys= dao.getAll();
			statesDAO= new StateDAO();
			setStates(statesDAO.getAll());
		} catch (RuntimeException e) {
			Messages.addGlobalInfo("Error in load Citys");
			e.printStackTrace();
		}
		
	}
	
	public void save() {
		try {
			dao.merge(city);
			clearFields();
			init();
			Messages.addGlobalInfo("Successfully");
		} catch (RuntimeException e) {
			Messages.addGlobalError("System temporarily unavailable");
			e.printStackTrace();
		}
	}
	
	public void delete(City city) {
		try {
			dao.delete(city);
			clearFields();
			init();
			Messages.addGlobalInfo("Successfully");
		} catch (RuntimeException e) {
			Messages.addGlobalError("System temporarily unavailable");
			e.printStackTrace();
		}
	}
	
	public void clearFields() {
		city= new City();
	}
	
	public void update(City city) {
		this.city= city;
	}
	
	public City getCity() {
		if(city == null) {
			city= new City();
		}
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public List<City> getCitys() {
		return citys;
	}
	public void setCitys(List<City> citys) {
		this.citys = citys;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

}
