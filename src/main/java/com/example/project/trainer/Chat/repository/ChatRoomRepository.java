package com.example.project.trainer.Chat.repository;

import com.example.project.trainer.Chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,String> {
    ChatRoom findByUser_LoginIdAndTrainer_BoardNo(String user_id, Long trainer_id);
}
