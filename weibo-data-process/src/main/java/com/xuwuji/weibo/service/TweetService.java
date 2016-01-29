package com.xuwuji.weibo.service;

import java.util.HashSet;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.xuwuji.weibo.dao.TweetMapper;
import com.xuwuji.weibo.model.Tweet;
import com.xuwuji.weibo.model.User;
import com.xuwuji.weibo.util.Constants;
import com.xuwuji.weibo.util.HttpUtil;
import com.xuwuji.weibo.util.MyBatisUtil;

public class TweetService {
	private int count;

	public TweetService() {
		// this.set = new HashSet();
	}

	public String getLatestTweets(int page) {
		String url = Constants.tweet_url + "?access_token=" + Constants.token + "&count=100&page=" + page;
		String response = null;
		try {
			response = HttpUtil.Get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public void parseResponse(int page, String input) throws ParseException {
		if (input.equals("") || input == null) {
			return;
		}
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(input);
		JSONArray tweets = (JSONArray) obj.get("statuses");
		// parse each tweet
		for (int i = 0; i < tweets.size(); i++) {
			JSONObject tweet = (JSONObject) tweets.get(i);

			// 1.get user info of this tweet
			JSONObject user = (JSONObject) tweet.get("user");
			String UserName = user.get("name").toString();
			int province = Integer.valueOf(user.get("province").toString());
			int city = Integer.valueOf(user.get("city").toString());
			String location = user.get("location").toString();
			String gender = user.get("gender").toString();
			int friends_count = Integer.valueOf(user.get("friends_count").toString());
			int followers_count = Integer.valueOf(user.get("followers_count").toString());
			String userId = user.get("idstr").toString();
			String user_time = user.get("created_at").toString();
			String description = user.get("description").toString();
			String verified_reason = user.get("verified_reason").toString();
			String ability_tags = "";
			if (user.get("ability_tags") != null) {
				ability_tags = user.get("ability_tags").toString();
			}
			User userInfo = new User.Builder().name(UserName).province(province).city(city).location(location)
					.description(description).gender(gender).friendsCount(friends_count).followersCount(followers_count)
					.verifiedReason(verified_reason).abilityTags(ability_tags).time(user_time).userId(userId).build();
			// System.out.println(userInfo.toString());
			// 2. if this user is not in db yet, persistent it
			UserService userDB = new UserService();
			if (!userDB.exist(UserName)) {
				// System.out.println("this user is not in the db");
				userDB.add(userInfo);
			}

			// 3. check this tweet is in the db or not based on this user id
			int userDBId = userDB.getByName(UserName).getId();
			HashSet<String> set = this.getAllTextsByUserId(userDBId);

			String Tweet_Time = tweet.get("created_at").toString();
			int repost = Integer.valueOf(tweet.get("reposts_count").toString());
			int comment = Integer.valueOf(tweet.get("comments_count").toString());
			String text = tweet.get("text").toString();
			String tweetId = tweet.get("idstr").toString();

			// 4. if this a new tweet from this user, persistent is to DB
			if (set == null || !set.contains(text)) {

				Tweet t = new Tweet.Builder().tweetId(tweetId).text(text).time(Tweet_Time).repostCount(repost)
						.commentCount(comment).userId(userDBId).build();

				System.out.println("-----this is " + (i + 1) + " tweet in page " + page + " -------");
				System.out.println("create at:" + Tweet_Time);
				System.out.println("user:" + UserName);
				System.out.println("text:" + text);
				System.out.println("\n");
				this.addTweet(t);
				count++;
			}
		}
	}

	public void addTweet(Tweet tweet) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			TweetMapper mapper = session.getMapper(TweetMapper.class);
			mapper.add(tweet);
			session.commit();
		} finally {
			session.close();
		}
	}

	public HashSet<String> getAllTextsByUserId(int UserId) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		TweetMapper mapper = session.getMapper(TweetMapper.class);
		List<Tweet> list = mapper.getAllTexts(UserId);
		if (list == null) {
			return null;
		}
		HashSet<String> set = new HashSet<String>();
		for (Tweet t : list) {
			set.add(t.getText());
		}
		session.commit();
		session.close();
		return set;
	}

	public void deleteDuplicated() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			TweetMapper mapper = session.getMapper(TweetMapper.class);
			mapper.deleteDuplicated();
			session.commit();
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) throws ParseException {
		TweetService t = new TweetService();
		for (int i = 1; i <= 10; i++) {
			String response = t.getLatestTweets(i);
			t.parseResponse(i, response);
		}
		System.out.println("total tweet: " + t.count);
		t.deleteDuplicated();
	}

}
