package com.test.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.dao.GoodsDAOImpl;
import com.test.dao.GoodscategoryDAOImpl;
import com.test.dao.SubcategoryDAOImpl;
import com.test.pojo.JsonData;
import com.test.pojo.Subcategory;
import com.test.pojo.Goods;
import com.test.pojo.Goodscategory;
import com.test.pojo.Goods;
import com.test.util.DButil;
import com.test.util.Util;

public class GoodsServiceImpl implements GoodsService{
  /**
   * 查询业务，此处做3件事：
   * 1、调用DAO层的select方法，返回查询到的记录集rows
   * 2、调用DAO层的count方法，返回查询到的记录数total
   * 3、利用JsonData将记录数total与记录集goodsList拼接成“total=50,rows=[{},{}...{}]”格式
   */
  boolean success;//操作成功与否
  String msg;//返回的结果信息
  int total;//返回记录数
  JsonData jd;//将数据转换为指定JSON格式的对象，并返回给Controller层
  
  
  public JsonData select(Goods obj) {
    Connection conn = DButil.getConnection();//数据库连接
    GoodsDAOImpl bo = new GoodsDAOImpl(conn);//业务层对象，用于调用DAO层方法
    try{
      ArrayList<Goods> rows = new ArrayList<Goods>();
      rows = bo.select(obj);//返回记录集
      total = bo.count(obj);//返回记录数
      conn.commit();
      success = true; 
      msg = "查询成功";
      jd = new JsonData(success,msg,total,rows);
      return jd;
    }catch(Exception e){
      try {
        conn.rollback();
      }
      catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      success = false; 
      msg = "查询失败";
      jd = new JsonData(success,msg);
      return jd;
    }finally{
      if(conn != null){DButil.closeConnection(conn);}
    }
  }
  
  
  public JsonData selectRnd(Goods obj) {
    Connection conn = DButil.getConnection();//数据库连接
    GoodsDAOImpl bo = new GoodsDAOImpl(conn);//业务层对象，用于调用DAO层方法
    try{
      ArrayList<Goods> rows = new ArrayList<Goods>();
      rows = bo.selectRnd(obj);//返回记录集
      conn.commit();
      success = true; 
      msg = "查询成功";
      jd = new JsonData(success,msg,total,rows);
      return jd;
    }catch(Exception e){
      try {
        conn.rollback();
      }
      catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      success = false; 
      msg = "查询失败";
      jd = new JsonData(success,msg);
      return jd;
    }finally{
      if(conn != null){DButil.closeConnection(conn);}
    }
  }
  
  
  
  /**
   * 添加业务，此处做这几件事：
   * 1、向Goods表中添加一条新纪录，并返回该记录的ID
   * 2、利用该ID向Goods表中添加一条新纪录，并返回操作结果
   * 3、将操作结果拼接成success=true,msg="添加成功"格式
   */
  public JsonData insert(Goods obj){
    Connection conn = DButil.getConnection();//数据库连接
    GoodsDAOImpl goodsBO = new GoodsDAOImpl(conn);//业务层对象，用于调用DAO层方法
    GoodscategoryDAOImpl ctBO = new GoodscategoryDAOImpl(conn);//业务层对象，用于调用DAO层方法
    SubcategoryDAOImpl sctBO = new SubcategoryDAOImpl(conn);//业务层对象，用于调用DAO层方法
    try{
      //添加Category账号
      Goodscategory ct = new Goodscategory();
      List<Goodscategory> ci = new ArrayList<Goodscategory>();
      ci = obj.getCategoryList();
      ct=ci.get(0);
      ArrayList<Goodscategory> ctrows = new ArrayList<Goodscategory>();
      ctrows = ctBO.select(ct);
      //添加Subcategory账号
      Subcategory sct = new Subcategory();
      List<Subcategory> sci = new ArrayList<Subcategory>();
      sci = obj.getSubcategoryList();
      sct=sci.get(0);
      ArrayList<Subcategory> sctrows = new ArrayList<Subcategory>();
      sctrows = sctBO.select(sct);
      
      //添加商品的类别id

      //obj.setCategoryId(ctrows.get(0).getId());
      obj.setSubcategoryId(sctrows.get(0).getId());
      //默认字段
      obj.setStatus("已上架");
      
      int i = goodsBO.insert(obj);//i为影响的行数
      conn.commit();
      success = true; 
      msg = "成功添加["+i+"]条记录";
      jd = new JsonData(success,msg);
      return jd;
    }catch(Exception e){
      try {conn.rollback();}
      catch (SQLException e1) {e1.printStackTrace();}
      e.printStackTrace();
      success = false; 
      msg = "添加失败";
      JsonData jrs = new JsonData(success,msg);
      return jrs;
    }finally{
      if(conn != null){DButil.closeConnection(conn);}
    }
  }

