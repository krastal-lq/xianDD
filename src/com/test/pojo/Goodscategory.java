package com.test.pojo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Goodscategory
 */
@WebServlet("/Goodscategory")
public class Goodscategory extends Base {
	private static final long serialVersionUID = 1L;
       
    int id;
    String category;
    
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Goodscategory [id=" + id + ", category=" + category + "]";
	}
    
    
}
