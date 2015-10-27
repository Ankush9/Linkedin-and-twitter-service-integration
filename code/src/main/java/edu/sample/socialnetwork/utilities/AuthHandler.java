package edu.sample.socialnetwork.utilities;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import edu.sample.socialnetwork.constants.LinkedInConverter;
import edu.sample.socialnetwork.dto.LinkedInAuthorizeResponseDTO;

/**
 * This will be the class to handles getting the Access Token It will take an
 * API Key and API secret and return a @Token that is an access token It will
 * handle the case when they Access token has already been granted
 * 
 * Ankush
 */
@Component
public class AuthHandler implements Serializable {
	private static final long serialVersionUID = 1L;
	private Token accessToken = null;
	Logger LOGGER = LoggerFactory.getLogger(AuthHandler.class);

	public OAuthService getLinkedInServiceProvider(String apiKey,
			String apiSecret) {
		return new ServiceBuilder().provider(LinkedInApi.class).apiKey(apiKey)
				.apiSecret(apiSecret).build();

	}

	public void setProxyConnection(String httpsProxy, String httpsPort,
			String httpProxy, String httpPort) {
		System.setProperty("https.proxyHost", httpsProxy);
		System.setProperty("https.proxyPort", httpsPort);

		System.setProperty("http.proxyHost", httpProxy);
		System.setProperty("http.proxyPort", httpPort);
	}

	public LinkedInAuthorizeResponseDTO inializeAuthHandler(
			OAuthService serviceProvider) {

		// this is our first time creating this object so we need to populate
		// the
		// accessToken
		Token requestToken = serviceProvider.getRequestToken();
		LOGGER.info(serviceProvider.getAuthorizationUrl(requestToken));
		LinkedInAuthorizeResponseDTO linkedInAuthorizeResponseDTO = new LinkedInAuthorizeResponseDTO();
		linkedInAuthorizeResponseDTO.setAuthorizationURL(serviceProvider
				.getAuthorizationUrl(requestToken));
		linkedInAuthorizeResponseDTO.setLinkedInToken(LinkedInConverter
				.convertTokenToLinkedInToken(requestToken));
		return linkedInAuthorizeResponseDTO;

	}

	public Token initializeAccessToken(OAuthService serviceProvider,
			Token requestToken, String verifierCode) {
		if (requestToken != null) {
			LOGGER.info("Verifier code sent ------>" + verifierCode);
			if (StringUtils.isNotBlank(verifierCode)) {
				Verifier verifier = new Verifier(verifierCode);
				accessToken = serviceProvider.getAccessToken(requestToken,
						verifier);
			} else {
				LOGGER.error("Verifier code sent is incorrect ------>"
						+ verifierCode);
			}
		} else {
			LOGGER.error("Request Token is null");
		}
		return accessToken;
	}

	/**
	 * You only need to call this if you didn't reserialize an object
	 * 
	 * @return an Access token you can use to make API calls
	 */
	public Token getAccessToken() {
		return this.accessToken;
	}

}