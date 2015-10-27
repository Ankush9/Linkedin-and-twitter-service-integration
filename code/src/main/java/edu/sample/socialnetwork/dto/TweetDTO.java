package edu.sample.socialnetwork.dto;

import java.io.Serializable;

public class TweetDTO implements Serializable {

	/**
	 * Ankush
	 */
	private static final long serialVersionUID = 5607431213376856L;

	private long id;
	private String text;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
