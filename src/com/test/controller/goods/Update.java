package com.test.controller.goods;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.pojo.JsonData;
import com.test.pojo.Goods;
import com.test.service.GoodsServiceImpl;

@WebServlet("/com/test/controller/goods/Update")
public class Update extends HttpServlet {
  
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
    // sequence,name,gender,birthday,card,nation,nativePlace,political,userId
    int id = Integer.parseInt(request.getParameter("id"));
    
    String sequence = request.getParameter("sequence");
    if(sequence == ""){sequence = null;}
    
    String name = request.getParameter("name");
    if(name == ""){name = null;}
    
    float price = Float.parseFloat(request.getParameter("price"));
    
    float promotPrice = Float.parseFloat(request.getParameter("promotPrice"));
    
    String unit = request.getParameter("unit");
    if(unit == ""){unit = null;}
    
    int number = Integer.parseInt(request.getParameter("number"));
    
    float freight = Float.parseFloat(request.getParameter("freight"));
    
    //int categoryId = Integer.parseInt(request.getParameter("categoryId"));
    
    //int subcategoryId = Integer.parseInt(request.getParameter("subcategoryId"));
    
    String place = request.getParameter("place");
    if(place == ""){place = null;}
    
    String status = request.getParameter("status");
    if(status == ""){status = null;}
    
    pojo.setId(id);
    pojo.setSequence(sequence);
    pojo.setName(name);
    pojo.setPrice(price);
    pojo.setPromotPrice(promotPrice);
    pojo.setUnit(unit);
    pojo.setNumber(number);
    pojo.setFreight(freight);
    //pojo.setCategoryId(categoryId);
    //pojo.setSubcategoryId(subcategoryId);
    pojo.setPlace(place);
    pojo.setStatus(status);
    
    // 2.(调)调用ServiceDAO的方法，完成对应业务
    JsonData jd = dto.update(pojo);
    
    // 3.(存)将数据对象存储到request作用范围变量
    request.setAttribute("JsonData", jd);
    
    // 4.(转)将业务转发到View
    RequestDispatcher rd = request.getRequestDispatcher("/com/test/view/JSON");
    rd.forward(request, response);
  }
}