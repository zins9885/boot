package com.smhrd.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.smhrd.board.domain.Board;

@Mapper
public interface BoardMapper {
 
	//추상메서드정의
	public int write(Board b);
	
	//게시판  전체 데이터 조회 
	public List<Board> getBoard();
	
	//게시판 선택한 해당 페이지만 조회
	@Select("select * from board where idx=#{idx}")
	public Board content(int idx);
	
	//삭제
	@Delete("delete from board where idx=#{idx}")
	public void delete(int idx);
	
}
