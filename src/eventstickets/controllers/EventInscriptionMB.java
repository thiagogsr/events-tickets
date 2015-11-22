package eventstickets.controllers;


import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import eventstickets.dao.EventDAO;
import eventstickets.dao.EventInscriptionDAO;
import eventstickets.helpers.MessageHelper;
import eventstickets.models.Event;
import eventstickets.models.EventInscription;
import eventstickets.policies.EventPolicy;

@ManagedBean(name = "eventInscriptionMB")
@RequestScoped
public class EventInscriptionMB extends AuthenticateUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private EventInscription eventInscription = new EventInscription();
	private Integer eventId;
	
	public EventInscriptionMB() {
		new AuthenticateUser();
		checkPermission(EventPolicy.register(getCurrentUser()));
	}
	
	public void create(){
		EventInscriptionDAO dao = new EventInscriptionDAO();
		Event event = getEvent();
		if(dao.totalSubscribersEvent(event.getId()) < event.getInscriptionsLimit()){
			eventInscription.setUser(getCurrentUser());
			eventInscription.setEvent(event);
			dao.create(eventInscription);
			MessageHelper.addMensage("Inscrição no evento " + event.getTitle() + " realizada com sucesso.", FacesMessage.SEVERITY_INFO);
		} else {
			MessageHelper.addMensage("Não há mais vagas no evento " + event.getTitle() + ".", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public List<Event> getEventsByUser() {
		EventInscriptionDAO dao = new EventInscriptionDAO();
		return dao.getNotRegisteredEvents(getCurrentUser().getId());
	}
	
	public List<Event> getEvents() {
		return new EventDAO().all();
	}
	
	private Event getEvent(){
		return new EventDAO().find(eventId);
	}
	
	public EventInscription getEventInscription() {
		return eventInscription;
	}
	
	public void setEventInscription(EventInscription eventInscription) {
		this.eventInscription = eventInscription;
	}
	
	public Integer getEventId() {
		return eventId;
	}
	
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
}
