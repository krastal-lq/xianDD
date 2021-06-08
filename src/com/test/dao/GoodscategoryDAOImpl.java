package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.test.pojo.Goodscategory;

public class GoodscategoryDAOImpl implements GoodscategoryDAO {
	  
	  private Connection conn = null;
	  private PreparedStatement pst = null;
	  
	  /**
	   * 无参构造函数
	   */
	  public GoodscategoryDAOImpl() {
	    super();
	  }
	  
	  /**
	   * 建议定义一个带参的构造函数
	   * 实例化的时候，完成连接的注入
	   */
	  public GoodscategoryDAOImpl(Connection conn) {
	    super();
	    this.conn = conn;
	  }
	  
	  /**
	   * 查询记录
	   */
	  public ArrayList<Goodscategory> select(Goodscategory pojo) throws SQLException {
	    try{
	      String sql = "select "
	                 + "  goodscategory.id as id, "
	                 + "  goodscategory.category as category "
	                 + "from goodscategory "
	                 + "where 1=1 ";
	      // 查询
	      String condition = pojo.getCondition();
	      if(condition != null && ! condition.equals("")){
	        sql += " and " + condition;
	      }
	      // 排序
	      String orderBy = pojo.getOrderBy();
	      if(orderBy != null && ! orderBy.equals("")){
	        sql += orderBy;
	      }
	      // 分页
	      String limit = pojo.getLimit();
	      if(limit != null && ! limit.equals("")){
	        sql += limit;
	      }
	      // System.out.println(sql);
	      pst = conn.prepareStatement(sql);
	      ResultSet rs = pst.executeQuery();
	      ArrayList<Goodscategory> al = new ArrayList<Goodscategory>();//封装数据并返回给Service层
	      while(rs.next()){
	        //将User相关信息保存到po对象的各个属性中
	    	  Goodscategory po = new Goodscategory();//持久层对象，用于调用POJO类的方法
	        po.setId(rs.getInt("id"));
	        po.setCategory(rs.getString("category"));
	        //将以上全部信息添加到集合
	        al.add(po);
	      }
	      // System.out.println(al.toString());
	      return al;
	    }catch(Exception e){
	      e.printStackTrace();
	      return null;
	    }
	  }
	    
	  
	  
	  /**
	   * 统计记录数
	   */
	  public int count(Goodscategory pojo) throws SQLException {
	    try{
	      String sql = "select count(*) as cnt from goodscategory where 1=1 ";
	      String condition = pojo.getCondition();
	      if(condition!=null && !condition.equals("")){
	        sql+=" and " + condition;
	      }
	      // System.out.println(sql);
	      pst = conn.prepareStatement(sql);
	      ResultSet rs = pst.executeQuery();
	      rs.next();
	      int cnt = rs.getInt("cnt");
	      return cnt;
	    }catch(Exception e){
	      e.printStackTrace();
	      return -1;
	    }
	  }

	  /**
	   * 插入记录
	   */  
	  public int insert(Goodscategory pojo) throws SQLException {
	    try{
	      String sql = "insert into "
	                 + "goodscategory(category) "
	                 + "values(?) ";
	      pst = conn.prepareStatement(sql);
	      pst.setString(1, pojo.getCategory());
	      //System.out.println(sql);
	      pst.executeUpdate();
	      //用last_insert_id()获取新插入记录的id,用于更新student或teacher表的userId字段
	      sql ="select last_insert_id()";
	      pst = conn.prepareStatement(sql);
	      ResultSet rs = pst.executeQuery();
	      rs.next();
	      int id = rs.getInt(1);
	      return id;
	    }catch(Exception e){
	      e.printStackTrace();
	      return -1;
	    }
	  }
	  
	  /**
	   * 更新部分记录
	   * 修改昵称：nickName
	   * 修改密码：password
	   * 修改头像：imgURL
	   */
	  public int update(Goodscategory pojo) throws SQLException {
	    try{
	      int cnt = 0;
	      String sql = "update goodscategory set ";

	      if(pojo.getCategory() != null && !pojo.getCategory().equals("")){
	        sql += "category='"+pojo.getCategory()+"',";
	        cnt++;
	      }


	      
	      if(cnt > 0){
	        sql = sql.substring(0,sql.length()-1);//去掉最后一个逗号
	        sql += " where id=?";
	      }
	      
	      pst = conn.prepareStatement(sql);
	      pst.setInt(1, pojo.getId());
	      //System.out.println(sql);
	      int i = pst.executeUpdate();
	      return i;
	    }catch(Exception e){
	      e.printStackTrace();
	      return -1;
	    }
	  }
	  
	  /**
	   * 删除记录
	   */
	  public int delete(Goodscategory Goodscategory) throws SQLException {
	    try{
	      // 删除一般就这样写了，大多数情况下，我们只是删除一条记录
	      // 即便是批量删除，大多时候也是前端选中n条记录，把ids传给后台来处理
	      String sql = "delete from goodscategory where id=?";
	      pst = conn.prepareStatement(sql);
	      pst.setInt(1, Goodscategory.getId());
	      //System.out.println(sql);
	      int i = pst.executeUpdate();
	      return i;
	    }catch(Exception e){
	      e.printStackTrace();
	      return -1;
	    }
	  }

	}
