package com.test.pojo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Merchant
 */
@WebServlet("/Merchant")
public class Merchant extends Base {
	private static final long serialVersionUID = 1L;
       
    int id;
    String name;
    String accout;
    String password;
    String imgURL;
    String status;
    
    
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAccout() {
		return accout;
	}
	
	public void setAccout(String accout) {
		this.accout = accout;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getImgURL() {
		return imgURL;
	}
	
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	@Override
	public String toString() {
		return "Merchant [id=" + id + ", name=" + name + ", accout=" + accout + ", password=" + password + ", imgURL="
				+ imgURL + ", status=" + status + "]";
	}

	
    
}
