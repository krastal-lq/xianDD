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

@WebServlet("/com/test/controller/goods/Delete")
public class Delete extends HttpServlet {
  
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
    int id = Integer.parseInt(request.getParameter("id"));
    pojo.setId(id);
    pojo.setCondition("goods.id="+id);
    
    // 2.(调)调用ServiceDAO的方法，完成对应业务
    JsonData jd = dto.delete(pojo);
    
    // 3.(存)将数据对象存储到request作用范围变量
    request.setAttribute("JsonData", jd);
    
    // 4.(转)将业务转发到View
    RequestDispatcher rd = request.getRequestDispatcher("/com/test/view/JSON");
    rd.forward(request, response);
  }
}