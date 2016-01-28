package com.xuwuji.weibo.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.xuwuji.weibo.dao.TweetMapper;
import com.xuwuji.weibo.model.Tweet;
import com.xuwuji.weibo.util.Constants;
import com.xuwuji.weibo.util.HttpUtil;
import com.xuwuji.weibo.util.MyBatisUtil;

public class TweetService {
	// private HashSet<String> set;

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

	public void parseResponse(String input) throws ParseException {
		if (input.equals("") || input == null) {
			return;
		}
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(input);
		JSONArray tweets = (JSONArray) obj.get("statuses");
		for (int i = 0; i < tweets.size(); i++) {
			JSONObject tweet = (JSONObject) tweets.get(i);
			String time = tweet.get("created_at").toString();
			JSONObject user = (JSONObject) tweet.get("user");
			String name = user.get("name").toString();
			String text = tweet.get("text").toString();
			int repost = Integer.valueOf(tweet.get("reposts_count").toString());
			int comment = Integer.valueOf(tweet.get("comments_count").toString());
			HashSet<String> set = this.getAllTexts(name);
			System.out.println(name);
			for (String s : set) {
				System.out.println(s);

			}
			System.out.println("------------");
			System.out.println("------------");

			if (!set.contains(text)) {
				Tweet t = new Tweet.Builder().name(name).text(text).time(time).repostCount(repost).commentCount(comment)
						.build();
				System.out.println("-----this is " + (i + 1) + "-------");
				System.out.println(time);
				System.out.println(name);
				System.out.println(text);
				System.out.println("------------");
				System.out.println("\n");
				 this.add(t);
			}

		}
	}

	public void add(Tweet t) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		TweetMapper mapper = session.getMapper(TweetMapper.class);
		mapper.add(t);
		session.commit();
		session.close();
	}

	public HashSet<String> getAllTexts(String name) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		TweetMapper mapper = session.getMapper(TweetMapper.class);
		List<Tweet> list = mapper.getAllTexts(name);
		HashSet<String> set = new HashSet();
		// for (Entry<String, String> entry : map.entrySet()) {
		// set.add(entry.getValue());
		// }
		for (Tweet t : list) {
			set.add(t.getText());
		}
		session.commit();
		session.close();
		return set;
	}

	public static void main(String[] args) throws ParseException {
		TweetService t = new TweetService();
		for (int i = 1; i <= 3; i++) {
			String response = t.getLatestTweets(i);
			t.parseResponse(response);
		}
	}

}
