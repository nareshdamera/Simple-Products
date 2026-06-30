package com.restapidemo.restdemo.Models.DL.Services;

import java.util.List;

import com.restapidemo.restdemo.Models.POJO.Product;

public interface IProduct {
	List<Product>allByCategory(String category);
	List<Product>getAll();
}
