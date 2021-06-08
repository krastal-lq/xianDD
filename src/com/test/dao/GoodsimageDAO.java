package com.test.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.test.pojo.Goodsimage;


public interface GoodsimageDAO {
	  public ArrayList<Goodsimage> select(Goodsimage obj) throws SQLException;//查询记录
	  public int count(Goodsimage obj) throws SQLException;//统计记录数
	  public int insert(Goodsimage obj) throws SQLException;//添加记录
	  public int update(Goodsimage obj) throws SQLException;//更新记录
	  public int delete(Goodsimage obj) throws SQLException;//删除记录
	}

