package edu.sample.socialnetwork.service.implement;

import org.apache.commons.lang.StringUtils;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import edu.sample.linkedin.model.InvitationRequest;
import edu.sample.linkedin.model.ItemContent;
import edu.sample.linkedin.model.MailboxItem;
import edu.sample.linkedin.model.Person;
import edu.sample.linkedin.model.Recipient;
import edu.sample.linkedin.model.Recipients;
import edu.sample.socialnetwork.constants.SocialNetworkConstant;
import edu.sample.socialnetwork.dto.LinkedInServiceAccessDTO;
import edu.sample.socialnetwork.utilities.AuthHandler;
import edu.sample.socialnetwork.utilities.JAXBUtils;
import edu.sample.socialnetwork.utilities.RestClient;


/**
 * LinkedIn Services
 * 
 * @author Ankush
 *
 */
@Component
public class LinkedInServiceImpl {

	@Autowired
	AuthHandler authHandler;

	@Autowired
	JAXBUtils jaxbUtils;

	@Autowired
	RestClient restClient;

	@Value("#{social.https_proxy}")
	private String httpsProxy;

	@Value("#{social.https_port}")
	private String httpsPort;

	@Value("#{social.http_proxy}")
	private String httpProxy;

	@Value("#{social.http_port}")
	private String httpPort;

	@Value("#{social.invitation_body}")
	private String invitationBody;

	@Value("#{social.invitation_subject}")
	private String invitationSubject;

	@Value("#{social.invitation_connect_type}")
	private String invitationConnectType;

	static OAuthRequest request;
	static Response response;

	private static Logger LOGGER = LoggerFactory
			.getLogger(LinkedInServiceImpl.class);

	/**
	 * @param linkedInServiceAccessDTO
	 * @return
	 */
	public String getBasicProfile(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		LOGGER.info("********Get the profile ********");
		// This basic call profile in JSON format
		// You can read more about JSON here http://json.org

		return restClient.performFunctionLinkedIn(
				SocialNetworkConstant.BASIC_USER_PROFILE_URL,
				linkedInServiceAccessDTO);
	}

	/**
	 * @param linkedInServiceAccessDTO
	 * @return
	 */
	public String getResourceConnection(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		LOGGER.info("********Get my connections - going into a resource********");
		// This basic call gets all your connections each one will be in a
		// person tag with some profile information
		// https://developer.linkedin.com/documents/connections-api
		return restClient.performFunctionLinkedIn(
				SocialNetworkConstant.BASIC_USER_CONNECTION_URL,
				linkedInServiceAccessDTO);

	}

	/**
	 * @param linkedInServiceAccessDTO
	 * @return
	 */
	public String getNetworkConnectUpdates(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		LOGGER.info("********GET network updates Based on Type********");

		if (linkedInServiceAccessDTO.getNetworkUpdateType() != null
				&& linkedInServiceAccessDTO.getNetworkUpdateType().size() > 0) {
			return restClient.performFunctionLinkedIn(
					SocialNetworkConstant.USER_NETWORK_UPDATE_URL,
					linkedInServiceAccessDTO,
					linkedInServiceAccessDTO.getNetworkUpdateType());
		} else {
			return restClient.performFunctionLinkedIn(
					SocialNetworkConstant.USER_NETWORK_UPDATE_URL,
					linkedInServiceAccessDTO);
		}
	}

	/**
	 * @param linkedInServiceAccessDTO
	 * @return
	 */
	public String getPeopleById(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		LOGGER.info("********A People Profile based on ID********");
		if (StringUtils.isNotBlank(linkedInServiceAccessDTO.getPeopleId())) {
			return restClient.performFunctionLinkedIn(
					SocialNetworkConstant.GET_PEOPLE_BY_ID_URL
							+ linkedInServiceAccessDTO.getPeopleId(),
					linkedInServiceAccessDTO);
		} else {
			return SocialNetworkConstant.PEOPLE_ID_ERROR;
		}
	}

