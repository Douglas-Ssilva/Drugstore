package br.com.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class User extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable= false, length= 32) //usando MD5 a fim de criptografar a senha
	private String password;
	
	@Column(nullable= false)
	private Character typeUser;
	
	@Column(nullable= false)
	private Boolean active; //A fim de bloquear acesso do user
	
	@OneToOne
	@JoinColumn(nullable= false)
	private Person person;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Character getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(Character typeUser) {
		this.typeUser = typeUser;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	@Transient//Hibernate, nao salve esse cara no BD, servirá apenas p mostrar no dataTable
	public String getReleasedFormat() {
		String released= "Sim";
		if(!active) {
			released= "Não";
		}
		return released;
	}
	
	@Transient//Hibernate, nao salve esse cara no BD, servirá apenas p mostrar no dataTable
	public String getTypePersonFormat() {
		String typePerson= "Administrador";
		
		if(typeUser == 'M') {
			typePerson= "Manager";
		}else if(typeUser == 'E') {
			typePerson= "Employee";
		}
		return typePerson;
	}
}
























