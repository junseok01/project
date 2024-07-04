package com.example.project.trainer.Chat;

import com.example.project.trainer.Chat.entity.ChatMessage;
import com.example.project.trainer.Chat.entity.ChatRoom;
import com.example.project.trainer.Chat.repository.ChatMessageRepository;
import com.example.project.trainer.Chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatDAOImpl implements ChatDAO{
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    @Override
    public void createChatRoom(ChatRoom room) {
        chatRoomRepository.save(room);
    }
    public ChatRoom findChatRoom(String user_id, Long trainer_id) {
        return chatRoomRepository.findByUser_LoginIdAndTrainer_BoardNo(user_id,trainer_id);
    }

    @Override
    public ChatMessage saveMessage(ChatMessage message) {
        return chatMessageRepository.save(message);
    }
}
