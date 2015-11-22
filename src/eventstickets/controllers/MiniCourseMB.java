package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import eventstickets.dao.MiniCourseDAO;
import eventstickets.dao.PlaceDAO;
import eventstickets.dao.UserDAO;
import eventstickets.models.MiniCourse;
import eventstickets.models.Place;
import eventstickets.models.Role;
import eventstickets.models.User;
import eventstickets.policies.MiniCoursePolicy;

@ManagedBean(name="miniCourseMB")
@RequestScoped
public class MiniCourseMB extends AuthenticateUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private MiniCourse miniCourse = new MiniCourse();
	private Integer id;
	private Integer speakerId;
	private Integer placeId;
	
	public MiniCourseMB() {
		checkPermission(MiniCoursePolicy.index(getCurrentUser()));
	}

	public List<MiniCourse> all() {
		return new MiniCourseDAO().all();
	}

	public String save() {
		if(miniCourse.getId() == null) {
			return create();
		} else {
			return update();
		}
	}

	private String create() {
		MiniCourseDAO dao = new MiniCourseDAO();
		miniCourse.setSpeaker(fetchUser());
		miniCourse.setPlace(fetchPlace());

		if (dao.create(miniCourse, getCurrentUser())) {
			return "index";
		} else {
			return "form";
		}
	}

	public String edit() {
		MiniCourseDAO dao = new MiniCourseDAO();
		miniCourse = dao.find(id);
		return "form";
	}

	private String update() {
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
			return "form";
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
		PlaceDAO placeDAO = new PlaceDAO();
		return placeDAO.all();
	}

	public List<User> getUsers() {
		UserDAO userDAO = new UserDAO();
		return userDAO.byRole(Role.SPEAKER);
	}
}
