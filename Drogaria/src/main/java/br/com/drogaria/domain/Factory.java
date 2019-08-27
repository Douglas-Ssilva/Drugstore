package br.com.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Factory extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable= false, length= 80)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
