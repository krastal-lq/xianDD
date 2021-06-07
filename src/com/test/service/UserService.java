package com.test.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.test.dao.UserDao;
import com.test.pojo.BaseDataPojo;
import com.test.pojo.User;
import com.test.util.DButil;
import com.google.gson.Gson;

public class UserService {
	
	// main方法用来测试业务层的所有方法，保证数据正确性
	public static void main(String[] args) {
		UserService userService = new UserService();
		System.out.println(new Gson().toJson
				(userService.getCredit(3)));
	}

	// 获取用户积分
	public BaseDataPojo<Map<String, Object>> getCredit(int id) {
		User user = selectById(id);
		Map<String, Object> map = new HashMap<>();
		if (user != null) {
			map.put("credit", user.getCredit());
			return new BaseDataPojo<Map<String, Object>>("获取积分成功", true, map);
		} else {
			return new BaseDataPojo<Map<String, Object>>("获取积分失败", false, null);
		}
	}
	
	// 通过id查询用户信息
	public User selectById(int id) {
		Connection conn = DButil.getConnection();
		UserDao userDao = new UserDao(conn);
		User user = null;
		try {
			user = userDao.selectById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				DButil.closeConnection(conn);
			}
		}
		return user;
	}
}
