package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import eventstickets.dao.PlaceDAO;
import eventstickets.helpers.MessageHelper;
import eventstickets.models.Place;
import eventstickets.policies.PlacePolicy;

@ManagedBean(name="placeMB")
@RequestScoped
public class PlaceMB extends AuthenticateUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Place place = new Place();
	private Integer id;
	
	public PlaceMB() {
		new AuthenticateUser();
		checkPermission(PlacePolicy.index(getCurrentUser()));
	}

	public List<Place> getAll() {
		return new PlaceDAO().all();
	}
	
	public String save() {
		if(place.getId() == null) {
			return create();
		} else {
			return update();
		}
	}
	
	private String create() {
		PlaceDAO dao = new PlaceDAO();
		
		if (dao.create(place, getCurrentUser())) {
			MessageHelper.addMensage("Lugar criado com sucesso", FacesMessage.SEVERITY_INFO);
			return "index";
		} else {
			return "form";
		}
	}
	
	public String edit() {
		PlaceDAO dao = new PlaceDAO();
		place = dao.find(id);
		return "form";
	}
	
	private String update() {
		PlaceDAO dao = new PlaceDAO();
		Place oldPlace = dao.find(place.getId());
		oldPlace.setTitle(place.getTitle());

		if (dao.update(oldPlace)) {
			MessageHelper.addMensage("Lugar atualizado com sucesso", FacesMessage.SEVERITY_INFO);
			return "index";
		} else {
			return "form";
		}
	}
	
	public String destroy() {
		PlaceDAO dao = new PlaceDAO();
		dao.destroy(id);
		MessageHelper.addMensage("Lugar removido com sucesso", FacesMessage.SEVERITY_INFO);
		return "index";
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
