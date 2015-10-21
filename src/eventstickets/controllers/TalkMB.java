package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import eventstickets.dao.PlaceDAO;
import eventstickets.dao.TalkDAO;
import eventstickets.dao.UserDAO;
import eventstickets.models.Place;
import eventstickets.models.Role;
import eventstickets.models.Talk;
import eventstickets.models.User;

@ManagedBean(name = "talkMB")
@RequestScoped
public class TalkMB extends AuthenticateUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Talk talk = new Talk();
	private List<Place> places;
	private List<User> users;
	private Integer speakerId;
	private Integer placeId;
	private Integer id;

	public List<Talk> all(){
		TalkDAO dao = new TalkDAO();
		return dao.all();
	}
	
	public String create() {
		TalkDAO dao = new TalkDAO();
		talk.setSpeaker(fetchUser());
		talk.setPlace(fetchPlace());
		
		if (dao.create(talk)) {
			return "index";
		} else {
			return "new";
		}
	}
	
	public String edit(){
		TalkDAO dao = new TalkDAO();
		talk = dao.find(id);
		return "edit";
	}
	
	public String update(){
		TalkDAO dao = new TalkDAO();
		Talk oldTalk = dao.find(talk.getId());
		oldTalk.setEndDate(talk.getEndDate());
		oldTalk.setPlace(fetchPlace());
		oldTalk.setSpeaker(fetchUser());
		oldTalk.setStartDate(talk.getStartDate());
		oldTalk.setTopic(talk.getTopic());
		
		if (dao.update(oldTalk)) {
			return "index";
		} else {
			return "edit";
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
		return userDAO.byRole(Role.SPEAKER);
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
