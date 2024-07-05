package com.example.project.trainer.Chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomResponse {
    private String roomId;//채팅룸이름
    private Long trainer;
    private Date createDate;
    private Date modifyDate;
}
