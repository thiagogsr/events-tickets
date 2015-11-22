package eventstickets.controllers;

import java.io.Serializable;
import java.util.HashMap;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import eventstickets.policies.EventPolicy;
import eventstickets.policies.MiniCoursePolicy;
import eventstickets.policies.PlacePolicy;
import eventstickets.policies.TalkPolicy;
import eventstickets.policies.UserPolicy;

@ManagedBean(name="navBarMB")
@RequestScoped
public class NavbarMB extends AuthenticateUser implements Serializable {
	private static final long serialVersionUID = 1L;

	public HashMap<String, String> getNavItems() {
		HashMap<String, String> all = new HashMap<String, String>();
		if (EventPolicy.register(getCurrentUser())) {
			all.put("../eventsInscriptions/index.xhtml", "Inscreva-se");
		}
		if (EventPolicy.index(getCurrentUser())) {
			all.put("../events/index.xhtml", "Eventos");
		}
		if (TalkPolicy.index(getCurrentUser())) {
			all.put("../talks/index.xhtml", "Palestras");
		}
		if (MiniCoursePolicy.index(getCurrentUser())) {
			all.put("../miniCourses/index.xhtml", "Mini Cursos");
		}
		if (PlacePolicy.index(getCurrentUser())) {
			all.put("../places/index.xhtml", "Lugares");
		}
		if (UserPolicy.index(getCurrentUser())) {
			all.put("../users/index.xhtml", "Usuários");
		}
		return all;
	}
}
