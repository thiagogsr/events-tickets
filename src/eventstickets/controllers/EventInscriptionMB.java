package eventstickets.controllers;


import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import eventstickets.dao.EventDAO;
import eventstickets.dao.EventInscriptionDAO;
import eventstickets.dao.MiniCourseInscriptionDAO;
import eventstickets.dao.TalkDAO;
import eventstickets.helpers.MessageHelper;
import eventstickets.models.Event;
import eventstickets.models.EventInscription;
import eventstickets.models.MiniCourseInscription;
import eventstickets.models.Talk;
import eventstickets.policies.EventPolicy;

@ManagedBean(name = "eventInscriptionMB")
@RequestScoped
public class EventInscriptionMB extends AuthenticateUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private EventInscription eventInscription = new EventInscription();
	
	@ManagedProperty(value="#{param.eventId}")
	private String eventId;
	
	
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
	
	public String details() {
		checkPermission(EventPolicy.register(getCurrentUser()));
		return "detailsInscription";
	}
	
	public List<Talk> getTalks() {
		TalkDAO dao = new TalkDAO();
		return dao.getTalkByEvent(Integer.parseInt(getEventId()));
	}
	
	public List<MiniCourseInscription> getMiniCourseInscription() {
		MiniCourseInscriptionDAO dao = new MiniCourseInscriptionDAO();
		return dao.registered(Integer.parseInt(getEventId()));
	}
	
	public List<Event> getEventsByUser() {
		EventInscriptionDAO dao = new EventInscriptionDAO();
		return dao.getNotRegisteredEvents(getCurrentUser().getId());
	}
	
	public List<Event> getEvents() {
		return new EventDAO().all();
	}
	
	public Event getEvent(){
		return new EventDAO().find(Integer.parseInt(getEventId()));
	}
	
	public EventInscription getEventInscription() {
		return eventInscription;
	}
	
	public void setEventInscription(EventInscription eventInscription) {
		this.eventInscription = eventInscription;
	}
	
	public String getEventId() {
		return eventId;
	}
	
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
}
