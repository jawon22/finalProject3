package com.kh.teamup.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import com.kh.teamup.vo.RoomVO;

@Service
public class ChannelServiceImpl implements ChannelService{
	private List<RoomVO> roomList = new CopyOnWriteArrayList<>();

	@Override
	public RoomVO createRoom(int chatRoomNo) {
		RoomVO room = RoomVO.builder().chatRoomNo(chatRoomNo).build();
		return room;
		
	}
	

}
