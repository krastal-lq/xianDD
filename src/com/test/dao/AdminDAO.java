package com.test.dao;



import java.sql.SQLException;
import java.util.ArrayList;

import com.test.pojo.Admin;

public interface AdminDAO {
	  public ArrayList<Admin> select(Admin obj) throws SQLException;//查询记录
	  public int count(Admin obj) throws SQLException;//统计记录数
	  public int insert(Admin obj) throws SQLException;//添加记录
	  public int update(Admin obj) throws SQLException;//更新记录
	  public int delete(Admin obj) throws SQLException;//删除记录
	}
