package com.ardic.example.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ardic.example.rest.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	TokenService tokenService;

	@Autowired
	EventRepository eventRepository;

	public Boolean addEvent(String event, String token) {
		if (tokenService.tokenValid(token)) {
			eventRepository.addNewEvent(event);
			return true;
		}
		return false;
	}

}
