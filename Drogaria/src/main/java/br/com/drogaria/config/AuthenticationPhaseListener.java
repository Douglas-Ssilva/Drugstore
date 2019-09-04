package br.com.drogaria.config;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.drogaria.bean.UserAuthenticationBean;
import br.com.drogaria.domain.User;

public class AuthenticationPhaseListener implements PhaseListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		System.out.println("Depois de : " + event.getPhaseId());
		
		String currentPage = Faces.getViewId();
		boolean isCurrentPage= currentPage.contains("/pages/page-initial.xhtml");
		
		if(!isCurrentPage) {
			UserAuthenticationBean user = (UserAuthenticationBean) Faces.getSessionAttribute("userAuthenticationBean");//Pegando o user na sessão com omnifaces
			
			System.out.println("User: " + user);
			if(user == null) {
				Faces.navigate("/pages/page-initial.xhtml");
				return;
			}
			
			User user2 = user.getUser();
			System.out.println("User2: " + user2);
			if(user2.getId() == null) {
				Faces.navigate("/pages/page-initial.xhtml");
				return;
			}
			
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
//		System.out.println("Antes de : " + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
