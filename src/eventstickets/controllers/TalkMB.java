package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import eventstickets.dao.PlaceDAO;
import eventstickets.dao.TalkDAO;
import eventstickets.dao.UserDAO;
import eventstickets.models.Place;
import eventstickets.models.Talk;
import eventstickets.models.User;

@ManagedBean(name = "talkMB")
@RequestScoped
public class TalkMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Talk talk = new Talk();
	private List<Place> places;
	private List<User> users;
	private Integer speakerId;
	private Integer placeId;

	public String create() {
		TalkDAO dao = new TalkDAO();
		talk.setSpeaker(fetchUser());

		if (dao.create(talk)) {
			return "index";
		} else {
			return "new";
		}
	}
	
	private User fetchUser() {
		User user = new User();
		user.setId(speakerId);
		return user;
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

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public List<User> getUsers() {
		UserDAO userDAO = new UserDAO();
		return userDAO.all();
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
