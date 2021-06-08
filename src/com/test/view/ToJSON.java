package com.test.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
//import net.sf.json.JSONArray;
//import com.alibaba.fastjson.JSON;

@WebServlet("/com/test/view/JSON")
public class ToJSON extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  
  public ToJSON() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
    PrintWriter out = response.getWriter();
    Object JsonData = request.getAttribute("JsonData");
    JSONObject msg = JSONObject.fromObject(JsonData);//转换成JSON对象
    //JSONArray msg = JSONArray.fromObject(JsonData);//转换成JSON数组
    //System.out.println(msg);
    out.print(msg);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
