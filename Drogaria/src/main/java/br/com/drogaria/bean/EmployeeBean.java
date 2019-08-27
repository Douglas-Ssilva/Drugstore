package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.EmployeeDAO;
import br.com.drogaria.dao.PersonDAO;
import br.com.drogaria.domain.Employee;
import br.com.drogaria.domain.Person;

@ManagedBean
@ViewScoped
public class EmployeeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Employee employee;
	private List<Employee> employees;
	private List<Person> persons;
	
	
	public void init() {
		try {
			employees= new EmployeeDAO().getAll();
		} catch (RuntimeException e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void preparedEmployee() {
		employee= new Employee();
		persons= new PersonDAO().getAllOrdered("name");
	}
	
	public void save() {
		try {
			new EmployeeDAO().merge(employee);
			init();
			employee= new Employee();
			Messages.addGlobalInfo("Successfully");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}
	public void delete(Employee employee) {
		try {
			new EmployeeDAO().delete(employee);
			init();
			Messages.addGlobalInfo("Successfully");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void update(Employee employee) {
		this.employee= employee;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
