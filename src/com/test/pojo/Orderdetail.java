package com.test.pojo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Orderdetail
 */
@WebServlet("/Orderdetail")
public class Orderdetail extends Base {
	private static final long serialVersionUID = 1L;
       
    int id;
    int orderId;
    int goodsId;
    int num;
    float price;
    float totalPrice;
    List<Goods> goodsList;
    
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getGoodsId() {
		return goodsId;
	}
	
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	@Override
	public String toString() {
		return "Orderdetail [id=" + id + ", orderId=" + orderId + ", goodsId=" + goodsId + ", num=" + num + ", price="
				+ price + ", totalPrice=" + totalPrice + ", goodsList=" + goodsList + ", condition=" + condition
				+ ", limit=" + limit + ", orderBy=" + orderBy + "]";
	}


	
    
}
