package com.example.project.trainer.Chat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatMessage {
	@Id @GeneratedValue
	private Long messageId;
	@ManyToOne
	@JoinColumn(name="room_id")
	private ChatRoom room;
  	private String sender;//채팅을 보낸 사람
	private String message;// 메세지
   	@Enumerated(EnumType.STRING)
	private MessageType type;
    @CreationTimestamp
	private Date createDate;//채팅발송시간
}
