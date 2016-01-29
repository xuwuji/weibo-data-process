package com.xuwuji.weibo.service;

import org.apache.ibatis.session.SqlSession;

import com.xuwuji.weibo.dao.UserMapper;
import com.xuwuji.weibo.model.User;
import com.xuwuji.weibo.util.MyBatisUtil;

public class UserService {

	public boolean exist(String name) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			User user = mapper.getByName(name);
			session.commit();
			if (user == null || user.getName().equals("")) {
				return false;
			} else {
				return true;
			}
		} finally {
			session.close();
		}
	}

	public void add(User user) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.add(user);
			session.commit();
		} finally {
			session.close();
		}
	}

	public User getByName(String name) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			User user = mapper.getByName(name);
			session.commit();
			return user;
		} finally {
			session.close();
		}
	}

}
