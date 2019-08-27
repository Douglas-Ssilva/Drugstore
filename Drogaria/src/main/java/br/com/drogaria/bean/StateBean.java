package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.StateDAO;
import br.com.drogaria.domain.State;

@ManagedBean
@ViewScoped
public class StateBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private State state;
	private StateDAO dao;
	private List<State> states;
	
	public void save() {
		try {
			dao.merge(state);
		    preparedState();
		    fillListState();
			Messages.addGlobalInfo("Successfully"); //Usando Omnifaces
		} catch (RuntimeException e) {
			Messages.addGlobalError("System temporarily unavailable"); //Usando Omnifaces
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void fillListState() {
		dao= new StateDAO();
		setStates(dao.getAll());
	}
	
	public void delete(State state) {
		System.out.println("Excluindo: "+ state);
		try {
			dao.delete(state);
		    fillListState();
			Messages.addGlobalInfo("State deleted successfully"); //Usando Omnifaces
		} catch (RuntimeException e) {
			Messages.addGlobalError("System temporarily unavailable"); //Usando Omnifaces
			e.printStackTrace();
		}
	}
	
	public void update(State state) {
		this.state= state;
	}
	
	public void toUpperCase() {
		this.state.setInitials(state.getInitials().toUpperCase());
	}
	
	public void preparedState() {
		state= new State();
		dao= new StateDAO();
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
}
