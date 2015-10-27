package edu.sample.socialnetwork.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import edu.sample.socialnetwork.constants.SocialNetworkConstant;
import edu.sample.socialnetwork.dto.TweetDTO;
import edu.sample.socialnetwork.dto.TwitterSearchTweetRequestDTO;

/**
 * Twitter Services
 * 
 * @author Ankush
 * 
 */
@Component
public class TwitterServiceImpl {

	@SuppressWarnings("unchecked")
	public List<TweetDTO> searchTwitterByHashTag(
			TwitterSearchTweetRequestDTO twitterSearchTweetRequestDTO) {
		// TODO Auto-generated method stub
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization",
				"Bearer " + twitterSearchTweetRequestDTO.getAccessToken());
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		
		Map<String, ?> result = rest.exchange(
				SocialNetworkConstant.TWITTER_SEARCH_URL, HttpMethod.GET,
				requestEntity, Map.class,
				twitterSearchTweetRequestDTO.getSearchTag()).getBody();
		List<Map<String, ?>> statuses = (List<Map<String, ?>>) result
				.get(SocialNetworkConstant.TWITTER_SEARCH_URL_STATUS);
		List<TweetDTO> tweets = new ArrayList<TweetDTO>();
		for (Map<String, ?> status : statuses) {
			TweetDTO tweetDTO = new TweetDTO();
			tweetDTO.setId(Long.valueOf(status.get(
					SocialNetworkConstant.TWITTER_SEARCH_URL_ID).toString()));
			tweetDTO.setText(status.get(
					SocialNetworkConstant.TWITTER_SEARCH_URL_TEXT).toString());
			tweets.add(tweetDTO);
		}
		return tweets;
	}

}
