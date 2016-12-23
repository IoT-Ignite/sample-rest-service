package com.ardic.example.rest.model;

import java.util.Date;

public class Event {

	private String event;
	private Date eventDate;
	private long id;

	public Event(long id, String event, Date eventDate) {
		this.event = event;
		this.eventDate = eventDate;
		this.id = id;
	}
	
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
