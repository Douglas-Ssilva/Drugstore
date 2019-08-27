package br.com.drogaria.dao;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

public class JPAUtil {
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			entityManagerFactory= Persistence.createEntityManagerFactory("Drogaria");
		}
		return entityManagerFactory.createEntityManager();
	}
	
//	public static Connection getConnection() {
//	}
}
