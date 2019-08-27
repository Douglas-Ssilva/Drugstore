package br.com.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Person extends GenericDomain{
	
	private static final long serialVersionUID = 1L;

	@Column(length= 100, nullable= false)
	private String name;
	
	@Column(length= 14, nullable= false)
	private String cpf;
	
	@Column(length= 14)
	private String rg;
	
	@Column(length= 50)
	private String street;
	
	@Column(length= 14)
	private Short number;
	
	@Column(length= 50)
	private String neighborhood;
	
	@Column(length= 50)
	private String complement;
	
	@Column(length= 14)
	private String cep;
	
	@Column(length= 14)
	private String phone;
	
	@Column(length= 30)
	private String email;
	
	@OneToOne
	@JoinColumn(nullable= false)
	private City city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Short getNumber() {
		return number;
	}

	public void setNumber(Short number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}
