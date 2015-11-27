package com.rajkrrsingh.storm;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import twitter4j.Place;
import twitter4j.Scopes;
import twitter4j.User;

public class Tweet {
	private Date createdAt;
	private long Id;
	private String text;
	private String source;
	private boolean truncated;
	private long inReplyToStatusId;
	private long inReplyToUserId;
	private String inReplyToScreenName;
	private double latitude;
	private double longitude;
	private String country;
	private boolean isFavourite;
	private boolean isRetweeted;
	private int favouriteCount;
	private String user;
	private boolean isRetweet;
	private long[] contributors;
	private int retweetCount;
	private boolean isRetweetByMe;  
	private long currentUserRetweetId;
	private boolean isPossibleySensitive;  
	private String lang;  
	private Scopes getScopes;  
	private String[] withHeldInContries;
	
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public boolean isTruncated() {
		return truncated;
	}
	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}
	public long getInReplyToStatusId() {
		return inReplyToStatusId;
	}
	public void setInReplyToStatusId(long inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}
	public long getInReplyToUserId() {
		return inReplyToUserId;
	}
	public void setInReplyToUserId(long inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}
	public String getInReplyToScreenName() {
		return inReplyToScreenName;
	}
	public void setInReplyToScreenName(String inReplyToScreenName) {
		this.inReplyToScreenName = inReplyToScreenName;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public boolean isFavourite() {
		return isFavourite;
	}
	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}
	public boolean isRetweeted() {
		return isRetweeted;
	}
	public void setRetweeted(boolean isRetweeted) {
		this.isRetweeted = isRetweeted;
	}
	
	public int getFavouriteCount() {
		return favouriteCount;
	}
	public void setFavouriteCount(int favouriteCount) {
		this.favouriteCount = favouriteCount;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public boolean isRetweet() {
		return isRetweet;
	}
	public void setRetweet(boolean isRetweet) {
		this.isRetweet = isRetweet;
	}
	public long[] getContributors() {
		return contributors;
	}
	public void setContributors(long[] contributors) {
		this.contributors = contributors;
	}
	public int getRetweetCount() {
		return retweetCount;
	}
	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}
	public boolean isRetweetByMe() {
		return isRetweetByMe;
	}
	public void setRetweetByMe(boolean isRetweetByMe) {
		this.isRetweetByMe = isRetweetByMe;
	}
	public long getCurrentUserRetweetId() {
		return currentUserRetweetId;
	}
	public void setCurrentUserRetweetId(long currentUserRetweetId) {
		this.currentUserRetweetId = currentUserRetweetId;
	}
	public boolean isPossibleySensitive() {
		return isPossibleySensitive;
	}
	public void setPossibleySensitive(boolean isPossibleySensitive) {
		this.isPossibleySensitive = isPossibleySensitive;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public Scopes getGetScopes() {
		return getScopes;
	}
	public void setGetScopes(Scopes getScopes) {
		this.getScopes = getScopes;
	}
	public String[] getWithHeldInContries() {
		return withHeldInContries;
	}
	public void setWithHeldInContries(String[] withHeldInContries) {
		this.withHeldInContries = withHeldInContries;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Id ^ (Id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tweet other = (Tweet) obj;
		if (Id != other.Id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Tweet [createdAt=" + createdAt + ", Id=" + Id + ", text=" + text + ", source=" + source + ", truncated="
				+ truncated + ", inReplyToStatusId=" + inReplyToStatusId + ", inReplyToUserId=" + inReplyToUserId
				+ ", inReplyToScreenName=" + inReplyToScreenName + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", country=" + country + ", isFavourite=" + isFavourite + ", isRetweeted=" + isRetweeted
				+ ", favouriteCount=" + favouriteCount + ", user=" + user + ", isRetweet=" + isRetweet
				+ ", getRetweetedStatus=" + new Random().nextInt(100) + ", contributors=" + Arrays.toString(contributors)
				+ ", retweetCount=" + retweetCount + ", isRetweetByMe=" + isRetweetByMe + ", currentUserRetweetId="
				+ currentUserRetweetId + ", isPossibleySensitive=" + isPossibleySensitive + ", lang=" + lang
				+ ", getScopes=" + getScopes + ", withHeldInContries=" + Arrays.toString(withHeldInContries)
				+ ", getCreatedAt()=" + getCreatedAt() + ", getId()=" + getId() + ", getText()=" + getText()
				+ ", getSource()=" + getSource() + ", isTruncated()=" + isTruncated() + ", getInReplyToStatusId()="
				+ getInReplyToStatusId() + ", getInReplyToUserId()=" + getInReplyToUserId()
				+ ", getInReplyToScreenName()=" + getInReplyToScreenName() + ", getLatitude()=" + getLatitude()
				+ ", getLongitude()=" + getLongitude() + ", getCountry()=" + getCountry() + ", isFavourite()="
				+ isFavourite() + ", isRetweeted()=" + isRetweeted() + ", getFavouriteCount()=" + getFavouriteCount()
				+ ", getUser()=" + getUser() + ", isRetweet()=" + isRetweet() + ", getContributors()=" + Arrays.toString(getContributors())
				+ ", getRetweetCount()=" + getRetweetCount() + ", isRetweetByMe()=" + isRetweetByMe()
				+ ", getCurrentUserRetweetId()=" + getCurrentUserRetweetId() + ", isPossibleySensitive()="
				+ isPossibleySensitive() + ", getLang()=" + getLang() + ", getGetScopes()=" + getGetScopes()
				+ ", getWithHeldInContries()=" + Arrays.toString(getWithHeldInContries()) + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
	

}

