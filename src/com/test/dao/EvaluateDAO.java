package com.test.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.test.pojo.Evaluate;


public interface EvaluateDAO {
	  public ArrayList<Evaluate> select(Evaluate obj) throws SQLException;//查询记录
	  public int count(Evaluate obj) throws SQLException;//统计记录数
	  public int insert(Evaluate obj) throws SQLException;//添加记录
	  public int update(Evaluate obj) throws SQLException;//更新记录
	  public int delete(Evaluate obj) throws SQLException;//删除记录
	}