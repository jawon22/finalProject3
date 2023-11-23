package com.kh.teamup.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kh.teamup.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data @Builder @AllArgsConstructor @NoArgsConstructor 
public class BoardVO {
    private long boardNo;
    private int empNo;
    private int deptNo;
    private String comId;
    private String boardTitle;
    private String boardContent;
    private Timestamp boardUpdateDate, boardWriteDate;
    private int boardReadCount;

    // 추가된 필드
    private int size;  // 페이지당 항목 수
    private int page;  // 현재 페이지 번호
    private int totalCount;  // 총 게시물 수
}
