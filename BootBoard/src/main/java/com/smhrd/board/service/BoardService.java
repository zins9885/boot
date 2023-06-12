package com.smhrd.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.board.domain.Board;
import com.smhrd.board.mapper.BoardMapper;

@Service
public class BoardService {
 
	@Autowired
	private BoardMapper mapper;
	
     public int write(Board b) {
    	 return mapper.write(b);
    	 
     }
     
     // 전체게시판 보드 
     public List<Board> getBoard(){
    	 return mapper.getBoard();
     }
     //해당게시물 
     public Board content(int idx) {
    	 return mapper.content(idx);
     }
     
     // 삭제메서드
     public void delete (int idx) {
    	 mapper.delete(idx);
     }
     
}
