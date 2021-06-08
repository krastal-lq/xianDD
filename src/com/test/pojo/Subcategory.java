package com.test.pojo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Subcategory
 */
@WebServlet("/Subcategory")
public class Subcategory extends Base {
	private static final long serialVersionUID = 1L;
       
    int id;
    int categoryId;
    String subcategory;
    
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getSubcategory() {
		return subcategory;
	}
	
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	@Override
	public String toString() {
		return "Subcategory [id=" + id + ", categoryId=" + categoryId + ", subcategory=" + subcategory + "]";
	}
    
    

}
