package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.HistoryDAO;
import br.com.drogaria.dao.ProductDAO;
import br.com.drogaria.domain.History;
import br.com.drogaria.domain.Product;

@ManagedBean
@ViewScoped
public class ProductBeanGetData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Product product;
	private History history;
	private Boolean displayPanelGridResult;
	
	@PostConstruct
	public void init() {
		product= new Product();
		history= new History();
		displayPanelGridResult= false;
	}
	
	public void getDataProduct() {
		try {
			Product result = new ProductDAO().findById(product.getId());
			if(result == null) {
				displayPanelGridResult= false;
				Messages.addGlobalWarn("Não há produtos cadastrados com esse código!");
			}else {
				displayPanelGridResult= true;
				product= result;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void saveHistory() {
		try {
			history.setHour(new Date());
			history.setProduct(product);
			new HistoryDAO().save(history);
			init();
			Messages.addGlobalInfo("History save successfully");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	public Boolean getDisplayPanelGridResult() {
		return displayPanelGridResult;
	}

	public void setDisplayPanelGridResult(Boolean displayPanelGridResult) {
		this.displayPanelGridResult = displayPanelGridResult;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

}
