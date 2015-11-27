package com.rajkrrsingh.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.StallWarning;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
public class TweetTopology {
  public static class TweetSpout extends BaseRichSpout
  {
    String custkey, custsecret;
    String accesstoken, accesssecret;
    SpoutOutputCollector collector;
    TwitterStream twitterStream;

    LinkedBlockingQueue<Tweet> queue = null;

    private class TweetListener implements StatusListener {

      @Override
      public void onStatus(Status status)
      {
    	TweetBuilder tweetBuilder = new TweetBuilder();
        queue.offer(tweetBuilder.tweetBuilerImpl(status));
      }

      @Override
      public void onDeletionNotice(StatusDeletionNotice sdn)
      {
      }

      @Override
      public void onTrackLimitationNotice(int i)
      {
      }

      @Override
      public void onScrubGeo(long l, long l1)
      {
      }

      @Override
      public void onStallWarning(StallWarning warning)
      {
      }

      @Override
      public void onException(Exception e)
      {
        e.printStackTrace();
      }
    };

    public TweetSpout(
        String                key,
        String                secret,
        String                token,
        String                tokensecret)
    {
      custkey = key;
      custsecret = secret;
      accesstoken = token;
      accesssecret = tokensecret;
    }

    @Override
    public void open(
        Map                     map,
        TopologyContext         topologyContext,
        SpoutOutputCollector    spoutOutputCollector)
    {
      queue = new LinkedBlockingQueue<Tweet>(1000);
      collector = spoutOutputCollector;

      ConfigurationBuilder config =
          new ConfigurationBuilder()
                 .setOAuthConsumerKey(custkey)
                 .setOAuthConsumerSecret(custsecret)
                 .setOAuthAccessToken(accesstoken)
                 .setOAuthAccessTokenSecret(accesssecret);

      TwitterStreamFactory fact =
          new TwitterStreamFactory(config.build());

      twitterStream = fact.getInstance();
      twitterStream.addListener(new TweetListener());
      twitterStream.sample();
    }

    @Override
    public void nextTuple()
    {
      Tweet ret = queue.poll();

      if (ret==null)
      {
        Utils.sleep(50);
        return;
      }

      collector.emit(new Values(ret));
    }

    @Override
    public void close()
    {
      twitterStream.shutdown();
    }

    @Override
    public Map<String, Object> getComponentConfiguration()
    {
      Config ret = new Config();
      ret.setMaxTaskParallelism(1);

      return ret;
    }

    @Override
    public void declareOutputFields(
        OutputFieldsDeclarer outputFieldsDeclarer)
    {
      outputFieldsDeclarer.declare(new Fields("tweet"));
    }
  }

  public static class MongoBolt extends BaseRichBolt
  {
    OutputCollector collector;
    MongoClient mongo;

    @Override
    public void prepare(
        Map                     map,
        TopologyContext         topologyContext,
        OutputCollector         outputCollector)
    {
      collector = outputCollector;
	try {
		mongo = new MongoClient("ip-10-0-0-233", 27017);
	} catch (UnknownHostException e) {
		e.printStackTrace();
	}
     
    }

    @Override
    public void execute(Tuple tuple)
    {
    	 DB db = mongo.getDB("tweets-db");
         DBCollection col = db.getCollection("tweetscol");
      Tweet tweet = (Tweet) tuple.getValue(0);
      DBObject dbObject = getDbObject(tweet);
      col.insert(dbObject);
    }
    
    public DBObject getDbObject(Tweet tweet){
    	BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
    	docBuilder.append("_id", tweet.getId())
    				.append("text", tweet.getText())
    				.append("createdAt", tweet.getCreatedAt())
    				.append("source", tweet.getSource())
    				.append("truncated",tweet.isTruncated())
    				.append("inReplyToStatusId",tweet.getInReplyToStatusId())
    				.append("inReplyToUserId", tweet.getInReplyToStatusId())
    				.append("inReplyToScreenName", tweet.getInReplyToScreenName())
    				.append("country", tweet.getCountry())
    				.append("longitude", tweet.getLongitude())
    				.append("latitude", tweet.getLatitude())
    				.append("favourite",tweet.isFavourite())
    				.append("retweeted", tweet.isRetweeted())
    				.append("favoriteCount", tweet.getFavouriteCount())
    				.append("username", tweet.getUser())
    				.append("contrubutors", tweet.getContributors())
    				.append("retweetCount",tweet.getRetweetCount())
    				.append("isRetweetByMe", tweet.isRetweetByMe())
    				.append("currrentUserRetweetId", tweet.getCurrentUserRetweetId())
    				.append("lang", tweet.getLang())
    				.append("withHeldInCountries", tweet.getWithHeldInContries());
    	return docBuilder.get();
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer)
    {
      declarer.declare(new Fields("tweet-word"));
    }
  }


  public static void main(String[] args) throws Exception
  {
    TopologyBuilder builder = new TopologyBuilder();
    TweetSpout tweetSpout = new TweetSpout(
        "JBF9oYAmIwPosU1hAUBqRcntY",
        "nwLuYoFtC3Pvulx9IHLfJGxBncG9BcXyLpCl2jwa2TablHBzOb",
        "18528977-1KV01AOASB5cZEkC0aZnR5THwDnU9hEuFBRpS3HVa",
        "v0kB0oD0TJJxska1VlH87fhBKYiYfYmPfvnoUKgJqDFoI"
    );

    // attach the tweet spout to the topology - parallelism of 1
    builder.setSpout("tweet-spout", tweetSpout, 1);
    builder.setBolt("save-mongo-bolt", new MongoBolt(),5).shuffleGrouping("tweet-spout");
    Config conf = new Config();
    conf.setDebug(true);

    if (args != null && args.length > 0) {
      conf.setNumWorkers(3);
      StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
    } else {
      conf.setMaxTaskParallelism(3);
      LocalCluster cluster = new LocalCluster();
      cluster.submitTopology("tweet-word-count", conf, builder.createTopology());
      Utils.sleep(30000);
      cluster.killTopology("tweet-word-count");
      cluster.shutdown();
    }
  }
}

