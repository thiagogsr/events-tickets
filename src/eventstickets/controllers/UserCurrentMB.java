package eventstickets.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;


import eventstickets.dao.UserDAO;
import eventstickets.models.Role;
import eventstickets.models.User;

@ManagedBean(name="UserCurrentMB")
@RequestScoped
public class UserCurrentMB extends AuthenticateUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private Integer id;

	public List<User> getAll() {
		return new UserDAO().all();
	}
	
	public String create(){
		UserDAO dao = new UserDAO();
		
		if (dao.create(user)) {
			return "index?faces-redirect=true";
		} else {
			return "new";
		}
	}
	
	public String edit(){
		UserDAO dao = new UserDAO();
		user = dao.find(id);
		return "edit";
	}
	
	public String update(){
		UserDAO dao = new UserDAO();
		User oldUser = dao.find(user.getId());
		oldUser.setEmail(user.getEmail());
		oldUser.setMobilePhoneNumber(user.getMobilePhoneNumber());
		oldUser.setName(user.getName());
		oldUser.setPhoneNumber(user.getPhoneNumber());
		oldUser.setRole(user.getRole());
		oldUser.setUsername(user.getUsername());
		oldUser.setPassword(user.getPassword());
		
		if (dao.update(oldUser)) {
			return "index?faces-redirect=true";
		} else {
			return "edit";
		}
	}
	
	public String destroy() {
		UserDAO dao = new UserDAO();
		dao.destroy(id);
		return "index?faces-redirect=true";
	}
	
	public Role[] getRole(){
		return Role.values();
	}
	
	public User getUser() {
		return user;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
