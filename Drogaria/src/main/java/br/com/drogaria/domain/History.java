package br.com.drogaria.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class History extends GenericDomain{ 
	
	private static final long serialVersionUID = 1L;
	@Column(nullable= false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date hour;
	@Column(length= 500)
	private String comments;
	@ManyToOne
	@JoinColumn(nullable= false)
	private Product product;
	
	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}



}
