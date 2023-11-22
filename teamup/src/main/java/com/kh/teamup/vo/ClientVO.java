package com.kh.teamup.vo;

import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(of = {}) //출력할때 여기에 작성한 항목만 작성
public class ClientVO {

	@JsonIgnore
	private WebSocketSession session;
	private Integer empNo, deptNo, empPositionNo;
	
	public ClientVO(WebSocketSession session) {
		this.session = session;
		
		Map<String, Object> attr = session.getAttributes();
		this.empNo = (Integer) attr.get("empNo");
		this.deptNo = (Integer)attr.get("deptNo");
		this.empPositionNo = (Integer)attr.get("empPositionNo");
		
	}
	
	
	
}
