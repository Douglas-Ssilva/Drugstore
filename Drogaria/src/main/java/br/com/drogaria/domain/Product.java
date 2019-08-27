package br.com.drogaria.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Product extends GenericDomain{

	private static final long serialVersionUID = 1L;
	@Column(length= 80, nullable= false)
	private String name;
	
	@Column(nullable= false)
	private Short quantity;
	
	@Column(nullable= false, precision= 6, scale= 2) //6 numeros no total com 2 casas pós vírgula
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(nullable= false)
	private Factory factory;
	
	@Transient //Armazenando caminho da foto temp, ajuda caso mudemos de diretório
	private String wayImage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getQuantity() {
		return quantity;
	}

	public void setQuantity(Short quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Factory getFactory() {
		return factory;
	}

	public void setFactory(Factory factory) {
		this.factory = factory;
	}

	public String getWayImage() {
		return wayImage;
	}

	public void setWayImage(String wayImage) {
		this.wayImage = wayImage;
	}
}
