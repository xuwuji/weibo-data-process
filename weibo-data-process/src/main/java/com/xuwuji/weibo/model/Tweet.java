package com.xuwuji.weibo.model;

public class Tweet {
	private String id;
	private String name;
	private String text;
	private String time;
	private int repostCount;
	private int commentCount;

	private Tweet(Builder builder) {
		id = builder.id;
		name = builder.name;
		text = builder.text;
		time = builder.time;
		repostCount = builder.repostCount;
		commentCount = builder.commentCount;

	}

	public Tweet() {

	}

	public static class Builder {
		public Builder() {

		}

		private String id;
		private String name;
		private String text;
		private String time;
		private int repostCount;
		private int commentCount;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
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

		public Tweet build() {
			return new Tweet(this);
		}

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
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

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", name=" + name + ", text=" + text + ", time=" + time + ", repostCount="
				+ repostCount + ", commentCount=" + commentCount + "]";
	}

}
