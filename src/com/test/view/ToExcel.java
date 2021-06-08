package com.test.view;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.test.util.Util;

@WebServlet("/com/test/view/Excel")
public class ToExcel extends HttpServlet {
	
  private static final long serialVersionUID = 1L;
  
  public ToExcel() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
    String[] title = (String[]) request.getAttribute("title");//定义标题
    String fileName = (String) request.getAttribute("fileName");//定义工作簿名，即xls文件名
    String sheetName = (String) request.getAttribute("sheetName");//定义工作表名，即sheet名
    String[][] content = (String[][]) request.getAttribute("content");//定义数据行
    //创建HSSFWorkbook 
    HSSFWorkbook wb = Util.getExcel(sheetName, title, content, null);
    //响应到客户端
    try {
      fileName = new String(fileName.getBytes(),"ISO8859-1");
      response.setContentType("application/octet-stream;charset=utf-8");
      response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
      response.addHeader("Pargam", "no-cache");
      response.addHeader("Cache-Control", "no-cache");
      OutputStream os = response.getOutputStream();
      wb.write(os);
      os.flush();
      os.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
