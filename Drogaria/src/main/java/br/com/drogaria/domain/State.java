package br.com.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class State extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable= false, length= 2)
	private String initials;

	@Column(nullable= false, length= 50)
	private String name;

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "State [initials=" + initials + ", name=" + name + "]";
	}
	
	
}
