/**
 *
 */
package edu.sample.socialnetwork.service.activator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sample.socialnetwork.dto.AuthenticationInitialDTO;
import edu.sample.socialnetwork.dto.LinkedInAccessTokenRequestDTO;
import edu.sample.socialnetwork.dto.LinkedInAuthorizeResponseDTO;
import edu.sample.socialnetwork.dto.LinkedInToken;
import edu.sample.socialnetwork.service.implement.AuthenticateServiceImpl;

/**
 * @author Ankush
 * 
 */
@Component
public class AuthenticationServiceActivator implements IServiceActivator {

	@Autowired
	AuthenticateServiceImpl authenticateServiceImplementor;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthenticationServiceActivator.class);

	/**
	 * @param apiAuthenticationInitialDTO
	 * @return
	 */
	public LinkedInAuthorizeResponseDTO linkedInAuthorization(
			AuthenticationInitialDTO apiAuthenticationInitialDTO) {
		LOGGER.info("LinkedIn Authorization activator start ---->");

		return authenticateServiceImplementor.linkedInAuthorization(
				apiAuthenticationInitialDTO.getApiKey(),
				apiAuthenticationInitialDTO.getApiSecret());
	}

	/**
	 * @param linkedInAccessTokenRequestDTO
	 * @return
	 */
	public LinkedInToken linkedInAccessToken(
			LinkedInAccessTokenRequestDTO linkedInAccessTokenRequestDTO) {
		LOGGER.info("LinkedIn Access Token activator start ---->");

		LinkedInToken linkedInToken = authenticateServiceImplementor
		.linkedInAccessToken(linkedInAccessTokenRequestDTO);
		return linkedInToken;
	}
	
	/**
	 * @param apiAuthenticationInitialDTO
	 * @return
	 */
	public String twitterAuthentication(
			AuthenticationInitialDTO apiAuthenticationInitialDTO) {
		LOGGER.info("Twitter Authentication activator start ---->");

		return authenticateServiceImplementor.twitterAuthentication(
				apiAuthenticationInitialDTO.getApiKey(),
				apiAuthenticationInitialDTO.getApiSecret());
	}
}
