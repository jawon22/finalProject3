package com.kh.teamup.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PagenationVO {
	
//	private String type;
//	private String keyword;
//	
	private int page;
	private int count;
	private int size;
	private int navi;
	

}
