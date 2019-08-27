package br.com.drogaria.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.drogaria.dao.JPAUtil;

public class ContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent event) {
		JPAUtil.getEntityManager();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		JPAUtil.getEntityManager().close();
	}

}
