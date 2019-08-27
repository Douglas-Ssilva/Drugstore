package br.com.drogaria.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

public class GenericDAO<T> {

	private Class<T> classe;
	
	//Usando Reflection p pegar a subclass a fim de buscar todos objetos
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]; 
	}

	public void save(T t) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(t);
			entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
			entityManager.getTransaction().rollback();
			throw e; // propagando a exception
		} finally {
			entityManager.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			return entityManager.createQuery("SELECT t FROM "+ classe.getCanonicalName() + " t ").getResultList();
		} catch (RuntimeException e) {
			throw e; 
		} finally {
			entityManager.close();
		}
	}
	
	public T findById(Long id) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		return entityManager.find(classe, id);
	}
	
	public void delete(T t) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(t));
			entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
			entityManager.getTransaction().rollback();
			throw e; // propagando a exception
		} finally {
			entityManager.close();
		}
	}
	
	public void update(T t) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(t);
			entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
			entityManager.getTransaction().rollback();
			throw e; // propagando a exception
		} finally {
			entityManager.close();
		}
	}
	
	public T merge(T t) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			T entity= entityManager.merge(t);
			entityManager.getTransaction().commit();
			return entity;							//return key para dar nome a img
		} catch (RuntimeException e) {
			entityManager.getTransaction().rollback();
			throw e; // propagando a exception
		} finally {
			entityManager.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllOrdered(String field){
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			return entityManager.createQuery("SELECT t FROM "+ classe.getCanonicalName() + " t ORDER BY t." + field + "").getResultList();
		} catch (RuntimeException e) {
			throw e; 
		} finally {
			entityManager.close();
		}
	}
	

}




















