package com.kh.teamup.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "TodoList 객체")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TodoDto {
	private int todoNo, empNo;
	private String todoContent;
	@Builder.Default
	private boolean todoDone = false;
}
