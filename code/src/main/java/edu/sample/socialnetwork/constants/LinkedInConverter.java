package edu.sample.socialnetwork.constants;

import org.scribe.model.Token;
import org.springframework.stereotype.Component;

import edu.sample.socialnetwork.dto.LinkedInToken;

@Component
public class LinkedInConverter {

	public static Token convertLinkedInTokenToToken(LinkedInToken linkedInToken) {
		Token token = new Token(linkedInToken.getToken(),
				linkedInToken.getSecret());
		return token;
	}

	public static LinkedInToken convertTokenToLinkedInToken(Token token) {
		LinkedInToken linkedInToken = new LinkedInToken();
		linkedInToken.setSecret(token.getSecret());
		linkedInToken.setToken(token.getToken());
		return linkedInToken;
	}
}
