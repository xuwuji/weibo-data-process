package com.xuwuji.wechat.client;

import com.xuwuji.weibo.util.Constants;

public class TweetClient {

	public void get() {
		String url = Constants.tweet_url + "?access_token=" + Constants.token;
	}

}
