package edu.sample.socialnetwork.constants;

public interface SocialNetworkConstant {

	String BASIC_USER_PROFILE_URL = "http://api.linkedin.com/v1/people/~";
	String BASIC_USER_CONNECTION_URL = "http://api.linkedin.com/v1/people/~/connections";
	String USER_NETWORK_UPDATE_URL = "http://api.linkedin.com/v1/people/~/network/updates";
	String GET_PEOPLE_BY_ID_URL = "http://api.linkedin.com/v1/people/id=";
	String GET_GROUP_POST_URL = "http://api.linkedin.com/v1/groups/%s/posts:(id,category,creator:(id,first-name,last-name),title,summary,creation-timestamp,site-group-post-url,comments,likes)";
	String GET_GROUP_MEMBERSHIP_URL = "http://api.linkedin.com/v1/people/~/group-memberships";
	String COMMUNICATION_INVITATION_URL = "https://api.linkedin.com/v1/people/~/mailbox";
	String COMMUNICATION_INVITATION_PATH = "/people/email=%s";
	String PEOPLE_SEARCH_URL  = "https://api.linkedin.com/v1/people-search:(people:(id,first-name,last-name,picture-url,headline),num-results)?first-name=%s&last-name=%s";

	String TWITTER_ACCESS_TOKEN_URL = "https://api.twitter.com/oauth2/token";
	String TWITTER_SEARCH_URL = "https://api.twitter.com/1.1/search/tweets.json?q=%{searchTag}";
	
	String PEOPLE_ID_ERROR = "People Id not found!";
	String GROUP_ID_ERROR = "Group Id not found!";
	String EMAIL_ID_ERROR = "Email Id not found!";
	String FIRST_NAME_ERROR = "First Name not found!";
	String LAST_NAME_ERROR = "Last Name not found!";
	String XML_GENERATE_ERROR = "ERROR will generating XML";
	String INVITATION_SENT_ERROR = "Failed in sending Invitation";
	String INVITATION_SENT_SUCCESS = "Invitation Sent Successfully";
	
	String TWITTER_SEARCH_URL_STATUS = "statuses";
	String TWITTER_SEARCH_URL_ID = "id";
	String TWITTER_SEARCH_URL_TEXT = "text";
	
}
