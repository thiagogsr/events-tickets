package eventstickets.controllers;

import java.io.IOException;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import eventstickets.models.User;

public class AuthenticateUser {
	private User currentUser;
	
	protected AuthenticateUser() {
		if (getSession().getAttribute("currentUser") == null) {
		    try {
				redirect("../login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		currentUser = (User) getSession().getAttribute("currentUser");
	}
	
	protected Map<String, String> params() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	}
	
	protected User getCurrentUser() {
		return currentUser;
	}
	
	protected void checkPermission(boolean allowed) {
		if (!allowed) {
			try {
				redirect("../dashboard/index.xhtml?denied=true");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void redirect(String path) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(path);
	}
	
	protected HttpSession getSession() {
       return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
   }
}
