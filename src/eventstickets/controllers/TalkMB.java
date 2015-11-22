package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import eventstickets.dao.EventDAO;
import eventstickets.dao.PlaceDAO;
import eventstickets.dao.TalkDAO;
import eventstickets.dao.UserDAO;
import eventstickets.models.Event;
import eventstickets.models.Place;
import eventstickets.models.Role;
import eventstickets.models.Talk;
import eventstickets.models.User;
import eventstickets.policies.TalkPolicy;

@ManagedBean(name = "talkMB")
@RequestScoped
public class TalkMB extends AuthenticateUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Talk talk = new Talk();
	private Integer speakerId;
	private Integer placeId;
	private Integer eventId;
	private Integer id;
	
	public TalkMB() {
		new AuthenticateUser();
		checkPermission(TalkPolicy.index(getCurrentUser()));
	}

	public List<Talk> getAll(){
		TalkDAO dao = new TalkDAO();
		return dao.all();
	}

	public String save() {
		if(talk.getId() == null) {
			return create();
		} else {
			return update();
		}
	}

	private String create() {
		TalkDAO dao = new TalkDAO();
		talk.setSpeaker(fetchUser());
		talk.setPlace(fetchPlace());
		talk.setEvent(fetchEvent());

		if (dao.create(talk, getCurrentUser())) {
			return "index";
		} else {
			return "form";
		}
	}

	public String edit(){
		TalkDAO dao = new TalkDAO();
		talk = dao.find(id);
		speakerId = talk.getSpeaker().getId();
		eventId = talk.getEvent().getId();
		placeId = talk.getPlace().getId();
		return "form";
	}

	private String update(){
		TalkDAO dao = new TalkDAO();
		Talk oldTalk = dao.find(talk.getId());
		oldTalk.setEndDate(talk.getEndDate());
		oldTalk.setPlace(fetchPlace());
		oldTalk.setSpeaker(fetchUser());
		oldTalk.setStartDate(talk.getStartDate());
		oldTalk.setTopic(talk.getTopic());
		oldTalk.setDescription(talk.getDescription());
		oldTalk.setEvent(fetchEvent());

		if (dao.update(oldTalk)) {
			return "index";
		} else {
			return "form";
		}
	}

	public String destroy() {
		TalkDAO dao = new TalkDAO();
		dao.destroy(id);
		return "index";
	}

	private User fetchUser() {
		User user = new User();
		user.setId(speakerId);
		return user;
	}

	private Place fetchPlace() {
		Place place = new Place();
		place.setId(placeId);
		return place;
	}

	private Event fetchEvent() {
		Event event = new Event();
		event.setId(eventId);
		return event;
	}

	public Talk getTalk() {
		return talk;
	}

	public void setTalk(Talk talk) {
		this.talk = talk;
	}

	public Integer getSpeakerId() {
		return speakerId;
	}

	public void setSpeakerId(Integer speakerId) {
		this.speakerId = speakerId;
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

	public List<User> getUsers() {
		UserDAO userDAO = new UserDAO();
		return userDAO.byRole(Role.SPEAKER);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Event> getEvents() {
		EventDAO eventDAO = new EventDAO();
		return eventDAO.all();
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
}
