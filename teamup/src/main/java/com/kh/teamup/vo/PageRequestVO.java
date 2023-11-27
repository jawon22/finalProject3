package com.kh.teamup.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PageRequestVO {
	
	private SearchVO searchVO;
	private PagenationVO pagenationVO;
	

}
