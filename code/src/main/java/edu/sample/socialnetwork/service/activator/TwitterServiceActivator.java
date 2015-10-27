/**
 *
 */
package edu.sample.socialnetwork.service.activator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sample.socialnetwork.dto.TweetDTO;
import edu.sample.socialnetwork.dto.TwitterSearchTweetRequestDTO;
import edu.sample.socialnetwork.service.implement.TwitterServiceImpl;

/**
 * @author Ankush
 * 
 */
@Component
public class TwitterServiceActivator implements IServiceActivator {

	@Autowired
	TwitterServiceImpl twitterServiceImpl;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TwitterServiceActivator.class);

	public List<TweetDTO> searchTwitterByHashTag(
			TwitterSearchTweetRequestDTO twitterSearchTweetRequestDTO) {
		// Getting Service Proxy from Service Registry.
		LOGGER.info("Twitter Search Tweet By Tag start ---->");

		return twitterServiceImpl.searchTwitterByHashTag(twitterSearchTweetRequestDTO);
	}
}
