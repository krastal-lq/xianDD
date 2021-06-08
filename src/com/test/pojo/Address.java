package com.test.pojo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Address
 */
@WebServlet("/Address")
public class Address extends Base {
	private static final long serialVersionUID = 1L;
       
	
	int id;
    int userId;
    String name;
    String telephone;
    String address;
	String defaulting;
    
    
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
	public String getDefaulting() {
		return defaulting;
	}
	
	public void setDefaulting(String defaulting) {
		this.defaulting = defaulting;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", userId=" + userId + ", name=" + name + ", telephone=" + telephone + ", address="
				+ address + ", defaulting=" + defaulting + "]";
	}

	

    
}
