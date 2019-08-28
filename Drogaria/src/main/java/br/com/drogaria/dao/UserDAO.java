package br.com.drogaria.dao;

import javax.persistence.EntityManager;

import org.apache.shiro.crypto.hash.SimpleHash;

import br.com.drogaria.domain.User;

public class UserDAO extends GenericDAO<User>{
	
	public User authentication(String cpf, String password) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		User user;
		try {
			SimpleHash hash= new SimpleHash("md5", password);
			user = entityManager.createQuery("SELECT u FROM User u where u.person.cpf= :cpf and u.password= :password", User.class)
					.setParameter("cpf", cpf).setParameter("password", hash.toHex()).getSingleResult();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
		return user;
	}

}
