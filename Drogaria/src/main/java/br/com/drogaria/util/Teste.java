package br.com.drogaria.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.drogaria.dao.CityDAO;
import br.com.drogaria.dao.ClientDAO;
import br.com.drogaria.dao.FactoryDAO;
import br.com.drogaria.dao.JPAUtil;
import br.com.drogaria.dao.PersonDAO;
import br.com.drogaria.dao.ProductDAO;
import br.com.drogaria.dao.SingleConnection;
import br.com.drogaria.dao.StateDAO;
import br.com.drogaria.dao.UserDAO;
import br.com.drogaria.domain.City;
import br.com.drogaria.domain.Client;
import br.com.drogaria.domain.Factory;
import br.com.drogaria.domain.Person;
import br.com.drogaria.domain.Product;
import br.com.drogaria.domain.State;
import br.com.drogaria.domain.User;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

public class Teste {

	@Test
	@Ignore
	public void testJunit() {
		JPAUtil.getEntityManager();
		JPAUtil.getEntityManager().close();
	}

	@Test
	@Ignore
	public void saveState() {
		State state = new State();
		state.setInitials("MG");
		state.setName("Minas Gerais");
		new StateDAO().save(state);
	}

	@Test
	@Ignore
	public void getAllState() {
		State state = new StateDAO().findById(1L);
		state.setInitials("MG");
		new StateDAO().update(state);
	}

	@Test
	@Ignore
	public void testFactory() {
		Factory factory2 = new Factory();
		factory2.setName("Phase");
		new FactoryDAO().save(factory2);
	}

	@Test
	@Ignore
	public void testClient() {
		State state = new StateDAO().findById(1L);
		City city = new City();
		city.setState(state);
		city.setName("Ribeirão das Neves");
		new CityDAO().save(city);
	}

	@Test
	public void testandoList() {
		List<City> list = new CityDAO().getAll();
		for (City city : list) {
			System.out.println(city.getState().getName());
		}
	}

	@Test
	@Ignore
	public void testeProduct() {
		Product product = new Product();
		product.setFactory(new FactoryDAO().findById(6L));
		product.setName("Ômega 3");
		product.setPrice(new BigDecimal("150.0"));
		product.setQuantity(new Short("100"));
		new ProductDAO().save(product);
	}

	@Test
	@Ignore
	public void person() {
		Person person = new Person();
		person.setCep("33940-130");
		person.setCity(new CityDAO().findById(3L));
		person.setComplement("Casa");
		person.setCpf("111.111.111-55");
		person.setEmail("douglas@email.com");
		person.setName("Douglas");
		person.setNeighborhood("Granjas Primavera");
		person.setNumber(new Short("825"));
		person.setPhone("3333-3333");
		person.setRg("111.111.11");
		person.setStreet("Viena");
		new PersonDAO().save(person);
	}

	@Test
	@Ignore
	public void cliet() throws ParseException {
		Client client = new Client();
//		client.setDateRegistration(new Date());
		client.setDateRegistration(new SimpleDateFormat("dd/MM/yyyy").parse("09/05/2019"));
		client.setPerson(new PersonDAO().findById(11L));
		client.setReleased(true);
		new ClientDAO().save(client);
	}
	// 164

	@Test@Ignore
	public void user() {
		User user = new User();
		user.setActive(true);
		user.setPassword("123");
		user.setPerson(new PersonDAO().findById(11L));
		user.setTypeUser('A');
		new UserDAO().save(user);
	}
	
	@Test @Ignore
	public void list() {
			
	}
	
}
































