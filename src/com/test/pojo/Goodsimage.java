package com.test.pojo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Goodsimage
 */
@WebServlet("/Goodsimage")
public class Goodsimage extends Base {
	private static final long serialVersionUID = 1L;
       
    int id;
    int goodsId;
    String url;
    
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getGoodsId() {
		return goodsId;
	}
	
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Goodsimage [id=" + id + ", goodsId=" + goodsId + ", url=" + url + "]";
	}
    
	
}
