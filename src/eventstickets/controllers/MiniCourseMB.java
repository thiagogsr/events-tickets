package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import eventstickets.dao.MiniCourseDAO;
import eventstickets.models.MiniCourse;
import eventstickets.models.Place;
import eventstickets.models.User;

@ManagedBean(name="minicourseMB")
@RequestScoped
public class MiniCourseMB extends AuthenticateUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private MiniCourse miniCourse = new MiniCourse();
	private Integer id;
	private List<Place> places;
	private List<User> users;
	private Integer speakerId;
	private Integer placeId;

	public List<MiniCourse> all() {
		return new MiniCourseDAO().all();
	}

	public String create() {
		MiniCourseDAO dao = new MiniCourseDAO();
		miniCourse.setSpeaker(fetchUser());
		miniCourse.setPlace(fetchPlace());

		if (dao.create(miniCourse, getCurrentUser())) {
			return "index";
		} else {
			return "new";
		}
	}

	public String edit() {
		MiniCourseDAO dao = new MiniCourseDAO();
		miniCourse = dao.find(id);
		return "edit";
	}

	public String update() {
		MiniCourseDAO dao = new MiniCourseDAO();

		MiniCourse oldMiniCourse = dao.find(miniCourse.getId());
		oldMiniCourse.setTitle(miniCourse.getTitle());
		oldMiniCourse.setQuantity(miniCourse.getQuantity());
		oldMiniCourse.setValue(miniCourse.getValue());
		oldMiniCourse.setObjective(miniCourse.getObjective());
		oldMiniCourse.setDate(miniCourse.getDate());
		oldMiniCourse.setPlace(fetchPlace());
		oldMiniCourse.setSpeaker(fetchUser());
		
		if (dao.update(oldMiniCourse)) {
			return "index?faces-redirect=true";
		} else {
			return "edit";
		}
	}

	public String destroy() {
		MiniCourseDAO dao = new MiniCourseDAO();
		dao.destroy(id);
		return "index?faces-redirect=true";
	}

	private User fetchUser() {
		User user = new User();
		user.setId(speakerId);
		return user;
	}

	public MiniCourse getMinicourse() {
		return miniCourse;
	}

	public void setMinicourse(MiniCourse minicourse) {
		this.miniCourse = minicourse;
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

	private Place fetchPlace() {
		Place place = new Place();
		place.setId(placeId);
		return place;
	}

	public MiniCourse getMiniCourse() {
		return miniCourse;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public List<Place> getPlaces() {
		return places;
	}


	public void setPlaces(List<Place> places) {
		this.places = places;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}
}