package com.smhrd.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.board.converter.ImageConverter;
import com.smhrd.board.converter.ImageToBase64;
import com.smhrd.board.domain.Board;
import com.smhrd.board.service.BoardService;

// 뷰 보여줄 때 컨트롤러 
@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	// 전체 게시판 보여줄 때 
	@GetMapping("/board")
	public String BoardList (Model model)  { // model : 데이터를 저장할 수있는 공간 
		List<Board> list = service.getBoard();
		model.addAttribute("boardList", service.getBoard());
		return "boardlist";
	}
	
	@GetMapping("/board/writeform")
	public String writeForm() {
		return "boardform";
	}
	
	// @RequestPart : multipart/form-data 에 특화된 어노테이션
	// file을 추가하기 위해서는 MultipartFile 형태로 받아줘야함 
	@PostMapping ("/board/write")
	public String write( Board b , @RequestPart("photo") MultipartFile file) {
		System.out.println(b.getTitle()+","+b.getContent()+","+b.getWriter());
		System.out.println(file.getOriginalFilename());
		// 겹치지 않는 랜덤한 문자 생성하기위해서 >> 파일이름을 겹치지 않게 하기위해 UUID를 활용함
		System.out.println(UUID.randomUUID().toString());
		// file.getOriginalFilename이 뒤로 가야됨! 순서중요 
		String newFileName = UUID.randomUUID().toString() +file.getOriginalFilename() ;
		
		//이미지 file -> 저장(지정된 경로에)
		try {
			file.transferTo(new File(newFileName));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b.setImg(newFileName);
		// b(Board) -> title, content , writer, img(filename)
		// board table(DB) -> 랜덤숫자+파일 이름만 저장 
		// 컨트롤러 (요청,응답) , 데이터 가공, 인코딩 필요한 작업 >> service에서 처리함 >> mapper값 넘김
		  int cnt =  service.write(b);
		
		  System.out.println(cnt); 
		
		  //페이지이동
		  if(cnt>0) {
			  // boardlist.jsp
			  return "redirect:/board";
		  }else {
			  // boardform.jsp 
			  return "redirect:/board/writeform";
		  }
	}
	
	
	@GetMapping("/board/content/{idx}")
	     public String content(@PathVariable("idx") int idx , Model model) {
		
		Board b = service.content(idx);
		File file = new File("c:\\uploadImage\\"+b.getImg());
		
		ImageConverter<File, String> converter = new ImageToBase64();
		
		try {
			String fileStringValue = converter.convert(file);
			System.out.println(fileStringValue);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("board" , b);
		return "boardcontent";
	}
}
