package com.restapidemo.restdemo.Models.POJO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {
	@Id
	private long productid;
	@Column(length=30)
	private String name;
	@Column(length=20)
	private String category;
	private int price;
	private int available;
}
