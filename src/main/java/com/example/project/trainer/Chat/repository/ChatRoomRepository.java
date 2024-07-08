package com.example.project.trainer.Chat.repository;

import com.example.project.trainer.Chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,String> {
    ChatRoom findByuserIdAndTrainerId(String user_id, String trainer_id);
    List<ChatRoom> findByuserId(String user_id);
    List<ChatRoom> findByTrainerId(String trainer_id);
    ChatRoom findByRoomId(String room_id);
}
