package com.example.project.trainer.Chat.dto;

import com.example.project.trainer.Chat.entity.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequest {
    private String roomId;
    private String sender;//채팅을 보낸 사람
    private String senderName;
    private String message;// 메세지
    private MessageType type;
}
