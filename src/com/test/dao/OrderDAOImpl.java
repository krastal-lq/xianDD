package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.test.pojo.User;
import com.test.pojo.Goods;
import com.test.pojo.Goodscategory;
import com.test.pojo.Order;
import com.test.pojo.Orderdetail;

public class OrderDAOImpl implements OrderDAO {
	  
	  private Connection conn = null;
	  private PreparedStatement pst = null;
	  
	  /**
	   * 无参构造函数
	   */
	  public OrderDAOImpl() {
	    super();
	  }
	  
	  /**
	   * 建议定义一个带参的构造函数
	   * 实例化的时候，完成连接的注入
	   */
	  public OrderDAOImpl(Connection conn) {
	    super();
	    this.conn = conn;
	  }
	  
	  /**
	   * 查询记录
	   */
	  public ArrayList<Order> select(Order pojo) throws SQLException {
	    try{
	      String sql = "select "
	                 + "  `order`.id as id, "
	                 + "  `order`.sequence as sequence, "
	                 + "  `order`.name as name, "
	                 + "  `order`.telephone as telephone, "
	                 + "  `order`.address as address, "
	                 + "  `order`.status as orderstatus, "
	                 + "  `order`.orderTime as orderTime, "
	                 + "  `order`.remark as remark, "
	                 + "  `order`.totalPrice as ordertotalPrice, "
	                 + "  `order`.userId as userId, "
	                 + "  user.name as username, "
	                 + "  user.sex as sex, "
	                 + "  user.imgURL as imgURL, "
	                 + "  user.status as userstatus "
	                 + "from `order` "
	                 + "  inner join user on `order`.userId=user.id "
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
	      //System.out.println(sql);
	      pst = conn.prepareStatement(sql);
	      ResultSet rs = pst.executeQuery();
	      ArrayList<Order> al = new ArrayList<Order>();//封装数据并返回给Service层
	      while(rs.next()){
	        //将Order相关信息保存到po对象的各个属性中
	    	  Order po = new Order();//持久层对象，用于调用POJO类的方法
	        po.setId(rs.getInt("id"));
	        po.setSequence(rs.getString("sequence"));
	        po.setName(rs.getString("name"));
	        po.setTelephone(rs.getString("telephone"));
	        po.setAddress(rs.getString("address"));
	        po.setStatus(rs.getString("orderstatus"));
	        po.setOrderTime(rs.getString("orderTime"));
	        po.setRemark(rs.getString("remark"));
	        po.setTotalPrice(rs.getFloat("ordertotalPrice"));
	        po.setUserId(rs.getInt("userId"));
	        
	      //将user相关信息保存到po对象的Order属性中(记住：多表操作时候，我们在DAO层的做法，都用这个套路)
	        User user = new User();
	        ArrayList<User> userli = new ArrayList<User>();
	        user.setId(rs.getInt("userId"));
	        user.setName(rs.getString("username"));
	        user.setSex(rs.getString("sex"));
	        user.setImgURL(rs.getString("imgURL"));
	        user.setStatus(rs.getString("userstatus"));
	        userli.add(user);
	        po.setUserList(userli);
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
	  public int count(Order pojo) throws SQLException {
	    try{
	      String sql = "select count(*) as cnt from `order`  where 1=1";
	      String condition = pojo.getCondition();
	      if(condition!=null && !condition.equals("")){
	        sql+=" and " + condition;
	      }
	       System.out.println(sql);
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
	  public int insert(Order pojo) throws SQLException {
	    try{
	      String sql = "insert into "
	                 + "`order`(sequence,name,telephone,address,status,orderTime,remark,totalPrice,userId) "
	                 + "values(?,?,?,?,?,?,?,?,?) ";
	      pst = conn.prepareStatement(sql);
	      pst.setString(1, pojo.getSequence());
	      pst.setString(2, pojo.getName());
	      pst.setString(3, pojo.getTelephone());
	      pst.setString(4, pojo.getAddress());
	      pst.setString(5, pojo.getStatus());
	      pst.setString(6, pojo.getOrderTime());
	      pst.setString(7, pojo.getRemark());
	      pst.setFloat(8, pojo.getTotalPrice());
	      pst.setFloat(9, pojo.getUserId());
	      System.out.println(sql);
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
	  public int update(Order pojo) throws SQLException {
	    try{
	      int cnt = 0;
	      String sql = "update order set ";

	      if(pojo.getSequence() != null && !pojo.getSequence().equals("")){
		        sql += "sequence='"+pojo.getSequence()+"',";
		        cnt++;
		      }
	      
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
	      
	      if(pojo.getOrderTime() != null && !pojo.getOrderTime().equals("")){
		        sql += "orderTime='"+pojo.getOrderTime()+"',";
		        cnt++;
		      }
	      
	      if(pojo.getStatus() != null && !pojo.getStatus().equals("")){
		        sql += "status='"+pojo.getStatus()+"',";
		        cnt++;
		      }
	      
	      if(pojo.getRemark() != null && !pojo.getRemark().equals("")){
		        sql += "remark='"+pojo.getRemark()+"',";
		        cnt++;
		      }
	      
	      if(Float.toString(pojo.getTotalPrice()) != null ){
		        sql += "totalPrice='"+pojo.getTotalPrice()+"',";
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
	  public int delete(Order Order) throws SQLException {
	    try{
	      // 删除一般就这样写了，大多数情况下，我们只是删除一条记录
	      // 即便是批量删除，大多时候也是前端选中n条记录，把ids传给后台来处理
	      String sql = "delete from order where id=?";
	      pst = conn.prepareStatement(sql);
	      pst.setInt(1, Order.getId());
	      //System.out.println(sql);
	      int i = pst.executeUpdate();
	      return i;
	    }catch(Exception e){
	      e.printStackTrace();
	      return -1;
	    }
	  }

	}
