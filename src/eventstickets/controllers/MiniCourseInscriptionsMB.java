package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import eventstickets.dao.MiniCourseDAO;
import eventstickets.dao.MiniCourseInscriptionsDAO;
import eventstickets.helpers.MessageHelper;
import eventstickets.models.MiniCourse;
import eventstickets.models.MiniCourseInscriptions;


@ManagedBean(name = "miniCourseInscriptionsMB")
@RequestScoped
public class MiniCourseInscriptionsMB  extends AuthenticateUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private MiniCourseInscriptions miniCourseInscription = new MiniCourseInscriptions();
	private Integer miniCourseId;
			
	public List<MiniCourseInscriptions> getAll(){
		MiniCourseInscriptionsDAO dao = new MiniCourseInscriptionsDAO();
		return dao.all();
	}
	public void create(){
		MiniCourseInscriptionsDAO dao = new MiniCourseInscriptionsDAO();
		MiniCourse miniCourse = (MiniCourse) getMiniCourse();
		if(dao.totalInsriptionMiniCourse(miniCourse.getId()) < miniCourse.getQuantity()){
			miniCourseInscription.setParticipant(getCurrentUser());
			miniCourseInscription.setMini(miniCourse);
			dao.create(miniCourseInscription);
			MessageHelper.addMensage("Inscrição no Mini Curso " + miniCourse.getTitle() + " realizada com sucesso.", FacesMessage.SEVERITY_INFO);
		} else {
			MessageHelper.addMensage("Nãoo há mais vagas para o Mini Curso " + miniCourse.getTitle() + ".", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public List<MiniCourse> getMini() {
		MiniCourseInscriptionsDAO dao = new MiniCourseInscriptionsDAO();
		return dao.getMiniCourseByUserId(getCurrentUser().getId());
	}
	private MiniCourse getMiniCourse(){
		return new MiniCourseDAO().find(miniCourseId);
	}
	public MiniCourseInscriptions getMiniCourseInscription() {
		return miniCourseInscription;
	}
	public void setMiniCourseInscription(MiniCourseInscriptions miniCourseInscription) {
		this.miniCourseInscription = miniCourseInscription;
	}
	public Integer getMiniCourseId() {
		return miniCourseId;
	}
	public void setMiniCourseId(Integer miniCourseId) {
		this.miniCourseId = miniCourseId;
	}	
}

