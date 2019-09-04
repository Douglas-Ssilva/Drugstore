package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.SaleDAO;
import br.com.drogaria.domain.Sale;

@ManagedBean
@ViewScoped
public class SaleWithItemBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Sale> sales;
	
	public void init() {
		try {
			sales= new SaleDAO().getAll();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public List<Sale> getSales() {
		return sales;
	}
	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}
	
	
	

}
