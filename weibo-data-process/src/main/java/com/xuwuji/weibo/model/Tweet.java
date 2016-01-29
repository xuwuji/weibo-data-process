package com.xuwuji.weibo.model;

public class Tweet {
	private String id;
	private String tweetId;
	private String text;
	private String time;
	private int repostCount;
	private int commentCount;
	private int userId;

	private Tweet(Builder builder) {
		id = builder.id;
		tweetId = builder.tweetId;
		text = builder.text;
		time = builder.time;
		repostCount = builder.repostCount;
		commentCount = builder.commentCount;
		userId = builder.userId;

	}

	public Tweet() {

	}

	public static class Builder {
		public Builder() {

		}

		private String id;
		private String tweetId;
		private String text;
		private String time;
		private int repostCount;
		private int commentCount;
		private int userId;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder tweetId(String tweetId) {
			this.tweetId = tweetId;
			return this;
		}

		public Builder text(String text) {
			this.text = text;
			return this;
		}

		public Builder time(String time) {
			this.time = time;
			return this;
		}

		public Builder repostCount(int count) {
			this.repostCount = count;
			return this;
		}

		public Builder commentCount(int count) {
			this.commentCount = count;
			return this;
		}

		public Builder userId(int userId) {
			this.userId = userId;
			return this;
		}

		public Tweet build() {
			return new Tweet(this);
		}

	}

	public String getId() {
		return id;
	}

	public String getTweetId() {
		return tweetId;
	}

	public String getText() {
		return text;
	}

	public String getTime() {
		return time;
	}

	public int getRepostCount() {
		return repostCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public int getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", tweetId=" + tweetId + ", text=" + text + ", time=" + time + ", repostCount="
				+ repostCount + ", commentCount=" + commentCount + ", userId=" + userId + "]";
	}

}
