package com.kobertech.kobertechv1.controllers;

import com.kobertech.kobertechv1.models.ChatModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    // Map to store subscribed users for each topic
    private final Map<String, Set<String>> subscribedUsersByTopic = new HashMap<>();

    @MessageMapping("/chat.message")
    @SendTo("/chatroom/public")
    public ChatModel receiveMessage(@Payload ChatModel message) {
        System.out.println("Subscribe username: " + message.getSenderName());
        System.out.println(message.toString());
        return message;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/chatroom/public")
    public ChatModel addUser(@Payload ChatModel message, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        System.out.println("chat.addUser: " + message.toString());
        String username = message.getSenderName();
        headerAccessor.getSessionAttributes().put("username", username);

        // Get the topic name (e.g., "/chatroom/public")
        String topic = headerAccessor.getDestination();

        // Add the user to the set of subscribed users for the topic
        subscribedUsersByTopic.computeIfAbsent(topic, k -> new HashSet<>()).add(username);
        System.out.println("subscribedUsersByTopic: " + topic);
        // Broadcast the updated list of connected users to all clients
        broadcastConnectedUsers(topic);

        return message;

    }

    @MessageMapping("/chat/disconnect")
    public void disconnect(SimpMessageHeaderAccessor headerAccessor) {
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        String topic = headerAccessor.getDestination();

        // Remove the user from the set of subscribed users for the topic
        subscribedUsersByTopic.computeIfAbsent(topic, k -> new HashSet<>()).remove(username);

        // Broadcast the updated list of connected users to all clients
        broadcastConnectedUsers(topic);
    }

    // Helper method to broadcast the list of connected users for a specific topic
    private void broadcastConnectedUsers(String topic) {
        Set<String> connectedUsers = subscribedUsersByTopic.getOrDefault(topic, Collections.emptySet());
        simpMessagingTemplate.convertAndSend(topic + "/users", connectedUsers);
        System.out.println("Broadcasting connected users for " + topic + ": " + connectedUsers);
    }

    @MessageMapping("/chat/private-message")
    public ChatModel receivePrivateMessage(@Payload ChatModel message) {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        System.out.println(message.toString());
        return message;
    }

}
