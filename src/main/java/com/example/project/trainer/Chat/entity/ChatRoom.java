package com.example.project.trainer.Chat.entity;

import com.example.project.login.UserEntity;
import com.example.project.trainer.TrainerEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatRoom {
	@Id
	private String roomId;//채팅룸이름
	private String userId;//일반회원 아이디
	private Long trainer;//트레이너 번호
	@CreationTimestamp
	private Date createDate;
	@UpdateTimestamp
	private Date modifyDate;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "roomId")
	private List<ChatMessage> messages = new ArrayList<>();

	public ChatRoom(String roomId, String userId, Long trainer) {
		this.roomId = roomId;
		this.userId = userId;
		this.trainer = trainer;
	}
}
