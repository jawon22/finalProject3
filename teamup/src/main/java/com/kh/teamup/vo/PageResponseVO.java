package com.kh.teamup.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PageResponseVO {
	
	private List<SearchVO> searchVO;
	private PagenationVO pagenationVO;
	

}

