package eventstickets.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import eventstickets.dao.EventDAO;
import eventstickets.dao.EventInscriptionDAO;
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
		oldEvent.setInscriptionsStartDate(event.getInscriptionsStartDate());
		oldEvent.setInscriptionsEndDate(event.getInscriptionsEndDate());
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
	
	public void gerarRelatorio() throws JRException, IOException{
		EventInscriptionDAO dao = new EventInscriptionDAO();
		List dataSource = dao.getEventsInscriptions(id);
		JasperPrint printReport = JasperFillManager.fillReport(getReportInputStream(), new HashMap(), new JRBeanCollectionDataSource(dataSource, false));
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse httpServletResponse =  (HttpServletResponse) facesContext.getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition", "attachment; filename=inscritos.pdf");  
		OutputStream outputStream = httpServletResponse.getOutputStream();
		JasperExportManager.exportReportToPdfStream(printReport, outputStream);
		facesContext.responseComplete();
	}
	
	private InputStream getReportInputStream(){
		return this.getClass().getResourceAsStream("../report/inscriptionsByEvent.jasper");
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