package com.restapidemo.restdemo.Models.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.restapidemo.restdemo.Models.DL.ServiceImpl.ProductService;
import com.restapidemo.restdemo.Models.POJO.Product;

public class ProductBL {
	@Autowired
	ProductService productService;
	public List<Product> allByCategory(String category){
		return productService.allByCategory(category);
	}
	public List<Product>getAll(){
		return productService.getAll();
	}
}
