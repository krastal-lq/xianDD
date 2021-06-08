package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.test.pojo.Evaluate;

public class EvaluateDAOImpl implements EvaluateDAO {
	  
	  private Connection conn = null;
	  private PreparedStatement pst = null;
	  
	  /**
	   * 无参构造函数
	   */
	  public EvaluateDAOImpl() {
	    super();
	  }
	  
	  /**
	   * 建议定义一个带参的构造函数
	   * 实例化的时候，完成连接的注入
	   */
	  public EvaluateDAOImpl(Connection conn) {
	    super();
	    this.conn = conn;
	  }
	  
	  /**
	   * 查询记录
	   */
	  public ArrayList<Evaluate> select(Evaluate pojo) throws SQLException {
	    try{
	      String sql = "select "
	                 + "  evaluate.id as id, "
	                 + "  evaluate.userId as userId, "
	                 + "  evaluate.goodsId as goodsId, "
	                 + "  user.name as name, "
	                 + "  evaluate.content as content, "
	                 + "  evaluate.score as score, "
	                 + "  evaluate.time as time "
	                 + "from evaluate "
	                 + "  inner join user on evaluate.userId = user.id "
	                 + "  inner join goods on evaluate.goodsId = goods.id "
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
	      ArrayList<Address> al = new ArrayList<Address>();//封装数据并返回给Service层
	      while(rs.next()){
	        //将User相关信息保存到po对象的各个属性中
	    	  Address po = new Address();//持久层对象，用于调用POJO类的方法
	        po.setId(rs.getInt("id"));
	        po.setName(rs.getString("name"));
	        po.setTelephone(rs.getString("telephone"));
	        po.setAddress(rs.getString("address"));
	        po.setDefaulting(rs.getInt("defaulting"));
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
	   * 随机查询3条记录数
	   */
	  public ArrayList<Address> selectRnd(Address pojo) throws SQLException {
	    try{
	      String sql = "select id,name,telephone,adress,rand() as rnd from address ";
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
	      pst = conn.prepareStatement(sql);
	      ResultSet rs = pst.executeQuery();
	      ArrayList<Address> al = new ArrayList<Address>();//封装数据并返回给Service层
	      while(rs.next()){
	        //将User相关信息保存到po对象的各个属性中
	    	  Address po = new Address();//持久层对象，用于调用POJO类的方法
	        po.setId(rs.getInt("id"));
	        po.setName(rs.getString("name"));
	        po.setTelephone(rs.getString("telephone"));
	        po.setAddress(rs.getString("address"));
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
	  public int count(Address pojo) throws SQLException {
	    try{
	      String sql = "select count(*) as cnt from user where 1=1 ";
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
	  public int insert(Address pojo) throws SQLException {
	    try{
	      String sql = "insert into "
	                 + "address(name,telephone,address,defaulting) "
	                 + "values(?,?,?,?) ";
	      pst = conn.prepareStatement(sql);
	      pst.setString(1, pojo.getName());
	      pst.setString(2, pojo.getTelephone());
	      pst.setString(3, pojo.getAddress());
	      pst.setInt(4, pojo.getDefaulting());
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
	  public int update(Address pojo) throws SQLException {
	    try{
	      int cnt = 0;
	      String sql = "update address set ";

	      if(pojo.getName() != null && !pojo.getName().equals("")){
	        sql += "name='"+pojo.getName()+"',";
	        cnt++;
	      }
	      
	      if(pojo.getTelephone() != null && !pojo.getTelephone().equals("")){
	        sql += "telephone='"+pojo.getTelephone()+"',";
	        cnt++;
	      }
	      
	      if(pojo.getAddress() != null && !pojo.getAddress().equals("")){
	        sql += "address='"+pojo.getAddress()+"',";
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
	  public int delete(Address Address) throws SQLException {
	    try{
	      // 删除一般就这样写了，大多数情况下，我们只是删除一条记录
	      // 即便是批量删除，大多时候也是前端选中n条记录，把ids传给后台来处理
	      String sql = "delete from address where id=?";
	      pst = conn.prepareStatement(sql);
	      pst.setInt(1, Address.getId());
	      //System.out.println(sql);
	      int i = pst.executeUpdate();
	      return i;
	    }catch(Exception e){
	      e.printStackTrace();
	      return -1;
	    }
	  }

	}
