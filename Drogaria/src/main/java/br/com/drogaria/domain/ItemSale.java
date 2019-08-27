package br.com.drogaria.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemSale extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable= false)
	private Short quantity;
	
	@Column(nullable= false, precision= 7, scale= 2)
	private BigDecimal partialValue;
	
	@ManyToOne
	@JoinColumn(nullable= false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(nullable= false)
	private Sale sale;

	public Short getQuantity() {
		return quantity;
	}

	public void setQuantity(Short quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPartialValue() {
		return partialValue;
	}

	public void setPartialValue(BigDecimal partialValue) {
		this.partialValue = partialValue;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
}
