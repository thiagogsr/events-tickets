package eventstickets.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name="mini_courses")
public class MiniCourse {
	private Integer id;
	private String title;
	private Date Date;
	private Place place;
	private Event event;
	private String objective;
	private int inscriptionsLimit;
	private float price;
	private Date createdAt;
	private Date updatedAt;
	private User speaker;
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
    @JoinColumn(name="placeId")
	public Place getPlace() {
		return place;
	}
	
	public void setPlace(Place place) {
		this.place = place;
	}
	
	@ManyToOne
    @JoinColumn(name="eventId")
	public Event getEvent() {
		return event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
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
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getDate() {
		return Date;
	}
	
	public void setDate(Date date) {
		Date = date;
	}
	
	public String getObjective() {
		return objective;
	}
	
	public void setObjective(String objective) {
		this.objective = objective;
	}
	
	public int getInscriptionsLimit() {
		return inscriptionsLimit;
	}
	
	public void setInscriptionsLimit(int inscriptionsLimit) {
		this.inscriptionsLimit = inscriptionsLimit;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	@ManyToOne
    @JoinColumn(name="speakerId")
	public User getSpeaker() {
		return speaker;
	}
	
	public void setSpeaker(User speaker) {
		this.speaker = speaker;
	}
}
