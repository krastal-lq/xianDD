package com.test.pojo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.Serializable;

/**
 * Servlet implementation class refund
 */
@WebServlet("/refund")
public class Refund extends Base implements Serializable{
	private static final long serialVersionUID = 1L;
       
    int id;
    int userId;
    String sequence;
    String name;
    String telephone;
    String address;
    String reason;
    String status;
    String refundStatus;
    String refundTime;
    float price;
    
    
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
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getRefundStatus() {
		return refundStatus;
	}
	
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	
	public String getRefundTime() {
		return refundTime;
	}
	
	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "refund [id=" + id + ", userId=" + userId + ", sequence=" + sequence + ", name=" + name + ", telephone="
				+ telephone + ", address=" + address + ", reason=" + reason + ", status=" + status + ", refundStatus="
				+ refundStatus + ", refundTime=" + refundTime + ", price=" + price + "]";
	}
    

}
