package com.xuwuji.weibo.model;

import java.util.List;

public class User {

	private int id;
	private String userId;
	private String name;
	private int province;
	private int city;
	private String location;
	private String description;
	private String gender;
	private int followersCount;
	private int friendsCount;
	private String verifiedReason;
	private String abilityTags;
	private String time;
	private List<Tweet> list;

	public User() {

	}

	public User(Builder builder) {
		id = builder.id;
		userId = builder.userId;
		name = builder.name;
		province = builder.province;
		city = builder.city;
		location = builder.location;
		description = builder.description;
		gender = builder.gender;
		followersCount = builder.followersCount;
		friendsCount = builder.friendsCount;
		verifiedReason = builder.verifiedReason;
		abilityTags = builder.abilityTags;
		time = builder.time;
		list = builder.list;
	}

	public static class Builder {
		private int id;
		private String userId;
		private String name;
		private int province;
		private int city;
		private String location;
		private String description;
		private String gender;
		private int followersCount;
		private int friendsCount;
		private String verifiedReason;
		private String abilityTags;
		private String time;
		private List<Tweet> list;

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder userId(String userId) {
			this.userId = userId;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder province(int province) {
			this.province = province;
			return this;
		}

		public Builder city(int city) {
			this.city = city;
			return this;
		}

		public Builder location(String location) {
			this.location = location;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder gender(String gender) {
			this.gender = gender;
			return this;
		}

		public Builder followersCount(int followersCount) {
			this.followersCount = followersCount;
			return this;
		}

		public Builder friendsCount(int friendsCount) {
			this.friendsCount = friendsCount;
			return this;
		}

		public Builder verifiedReason(String verifiedReason) {
			this.verifiedReason = verifiedReason;
			return this;
		}

		public Builder abilityTags(String abilityTags) {
			this.abilityTags = abilityTags;
			return this;
		}

		public Builder time(String time) {
			this.time = time;
			return this;
		}

		public Builder list(List<Tweet> list) {
			this.list = list;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}

	public int getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public int getProvince() {
		return province;
	}

	public int getCity() {
		return city;
	}

	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}

	public String getGender() {
		return gender;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public int getFriendsCount() {
		return friendsCount;
	}

	public String getVerifiedReason() {
		return verifiedReason;
	}

	public String getAbilityTags() {
		return abilityTags;
	}

	public String getTime() {
		return time;
	}

	public List<Tweet> getList() {
		return list;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", name=" + name + ", province=" + province + ", city=" + city
				+ ", location=" + location + ", description=" + description + ", gender=" + gender + ", followersCount="
				+ followersCount + ", friendsCount=" + friendsCount + ", verifiedReason=" + verifiedReason
				+ ", abilityTags=" + abilityTags + ", time=" + time + ", list=" + list + "]";
	}

}
