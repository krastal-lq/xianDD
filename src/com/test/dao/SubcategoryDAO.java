package com.test.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.test.pojo.Subcategory;

public interface SubcategoryDAO {
	  public ArrayList<Subcategory> select(Subcategory obj) throws SQLException;//查询记录
	  public int count(Subcategory obj) throws SQLException;//统计记录数
	  public int insert(Subcategory obj) throws SQLException;//添加记录
	  public int update(Subcategory obj) throws SQLException;//更新记录
	  public int delete(Subcategory obj) throws SQLException;//删除记录
	}
