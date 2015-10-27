/**
 *
 */
package edu.sample.socialnetwork.service.activator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sample.socialnetwork.dto.LinkedInServiceAccessDTO;
import edu.sample.socialnetwork.service.implement.LinkedInServiceImpl;

/**
 * @author Ankush
 * 
 */
@Component
public class LinkedInServiceActivator implements IServiceActivator {

	@Autowired
	LinkedInServiceImpl linkedInServiceImpl;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LinkedInServiceActivator.class);

	public String linkedInBasicProfile(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		// Getting Service Proxy from Service Registry.
		LOGGER.info("LinkedIn Basci Profile activator start ---->");

		return linkedInServiceImpl.getBasicProfile(linkedInServiceAccessDTO);
	}

	public String linkedInBasicResourceConnection(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		// Getting Service Proxy from Service Registry.
		LOGGER.info("LinkedIn Basic Profile activator start ---->");

		return linkedInServiceImpl
				.getResourceConnection(linkedInServiceAccessDTO);
	}

	public String linkedInNetworkConnectUpdates(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		// Getting Service Proxy from Service Registry.
		LOGGER.info("LinkedIn Network Connection Updates start ---->");

		return linkedInServiceImpl
				.getNetworkConnectUpdates(linkedInServiceAccessDTO);
	}

	public String linkedInPeopleById(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		// Getting Service Proxy from Service Registry.
		LOGGER.info("LinkedIn Search People By ID start ---->");

		return linkedInServiceImpl.getPeopleById(linkedInServiceAccessDTO);
	}
	
	public String linkedInPostsOfGroup(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		// Getting Service Proxy from Service Registry.
		LOGGER.info("LinkedIn POST of groups start ---->");

		return linkedInServiceImpl.postsOfGroups(linkedInServiceAccessDTO);
	}
	
	public String linkedInFetchGroupMembership(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		// Getting Service Proxy from Service Registry.
		LOGGER.info("LinkedIn Fetch Group Membership start ---->");

		return linkedInServiceImpl.fetchGroupMembership(linkedInServiceAccessDTO);
	}
	
	public String linkedInSendInvitationRequest(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		// Getting Service Proxy from Service Registry.
		LOGGER.info("LinkedIn Send Invitation Request start ---->");

		return linkedInServiceImpl
				.sendInvitationRequest(linkedInServiceAccessDTO);
	}
	
	public String searchPeopleByFirstAndLastName(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		// Getting Service Proxy from Service Registry.
		LOGGER.info("LinkedIn Search People Request start ---->");

		return linkedInServiceImpl
				.searchPeopleByFirstAndLastName(linkedInServiceAccessDTO);
	}
}
