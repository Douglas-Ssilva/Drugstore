package br.com.drogaria.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.ClientDAO;
import br.com.drogaria.dao.EmployeeDAO;
import br.com.drogaria.dao.ProductDAO;
import br.com.drogaria.dao.SaleDAO;
import br.com.drogaria.domain.Client;
import br.com.drogaria.domain.Employee;
import br.com.drogaria.domain.ItemSale;
import br.com.drogaria.domain.Product;
import br.com.drogaria.domain.Sale;

@ManagedBean
@ViewScoped
public class SaleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Product> products;
	private List<ItemSale> sales;
	private List<Employee> employees;
	private List<Client> clients;
	private Sale sale;

	@PostConstruct
	public void init() {
		System.out.println("Chamando init");
		sale = new Sale();
		sale.setAmount(new BigDecimal("0.00"));
		initializeAttributes();

		try {
			setProducts(new ProductDAO().getAllOrdered("name"));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public void finalizeSale() {
		try {
			sale.setHour(new Date());
			employees= new EmployeeDAO().getAllOrdered("person.name");
			clients= new ClientDAO().getAllOrdered("person.name");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	public void save() {
		try {
			new SaleDAO().save(sale, sales); //Como a sale a partir de salver encontra-se no estado manager, consigo o id dela
			clearDialog();
			Messages.addGlobalInfo("Sale saved successfully");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError(e.getMessage());
		}
	}

	private void clearDialog() {
		sale= new Sale();
		employees= new ArrayList<>();
		clients= new ArrayList<>();
		sales= new ArrayList<>();
		sale.setAmount(new BigDecimal("0.00"));
	}
	
	private void initializeAttributes() {
		sales = new ArrayList<>();
	}

	public void addProductsToCart(Product product) {
		byte index = -1;

		for (byte i = 0; i < sales.size(); i++) {
			if (sales.get(i).getProduct().equals(product)) {
				index = i;
			}
		}

		if (index < 0) {
			ItemSale itemSale = new ItemSale();
			itemSale.setPartialValue(product.getPrice());
			itemSale.setProduct(product);
			itemSale.setQuantity(new Short("1"));

			sales.add(itemSale);
		} else {
			ItemSale itemSale = sales.get(index);
			itemSale.setQuantity(new Short((itemSale.getQuantity() + 1) + "")); // Em java, qualquer somatório vira int
			itemSale.setPartialValue(itemSale.getProduct().getPrice().multiply(new BigDecimal(itemSale.getQuantity())));
		}

		calculateAmount();
		Messages.addGlobalInfo("Product successfully added to shopping cart");
	}

	private void calculateAmount() {
		sale.setAmount(new BigDecimal("0.00")); //A fim de nao fazer calculo com objeto já calculado anteriormente
		
		for (int i = 0; i < sales.size(); i++) {
			ItemSale itemSale = sales.get(i);
			sale.setAmount(sale.getAmount().add(itemSale.getPartialValue()));
		}
	}

	public void delete(ItemSale item) {
		byte index = -1;

		for (byte i = 0; i < sales.size(); i++) {
			if (sales.get(i).getProduct().equals(item.getProduct())) {
				index = i;
			}
		}

		if (index > -1) {
			ItemSale itemSale = sales.get(index);
			itemSale.setQuantity(new Short((itemSale.getQuantity() - 1) + ""));
			itemSale.setPartialValue(itemSale.getPartialValue().subtract(item.getProduct().getPrice()));

			if (itemSale.getQuantity() < 1) {
				sales.remove(index);
			}
			calculateAmount();
		}

		Messages.addGlobalInfo("Product removed to shopping cart");
	}

	public void deleteAll(ItemSale item) {
		for (byte i = 0; i < sales.size(); i++) {
			if (sales.get(i).getProduct().equals(item.getProduct())) {
				sales.remove(i);
				calculateAmount();
				Messages.addGlobalInfo("Product removed to shopping cart");
			}
		}
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<ItemSale> getSales() {
		return sales;
	}

	public void setSales(List<ItemSale> sales) {
		this.sales = sales;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}


}
