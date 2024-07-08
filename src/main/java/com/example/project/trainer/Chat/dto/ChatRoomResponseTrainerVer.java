package com.example.project.trainer.Chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomResponseTrainerVer {
    private String roomId;//채팅룸이름
    private String userName;
    private String userId;
    private Date modifyDate;
    private String lastMessage;
}
