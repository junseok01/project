package com.example.project.trainer.Chat;

import com.example.project.trainer.Chat.entity.ChatMessage;
import com.example.project.trainer.Chat.entity.ChatRoom;
import com.example.project.trainer.Chat.repository.ChatMessageRepository;
import com.example.project.trainer.Chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatDAOImpl implements ChatDAO{
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    @Override
    public void createChatRoom(ChatRoom room) {
        chatRoomRepository.save(room);
    }
    public ChatRoom findChatRoom(String user_id, String trainer_id) {
        return chatRoomRepository.findByuserIdAndTrainerId(user_id,trainer_id);
    }

    @Override
    public ChatMessage saveMessage(ChatMessage message) {
        ChatMessage save = chatMessageRepository.save(message);
        chatMessageRepository.flush();
        System.out.println(save);
        return save;
    }

    @Override
    public List<ChatRoom> findAllChatRooms(String user_id) {
        return chatRoomRepository.findByuserId(user_id);
    }

    @Override
    public List<ChatRoom> findAllChatRoomsTrainerVer(String trainer_id) {
        return chatRoomRepository.findByTrainerId(trainer_id);
    }

    @Override
    public List<ChatMessage> findAllChatMessages(String roomId) {
            return chatMessageRepository.findAllByRoomIdOrderByMessageIdAsc(roomId);
    }
    public ChatRoom findChatRoomById(String id) { return chatRoomRepository.findByRoomId(id);}
}
