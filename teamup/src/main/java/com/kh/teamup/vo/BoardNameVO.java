package com.kh.teamup.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data @Builder @AllArgsConstructor @NoArgsConstructor 
public class BoardNameVO {//메인목록 + 상세(수정용)+상세(조회수증가)
    private long boardNo;
    private int empNo;
    private int deptNo;
    private String comId;
    private String boardTitle;
    private String boardContent;
    private Timestamp boardUpdateDate, boardWriteDate;
    private int boardReadCount, boardReplyCount;
    // 추가된 필드
    private String empName; // emp 테이블의 emp_name 필드
    private String deptName; // dept 테이블의 dept_name 필드
}
