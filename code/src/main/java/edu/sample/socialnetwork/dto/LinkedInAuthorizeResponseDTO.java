package edu.sample.socialnetwork.dto;

import java.io.Serializable;

public class LinkedInAuthorizeResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5949942036385667362L;

	private LinkedInToken linkedInToken;
	private String authorizationURL;

	public LinkedInToken getLinkedInToken() {
		return linkedInToken;
	}

	public void setLinkedInToken(LinkedInToken linkedInToken) {
		this.linkedInToken = linkedInToken;
	}

	public String getAuthorizationURL() {
		return authorizationURL;
	}

	public void setAuthorizationURL(String authorizationURL) {
		this.authorizationURL = authorizationURL;
	}

}
