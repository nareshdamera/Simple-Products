package com.restapidemo.restdemo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapidemo.restdemo.Models.DL.ServiceImpl.ProductService;
import com.restapidemo.restdemo.Models.POJO.Product;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;
	@GetMapping("/getbycategory/{category}")
	public List<Product> getAllByCategory(@PathVariable("category") String category){
		return productService.allByCategory(category);
	}
	@GetMapping("/getall")
	public List<Product> getall(){
		return productService.getAll();
	}
}
