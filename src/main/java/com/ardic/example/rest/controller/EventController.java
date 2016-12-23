package com.ardic.example.rest.controller;

import java.util.ArrayList;

import javax.naming.AuthenticationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ardic.example.rest.model.Event;
import com.ardic.example.rest.repository.EventRepository;
import com.ardic.example.rest.service.EventService;

@RestController
@RequestMapping(value = "/event")
public class EventController {

	@Autowired
	EventRepository repository;

	@Autowired
	EventService eventService;

	Logger logger = Logger.getLogger(EventController.class);

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Event>> greeting() {
		return new ResponseEntity<ArrayList<Event>>(repository.getEventList(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Boolean> event(@RequestBody String body, @RequestHeader(value = "token", defaultValue = "") String token) throws AuthenticationException {
		logger.debug("EventBody:[" + body + "]");
		return new ResponseEntity<Boolean>(eventService.addEvent(body, token), HttpStatus.OK);
	}
}
