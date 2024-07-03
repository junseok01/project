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
	private String roomName;//채팅룸이름
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;//일반회원 아이디
	@ManyToOne
	@JoinColumn(name = "trainer_id")
	private TrainerEntity trainer;//트레이너 번호
	@CreationTimestamp
	private Date createDate;
	@UpdateTimestamp
	private Date modifyDate;

}
