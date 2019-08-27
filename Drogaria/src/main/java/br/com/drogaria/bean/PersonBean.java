package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.CityDAO;
import br.com.drogaria.dao.PersonDAO;
import br.com.drogaria.dao.StateDAO;
import br.com.drogaria.domain.City;
import br.com.drogaria.domain.Person;
import br.com.drogaria.domain.State;

@ManagedBean
@ViewScoped
public class PersonBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Person person;
	private PersonDAO dao;
	private CityDAO daoCity;
	private List<Person> persons;
	private List<City> citys;
	private List<State> states;
	private State state;
	private StateDAO daoState;
	
	public void init() {
		initAttributes();
		loadPersons();
	}
	
	private void loadPersons() {
		try {
			persons= dao.getAll();
			citys= new ArrayList<>(); //Ainda nao preciso popular as cidades já que nenhum estado foi selecionado ainda
			states= daoState.getAllOrdered("name");
		} catch (RuntimeException e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void initAttributes() {
		person= new Person();
		dao= new PersonDAO();
		daoCity= new CityDAO();
		daoState= new StateDAO();
	}
	
	public void cleanFields() {
		person= new Person();
	}
	
	public void save() {
		try {
			dao.merge(person);
			loadPersons();
			clearPosSave();
			Messages.addGlobalInfo("Successfully");
			System.out.println("Salvei");
		} catch (RuntimeException e) {
			Messages.addGlobalError(e.getMessage());
		}
	}

	private void clearPosSave() {
		person= new Person();
		state= new State(); 
		citys= new ArrayList<>();
	}
	
	public void loadCitys() {
		try {
			if (state != null) {
				citys= daoCity.getAllFiltered(state);
			}else {
				citys= new ArrayList<>(); //P nao ficar com a lista da última cidade
			}
		} catch (RuntimeException e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void update(Person p) {
		this.person= p;
		this.state= this.person.getCity().getState();
		loadCitys();
//		this.citys.add(p.getCity());
	}
	
	public void delete(Person p) {
		try {
			dao.delete(p);
			loadPersons();
			Messages.addGlobalInfo("Successfully");
		} catch (RuntimeException e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
