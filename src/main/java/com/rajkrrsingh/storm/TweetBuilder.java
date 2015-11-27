package com.rajkrrsingh.storm;

import twitter4j.Status;

public class TweetBuilder {
	
	public Tweet tweetBuilerImpl(Status status){
		Tweet tweet = new Tweet();
		tweet.setCreatedAt(status.getCreatedAt());
		tweet.setId(status.getId());
		tweet.setSource(status.getSource());
		tweet.setTruncated(status.isTruncated());
		tweet.setInReplyToStatusId(status.getInReplyToStatusId());
		tweet.setInReplyToUserId(status.getInReplyToUserId());
		tweet.setInReplyToScreenName(status.getInReplyToScreenName());
		tweet.setLatitude(status.getGeoLocation()!=null?status.getGeoLocation().getLatitude():0);
		tweet.setLongitude(status.getGeoLocation()!=null?status.getGeoLocation().getLongitude():0);
		tweet.setCountry(status.getPlace()!=null?status.getPlace().getCountry():"NA");
		tweet.setFavourite(status.isFavorited());
		tweet.setRetweet(status.isRetweet());
		tweet.setFavouriteCount(status.getFavoriteCount());
		tweet.setUser(status.getUser().getName());
		tweet.setRetweet(status.isRetweet());
		tweet.setContributors(status.getContributors());
		tweet.setRetweetCount(status.getRetweetCount());
		tweet.setRetweetByMe(status.isRetweetedByMe());
		tweet.setCurrentUserRetweetId(status.getCurrentUserRetweetId());
		tweet.setPossibleySensitive(status.isPossiblySensitive());
		tweet.setLang(status.getLang());
		tweet.setGetScopes(status.getScopes());
		tweet.setWithHeldInContries(status.getWithheldInCountries());
		return tweet;
	}
	
}

