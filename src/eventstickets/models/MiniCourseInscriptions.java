package eventstickets.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="miniCourse_inscription")
	public class MiniCourseInscriptions {
			private Integer id;
			private User participant;
			private String price;
			private Status status;
			private MiniCourse miniCourse;
			private Date createdAt;
			private Date updatedAt;
			private User createdBy;
			
			@ManyToOne
		    @JoinColumn(name="createdById")
			public User getCreatedBy() {
				return createdBy;
			}
			public void setCreatedBy(User createdBy) {
				this.createdBy = createdBy;
			}
			@Id
			@GeneratedValue
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			@ManyToOne
		    @JoinColumn(name="minicourseId")
			public MiniCourse getMini() {
				return miniCourse;
			}
			public void setMini(MiniCourse miniCourse) {
				this.miniCourse = miniCourse;
			}
			public Date getCreatedAt() {
				return createdAt;
			}
			public void setCreatedAt(Date createdAt) {
				this.createdAt = createdAt;
			}
			public Date getUpdatedAt() {
				return updatedAt;
			}
			public void setUpdatedAt(Date updatedAt) {
				this.updatedAt = updatedAt;
			}
			@ManyToOne
		    @JoinColumn(name="participantId")
			public User getParticipant() {
				return participant;
			}
			public void setParticipant(User participant) {
				this.participant = participant;
			}
			public Status getStatus() {
				return status;
			}
			public void setStatus(Status status) {
				this.status = status;
			}
			public String getPrice() {
				return price;
			}
			public void setPrice(String price) {
				this.price = price;
			}
}
