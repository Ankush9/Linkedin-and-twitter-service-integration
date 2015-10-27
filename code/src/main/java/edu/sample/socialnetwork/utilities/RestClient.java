package edu.sample.socialnetwork.utilities;

import java.util.List;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import edu.sample.socialnetwork.constants.LinkedInConverter;
import edu.sample.socialnetwork.dto.LinkedInServiceAccessDTO;

/**
 * 
 * Ankush
 */
@Component
public class RestClient {

	@Autowired
	AuthHandler authHandler;

	@Value("#{social.https_proxy}")
	private String httpsProxy;

	@Value("#{social.https_port}")
	private String httpsPort;

	@Value("#{social.http_proxy}")
	private String httpProxy;

	@Value("#{social.http_port}")
	private String httpPort;

	static OAuthRequest request;
	static Response response;

	Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

	public String performFunctionLinkedIn(String url,
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		authHandler.setProxyConnection(httpsProxy, httpsPort, httpProxy,
				httpPort);
		OAuthService service = authHandler.getLinkedInServiceProvider(
				linkedInServiceAccessDTO.getApiKey(),
				linkedInServiceAccessDTO.getApiSecret());
		request = new OAuthRequest(Verb.GET, url);
		request.addHeader("x-li-format", "json");
		service.signRequest(LinkedInConverter
				.convertLinkedInTokenToToken(linkedInServiceAccessDTO
						.getAccessToken()), request);
		response = request.send();
		return response.getBody();
	}

	public Response performFunctionLinkedInInvitation(String url,
			LinkedInServiceAccessDTO linkedInServiceAccessDTO, String payLoader) {
		authHandler.setProxyConnection(httpsProxy, httpsPort, httpProxy,
				httpPort);
		OAuthService service = authHandler.getLinkedInServiceProvider(
				linkedInServiceAccessDTO.getApiKey(),
				linkedInServiceAccessDTO.getApiSecret());
		request = new OAuthRequest(Verb.POST, url);
		request.addOAuthParameter("scope", "w_messages");
		request.addHeader("Content-Type", "text/xml");
		request.addPayload(payLoader);
		service.signRequest(LinkedInConverter
				.convertLinkedInTokenToToken(linkedInServiceAccessDTO
						.getAccessToken()), request);
		response = request.send();
		return response;
	}

	public String performFunctionLinkedIn(String url,
			LinkedInServiceAccessDTO linkedInServiceAccessDTO,
			List<String> networkUpdateType) {
		authHandler.setProxyConnection(httpsProxy, httpsPort, httpProxy,
				httpPort);
		OAuthService service = authHandler.getLinkedInServiceProvider(
				linkedInServiceAccessDTO.getApiKey(),
				linkedInServiceAccessDTO.getApiSecret());

		request = new OAuthRequest(Verb.GET, url);
		for (String updateType : networkUpdateType) {
			request.addQuerystringParameter("type", updateType);
		}

		request.addHeader("x-li-format", "json");
		service.signRequest(LinkedInConverter
				.convertLinkedInTokenToToken(linkedInServiceAccessDTO
						.getAccessToken()), request);
		response = request.send();
		return response.getBody();
	}
}