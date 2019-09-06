package br.com.drogaria.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Menu extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable= false, length= 30)
	private String label;

	@Column(length= 50)
	private String wayURL;
	
	@OneToMany(fetch= FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(referencedColumnName= "id") //GenericDomain, tabelas separadas o Hibernate descobre sozinho
	private List<Menu> menus;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getWayURL() {
		return wayURL;
	}

	public void setWayURL(String wayURL) {
		this.wayURL = wayURL;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
}
