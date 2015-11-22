package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import eventstickets.dao.MiniCourseDAO;
import eventstickets.dao.MiniCourseInscriptionDAO;
import eventstickets.helpers.MessageHelper;
import eventstickets.models.MiniCourse;
import eventstickets.models.MiniCourseInscription;
import eventstickets.policies.EventPolicy;


@ManagedBean(name = "miniCourseInscriptionMB")
@RequestScoped
public class MiniCourseInscriptionMB  extends AuthenticateUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private MiniCourseInscription miniCourseInscription = new MiniCourseInscription();
	private Integer miniCourseId;
	
	public MiniCourseInscriptionMB() {
		new AuthenticateUser();
		checkPermission(EventPolicy.register(getCurrentUser()));
	}
			
	public List<MiniCourseInscription> getAll(){
		MiniCourseInscriptionDAO dao = new MiniCourseInscriptionDAO();
		return dao.all();
	}
	public void create(){
		MiniCourseInscriptionDAO dao = new MiniCourseInscriptionDAO();
		MiniCourse miniCourse = (MiniCourse) getMiniCourse();
		if(dao.totalInsriptionMiniCourse(miniCourse.getId()) < miniCourse.getInscriptionsLimit()){
			miniCourseInscription.setParticipant(getCurrentUser());
			miniCourseInscription.setMini(miniCourse);
			dao.create(miniCourseInscription);
			MessageHelper.addMensage("Inscrição no Mini Curso " + miniCourse.getTitle() + " realizada com sucesso.", FacesMessage.SEVERITY_INFO);
		} else {
			MessageHelper.addMensage("Nãoo há mais vagas para o Mini Curso " + miniCourse.getTitle() + ".", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public List<MiniCourse> getMini() {
		MiniCourseInscriptionDAO dao = new MiniCourseInscriptionDAO();
		return dao.getMiniCourseByUserId(getCurrentUser().getId());
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
}

