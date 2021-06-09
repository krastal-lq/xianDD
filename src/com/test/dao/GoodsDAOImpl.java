package com.test.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.test.pojo.Goods;
import com.test.pojo.Goodscategory;
import com.test.pojo.Goodsimage;
import com.test.pojo.Subcategory;
import com.test.pojo.Goods;

public class GoodsDAOImpl implements GoodsDAO {
  
  private Connection conn = null;
  private PreparedStatement pst = null;
  
  /**
   * 无参构造函数
   */
  public GoodsDAOImpl() {
    super();
  }
  
  /**
   * 建议定义一个带参的构造函数
   * 实例化的时候，完成连接的注入
   */
  public GoodsDAOImpl(Connection conn) {
    super();
    this.conn = conn;
  }
  
  /**
   * 查询记录
   */
  public ArrayList<Goods> select(Goods pojo) throws SQLException {
    try{
      String sql = "select "
                 + "  goods.id as id, "
                 + "  goods.name as name, "
                 + "  goods.sequence as sequence, "
                 + "  goods.price as price, "
                 + "  goods.promotPrice as promotPrice, "
                 + "  goods.unit as unit, "
                 + "  goods.number as number, "
                 + "  goods.freight as freight, "
                 + "  goods.categoryId as categoryId, "
                 + "  goods.subcategoryId as subcategoryId, "
                 + "  goods.place as place, "
                 + "  goods.status as status, "
                 + "  goodscategory.category as category, "
                 + "  subcategory.subcategory as subcategory "
                 + "from goods "
                 + "  inner join goodscategory on goods.categoryId = goodscategory.id "
                 + "  inner join subcategory on goods.subcategoryId = subcategory.id "
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
      System.out.println(sql);
      pst = conn.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      ArrayList<Goods> al = new ArrayList<Goods>();//封装数据并返回给Service层
      while(rs.next()){
        //将goods相关信息保存到po对象的各个属性中
        Goods po = new Goods();//持久层对象，用于调用POJO类的方法
        po.setId(rs.getInt("id"));
        po.setName(rs.getString("name"));
        po.setSequence(rs.getString("sequence"));
        po.setPrice(rs.getFloat("price"));
        po.setPromotPrice(rs.getFloat("promotPrice"));
        po.setUnit(rs.getString("unit"));
        po.setNumber(rs.getInt("number"));
        po.setFreight(rs.getFloat("freight"));
        po.setSubcategoryId(rs.getInt("categoryId"));
        po.setSubcategoryId(rs.getInt("subcategoryId"));
        po.setPlace(rs.getString("place"));
        po.setStatus(rs.getString("status"));
        //将Goodscategory相关信息保存到po对象的goodsList属性中(记住：多表操作时候，我们在DAO层的做法，都用这个套路)
        Goodscategory ct =new Goodscategory();
        ArrayList<Goodscategory> ci = new ArrayList<Goodscategory>();
        ct.setId(rs.getInt("categoryId"));
        ct.setCategory(rs.getString("category"));
        ci.add(ct);
        po.setCategoryList(ci);
        //将Subcategory相关信息保存到po对象的各个属性中
        Subcategory sct =new Subcategory();
        ArrayList<Subcategory> si = new ArrayList<Subcategory>();
        sct.setId(rs.getInt("subcategoryId"));
        sct.setCategoryId(rs.getInt("categoryId"));
        sct.setSubcategory(rs.getString("subcategory"));
        si.add(sct);
        po.setSubcategoryList(si);
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
	 * 随机查询10条记录数
	 */
	public ArrayList<Goods> selectRnd(Goods pojo) throws SQLException {
	  try{
	    String sql = "select goods.id as id,name,price,promotPrice,unit,number,unit,freight,place,url,status,rand() as rnd from goods "
		    		+ "  inner join goodsimage on goods.id = goodsimage.goodsId "
	                + "where goods.status='已上架' ";
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
//	    System.out.println(sql);
	    pst = conn.prepareStatement(sql);
	    ResultSet rs = pst.executeQuery();
	    ArrayList<Goods> al = new ArrayList<Goods>();//封装数据并返回给Service层
	    while(rs.next()){
		      //将Goods相关信息保存到po对象的各个属性中
		        Goods po = new Goods();//持久层对象，用于调用POJO类的方法
		        po.setId(rs.getInt("id"));
		        po.setName(rs.getString("name"));
		        po.setPrice(rs.getFloat("price"));
		        po.setPromotPrice(rs.getFloat("promotPrice"));
		        po.setUnit(rs.getString("unit"));
		        po.setNumber(rs.getInt("number"));
		        po.setFreight(rs.getFloat("freight"));
		        po.setPlace(rs.getString("place"));
		        po.setStatus(rs.getString("status"));
		      //将Goodsimage相关信息保存到po对象的goodsList属性中(记住：多表操作时候，我们在DAO层的做法，都用这个套路)
		        Goodsimage ct =new Goodsimage();
		        ArrayList<Goodsimage> ci = new ArrayList<Goodsimage>();
		        ct.setUrl(rs.getString("url"));
		        ct.setGoodsId(rs.getInt("id"));
		        ci.add(ct);
		        po.setGoodsimageList(ci);
		      //将以上全部信息添加到集合
		      al.add(po);
		    }
	    //System.out.println(al.toString());
	    return al;
	  }catch(Exception e){
	    e.printStackTrace();
	    return null;
	  }
	}

	/**
	 * 随机查询10条记录数
	 */
	public ArrayList<Goods> selectDetail(Goods pojo) throws SQLException {
	  try{
	    String sql = "select goods.id as id,name,price,promotPrice,unit,number,unit,freight,place,url,status,rand() as rnd from goods "
		    		+ "  inner join goodsimage on goods.id = goodsimage.goodsId "
	                + "where goods.status='已上架' ";
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
//	    System.out.println(sql);
	    pst = conn.prepareStatement(sql);
	    ResultSet rs = pst.executeQuery();
	    ArrayList<Goods> al = new ArrayList<Goods>();//封装数据并返回给Service层
	    while(rs.next()){
	    	if(al.isEmpty()){
	    		Goods po = new Goods();//持久层对象，用于调用POJO类的方法
	    		//将Goods相关信息保存到po对象的各个属性中
		        po.setId(rs.getInt("id"));
		        po.setName(rs.getString("name"));
		        po.setPrice(rs.getFloat("price"));
		        po.setPromotPrice(rs.getFloat("promotPrice"));
		        po.setUnit(rs.getString("unit"));
		        po.setNumber(rs.getInt("number"));
		        po.setFreight(rs.getFloat("freight"));
		        po.setPlace(rs.getString("place"));
		        po.setStatus(rs.getString("status"));
		      //将Goodsimage相关信息保存到po对象的goodsList属性中(记住：多表操作时候，我们在DAO层的做法，都用这个套路)
		        Goodsimage ct =new Goodsimage();
		        ArrayList<Goodsimage> ci = new ArrayList<Goodsimage>();
		        ct.setUrl(rs.getString("url"));
		        ct.setGoodsId(rs.getInt("id"));
		        ci.add(ct);
		        po.setGoodsimageList(ci);
		      //将以上全部信息添加到集合
			    al.add(po);
	    	}else{
	    		Goods po = al.get(0);
	    		List<Goodsimage> ci = po.getGoodsimageList();
		        Goodsimage ct =new Goodsimage();
		        ct.setUrl(rs.getString("url"));
		        ct.setGoodsId(rs.getInt("id"));
		        ci.add(ct);
		        po.setGoodsimageList(ci);
	    	}
	    }
	    //System.out.println(al.toString());
	    return al;
	  }catch(Exception e){
	    e.printStackTrace();
	    return null;
	  }
	}


  /**
   * 统计记录数
   */
  public int count(Goods pojo) throws SQLException {
    try{
      String sql = "select count(*) as cnt from goods where 1=1 ";
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
  public int insert(Goods pojo) throws SQLException {
    try{
      String sql = "insert into "
                 + "goods(sequence,name,price,promotPrice,unit,number,freight,categoryId,subcategoryId,place,status) "
                 + "values(?,?,?,?,?,?,?,?,?,?,?) ";
      pst = conn.prepareStatement(sql);
      pst.setString(1, pojo.getSequence());
      pst.setString(2, pojo.getName());
      pst.setFloat(3, pojo.getPrice());
      pst.setFloat(4, pojo.getPromotPrice());
      pst.setString(5, pojo.getUnit());
      pst.setInt(6, pojo.getNumber());
      pst.setFloat(7, pojo.getFreight());
      pst.setInt(8, pojo.getSubcategoryId());
      pst.setInt(9, pojo.getSubcategoryId());
      pst.setString(10, pojo.getPlace());
      pst.setString(11, pojo.getStatus());
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
   */
  public int update(Goods pojo) throws SQLException {
    try{
      int cnt = 0;
      String sql = "update goods set ";

      if(pojo.getSequence() != null && !pojo.getSequence().equals("")){
        sql += "sequence='"+pojo.getSequence()+"',";
        cnt++;
      }
      
      if(pojo.getName() != null && !pojo.getName().equals("")){
        sql += "name='"+pojo.getName()+"',";
        cnt++;
      }
      
      if(Float.toString(pojo.getPrice()) != null){
        sql += "price='"+pojo.getPrice()+"',";
        cnt++;
      }
      
      if(Float.toString(pojo.getPromotPrice()) != null){
          sql += "promotPrice='"+pojo.getPrice()+"',";
          cnt++;
        }
      
      if(pojo.getUnit() != null && !pojo.getUnit().equals("")){
        sql += "unit='"+pojo.getUnit()+"',";
        cnt++;
      }
      
      if(Integer.toString(pojo.getNumber()) != null ){
        sql += "number='"+pojo.getNumber()+"',";
        cnt++;
      }
      
      if(Float.toString(pojo.getFreight()) != null  ){
        sql += "freight='"+pojo.getFreight()+"',";
        cnt++;
      }
      
//      if(Integer.toString(pojo.getCategoryId()) != null ){
//        sql += "categoryId='"+pojo.getCategoryId()+"',";
//        cnt++;
//      }
//      
//      if(Integer.toString(pojo.getSubcategoryId()) != null ){
//    	  sql += "subcategoryId='"+pojo.getSubcategoryId()+"',";
//    	  cnt++;
//      }
      
      if(pojo.getStatus() != null && !pojo.getStatus().equals("")){
    	  sql += "status='"+pojo.getStatus()+"',";
    	  cnt++;
      }
      
      if(pojo.getPlace() != null && !pojo.getPlace().equals("")){
    	  sql += "place='"+pojo.getPlace()+"',";
    	  cnt++;
      }
      
      if(cnt > 0){
        sql = sql.substring(0,sql.length()-1);//去掉最后一个逗号
        sql += " where id=?";
      }
      
      pst = conn.prepareStatement(sql);
      pst.setInt(1, pojo.getId());
      System.out.println(sql);
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
  public int delete(Goods goods) throws SQLException {
    try{
      // 删除一般就这样写了，大多数情况下，我们只是删除一条记录
      // 即便是批量删除，大多时候也是前端选中n条记录，把ids传给后台来处理
      String sql = "delete from goods where id=?";
      pst = conn.prepareStatement(sql);
      pst.setInt(1, goods.getId());
      //System.out.println(sql);
      int i = pst.executeUpdate();
      return i;
    }catch(Exception e){
      e.printStackTrace();
      return -1;
    }
  }
}
