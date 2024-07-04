package com.example.project.trainer.Chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomRequest {
	private String roomName;//채팅룸이름 UUID사용
	private String loginId;//일반회원이름
	private String trainerId;//문의할 트레이너 이름
	
}
