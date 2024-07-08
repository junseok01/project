package com.example.project.trainer.Chat.entity;

import com.example.project.login.UserEntity;
import com.example.project.trainer.TrainerEntity;
import jakarta.persistence.*;
import lombok.*;
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
	private String userName;
	private String trainerId;//트레이너 번호
	private String trainerName;
	@CreationTimestamp
	private Date createDate;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "roomId")
	private List<ChatMessage> messages = new ArrayList<>();

	public ChatRoom(String roomId, String userId, String userName, String trainerId, String trainerName) {
		this.roomId = roomId;
		this.userId = userId;
		this.userName = userName;
		this.trainerId = trainerId;
		this.trainerName = trainerName;
	}

	public String getMessage() {
		return messages.get(messages.size()-1).getMessage();
	}
	public Date getModifyDate() {
		return messages.get(messages.size()-1).getCreateDate();
	}
}
