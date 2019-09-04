package br.com.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.drogaria.dao.UserDAO;
import br.com.drogaria.domain.Person;
import br.com.drogaria.domain.User;

@ManagedBean
@SessionScoped
public class UserAuthenticationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;

	@PostConstruct
	public void init() {
		user = new User();
		user.setPerson(new Person());
	}

	public void logar() {
		try {
			user = new UserDAO().authentication(user.getPerson().getCpf(), user.getPassword());
			if (user != null) {
				Faces.redirect("./pages/states.xhtml");
			} else {
				Messages.addGlobalError("User or password invalids");
			}
		} catch (RuntimeException e) {
			Messages.addGlobalError("User or password invalids");
			e.printStackTrace();
		} catch (IOException e) {
			Messages.addGlobalError("Error ao fazer redirect");
			e.printStackTrace();
		}
	}

	public boolean havePermissions(List<String> permissions) { // Só aceita passar String
		if (user.getTypeUser() != null) {
			for (String p : permissions) {
				if (user.getTypeUser() == p.charAt(0)) {
					return true;
				}
			}
		}
		return false;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
