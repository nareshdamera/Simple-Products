package com.restapidemo.restdemo.Models.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restapidemo.restdemo.Models.POJO.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("""
			select p from Product p where p.category=?1 
			""")
	List<Product> findAllByCategory(String catergory);
	
	@Query("""
			select p from Product p where p.productid=?1
			""")
	Product findByProductId(long id);
}
