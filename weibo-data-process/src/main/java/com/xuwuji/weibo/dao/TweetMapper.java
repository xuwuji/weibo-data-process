package com.xuwuji.weibo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xuwuji.weibo.model.Tweet;

public interface TweetMapper {

	@Insert("insert into weibo_tweet (text,time,repostCount,commentCount,userId) values(#{text},#{time},#{repostCount},#{commentCount},#{userId})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void add(Tweet t);

	@Select("select text from weibo_tweet where userId=${UserId}")
	public List<Tweet> getAllTexts(@Param("UserId") int UserId);

	@Delete("delete w1 from weibo_tweet w1, weibo_tweet w2 where w1.text=w2.text and w1.id>w2.id")
	public void deleteDuplicated();
}
