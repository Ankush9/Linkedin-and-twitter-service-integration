package edu.sample.socialnetwork.dto;

import java.io.Serializable;

public class AuthenticationInitialDTO implements Serializable {

	/**
	 * LinkedIn Initial Authentication
	 * 
	 * Ankush
	 */
	private static final long serialVersionUID = 8133709431190420442L;

	private String apiKey;
	private String apiSecret;

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

}
