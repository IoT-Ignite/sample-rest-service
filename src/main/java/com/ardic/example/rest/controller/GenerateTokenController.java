package com.ardic.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ardic.example.rest.service.TokenService;

@RestController
public class GenerateTokenController {

	@Autowired
	TokenService tokenService;

	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public String token() {
		return tokenService.getToken();
	}
}
