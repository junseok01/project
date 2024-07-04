package com.example.project.trainer.Chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService service;
    private final SimpMessageSendingOperations operations;

   // public String createRoom()
}
