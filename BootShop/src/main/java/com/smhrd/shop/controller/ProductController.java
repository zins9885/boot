package com.smhrd.shop.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.shop.service.ProductService;

@RestController
//CORS : Cross-Origin
@CrossOrigin("http://localhost:3000")
public class ProductController {
    // 1.모든 상품정보 불러오기
	// 2. 특정 상품 1개에 대한 정보 불러오기
	// 데이터(model)만 반환함, (뷰는 리액트로반활할예정)
	
	@Autowired
	private ProductService service;
	
	// 상품을 전체 리스트로 가져올때 
	@GetMapping("/")
	public JSONArray productList() {
		System.out.println("이곳은 8090");
		JSONArray array = service.productList();
		return array;
		
	}
	
	//한개만 고를때 
	@GetMapping("/{pcode}")           // url에 포함되어 넘어오는 파라미터 받아주기
	public JSONObject productOne(@PathVariable("pcode") String pcode) {
		return service.productOne(pcode);
		
		
	}
}
