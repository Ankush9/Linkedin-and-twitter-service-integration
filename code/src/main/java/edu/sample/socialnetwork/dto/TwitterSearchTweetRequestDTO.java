package edu.sample.socialnetwork.dto;

import java.io.Serializable;

public class TwitterSearchTweetRequestDTO implements Serializable {

	/**
	 * A545423
	 */
	private static final long serialVersionUID = 577431213376856L;

	private String accessToken;
	private String searchTag;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSearchTag() {
		return searchTag;
	}

	public void setSearchTag(String searchTag) {
		this.searchTag = searchTag;
	}
}
