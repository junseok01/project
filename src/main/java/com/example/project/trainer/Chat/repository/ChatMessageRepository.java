package com.example.project.trainer.Chat.repository;


import com.example.project.trainer.Chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
    @Query(
            value = "select * from chat_message m where m.room_id=?1 order by messageId",
            nativeQuery = true
    )
    List<ChatMessage> findAllByChatList(String roomId);
    List<ChatMessage> findAllByRoomIdOrderByMessageIdAsc(String roomId);
}
