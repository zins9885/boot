package com.smhrd.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // 기본생성자
@Getter
@Setter
@AllArgsConstructor
public class Board {
	 private int idx;
	 private String title;
	 private String content;
	 private String writer;
	 private String img;
	 private String indate;
	
}
