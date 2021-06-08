package com.test.dao;

import java.sql.*;
import java.util.ArrayList;

import com.test.pojo.Merchant;

public class MerchantDAOImpl implements MerchantDAO {
  
  private Connection conn = null;
  private PreparedStatement pst = null;
  
  /**
   * 无参构造函数
   */
  public MerchantDAOImpl() {
    super();
  }
  
  /**
   * 建议定义一个带参的构造函数
   * 实例化的时候，完成连接的注入
   */
  public MerchantDAOImpl(Connection conn) {
    super();
    this.conn = conn;
  }
  
  /**
   * 查询记录
   */
  public ArrayList<Merchant> select(Merchant pojo) throws SQLException {
    try{
      String sql = "select "
                 + "  merchant.id as id, "
                 + "  merchant.name as name, "
                 + "  merchant.accout as accout, "
                 + "  merchant.status as status "
                 + "from merchant "
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
      ArrayList<Merchant> al = new ArrayList<Merchant>();//封装数据并返回给Service层
      while(rs.next()){
        //将goods相关信息保存到po对象的各个属性中
    	  Merchant po = new Merchant();//持久层对象，用于调用POJO类的方法
        po.setId(rs.getInt("id"));
        po.setName(rs.getString("name"));
        po.setAccout(rs.getString("accout"));
        po.setStatus(rs.getString("status"));
        //将以上全部信息添加到集合
        al.add(po);
      }
       System.out.println(al.toString());
      return al;
    }catch(Exception e){
      e.printStackTrace();
      return null;
    }
  }
    
  /**
   * 统计记录数
   */
  public int count(Merchant pojo) throws SQLException {
    try{
      String sql = "select count(*) as cnt from merchant where 1=1 ";
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
  public int insert(Merchant pojo) throws SQLException {
    try{
      String sql = "insert into "
                 + "merchant(name,accout,password,imgURL,status) "
                 + "values(?,?,?,?,?) ";
      pst = conn.prepareStatement(sql);
      pst.setString(1, pojo.getName());
      pst.setString(2, pojo.getAccout());
      pst.setString(3, pojo.getPassword());
      pst.setString(4, pojo.getImgURL());
      pst.setString(5, pojo.getStatus());
      System.out.println(sql);
      int i = pst.executeUpdate();
      return i;
    }catch(Exception e){
      e.printStackTrace();
      return -1;
    }
  }
  
  /**
   * 更新部分记录
   */
  public int update(Merchant pojo) throws SQLException {
    try{
      int cnt = 0;
      String sql = "update merchant set ";

      
      if(pojo.getName() != null && !pojo.getName().equals("")){
        sql += "name='"+pojo.getName()+"',";
        cnt++;
      }
      
      if(pojo.getAccout() != null && !pojo.getAccout().equals("")){
          sql += "accout='"+pojo.getAccout()+"',";
          cnt++;
        }
      
      if(pojo.getPassword() != null && !pojo.getPassword().equals("")){
          sql += "password='"+pojo.getPassword()+"',";
          cnt++;
        }
      
      if(pojo.getStatus() != null && !pojo.getStatus().equals("")){
    	  sql += "status='"+pojo.getStatus()+"',";
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
  public int delete(Merchant merchant) throws SQLException {
    try{
      // 删除一般就这样写了，大多数情况下，我们只是删除一条记录
      // 即便是批量删除，大多时候也是前端选中n条记录，把ids传给后台来处理
      String sql = "delete from merchant where id=?";
      pst = conn.prepareStatement(sql);
      pst.setInt(1, merchant.getId());
      System.out.println(sql);
      int i = pst.executeUpdate();
      return i;
    }catch(Exception e){
      e.printStackTrace();
      return -1;
    }
  }
}
