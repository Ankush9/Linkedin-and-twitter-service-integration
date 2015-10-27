package edu.sample.socialnetwork.dto;

import java.io.Serializable;
import java.util.List;

public class LinkedInServiceAccessDTO implements Serializable {

	/**
	 * Ankush
	 */
	private static final long serialVersionUID = 8133709431190420442L;

	private String apiKey;
	private String apiSecret;
	private LinkedInToken accessToken;
	private List<String> networkUpdateType;
	private String peopleId;
	private String groupId;
	private String emailId;
	private String firstName;
	private String lastName;

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

	public LinkedInToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(LinkedInToken accessToken) {
		this.accessToken = accessToken;
	}

	public List<String> getNetworkUpdateType() {
		return networkUpdateType;
	}

	public void setNetworkUpdateType(List<String> networkUpdateType) {
		this.networkUpdateType = networkUpdateType;
	}

	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
