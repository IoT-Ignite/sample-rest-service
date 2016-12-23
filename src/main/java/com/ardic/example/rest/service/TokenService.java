package com.ardic.example.rest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

	@Value("${token}")
	private String token;

	private TokenService() {
	}

	public String getToken() {
		return token;
	}

	public boolean tokenValid(String token) {
		return this.token.equals(token);
	}
}
