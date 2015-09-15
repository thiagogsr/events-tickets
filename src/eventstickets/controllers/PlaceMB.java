package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import eventstickets.dao.PlaceDAO;
import eventstickets.models.Place;

@ManagedBean(name="placeMB")
@RequestScoped
public class PlaceMB extends AuthenticateUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Place place = new Place();
	private Integer id;

	public List<Place> getAll() {
		return new PlaceDAO().all();
	}
	
	public String create() {
		PlaceDAO dao = new PlaceDAO();
		
		if (dao.create(place, getCurrentUser())) {
			return "index?faces-redirect=true";
		} else {
			return "new";
		}
	}
	
	public String edit() {
		PlaceDAO dao = new PlaceDAO();
		place = dao.find(id);
		return "edit";
	}
	
	public String update() {
		PlaceDAO dao = new PlaceDAO();
		Place oldPlace = dao.find(place.getId());
		oldPlace.setTitle(place.getTitle());

		if (dao.update(oldPlace)) {
			return "index?faces-redirect=true";
		} else {
			return "edit";
		}
	}
	
	public String destroy() {
		PlaceDAO dao = new PlaceDAO();
		dao.destroy(id);
		return "index?faces-redirect=true";
	}

	public Place getPlace() {
		return place;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
