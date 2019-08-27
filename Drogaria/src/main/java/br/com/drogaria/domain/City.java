package br.com.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class City extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	@Column(length= 80, nullable= false)			
	private String name;
	
	@ManyToOne
	@JoinColumn(nullable= false)
	private State state;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
}