	/**
	 * @param linkedInServiceAccessDTO
	 * @return
	 */
	public String searchPeopleByFirstAndLastName(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		LOGGER.info("********A People Profile based on First and Last Name********");
		if (StringUtils.isNotBlank(linkedInServiceAccessDTO.getFirstName())
				&& StringUtils.isNotBlank(linkedInServiceAccessDTO
						.getLastName())) {
			return restClient.performFunctionLinkedIn(String.format(
					SocialNetworkConstant.PEOPLE_SEARCH_URL,
					linkedInServiceAccessDTO.getFirstName(),
					linkedInServiceAccessDTO.getLastName()),
					linkedInServiceAccessDTO);
		} else {
			if (StringUtils.isBlank(linkedInServiceAccessDTO.getFirstName())) {
				return SocialNetworkConstant.FIRST_NAME_ERROR;
			} else {
				return SocialNetworkConstant.LAST_NAME_ERROR;
			}
		}
	}

	/**
	 * @param linkedInServiceAccessDTO
	 * @return
	 */
	public String postsOfGroups(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		LOGGER.info("********fetching postings into groups********");
		if (StringUtils.isNotBlank(linkedInServiceAccessDTO.getGroupId())) {
			return restClient.performFunctionLinkedIn(String.format(
					SocialNetworkConstant.GET_GROUP_POST_URL,
					linkedInServiceAccessDTO.getGroupId()),
					linkedInServiceAccessDTO);
		} else {
			return SocialNetworkConstant.GROUP_ID_ERROR;
		}
	}

	/**
	 * @param linkedInServiceAccessDTO
	 * @return
	 */
	public String fetchGroupMembership(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		LOGGER.info("********Fetch Group Membership********");

		// https://developer.linkedin.com/documents/connections-api
		return restClient.performFunctionLinkedIn(
				SocialNetworkConstant.GET_GROUP_MEMBERSHIP_URL,
				linkedInServiceAccessDTO);
	}

	/**
	 * @param linkedInServiceAccessDTO
	 * @return
	 */
	public String sendInvitationRequest(
			LinkedInServiceAccessDTO linkedInServiceAccessDTO) {
		LOGGER.info("********Send Connection Request********");

		if (StringUtils.isNotBlank(linkedInServiceAccessDTO.getEmailId())
				&& StringUtils.isNotBlank(linkedInServiceAccessDTO
						.getFirstName())
				&& StringUtils.isNotBlank(linkedInServiceAccessDTO
						.getLastName())) {
			String payLoader = convertMailBoxToXML(
					linkedInServiceAccessDTO.getEmailId(),
					linkedInServiceAccessDTO.getFirstName(),
					linkedInServiceAccessDTO.getLastName());
			if (payLoader != null) {
				Response response = restClient
						.performFunctionLinkedInInvitation(
								SocialNetworkConstant.COMMUNICATION_INVITATION_URL,
								linkedInServiceAccessDTO, payLoader);
				LOGGER.info("Status Code ---->" + response.getCode());
				if (response.getCode() > 300) {
					return SocialNetworkConstant.INVITATION_SENT_ERROR;
				} else {
					return SocialNetworkConstant.INVITATION_SENT_SUCCESS;
				}
			} else {
				return SocialNetworkConstant.XML_GENERATE_ERROR;
			}
		} else {
			if (StringUtils.isBlank(linkedInServiceAccessDTO.getEmailId())) {
				return SocialNetworkConstant.EMAIL_ID_ERROR;
			} else if (StringUtils.isBlank(linkedInServiceAccessDTO
					.getFirstName())) {
				return SocialNetworkConstant.FIRST_NAME_ERROR;
			} else {
				return SocialNetworkConstant.LAST_NAME_ERROR;
			}
		}

	}

	/**
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	private String convertMailBoxToXML(String email, String firstName,
			String lastName) {
		MailboxItem mailboxItem = new MailboxItem();
		mailboxItem.setBody(invitationBody);
		mailboxItem.setSubject(invitationSubject);

		Person person = new Person();
		person.setPath(String.format(
				SocialNetworkConstant.COMMUNICATION_INVITATION_PATH, email));
		person.setFirstName(firstName);
		person.setLastName(lastName);
		Recipient recipient = new Recipient();
		recipient.setPerson(person);
		Recipients recipients = new Recipients();
		recipients.setRecipient(recipient);

		mailboxItem.setRecipients(recipients);
		InvitationRequest invitationRequest = new InvitationRequest();
		invitationRequest.setConnectType(invitationConnectType);
		ItemContent itemContent = new ItemContent();
		itemContent.setInvitationRequest(invitationRequest);
		mailboxItem.setItemContent(itemContent);

		return jaxbUtils.convertMailBoxItemToXML(mailboxItem);
	}
}
