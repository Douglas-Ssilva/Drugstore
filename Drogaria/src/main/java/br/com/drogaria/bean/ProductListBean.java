package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.ProductDAO;
import br.com.drogaria.domain.Product;

@ManagedBean
@RequestScoped
public class ProductListBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Product> products;
	private ProductDAO dao;
	
	public ProductListBean() {
		System.out.println("Construtor");
	}

	@PostConstruct //é executado depois do construtor da página
	public void init() {
		System.out.println("PostContruct");
		dao= new ProductDAO();
	}
	
	public void getAll() {
		try {
			products= dao.getAll();
		} catch (RuntimeException e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
