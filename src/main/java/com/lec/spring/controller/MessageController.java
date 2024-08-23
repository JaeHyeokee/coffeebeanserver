package com.lec.spring.controller;

import com.lec.spring.domain.ChatRoom;
import com.lec.spring.domain.Message;
import com.lec.spring.domain.User;
import com.lec.spring.service.ChatRoomService;
import com.lec.spring.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/sendMessage/{chatRoomId}")
    @SendTo("/topic/public/{chatRoomId}")
    @Transactional
    public Message sendMessage(@DestinationVariable Long chatRoomId, @Payload Message message) {
        ChatRoom chatRoom = chatRoomService.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("ChatRoom not found"));

        if (message.getSender() == null || message.getSender().getUserId() == null) {
            throw new RuntimeException("Sender information is missing");
        }

        message.setSendTime(LocalDateTime.now());
        message.setIsRead(true);
        message.setChatRoom(chatRoom);

        System.out.println("Received message: " + message);
        return messageService.sendMessage(message, message.getSender().getUserId());
    }

    @PostMapping("/messages/read/{chatRoomId}/{userId}")
    public void markMessagesAsRead(@PathVariable Long chatRoomId, @PathVariable Long userId) {
        messageService.markMessagesAsRead(chatRoomId, userId);
    }

    @GetMapping("/messages/{chatRoomId}")
    public List<Message> getMessagesByChatRoomId(@PathVariable Long chatRoomId) {
        return messageService.MessageByRoomId(chatRoomId);
    }

    @GetMapping("/user/{userId}")
    public List<Message> findBySenderId(@PathVariable Long userId) {
        return messageService.findBySenderId(userId);
    }

    @DeleteMapping("/messages/{messageId}")
    public void deleteMessage(@PathVariable Long messageId) {
        messageService.deleteMessage(messageId);
    }

    @GetMapping("/leave/{chatRoomId}")
    public ResponseEntity<Long> leaveMessage(@PathVariable Long chatRoomId) {
        Long isJoin = chatRoomService.leaveMessage(chatRoomId);
        return ResponseEntity.ok(isJoin);
    }
}
