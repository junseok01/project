package com.example.project.trainer.Chat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Date createDate;
    @JsonFormat(pattern = "MM-dd",timezone = "Asia/Seoul")
    private Date modifyDate;
    private String lastMessage;
}
