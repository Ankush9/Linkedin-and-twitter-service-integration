package edu.sample.socialnetwork.dto;

import java.io.Serializable;

public class LinkedInToken implements Serializable {

	/**
	 * Ankush
	 */
	private static final long serialVersionUID = 5730607431213376856L;

	private String token;
	private String secret;

	public String getToken() {
		return token;
	}

	public String getSecret() {
		return secret;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
