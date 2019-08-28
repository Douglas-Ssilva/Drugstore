package br.com.drogaria.dao;

import java.util.Date;

import javax.persistence.EntityManager;

import br.com.drogaria.domain.CashRegister;

public class CashRegisterDAO extends GenericDAO<CashRegister>{
	
	public CashRegister getCashRegisterByDate(Date date) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		CashRegister cashRegister;
		try {
			cashRegister = (CashRegister) entityManager.createQuery("SELECT c FROM CashRegister c where c.openingDate= :dateOpening")
					.setParameter("dateOpening", date).getSingleResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			
		}
		return cashRegister;
	}

}
