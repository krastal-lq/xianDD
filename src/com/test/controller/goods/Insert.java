package com.test.controller.goods;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.pojo.JsonData;
import com.test.pojo.Subcategory;
import com.test.pojo.Goods;
import com.test.pojo.Goodscategory;
import com.test.pojo.Goodsimage;
import com.test.service.GoodsServiceImpl;

@WebServlet("/com/test/controller/goods/Insert")
public class Insert extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    doPost(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	// 初始化
    request.setCharacterEncoding("utf-8");
    GoodsServiceImpl dto = new GoodsServiceImpl();//数据传输层对象，用于调用业务层方法
    Goods pojo = new Goods(); //持久层对象，用于参数传递
    
    // 1.(参)获取参数值，并保存到POJO对象
    String name = request.getParameter("name");
    if(name == ""){name = null;}
    
    String sequence = request.getParameter("sequence");
    if(sequence == ""){sequence = null;}
    
    int number = Integer.parseInt(request.getParameter("number"));
    
    String unit = request.getParameter("unit");
    if(unit == ""){unit = null;}
    
    float freight = Float.parseFloat(request.getParameter("freight"));
    
    float price = Float.parseFloat(request.getParameter("price"));
    
    float promotPrice = Float.parseFloat(request.getParameter("promotPrice"));
    
    String category = request.getParameter("category");
    if(category == ""){category = null;}
    
    String subcategory = request.getParameter("subcategory");
    if(subcategory == ""){subcategory = null;}
    
    String province = request.getParameter("province");
    if(province == ""){province = null;}
    String city = request.getParameter("city");
    if(city == ""){city = null;}
    String district = request.getParameter("district");
    if(district == ""){district = null;}
    
    String place = province.concat(city).concat(district);
    if(place == ""){place = null;}
    
    String status = request.getParameter("status");
    if(status == ""){status = null;}
    
    pojo.setSequence(sequence);
    pojo.setName(name);
    pojo.setPrice(price);
    pojo.setPromotPrice(promotPrice);
    pojo.setUnit(unit);
    pojo.setNumber(number);
    pojo.setFreight(freight);
    pojo.setPlace(place);
    pojo.setStatus(status);
    //System.out.println(pojo);
    //将Goodscategory相关信息保存到pojo对象的CategoryList属性中
    Goodscategory ct =new Goodscategory();
    ArrayList<Goodscategory> ci = new ArrayList<Goodscategory>();
    ct.setCategory(category);
    //设置select条件
    if (category != null && category.length() > 0){
    	ct.setCondition("goodscategory.category = '"+category+"'");
    }else{
    	ct.setCondition("");
    }
    ci.add(ct);
    pojo.setCategoryList(ci);
    //System.out.println(pojo);
    //将Subcategory相关信息保存到pojo对象的SubcategoryList属性中
    Subcategory sct =new Subcategory();
    ArrayList<Subcategory> sci = new ArrayList<Subcategory>();
    sct.setSubcategory(subcategory);
    //设置select条件
    if (subcategory != null && subcategory.length() > 0){
    	sct.setCondition("subcategory.subcategory = '"+subcategory+"'");
    }else{
    	sct.setCondition("");
    }
    sci.add(sct);
    pojo.setSubcategoryList(sci);
    //System.out.println(pojo);
    
    // 2.(调)调用ServiceDAO的方法，完成对应业务
    JsonData jd = dto.insert(pojo);
    // 3.(存)将数据对象存储到request作用范围变量
    request.setAttribute("JsonData", jd);
    
    // 4.(转)将业务转发到View
    RequestDispatcher rd = request.getRequestDispatcher("/com/test/view/JSON");
    rd.forward(request, response);
  }
}
