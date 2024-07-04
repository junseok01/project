package com.example.project.trainer.Chat.dto;

import com.example.project.trainer.Chat.entity.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageResponse {
    private Long messageId;
    private String roomId;
    private String sender;//채팅을 보낸 사람
    private String message;// 메세지
    private MessageType type;
    private Date createDate;//채팅발송시간
}
