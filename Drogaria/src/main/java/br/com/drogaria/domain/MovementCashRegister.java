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
public class MovementCashRegister extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable= false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date hour;
	
	@Column(nullable= false, precision= 7, scale= 2)
	private BigDecimal valueMovement;
	
	@ManyToOne
	@JoinColumn(nullable= false)
	private CashRegister cashRegister;

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public BigDecimal getValueMovement() {
		return valueMovement;
	}

	public void setValueMovement(BigDecimal valueMovement) {
		this.valueMovement = valueMovement;
	}
	
}
