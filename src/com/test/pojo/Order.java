package com.test.pojo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.pojo.User;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Order")
public class Order extends Base {
	private static final long serialVersionUID = 1L;
       
    int id;
    int userId;
    String sequence;
    String name;
    String telephone;
    String address;
    String status;
    String orderTime;
    String remark;
    float totalPrice;
    
    List<User> userList;
    List<Orderdetail> orderdetailList;
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getSequence() {
		return sequence;
	}
	
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getOrderTime() {
		return orderTime;
	}
	
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public float getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public List<Orderdetail> getOrderdetailList() {
		return orderdetailList;
	}

	public void setOrderdetailList(List<Orderdetail> orderdetailList) {
		this.orderdetailList = orderdetailList;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", sequence=" + sequence + ", name=" + name + ", telephone="
				+ telephone + ", address=" + address + ", status=" + status + ", orderTime=" + orderTime + ", remark="
				+ remark + ", totalPrice=" + totalPrice + ", userList=" + userList +", orderdetailList=" + orderdetailList 
				+ ", condition=" + condition + ", limit=" + limit
				+ ", orderBy=" + orderBy + "]";
	}

	


    
    
}
