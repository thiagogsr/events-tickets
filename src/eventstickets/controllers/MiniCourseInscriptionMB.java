package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import eventstickets.dao.EventDAO;
import eventstickets.dao.MiniCourseDAO;
import eventstickets.dao.MiniCourseInscriptionDAO;
import eventstickets.helpers.MessageHelper;
import eventstickets.models.Event;
import eventstickets.models.MiniCourse;
import eventstickets.models.MiniCourseInscription;
import eventstickets.policies.EventPolicy;


@ManagedBean(name = "miniCourseInscriptionMB")
@RequestScoped
public class MiniCourseInscriptionMB  extends AuthenticateUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private MiniCourseInscription miniCourseInscription = new MiniCourseInscription();
	private Integer miniCourseId;

	@ManagedProperty(value="#{param.eventId}")
	private String eventId;

	public MiniCourseInscriptionMB() {
		new AuthenticateUser();
		checkPermission(EventPolicy.register(getCurrentUser()));
	}

	public List<MiniCourse> getNotRegistered() {
		return new MiniCourseDAO().notRegistered(getCurrentUser().getId(), Integer.parseInt(getEventId()));
	}

	public List<MiniCourseInscription> getRegistered() {
		return new MiniCourseInscriptionDAO().registered(Integer.parseInt(getEventId()));
	}

	public String create() {
		MiniCourseInscriptionDAO dao = new MiniCourseInscriptionDAO();
		MiniCourse miniCourse = getMiniCourse();
		if(dao.totalInsriptionMiniCourse(miniCourse.getId()) < miniCourse.getInscriptionsLimit()){
			miniCourseInscription.setParticipant(getCurrentUser());
			miniCourseInscription.setMiniCourse(miniCourse);
			dao.create(miniCourseInscription);
			MessageHelper.addMensage("Inscrição no Mini Curso " + miniCourse.getTitle() + " realizada com sucesso.", FacesMessage.SEVERITY_INFO);
		} else {
			MessageHelper.addMensage("Não há mais vagas para o Mini Curso " + miniCourse.getTitle() + ".", FacesMessage.SEVERITY_ERROR);
		}
		return "index?faces-redirect=true&includeViewParams=true";
	}

	private MiniCourse getMiniCourse(){
		return new MiniCourseDAO().find(miniCourseId);
	}

	public MiniCourseInscription getMiniCourseInscription() {
		return miniCourseInscription;
	}

	public void setMiniCourseInscription(MiniCourseInscription miniCourseInscription) {
		this.miniCourseInscription = miniCourseInscription;
	}

	public Integer getMiniCourseId() {
		return miniCourseId;
	}

	public void setMiniCourseId(Integer miniCourseId) {
		this.miniCourseId = miniCourseId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public Event getEvent() {
		return new EventDAO().find(Integer.parseInt(getEventId()));
	}
}

