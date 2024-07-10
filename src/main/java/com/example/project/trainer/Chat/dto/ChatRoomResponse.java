package com.example.project.trainer.Chat.dto;

import com.example.project.trainer.Chat.entity.ChatMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomResponse {
    private String roomId;//채팅룸이름
    private String trainerName;
    private String trainerId;
    private Date createDate;
    @JsonFormat(pattern = "MM-dd",timezone = "Asia/Seoul")
    private Date modifyDate;
    private String lastMessage;
}
