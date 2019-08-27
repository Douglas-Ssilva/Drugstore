package br.com.drogaria.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employee extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable= false, length= 50)
	private String workCard;
	
	@Column(nullable= false)
	@Temporal(TemporalType.DATE)
	private Date dateAdmission;
	
	@OneToOne
	@JoinColumn(nullable= false)
	private Person person;

	public String getWorkCard() {
		return workCard;
	}

	public void setWorkCard(String workCard) {
		this.workCard = workCard;
	}

	public Date getDateAdmission() {
		return dateAdmission;
	}

	public void setDateAdmission(Date dateAdmission) {
		this.dateAdmission = dateAdmission;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
