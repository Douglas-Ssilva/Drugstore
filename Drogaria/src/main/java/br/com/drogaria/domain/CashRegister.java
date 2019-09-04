package br.com.drogaria.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CashRegister extends GenericDomain { //Controla abertura e fechamento do caixa
	
	private static final long serialVersionUID = 1L;

	@Column(nullable= false, unique= true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date openingDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date closingDate;
	
	@Column(nullable= false, precision= 7, scale= 2)
	private BigDecimal valueOpening;
	
	@ManyToOne
	@JoinColumn(nullable= false)
	private User user;
	
	
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	public Date getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
	public BigDecimal getValueInput() {
		return valueOpening;
	}
	public void setValueInput(BigDecimal valueInput) {
		this.valueOpening = valueInput;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
