package com.restapidemo.restdemo.Models.DL.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapidemo.restdemo.Models.DL.Services.IProduct;
import com.restapidemo.restdemo.Models.POJO.Product;
import com.restapidemo.restdemo.Models.Repositories.ProductRepository;
@Service
public class ProductService implements IProduct{
	@Autowired
	ProductRepository productRepository;


	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public List<Product> allByCategory(String category) {
		// TODO Auto-generated method stub
		List<Product> list=productRepository.findAllByCategory(category);
		return list;
	}

	@Override
	public String addProduct(Product product) {
		// TODO Auto-generated method stub
		Product record=productRepository.findByProductId(product.getProductid());
		if(record==null) {
			productRepository.save(product);
			return "Successfully added";
		}else {
			return "Product already exists";
		}
		
	}

}
