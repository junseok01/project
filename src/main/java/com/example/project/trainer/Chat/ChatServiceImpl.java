package com.example.project.trainer.Chat;

import com.example.project.login.UserService;
import com.example.project.trainer.Chat.dto.ChatMessageRequest;
import com.example.project.trainer.Chat.dto.ChatMessageResponse;
import com.example.project.trainer.Chat.dto.ChatRoomResponse;
import com.example.project.trainer.Chat.entity.ChatMessage;
import com.example.project.trainer.Chat.entity.ChatRoom;
import com.example.project.trainer.TrainerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatDAO dao;
    private final UserService userService;
    private final TrainerService trainerService;

    @Override
    public void createChatRoom(String loginId, Long boardNo) {
            dao.createChatRoom(new ChatRoom(UUID.randomUUID().toString(), loginId, boardNo));
    }

    @Override
    public ChatRoom getChatRoom(String loginId, Long boardNo) {
        return dao.findChatRoom(loginId,boardNo);
    }

    @Override
    public ChatMessageResponse saveMessage(ChatMessageRequest message) {
        ModelMapper modelMapper = new ModelMapper();
        System.out.println(message+"서비스>>>>>>>>>>>>>>>>>");
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ChatMessage chatMessage = modelMapper.map(message, ChatMessage.class);
        System.out.println(chatMessage);
        ChatMessageResponse responseMsg = modelMapper.map(dao.saveMessage(chatMessage),ChatMessageResponse.class);
        return responseMsg;
    }

    @Override
    public List<ChatRoomResponse> findAllChatRooms(String loginId) {
        ModelMapper modelMapper = new ModelMapper();
        return dao.findAllChatRooms(loginId).stream()
                .map(entity -> modelMapper.map(entity,ChatRoomResponse.class))
                .collect(Collectors.toList());
    }
}