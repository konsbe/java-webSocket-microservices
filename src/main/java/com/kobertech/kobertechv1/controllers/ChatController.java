package com.kobertech.kobertechv1.controllers;
import com.kobertech.kobertechv1.models.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/message")
    @SendTo("/chatroom/public")
    public ChatModel receiveMessage(@Payload ChatModel message) {
        System.out.println(message.toString());
        return message;
    }

    @MessageMapping("/chat/add-user")
    @SendTo("/chatroom/public")
    public ChatModel addUser(@Payload ChatModel message, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSenderName());
        return message;

    }

    @MessageMapping("/chat/private-message")
    public ChatModel receivePrivateMessage(@Payload ChatModel message) {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        System.out.println(message.toString());
        return message;
    }
}
