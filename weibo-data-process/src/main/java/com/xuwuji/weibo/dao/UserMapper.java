package com.xuwuji.weibo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.xuwuji.weibo.model.User;

public interface UserMapper {

	@Insert("insert into tweet_user (userId,name,province,city,location,description,gender,followersCount,friendsCount,verifiedReason,abilityTags,time) values(#{userId},#{name},#{province},#{city},#{location},#{description},#{gender},#{followersCount},#{friendsCount},#{verifiedReason},#{abilityTags},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void add(User user);

	@Select("select * from tweet_user where name='${name}'")
	public User getByName(@Param("name") String name);

}
