package com.example.project.trainer.Chat;

import com.example.project.trainer.Chat.entity.ChatMessage;
import com.example.project.trainer.Chat.entity.ChatRoom;

import java.util.List;

public interface ChatDAO {
    void createChatRoom(ChatRoom room);
    public ChatRoom findChatRoom(String user_id,String trainer_id);
    ChatMessage saveMessage(ChatMessage message);
    List<ChatRoom> findAllChatRooms(String user_id);
    List<ChatRoom> findAllChatRoomsTrainerVer(String trainer_id);
    List<ChatMessage> findAllChatMessages(String roomId);
    ChatRoom findChatRoomById(String id);
}
