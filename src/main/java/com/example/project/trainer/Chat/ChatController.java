package com.example.project.trainer.Chat;

import com.example.project.login.UserService;
import com.example.project.trainer.Chat.dto.ChatMessageRequest;
import com.example.project.trainer.Chat.dto.ChatMessageResponse;
import com.example.project.trainer.Chat.dto.ChatRoomResponse;
import com.example.project.trainer.Chat.entity.MessageType;
import com.example.project.trainer.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService service;
    private final SimpMessageSendingOperations operations;
    private final TrainerService trainerService;
    private final UserService userService;
    @GetMapping("/chat")
    public String connectRoom( String loginId, String trainerId, Model model){
        System.out.println(loginId+" - "+trainerId);
        if (service.getChatRoom(loginId, trainerId) == null) {
            service.createChatRoom(loginId,trainerId);
        }
        String trainerName = trainerService.gettrainerInfo(trainerId).getName();
        String roomId = service.getChatRoom(loginId,trainerId).getRoomId();
        List<ChatMessageResponse> chatList = service.findAllChatMessages(roomId);
        model.addAttribute("chatList", chatList);
        model.addAttribute("roomId",roomId);
        model.addAttribute("trainerName",trainerName);
        return "trainer/chat2";
    }
    @MessageMapping("/{roomId}")
    @SendTo("/sub/chat/room/{roomId}")
    public ChatMessageResponse chat(@DestinationVariable("roomId") String roomId, ChatMessageRequest message) {
        message.setType(MessageType.MESSAGE);
        //메시지내역저장
        ChatMessageResponse chatmessage = service.saveMessage(message);
        return chatmessage;
    }
    @GetMapping("/chatRoom")
    public String ChatRoomList(String loginId,Model model){
        List<ChatRoomResponse> chatRooms = service.findAllChatRooms(loginId);
        model.addAttribute("roomlist",chatRooms);
        return "mypage/chatlist";
    }
    @GetMapping("/chatRoom2")
    public String ChatRoomList2(String trainerId,Model model){
        List<ChatRoomResponse> chatRooms = service.findAllChatRooms(trainerId);
        model.addAttribute("roomlist",chatRooms);
        return "mypageLayout/chatlist";
    }
}
