package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import eventstickets.dao.EventInscriptionDAO;
import eventstickets.helpers.MessageHelper;
import eventstickets.models.Event;

@ManagedBean(name = "DashboardMB")
@RequestScoped
public class DashboardMB extends AuthenticateUser implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public DashboardMB() {
		if (params().get("denied") != null) {
			MessageHelper.addMensage("Você não tem permissão para acessar esta página.", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public List<Event> getEvents() {
		EventInscriptionDAO dao = new EventInscriptionDAO();
		return dao.getRegisteredEvents(getCurrentUser().getId());
	}
	
	private Map<String, String> params() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	}
}