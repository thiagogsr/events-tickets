package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import eventstickets.dao.PlaceDAO;
import eventstickets.models.Place;

@ManagedBean(name="place")
public class PlaceMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final List<Place> all = new PlaceDAO().all();

	public List<Place> getAll() {
		return all;
	}
}
