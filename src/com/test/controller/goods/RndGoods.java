package com.test.controller.goods;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.pojo.Goods;
import com.test.pojo.JsonData;
import com.test.service.GoodsServiceImpl;

/**
 * Servlet implementation class RndGoods
 */
@WebServlet("/goods/RndGoods")
public class RndGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
    GoodsServiceImpl dto = new GoodsServiceImpl();//数据传输层对象，用于调用业务层方法
    Goods pojo = new Goods(); //持久层对象，用于参数传递
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RndGoods() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//初始化
	    request.setCharacterEncoding("utf-8");
	    
	    //1.(参)获取参数值，并保存到POJO对象
	    pojo.setLimit(" limit 0,5 ");//分页条件
	    pojo.setOrderBy(" order by rnd ");//排序条件

	    //2.(调)调用ServiceDAO的方法，完成对应业务
	    //System.out.println(pojo.toString());
	    JsonData jd = dto.selectRnd(pojo);
	    //System.out.println(jd.toString());
	    
	    //3.(存)将数据对象存储到request作用范围变量
	    request.setAttribute("JsonData", jd);
	    
	    //4.(转)将业务转发到View层
	    //利用JsonResultSet将记录数total与记录集rows拼接成“total=50,rows=[{},{}...{}]”格式
	    RequestDispatcher rd = request.getRequestDispatcher("/com/test/view/JSON");
	    rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
