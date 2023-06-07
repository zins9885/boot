package com.smhrd.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.shop.domain.Product;

@Mapper
public interface ProductMapper {

	// product 전체 정보 불러오기
	public List<Product> productList();
	
	// product 한개 가져오기
	public Product productOne(String pcode);
}
