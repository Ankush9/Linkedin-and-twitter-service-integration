package edu.sample.socialnetwork.dto;

import java.io.Serializable;

public class LinkedInAccessTokenRequestDTO implements Serializable {

	/**
	 * LinkedIn Access token DTO
	 * 
	 * Ankush
	 */
	private static final long serialVersionUID = 6821236710502152826L;

	private String apiKey;
	private String apiSecret;
	private LinkedInToken requestToken;
	private String verifierCode;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public LinkedInToken getRequestToken() {
		return requestToken;
	}

	public void setRequestToken(LinkedInToken requestToken) {
		this.requestToken = requestToken;
	}

	public String getVerifierCode() {
		return verifierCode;
	}

	public void setVerifierCode(String verifierCode) {
		this.verifierCode = verifierCode;
	}
}
