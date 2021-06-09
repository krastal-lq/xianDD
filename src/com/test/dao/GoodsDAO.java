package com.test.dao;

import java.sql.*;
import java.util.ArrayList;
import com.test.pojo.Goods;

/**
 * 每个DAO对应数据库中的一张表
 * 包含添加、删除、修改、查询、统计等操作
 */

public interface GoodsDAO {
  public ArrayList<Goods> select(Goods obj) throws SQLException;//查询记录
  public ArrayList<Goods> selectDetail(Goods obj) throws SQLException;//查询记录
  public ArrayList<Goods> selectRnd(Goods obj) throws SQLException;//随机查询记录
  public int count(Goods obj) throws SQLException;//统计记录数
  public int insert(Goods obj) throws SQLException;//添加记录
  public int update(Goods obj) throws SQLException;//更新记录
  public int delete(Goods obj) throws SQLException;//删除记录
}