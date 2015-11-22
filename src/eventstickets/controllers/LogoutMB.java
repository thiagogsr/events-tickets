package eventstickets.controllers;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "logoutMB")
@RequestScoped
public class LogoutMB extends AuthenticateUser implements Serializable {
	private static final long serialVersionUID = 1L;

	public void logout() throws IOException {
		getSession().invalidate();
		redirect("../login.jsp");
	}
}
