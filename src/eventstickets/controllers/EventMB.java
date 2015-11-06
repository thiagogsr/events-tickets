package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import eventstickets.dao.EventDAO;
import eventstickets.dao.PlaceDAO;
import eventstickets.helpers.MessageHelper;
import eventstickets.models.Event;
import eventstickets.models.Place;

@ManagedBean(name="eventMB")
@RequestScoped
public class EventMB extends AuthenticateUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Event event = new Event();
	private Integer placeId;
	private Integer id;

	public List<Event> getAll() {
		return new EventDAO().all();
	}
	
	public String save() {
		if(event.getId() == null) {
			return create();
		} else {
			return update();
		}
	}
	
	private String create() {
		EventDAO dao = new EventDAO();
		event.setPlace(fetchPlace());
		
		if (dao.create(event, getCurrentUser())) {
			MessageHelper.addMensage("Evento criado com sucesso", FacesMessage.SEVERITY_INFO);
			return "index";
		} else {
			return "form";
		}
	}
	
	public String edit() {
		EventDAO dao = new EventDAO();
		event = dao.find(id);
		placeId = event.getPlace().getId();
		return "form";
	}
	
	private String update() {
		EventDAO dao = new EventDAO();
		Event oldEvent = dao.find(event.getId());
		oldEvent.setTitle(event.getTitle());
		oldEvent.setDescription(event.getDescription());
		oldEvent.setInscriptionsEndDate(event.getInscriptionsEndDate());
		oldEvent.setInscriptionsStartDate(event.getStartDate());
		oldEvent.setPrice(event.getPrice());
		oldEvent.setPlace(fetchPlace());
		oldEvent.setStartDate(event.getStartDate());
		oldEvent.setEndDate(event.getEndDate());
		oldEvent.setInscriptionsLimit(event.getInscriptionsLimit());
		
		if (dao.update(oldEvent)) {
			MessageHelper.addMensage("Evento atualizado com sucesso", FacesMessage.SEVERITY_INFO);
			return "index";
		} else {
			return "form";
		}
	}
	
	public String destroy() {
		EventDAO dao = new EventDAO();
		dao.destroy(id);
		MessageHelper.addMensage("Evento removido com sucesso", FacesMessage.SEVERITY_INFO);
		return "index";
	}
	
	private Place fetchPlace() {
		Place place = new Place();
		place.setId(placeId);
		return place;
	}
	
	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}
	
	public List<Place> getPlaces() {
		PlaceDAO placeDAO = new PlaceDAO();
		return placeDAO.all();
	}
	
	public Event getEvent() {
		return event;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}