package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import eventstickets.dao.EventDAO;
import eventstickets.dao.EventInscriptionsDAO;
import eventstickets.helpers.MessageHelper;
import eventstickets.models.Event;
import eventstickets.models.EventInscriptions;

@ManagedBean(name = "eventInscriptionsMB")
@RequestScoped
public class EventInscriptionsMB extends AuthenticateUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private EventInscriptions eventInscriptions = new EventInscriptions();
	private Integer eventId;
	
	public void create(){
		EventInscriptionsDAO dao = new EventInscriptionsDAO();
		Event event = getEvent();
		if(dao.totalSubscribersEvent(event.getId()) < event.getInscriptionsLimit()){
			eventInscriptions.setUser(getCurrentUser());
			eventInscriptions.setEvent(event);
			dao.create(eventInscriptions);
			MessageHelper.addMensage("Inscrição no evento " + event.getTitle() + " realizada com sucesso.", FacesMessage.SEVERITY_INFO);
		} else {
			MessageHelper.addMensage("Não há mais vagas no evento " + event.getTitle() + ".", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public List<Event> getEvents() {
		EventInscriptionsDAO dao = new EventInscriptionsDAO();
		return dao.getEventByUserId(getCurrentUser().getId());
	}
	
	private Event getEvent(){
		return new EventDAO().find(eventId);
	}
	
	public EventInscriptions getEventInscriptions() {
		return eventInscriptions;
	}
	
	public void setEventInscriptions(EventInscriptions eventInscriptions) {
		this.eventInscriptions = eventInscriptions;
	}
	
	public Integer getEventId() {
		return eventId;
	}
	
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
}
