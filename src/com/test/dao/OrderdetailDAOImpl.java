package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.test.pojo.Goods;
import com.test.pojo.Goodsimage;
import com.test.pojo.Orderdetail;

public class OrderdetailDAOImpl implements OrderdetailDAO {
	  
	  private Connection conn = null;
	  private PreparedStatement pst = null;
	  
	  /**
	   * 无参构造函数
	   */
	  public OrderdetailDAOImpl() {
	    super();
	  }
	  
	  /**
	   * 建议定义一个带参的构造函数
	   * 实例化的时候，完成连接的注入
	   */
	  public OrderdetailDAOImpl(Connection conn) {
	    super();
	    this.conn = conn;
	  }
	  
	  /**
	   * 查询记录
	   */
	  public ArrayList<Orderdetail> select(Orderdetail pojo) throws SQLException {
	    try{
	      String sql = "select "
    		  		 + "  orderdetail.id as id, "
	                 + "  orderdetail.num as num, "
	                 + "  orderdetail.price as price, "
	                 + "  orderdetail.totalPrice as totalPrice, "
	                 + "  goods.id as goodsid, "
	                 + "  goods.name as goodsname, "
	                 + "  goods.promotPrice as promotPrice, "
	                 + "  goods.unit as unit, "
	                 + "  goods.number as number, "
	                 + "  goods.freight as freight, "
	                 + "  goods.place as place, "
	                 + "  goodsimage.id as goodsimageid, "
	                 + "  goodsimage.url as url "
	                 + "from orderdetail "
	                 + "  inner join goods on orderdetail.goodsId=goods.id "
	                 + "  inner join goodsimage on goodsimage.goodsId=goods.id "
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
	      ArrayList<Orderdetail> al = new ArrayList<Orderdetail>();//封装数据并返回给Service层
	      ArrayList<Orderdetail> ali = new ArrayList<Orderdetail>();//封装数据并返回给Service层
	      while(rs.next()){
	    	  boolean flag = true;
	    	  for(int i = 0;i < al.size(); i ++){
	    		  if(al.get(i).getGoodsId()==rs.getInt("goodsid")){
	    			  flag = false;
	    		  }
	    	  }
    	  if(flag){
	        //将User相关信息保存到po对象的各个属性中
	    	Orderdetail po = new Orderdetail();//持久层对象，用于调用POJO类的方法
	        po.setId(rs.getInt("id"));
	        po.setGoodsId(rs.getInt("goodsid"));
	        po.setNum(rs.getInt("num"));
	        po.setPrice(rs.getFloat("price"));
	        po.setTotalPrice(rs.getFloat("totalPrice"));
	      //将Goods相关信息保存到po对象的Order属性中(记住：多表操作时候，我们在DAO层的做法，都用这个套路)
	        Goods goods = new Goods();
	        ArrayList<Goods> goodsli = new ArrayList<Goods>();
	        goods.setId(rs.getInt("goodsid"));
	        goods.setName(rs.getString("goodsname"));
	        goods.setPromotPrice(rs.getFloat("promotPrice"));
	        goods.setUnit(rs.getString("unit"));
	        goods.setNumber(rs.getInt("number"));
	        goods.setFreight(rs.getFloat("freight"));
	        goods.setPlace(rs.getString("place"));
	      //将Goodsimage相关信息保存到goods对象的Order属性中
	        Goodsimage goodsimg = new Goodsimage();
	        ArrayList<Goodsimage> goodsimgli = new ArrayList<Goodsimage>();
	        goodsimg.setId(rs.getInt("goodsimageid"));
	        goodsimg.setGoodsId(rs.getInt("goodsId"));
	        goodsimg.setUrl(rs.getString("url"));
	        goodsimgli.add(goodsimg);
	        
	        goods.setGoodsimageList(goodsimgli);
	        
	        goodsli.add(goods);
	        po.setGoodsList(goodsli);
	        //将以上全部信息添加到集合
	        al.add(po);
    	  }
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
	  public int count(Orderdetail pojo) throws SQLException {
	    try{
	      String sql = "select count(*) as cnt from orderdetail where 1=1 ";
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
	  public int insert(Orderdetail pojo) throws SQLException {
	    try{
	      String sql = "insert into "
	                 + "orderdetail(num,price,totalPrice) "
	                 + "values(?,?,?) ";
	      pst = conn.prepareStatement(sql);
	      pst.setInt(1, pojo.getNum());
	      pst.setFloat(2, pojo.getPrice());
	      pst.setFloat(3, pojo.getTotalPrice());
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
	  public int update(Orderdetail pojo) throws SQLException {
	    try{
	      int cnt = 0;
	      String sql = "update orderdetail set ";


	        sql += "num='"+pojo.getNum()+"',";
	        cnt++;

	      

	        sql += "totalPrice='"+pojo.getTotalPrice()+"',";
	        cnt++;

	      
	      
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
	  public int delete(Orderdetail Orderdetail) throws SQLException {
	    try{
	      // 删除一般就这样写了，大多数情况下，我们只是删除一条记录
	      // 即便是批量删除，大多时候也是前端选中n条记录，把ids传给后台来处理
	      String sql = "delete from orderdetail where id=?";
	      pst = conn.prepareStatement(sql);
	      pst.setInt(1, Orderdetail.getId());
	      //System.out.println(sql);
	      int i = pst.executeUpdate();
	      return i;
	    }catch(Exception e){
	      e.printStackTrace();
	      return -1;
	    }
	  }

	}
