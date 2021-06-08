package com.test.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import com.test.pojo.Address;


public interface AddressDAO {
	  public ArrayList<Address> select(Address obj) throws SQLException;//查询记录
	  public ArrayList<Address> selectRnd(Address obj) throws SQLException;//随机查询3条记录
	  public int count(Address obj) throws SQLException;//统计记录数
	  public int insert(Address obj) throws SQLException;//添加记录
	  public int update(Address obj) throws SQLException;//更新记录
	  public int delete(Address obj) throws SQLException;//删除记录
	}
