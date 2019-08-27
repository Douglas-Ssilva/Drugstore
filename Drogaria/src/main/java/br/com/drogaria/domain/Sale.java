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
public class Sale extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable= false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date hour;
	
	@Column(nullable= false, precision= 7, scale= 2)
	private BigDecimal amount;		//Para efeitos históris é bom guardar o total, já que pode aumentar valor
	
	@ManyToOne //venda com client é a prazo, sem cliente é a vista
	private Client client; //p venda a vista, iremos deixar esse cara como null, assim sei que a venda foi a vista
	
	@ManyToOne
	@JoinColumn(nullable= false)
	private Employee employee;

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
