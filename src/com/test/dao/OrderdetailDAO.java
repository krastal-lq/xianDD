package com.test.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.test.pojo.Orderdetail;


public interface OrderdetailDAO {
	  public ArrayList<Orderdetail> select(Orderdetail obj) throws SQLException;//查询记录
	  public int count(Orderdetail obj) throws SQLException;//统计记录数
	  public int insert(Orderdetail obj) throws SQLException;//添加记录
	  public int update(Orderdetail obj) throws SQLException;//更新记录
	  public int delete(Orderdetail obj) throws SQLException;//删除记录
	}
