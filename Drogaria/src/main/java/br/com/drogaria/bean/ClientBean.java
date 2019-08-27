package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;

import br.com.drogaria.dao.ClientDAO;
import br.com.drogaria.dao.PersonDAO;
import br.com.drogaria.domain.Client;
import br.com.drogaria.domain.Person;

@ManagedBean
@ViewScoped
public class ClientBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Client client;
	private List<Client> clients;
	private List<Person> persons;
	private ClientDAO dao;
	private PersonDAO daoPerson;

	public void init() {
		loadClients();
	}

	private void loadClients() {
		dao= new ClientDAO();
		clients= dao.getAll();
		System.out.println("Lista: " + clients.size());
	}
	
	public void loadPersons() {
		daoPerson = new PersonDAO();
		setPersons(daoPerson.getAllOrdered("name"));
		initializeAttributes();
	}
	
	public void save() {
		try {
			System.out.println("Salvando client");
			dao.merge(client);
			initializeAttributes();
			loadClients();
			Messages.addGlobalInfo("Successfully");
		} catch (RuntimeException e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void update(Client client) {
		this.client= client;
		this.persons= new ArrayList<>();
		this.persons.add(client.getPerson());
	}


	public void delete(Client c) {
		try {
			//Atrasando a ação p gif ficar nitido
			for(int i= 0; i< 30000; i++) {
				System.out.println(i);
			}
			dao.delete(c);
			loadClients();
			Messages.addGlobalInfo("Successfully");
			
			clearAllFilters();
		} catch (RuntimeException e) {
			Messages.addGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void clearAllFilters() {

	    DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formClientValues:dataClient");
	    if (!dataTable.getFilters().isEmpty()) {
	        dataTable.reset();

//	        RequestContext requestContext = RequestContext.getCurrentInstance();
//	        requestContext.update("formClientValues:dataClient");
	        PrimeFaces.current().ajax().update("formClientValues:dataClient");
	    }
	}

	private void initializeAttributes() {
		client= new Client();
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
}
