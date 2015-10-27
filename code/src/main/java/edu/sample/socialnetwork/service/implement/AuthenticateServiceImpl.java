package edu.sample.socialnetwork.service.implement;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.stereotype.Component;

import edu.sample.socialnetwork.constants.LinkedInConverter;
import edu.sample.socialnetwork.constants.SocialNetworkConstant;
import edu.sample.socialnetwork.dto.LinkedInAccessTokenRequestDTO;
import edu.sample.socialnetwork.dto.LinkedInAuthorizeResponseDTO;
import edu.sample.socialnetwork.dto.LinkedInToken;
import edu.sample.socialnetwork.utilities.AuthHandler;

@Component
public class AuthenticateServiceImpl {

	@Value("#{social.https_proxy}")
	private String httpsProxy;

	@Value("#{social.https_port}")
	private String httpsPort;

	@Value("#{social.http_proxy}")
	private String httpProxy;

	@Value("#{social.http_port}")
	private String httpPort;

	static OAuthService service = null;

	@Autowired
	AuthHandler authHandler;

	Logger LOGGER = LoggerFactory.getLogger(AuthenticateServiceImpl.class);

	/**
	 * @param apiKey
	 * @param apiSecret
	 * @return
	 */
	public LinkedInAuthorizeResponseDTO linkedInAuthorization(String apiKey,
			String apiSecret) {

		// Using the Scribe library we enter the information needed to begin the
		// chain of Oauth2 calls.

		service = authHandler.getLinkedInServiceProvider(apiKey, apiSecret);
		authHandler.setProxyConnection(httpsProxy, httpsPort, httpProxy,
				httpPort);

		LinkedInAuthorizeResponseDTO linkedInAuthorizeResponseDTO = authHandler
				.inializeAuthHandler(service);
		return linkedInAuthorizeResponseDTO;

	}

	/**
	 * @param linkedInAccessTokenRequestDTO
	 * @return
	 */
	public LinkedInToken linkedInAccessToken(
			LinkedInAccessTokenRequestDTO linkedInAccessTokenRequestDTO) {
		service = authHandler.getLinkedInServiceProvider(
				linkedInAccessTokenRequestDTO.getApiKey(),
				linkedInAccessTokenRequestDTO.getApiSecret());
		authHandler.setProxyConnection(httpsProxy, httpsPort, httpProxy,
				httpPort);

		Token accessToken = authHandler
				.initializeAccessToken(
						service,
						LinkedInConverter
								.convertLinkedInTokenToToken(linkedInAccessTokenRequestDTO
										.getRequestToken()),
						linkedInAccessTokenRequestDTO.getVerifierCode());
		return LinkedInConverter.convertTokenToLinkedInToken(accessToken);
	}

	/**
	 * @param apiKey
	 * @param apiSecret
	 * @return
	 */
	public String twitterAuthentication(String apiKey, String apiSecret) {
		// TODO Auto-generated method stub
		authHandler.setProxyConnection(httpsProxy, httpsPort, httpProxy,
				httpPort);
		OAuth2Operations oauth = new OAuth2Template(apiKey, apiSecret, "",
				SocialNetworkConstant.TWITTER_ACCESS_TOKEN_URL);
		return oauth.authenticateClient().getAccessToken();
	}
}
