package eventstickets.controllers;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import eventstickets.models.User;

public class AuthenticateUser {
	private User currentUser;
	
	public AuthenticateUser() {
		if (getSession().getAttribute("currentUser") == null) {
		    try {
		    	String uri = "../login.jsp";
				FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		currentUser = (User) getSession().getAttribute("currentUser");
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	private HttpSession getSession() {
       return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
   }
}
