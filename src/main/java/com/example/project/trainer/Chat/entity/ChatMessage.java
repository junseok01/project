package com.example.project.trainer.Chat.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ChatMessage {
	@Id @GeneratedValue
	private Long messageId;
	private String roomId;
  	private String sender;//채팅을 보낸 사람
	private String senderName;
	private String message;// 메세지
   	@Enumerated(EnumType.STRING)
	private MessageType type;
    @CreationTimestamp
	private Date createDate;//채팅발송시간

	public ChatMessage(String roomId, String sender, String message, MessageType type) {
		this.roomId = roomId;
		this.sender = sender;
		this.message = message;
		this.type = type;
	}
}
