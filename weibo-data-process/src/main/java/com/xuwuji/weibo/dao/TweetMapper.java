package com.xuwuji.weibo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xuwuji.weibo.model.Tweet;

public interface TweetMapper {

	@Insert("insert into tweet (name,text,time,repostCount,commentCount) values(#{name},#{text},#{time},#{repostCount},#{commentCount})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void add(Tweet t);

	@Select("select text from tweet where name='${name}'")
	public List<Tweet> getAllTexts(@Param("name") String name);
}
