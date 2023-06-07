package com.smhrd.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.myapp.domain.Member;
import com.smhrd.myapp.service.MemberService;

//데이터만 반환하는 컨트롤러 (RestController는 view 반환 X)
// @Controller+ @ResponseBody
// 데이터(모델)을 반환해주는 컨트롤러 지정
@RestController
public class MemberController {
  @Autowired
  private MemberService service;
	// Spring 기본순서
	//(요청처리)Controller -> Service(class) -> mapper Interface-> mapper XML
	//(결과처리)mapper XML -> mapper Interface-> Service-> Controller
	//Controller : 요청받는거, 응답 하는거에 집중
	//Service: 데이터 가공, 요청 가공에 집중 (로직)
	@GetMapping("/member")
	public List<Member> memberList(){
		//List<Member> 반환(return)
		//-> java에서 제공하는 객체타입
		// jackson library (boot 기본 내장)
		// java object ->json 형식으로 만들어줌 (잭슨라이브러리)
		// json-> java object
		return service.memberList();
	}
}
