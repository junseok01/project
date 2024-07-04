package com.example.project.trainer.Chat;

import com.example.project.login.UserService;
import com.example.project.trainer.Chat.dto.ChatMessageRequest;
import com.example.project.trainer.Chat.dto.ChatMessageResponse;
import com.example.project.trainer.Chat.entity.ChatMessage;
import com.example.project.trainer.Chat.entity.ChatRoom;
import com.example.project.trainer.TrainerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatDAO dao;
    private final UserService userService;
    private final TrainerService trainerService;

    @Override
    public void createChatRoom(String loginId, Long boardNo) {
            dao.createChatRoom(new ChatRoom(UUID.randomUUID().toString(), userService.chatsearch(loginId), trainerService.gettrainerInfo(boardNo)));
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
//        TypeMap<ChatMessageRequest,ChatMessage> typeMap = modelMapper.createTypeMap(ChatMessageRequest.class, ChatMessage.class);
//        typeMap.addMapping(ChatMessageRequest :: getRoomId, ChatMessage :: setRoom);
        ChatMessage chatMessage = modelMapper.map(message, ChatMessage.class);
        System.out.println(chatMessage);
        ChatMessageResponse responseMsg = modelMapper.map(dao.saveMessage(chatMessage),ChatMessageResponse.class);
        return responseMsg;
    }
}