package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.FactoryDAO;
import br.com.drogaria.dao.ProductDAO;
import br.com.drogaria.domain.Factory;
import br.com.drogaria.domain.Product;

@ManagedBean
@ViewScoped
public class ProductBeanEdition implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idProduct;
	private String action;
	private Product product;
	private ProductDAO dao;
	private List<Factory> factorys;
	private FactoryDAO daoFactory;
	
	@PostConstruct
	public void init() {
		product= new Product();
		daoFactory= new FactoryDAO();
		dao= new ProductDAO();
	}
	
	public void fillListFactory() {
		try {
			factorys= daoFactory.getAllOrdered("name");
			if(idProduct != null) {
				product= dao.findById(idProduct);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void save() {
		try {
			dao.merge(product);
			product= new Product();
			factorys= daoFactory.getAllOrdered("name");
			Messages.addGlobalInfo("Successfully");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public String delete() {
		try {
			dao.delete(product);
			Messages.addGlobalInfo("Successfully");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
		return "/pages/products-list.xhtml?faces-redirect=true";
	}
	
	public Long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Factory> getFactorys() {
		return factorys;
	}
	public void setFactorys(List<Factory> factorys) {
		this.factorys = factorys;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
