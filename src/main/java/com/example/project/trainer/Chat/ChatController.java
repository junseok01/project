package com.example.project.trainer.Chat;

import com.example.project.trainer.Chat.dto.ChatMessageRequest;
import com.example.project.trainer.Chat.dto.ChatMessageResponse;
import com.example.project.trainer.Chat.dto.ChatRoomResponse;
import com.example.project.trainer.Chat.entity.MessageType;
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

    @GetMapping("/chat")
    public String connectRoom(String loginId, Long boardNo, Model model){
        if (service.getChatRoom(loginId, boardNo) == null) {
            service.createChatRoom(loginId,boardNo);
        }
        String roomId = service.getChatRoom(loginId,boardNo).getRoomId();
        model.addAttribute("roomId",roomId);
        return "trainer/chat";
    }
    @MessageMapping("/{roomId}")
    @SendTo("/sub/chat/room/{roomId}")
    public ChatMessageResponse chat(@DestinationVariable("roomId") String roomId, ChatMessageRequest message) {
        message.setType(MessageType.MESSAGE);
        System.out.println(message);
        System.out.println(roomId);
        //메시지내역저장
        ChatMessageResponse chatmessage = service.saveMessage(message);
        return chatmessage;
    }
    @GetMapping("/chatRoom")
    public String ChatRoomList(String loginId,Model model){
        System.out.println(loginId);
        List<ChatRoomResponse> chatRooms = service.findAllChatRooms(loginId);
        System.out.println(chatRooms+"12324214124");
        model.addAttribute("roomlist",chatRooms);
        return "mypageLayout/chatlist";
    }

}
