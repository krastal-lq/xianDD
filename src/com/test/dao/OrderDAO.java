package com.test.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.test.pojo.Order;


public interface OrderDAO {
	  public ArrayList<Order> select(Order obj) throws SQLException;//查询记录
	  public int count(Order obj) throws SQLException;//统计记录数
	  public int insert(Order obj) throws SQLException;//添加记录
	  public int update(Order obj) throws SQLException;//更新记录
	  public int delete(Order obj) throws SQLException;//删除记录
	}
