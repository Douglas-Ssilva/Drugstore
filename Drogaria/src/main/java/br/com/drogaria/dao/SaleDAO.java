package br.com.drogaria.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.drogaria.domain.ItemSale;
import br.com.drogaria.domain.Product;
import br.com.drogaria.domain.Sale;

public class SaleDAO extends GenericDAO<Sale> {
	
	private String outOfStockProduct;

	public void save(Sale sale, List<ItemSale> items) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			if(verifyQuantityStock(items)) {
				entityManager.persist(sale);

				for (ItemSale item : items) {
					item.setSale(sale);
					entityManager.persist(item);

					// Dando baixa no estoque
					Product product = item.getProduct();
					int quantityResult = product.getQuantity() - item.getQuantity();

//					if (quantityResult >= 0) {
						product.setQuantity(new Short(quantityResult + ""));
						entityManager.merge(product);
//					}
				}
			}  else {
				throw new RuntimeException("O " + outOfStockProduct + " não possui quantidade suficiente em nossos estoques."); // Repropagando o error
			}
			entityManager.getTransaction().commit();
			
		} catch (RuntimeException e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e; 
		} finally {
			entityManager.close();
		}
	}

	private boolean verifyQuantityStock(List<ItemSale> items) {
		for (ItemSale i : items) {
			Product product = i.getProduct();
			int quantity= product.getQuantity() - i.getQuantity();
			
			if(quantity < 0) {
				outOfStockProduct= product.getName();
				return false;
			}
		}
		return true;
	}

}
