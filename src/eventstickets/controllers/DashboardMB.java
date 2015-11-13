package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import eventstickets.dao.EventInscriptionsDAO;
import eventstickets.models.Event;

@ManagedBean(name = "DashboardMB")
@RequestScoped
public class DashboardMB extends AuthenticateUser implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public List<Event> getEvents() {
		EventInscriptionsDAO dao = new EventInscriptionsDAO();
		return dao.getEventsRegisteredByUserId(getCurrentUser().getId());
	}
}