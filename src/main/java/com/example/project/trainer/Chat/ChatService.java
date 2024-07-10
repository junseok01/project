package com.example.project.trainer.Chat;

import com.example.project.trainer.Chat.dto.*;
import com.example.project.trainer.Chat.entity.ChatRoom;

import java.util.List;

public interface ChatService {
    void createChatRoom(String loginId, String trainerId);
    ChatRoom getChatRoom(String loginId, String trainerId);
    ChatMessageResponse saveMessage(ChatMessageRequest message);
    List<ChatRoomResponse> findAllChatRooms(String loginId);
    List<ChatRoomResponseTrainerVer> findAllChatRoomsTrainerVer(String trainerId);
    List<ChatMessageResponse> findAllChatMessages(String roomId);
    ChatRoomResponsechat findAllChatMessagesByRoomId(String roomId);
}
