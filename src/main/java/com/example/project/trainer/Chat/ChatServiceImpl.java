package com.example.project.trainer.Chat;

import com.example.project.login.UserEntity;
import com.example.project.login.UserService;
import com.example.project.trainer.Chat.dto.ChatMessageRequest;
import com.example.project.trainer.Chat.dto.ChatMessageResponse;
import com.example.project.trainer.Chat.dto.ChatRoomResponse;
import com.example.project.trainer.Chat.entity.ChatMessage;
import com.example.project.trainer.Chat.entity.ChatRoom;
import com.example.project.trainer.TrainerEntity;
import com.example.project.trainer.TrainerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void createChatRoom(String loginId, String trainerId) {
        UserEntity user = userService.chatsearch(loginId);
        TrainerEntity trainer = trainerService.gettrainerInfo(trainerId);
        dao.createChatRoom(new ChatRoom(UUID.randomUUID().toString(), loginId, user.getName(), trainerId, trainer.getName()));
    }

    @Override
    public ChatRoom getChatRoom(String loginId, String trainerId) {
        return dao.findChatRoom(loginId,trainerId);
    }
    @Transactional
    @Override
    public ChatMessageResponse saveMessage(ChatMessageRequest message) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ChatRoom chatRoom = dao.findChatRoomById(message.getRoomId());
        ChatMessage chatMessage = modelMapper.map(message, ChatMessage.class);
        ChatMessage saveMessage = dao.saveMessage(chatMessage);
        System.out.println(saveMessage+"12313214141");
//        List<ChatMessage> messages = chatRoom.getMessages();
//        messages.add(saveMessage);
//        chatRoom.setMessages(messages);
        ChatMessageResponse responseMsg = modelMapper.map(saveMessage,ChatMessageResponse.class);
        return responseMsg;
    }

    @Override
    public List<ChatRoomResponse> findAllChatRooms(String loginId) {
        ModelMapper modelMapper = new ModelMapper();
        return dao.findAllChatRooms(loginId).stream()
                .map(entity ->new ChatRoomResponse(entity.getRoomId(),
                        entity.getTrainerName(), entity.getTrainerId(),entity.getCreateDate(),entity.getModifyDate(),entity.getMessage()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatRoomResponse> findAllChatRoomsTrainerVer(String trainerId) {
        ModelMapper modelMapper = new ModelMapper();
        return dao.findAllChatRoomsTrainerVer(trainerId).stream()
                .map(entity ->new ChatRoomResponse(entity.getRoomId(),
                        entity.getUserName(), entity.getUserId(),entity.getCreateDate(),entity.getModifyDate(),entity.getMessage()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatMessageResponse> findAllChatMessages(String roomId) {
        ModelMapper modelMapper = new ModelMapper();
            return dao.findAllChatMessages(roomId).stream()
                    .map(entity -> modelMapper.map(entity, ChatMessageResponse.class))
                    .collect(Collectors.toList());

    }
}