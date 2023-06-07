package com.smhrd.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// MySQL필드명과 같게 만들어주기
public class Product {
    private String ptype;
    private String pcode;
    private String pname;
    private int price;
    private String color;
    private String img; // 파일이름 -> 바이트로 변환된 문자열
}
