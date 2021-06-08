package com.test.dao;


import java.sql.SQLException;
import java.util.ArrayList;

import com.test.pojo.Merchant;

public interface MerchantDAO {
	  public ArrayList<Merchant> select(Merchant obj) throws SQLException;//查询记录
	  public int count(Merchant obj) throws SQLException;//统计记录数
	  public int insert(Merchant obj) throws SQLException;//添加记录
	  public int update(Merchant obj) throws SQLException;//更新记录
	  public int delete(Merchant obj) throws SQLException;//删除记录
	}