package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import eventstickets.dao.PlaceDAO;
import eventstickets.models.Place;

@ManagedBean(name="placeMB")
public class PlaceMB extends AuthenticateUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Place place = new Place();

	public List<Place> getAll() {
		return new PlaceDAO().all();
	}
	
	public Place getPlace() {
		return place;
	}
	
	public String create() {
		PlaceDAO dao = new PlaceDAO();
		dao.create(place, getCurrentUser());
		return "index?faces-redirect=true";
	}
}
