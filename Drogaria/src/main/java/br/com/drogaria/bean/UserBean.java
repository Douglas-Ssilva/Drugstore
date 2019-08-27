package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;

import br.com.drogaria.dao.PersonDAO;
import br.com.drogaria.dao.UserDAO;
import br.com.drogaria.domain.Person;
import br.com.drogaria.domain.User;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	private UserDAO dao;
	private PersonDAO daoPerson;
	private List<User> users;
	private List<Person> persons;
	
	public void init() {
		initAttributes();
		users= dao.getAll();
	}
	
	public void update(User u) {
		this.user= u;
	}
	
	public void save() {
		try {
			dao.merge(user);
			init();
			verifyIfUpdate(user);
			newUser();
			Messages.addGlobalInfo("Successfully");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	private void verifyIfUpdate(User user2) {
		if (user2.getId() != null) {
			clearFieldsDataTable();
		}
	}

	public void delete(User u) {
		try {
			dao.delete(u);
			init();
			clearFieldsDataTable();
			Messages.addGlobalInfo("Successfully");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}

	private void clearFieldsDataTable() {
		DataTable dataTable= (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formUser:dataUser");
		if(!dataTable.getFilters().isEmpty()) {
			dataTable.reset();
			
			PrimeFaces.current().ajax().update("formUser:dataUser");
		}
	}

	private void initAttributes() {
		dao= new UserDAO();
	}
	
	public void newUser() {
		user= new User();
	}
	
	public void addUser() {
		user= new User();
		daoPerson= new PersonDAO();
		persons= daoPerson.getAllOrdered("name");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
}
