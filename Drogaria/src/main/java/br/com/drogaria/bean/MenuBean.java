package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import br.com.drogaria.dao.MenuDAO;
import br.com.drogaria.domain.Menu;

@ManagedBean
@SessionScoped
public class MenuBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private MenuModel menuModel;
	
	@PostConstruct
	public void init() {
		menuModel= new DefaultMenuModel();
		
		List<Menu> menus = new MenuDAO().getAll();
		for (Menu menu : menus) {
			if(menu.getWayURL() == null) {
				DefaultSubMenu defaultSubMenu = new DefaultSubMenu(menu.getLabel());
				
				for (Menu item : menu.getMenus()) {
					DefaultMenuItem defaultMenuItem = new DefaultMenuItem(item.getLabel());
					defaultMenuItem.setUrl(item.getWayURL());
					
					defaultSubMenu.addElement(defaultMenuItem);
				}
				menuModel.addElement(defaultSubMenu);
			}
		}
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

}
