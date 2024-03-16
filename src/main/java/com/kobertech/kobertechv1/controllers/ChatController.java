package com.kobertech.kobertechv1.controllers;

import com.kobertech.kobertechv1.models.ChatModel;
import com.kobertech.kobertechv1.models.RoomModel;

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
        System.out.println("chat.message: " + message.getSenderName());
        System.out.println(message.toString());
        return message;
    }

    @MessageMapping("/private.message")
    public ChatModel recMessage(@Payload ChatModel message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        System.out.println(message.toString());
        return message;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/chatroom/public")
    public RoomModel addUser(@Payload ChatModel message, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        RoomModel room = new RoomModel();
        System.out.println("chat.addUser: " + message.toString());
        String username = message.getSenderName();
        headerAccessor.getSessionAttributes().put("username", username);
        // Get the topic name (e.g., "/chatroom/public")
        String topic = headerAccessor.getDestination();

        // Add the user to the set of subscribed users for the topic
        // or  Remove the user by username from all topics
        if ("JOIN".equals(message.getStatus().toString())) {
            subscribedUsersByTopic.computeIfAbsent(topic, k -> new HashSet<>()).add(username);
        } else if ("LEAVE".equals(message.getStatus().toString())) {
            for (Set<String> users : subscribedUsersByTopic.values()) {
                users.remove(username);
            }
        }
        // Broadcast the updated list of connected users to all clients
        String msg = broadcastConnectedUsers(topic).toString();
        message.setMessage(msg);
        room.setConnctedUsers(broadcastConnectedUsers(topic));
        room.setStatus(message.getStatus());
        return room;

    }

    // Helper method to broadcast the list of connected users for a specific topic
    private Set<String> broadcastConnectedUsers(String topic) {
        Set<String> connectedUsers = subscribedUsersByTopic.getOrDefault(topic, Collections.emptySet());
        simpMessagingTemplate.convertAndSend(topic + "/users", connectedUsers);
        System.out.println("Broadcasting connected users for " + topic + ": " + connectedUsers);
        return connectedUsers;
    }

}
