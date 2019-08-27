package br.com.drogaria.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Client extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable= false)
	@Temporal(TemporalType.DATE)
	private Date dateRegistration;
	
	@Column(nullable= false)
	private Boolean released;// a fim de bloquear o acesso em caso de venda a prazo e nao pagar.
	
	@OneToOne
	@JoinColumn(nullable= false) //Não tenho clientes sem ter o cadastro da pessoa
	private Person person;

	public Date getDateRegistration() {
		return dateRegistration;
	}

	public void setDateRegistration(Date dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	public Boolean getReleased() {
		return released;
	}

	public void setReleased(Boolean released) {
		this.released = released;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}
