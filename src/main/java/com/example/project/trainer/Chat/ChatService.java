package com.example.project.trainer.Chat;

import com.example.project.trainer.Chat.dto.ChatMessageRequest;
import com.example.project.trainer.Chat.dto.ChatMessageResponse;
import com.example.project.trainer.Chat.entity.ChatRoom;

public interface ChatService {
    void createChatRoom(String loginId, Long boardNo);
    ChatRoom getChatRoom(String loginId, Long boardNo);
    ChatMessageResponse saveMessage(ChatMessageRequest message);
}
