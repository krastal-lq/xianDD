package com.test.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.test.dao.AdminDAOImpl;
import com.test.pojo.Goods;
import com.test.pojo.JsonData;
import com.test.pojo.Admin;
import com.test.util.DButil;
import com.test.util.Util;

public class AdminServiceImpl implements AdminService{
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
  
  
  public JsonData select(Admin obj) {
    Connection conn = DButil.getConnection();//数据库连接
    AdminDAOImpl bo = new AdminDAOImpl(conn);//业务层对象，用于调用DAO层方法
    try{
      ArrayList<Admin> rows = new ArrayList<Admin>();
      rows = bo.select(obj);//返回记录集
      total = bo.count(obj);//返回记录数
      conn.commit();
      success = true; 
      msg = "http://localhost:8080/test/html/admin/main.jsp";
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
   * 1、向Admin表中添加一条新纪录，并返回该记录的ID
   * 2、利用该ID向Admin表中添加一条新纪录，并返回操作结果
   * 3、将操作结果拼接成success=true,msg="添加成功"格式
   */
  public JsonData insert(Admin obj){
    Connection conn = DButil.getConnection();//数据库连接
    AdminDAOImpl stuBO = new AdminDAOImpl(conn);//业务层对象，用于调用DAO层方法
    //AdminDAOImpl userBO = new AdminDAOImpl(conn);//业务层对象，用于调用DAO层方法
    try{
      //添加Admin账号
      //String userName = obj.getSequence();//把学号作为账号
      String nickName = obj.getName();//把用户名作为昵称
//      Util util = new Util();
//      String pw = util.EncoderByMd5("123456");//把默认密码123456加密
      String role = "学生";//默认权限为学生
      String imgURL = "user-default.png";//默认图片
      Admin user = new Admin();
//      user.setAdminName(userName);
//      user.setNickName(nickName);
//      user.setPassword(pw);
//      user.setRole(role);
//      user.setImgURL(imgURL);
//      int id = userBO.insert(user);
//      //添加学生
//      obj.setAdminId(id);
      int i = stuBO.insert(obj);//i为影响的行数
      conn.commit();
      success = true; 
      msg = "成功添加第["+i+"]条记录";
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
  public JsonData update(Admin obj){
    Connection conn = DButil.getConnection();//数据库连接
    AdminDAOImpl bo = new AdminDAOImpl(conn);//业务层对象，用于调用DAO层方法
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
   * 1、根据id查询对应记录的userId
   * 2、根据userId删除user表中记录
   * 3、根据id删除goods表中记录
   * 4、将操作结果拼接成success=true,msg="删除新成功"格式
   */
  public JsonData delete(Admin obj){
    Connection conn = DButil.getConnection();//数据库连接
    AdminDAOImpl stuBO = new AdminDAOImpl(conn);//业务层对象，用于调用DAO层方法
    //AdminDAOImpl userBO = new AdminDAOImpl(conn);//业务层对象，用于调用DAO层方法
    try{
      //根据id查询goods表中记录
      ArrayList<Admin> rows = new ArrayList<Admin>();
      rows = stuBO.select(obj);
//      int id = rows.get(0).getAdminId(); //id为需要删除的user表中的记录id
//      //删除user表中指定id的记录
//      Admin user = new Admin();
//      user.setId(id);
//      userBO.delete(user);
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

}