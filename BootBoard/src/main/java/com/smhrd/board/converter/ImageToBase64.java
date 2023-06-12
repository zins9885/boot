package com.smhrd.board.converter;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class ImageToBase64 extends ImageConverter<File,String> {

	@Override
	public String convert(File f) throws IOException {
		
		// 파일을 문자열로 변환코드 (외우지말고 이거 갖다쓰면댐!) 
		// 1. 파일 가지고 오기 -> 바이트 배열형태로 읽기 
		// FileUtils는 commoms-io에서 제공함(pom에등록)
		byte[] fileContent = FileUtils.readFileToByteArray(f);
		
		// 2. 바이트 형태를 인코딩(base64)해서 문자열로 변환 >> 보통 이미지를 이걸로 인코딩함.
		String result = Base64.getEncoder().encodeToString(fileContent);
		return result;
	}

}
