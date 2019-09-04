package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.drogaria.dao.CashRegisterDAO;
import br.com.drogaria.dao.UserDAO;
import br.com.drogaria.domain.CashRegister;
import br.com.drogaria.domain.User;

@ManagedBean
@ViewScoped
public class CashBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ScheduleModel scheduleModel;
	private CashRegister cash;
	private CashRegisterDAO dao;
	private List<User> users;
	
	@PostConstruct
	public void init() {
		scheduleModel= new DefaultScheduleModel();
		cash= new CashRegister();
		dao= new CashRegisterDAO();
	}
	
	public void openDialog(SelectEvent selectEvent) {
		try {
			cash.setOpeningDate((Date)selectEvent.getObject());
			users= new UserDAO().getAll();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(cash.getOpeningDate());
			calendar.add(Calendar.DATE, 1);
			cash.setOpeningDate(calendar.getTime());
			dao.save(cash);
			cash= new CashRegister();
			Messages.addGlobalInfo("Successfully");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public ScheduleModel getScheduleModel() {
		return scheduleModel;
	}
	public void setScheduleModel(ScheduleModel scheduleModel) {
		this.scheduleModel = scheduleModel;
	}

	public CashRegister getCash() {
		return cash;
	}

	public void setCash(CashRegister cash) {
		this.cash = cash;
	}

	public List<User> getUsers() {
		return users;
	}
}