  /**
   * 更新业务，此处做2件事：
   * 1、调用DAO层的update方法，返回操作结果
   * 2、将操作结果拼接成success=true,msg="更新成功"格式
   */
  public JsonData update(Goods obj){
    Connection conn = DButil.getConnection();//数据库连接
    GoodsDAOImpl bo = new GoodsDAOImpl(conn);//业务层对象，用于调用DAO层方法
    try{
      int i = bo.update(obj);//i为影响的行数
      conn.commit();
      success = true; 
      msg = "成功更新["+i+"]条记录";
      jd = new JsonData(success,msg);
      return jd;
    }catch(Exception e){
      try {conn.rollback();}
      catch (SQLException e1) {e1.printStackTrace();}
      e.printStackTrace();
      success = false; 
      msg = "更新失败";
      jd = new JsonData(success,msg);
      return jd;
    }finally{
      if(conn != null){DButil.closeConnection(conn);}
    }
  }
  
  /**
   * 删除业务，此处做这几件事：
   * 1、根据id查询对应记录的goodsId
   * 2、根据goodsId删除goods表中记录
   * 3、根据id删除goods表中记录
   * 4、将操作结果拼接成success=true,msg="删除新成功"格式
   */
  public JsonData delete(Goods obj){
    Connection conn = DButil.getConnection();//数据库连接
    GoodsDAOImpl stuBO = new GoodsDAOImpl(conn);//业务层对象，用于调用DAO层方法
    //GoodsDAOImpl goodsBO = new GoodsDAOImpl(conn);//业务层对象，用于调用DAO层方法
    try{
      //根据id查询goods表中记录
      ArrayList<Goods> rows = new ArrayList<Goods>();
      rows = stuBO.select(obj);
//      int id = rows.get(0).getGoodsId(); //id为需要删除的goods表中的记录id
//      //删除goods表中指定id的记录
//      Goods goods = new Goods();
//      goods.setId(id);
//      goodsBO.delete(goods);
      //删除goods表中指定id的记录
      int i = stuBO.delete(obj);
      conn.commit();
      success = true; 
      msg = "成功删除["+i+"]条记录";
      jd = new JsonData(success,msg);
      return jd;
    }catch(Exception e){
      try {conn.rollback();}
      catch (SQLException e1) {e1.printStackTrace();}
      e.printStackTrace();
      success = false; 
      msg = "删除失败";
      jd = new JsonData(success,msg);
      return jd;
    }finally{
      if(conn != null){DButil.closeConnection(conn);}
    }
  }


public JsonData selectDetail(Goods obj) {
    Connection conn = DButil.getConnection();//数据库连接
    GoodsDAOImpl bo = new GoodsDAOImpl(conn);//业务层对象，用于调用DAO层方法
    try{
      ArrayList<Goods> rows = new ArrayList<Goods>();
      rows = bo.selectDetail(obj);//返回记录集
      conn.commit();
      success = true; 
      msg = "查询成功";
      jd = new JsonData(success,msg,total,rows);
      return jd;
    }catch(Exception e){
      try {
        conn.rollback();
      }
      catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      success = false; 
      msg = "查询失败";
      jd = new JsonData(success,msg);
      return jd;
    }finally{
      if(conn != null){DButil.closeConnection(conn);}
    }
  }
}