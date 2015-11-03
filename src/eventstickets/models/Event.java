package eventstickets.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="events")
public class Event {
	private Integer id;
	private Place place;
	private float price;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int inscriptionsLimit;
	private Date inscriptionsStartDate;
	private Date inscriptionsEndDate;
	private User createdBy;
	private Date createdAt;
	private Date updatedAt;
	
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	@GeneratedValue
	@Id
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
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public int getInscriptionsLimit() {
		return inscriptionsLimit;
	}

	public void setInscriptionsLimit(int inscriptionsLimit) {
		this.inscriptionsLimit = inscriptionsLimit;
	}

	public Date getInscriptionsStartDate() {
		return inscriptionsStartDate;
	}

	public void setInscriptionsStartDate(Date inscriptionsStartDate) {
		this.inscriptionsStartDate = inscriptionsStartDate;
	}

	public Date getInscriptionsEndDate() {
		return inscriptionsEndDate;
	}

	public void setInscriptionsEndDate(Date inscriptionsEndDate) {
		this.inscriptionsEndDate = inscriptionsEndDate;
	}
	
	@ManyToOne
    @JoinColumn(name="createdById")
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
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

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
