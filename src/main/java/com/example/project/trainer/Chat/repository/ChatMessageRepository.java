package com.example.project.trainer.Chat.repository;


import com.example.project.trainer.Chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{

}
