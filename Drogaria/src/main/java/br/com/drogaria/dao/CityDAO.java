package br.com.drogaria.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.drogaria.domain.City;
import br.com.drogaria.domain.State;

public class CityDAO extends GenericDAO<City>{

	//Funcionalidade especifica da cidade
	public List<City> getAllFiltered(State state) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			return entityManager.createQuery("SELECT c FROM City c where c.state= :state", City.class).setParameter("state", state).getResultList();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

}
