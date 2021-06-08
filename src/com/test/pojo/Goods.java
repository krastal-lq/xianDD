package com.test.pojo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Goods
 */
@WebServlet("/Goods")
public class Goods extends Base {
	private static final long serialVersionUID = 1L;
       
    int id;
    String name;
    String sequence;
	float price; 
    float promotPrice; 
    String unit;
    int number;
    float freight;
    int categoryId;
    int subcategoryId;
    String place;
    String status;

	List<Goodscategory> categoryList;
    List<Subcategory> subcategoryList;
    List<Goodsimage> GoodsimageList;
    
	public List<Goodsimage> getGoodsimageList() {
		return GoodsimageList;
	}

	public void setGoodsimageList(List<Goodsimage> goodsimageList) {
		GoodsimageList = goodsimageList;
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
	
	public String getSequence() {
		return sequence;
	}
	
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public float getFreight() {
		return freight;
	}
	
	public void setFreight(float freight) {
		this.freight = freight;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public int getSubcategoryId() {
		return subcategoryId;
	}
	
	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	 public float getPromotPrice() {
			return promotPrice;
	}

	public void setPromotPrice(float promotPrice) {
		this.promotPrice = promotPrice;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	 public List<Goodscategory> getCategoryList() {
			return categoryList;
	}

	public void setCategoryList(List<Goodscategory> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Subcategory> getSubcategoryList() {
		return subcategoryList;
	}

	public void setSubcategoryList(List<Subcategory> subcategoryList) {
		this.subcategoryList = subcategoryList;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", sequence=" + sequence + ", price=" + price + ", promotPrice="
				+ promotPrice + ", unit=" + unit + ", number=" + number + ", freight=" + freight + ", categoryId="
				+ categoryId + ", subcategoryId=" + subcategoryId + ", place=" + place + ", status=" + status
				+ ", categoryList=" + categoryList + ", subcategoryList=" + subcategoryList + ", GoodsimageList="
				+ GoodsimageList + "]";
	}



	
	
}
