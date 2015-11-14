package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import eventstickets.dao.MiniInscriptionsDAO;

import eventstickets.helpers.MessageHelper;
import eventstickets.models.MiniCourse;
import eventstickets.models.MiniInscriptions;


@ManagedBean(name = "miniInscriptionsMB")
@RequestScoped
public class MiniInscriptionsMB  extends AuthenticateUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private MiniInscriptions miniInscriptions = new MiniInscriptions();
	private Integer miniCourseId;
	private List<MiniCourse> mini;
	private Integer miniId;
		
	public List<MiniInscriptions> getAll(){
		MiniInscriptionsDAO dao = new MiniInscriptionsDAO();
		return dao.all();
	}
	public void create(){
		MiniInscriptionsDAO dao = new MiniInscriptionsDAO();
		MiniCourse miniCourse = (MiniCourse) getMini();
		if(dao.totalInsriptionMini(miniCourse.getId()) < miniCourse.getQuantity()){
			miniInscriptions.setParticipant(getCurrentUser());
			miniInscriptions.setMini(miniCourse);
			dao.create(miniInscriptions);
			MessageHelper.addMensage("Inscrição no Mini Curso " + miniCourse.getTitle() + " realizada com sucesso.", FacesMessage.SEVERITY_INFO);
		} else {
			MessageHelper.addMensage("Nãoo há mais vagas para o Mini Curso " + miniCourse.getTitle() + ".", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public List<MiniCourse> getMini() {
		MiniInscriptionsDAO dao = new MiniInscriptionsDAO();
		return dao.getMiniCourseByUserId(getCurrentUser().getId());
	}

	public void setMini(List<MiniCourse> mini) {
		this.mini = mini;
	}

	public Integer getMiniId() {
		return miniId;
	}

	public void setMiniId(Integer miniId) {
		this.miniId = miniId;
	}

	public Integer getMiniCourseId() {
		return miniCourseId;
	}

	public void setMiniCourseId(Integer miniCourseId) {
		this.miniCourseId = miniCourseId;
	}

	public MiniInscriptions getMiniInscriptions() {
		return miniInscriptions;
	}

	public void setMiniInscriptions(MiniInscriptions miniInscriptions) {
		this.miniInscriptions = miniInscriptions;
	}
}

