package com.test.dao;


import java.sql.SQLException;
import java.util.ArrayList;

import com.test.pojo.Goodscategory;

public interface GoodscategoryDAO {
	  public ArrayList<Goodscategory> select(Goodscategory obj) throws SQLException;//查询记录
	  public int count(Goodscategory obj) throws SQLException;//统计记录数
	  public int insert(Goodscategory obj) throws SQLException;//添加记录
	  public int update(Goodscategory obj) throws SQLException;//更新记录
	  public int delete(Goodscategory obj) throws SQLException;//删除记录
	}