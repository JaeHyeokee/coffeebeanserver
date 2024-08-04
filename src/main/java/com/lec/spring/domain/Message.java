package com.lec.spring.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;             // 메세지 id

    private String messageText;         // 메세지 내용
    private LocalDateTime sendTime;     // 메세지 보낸 시간

    @Column(nullable = false)
    private boolean IsRead;             // 메세지 읽음 여부

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;        // ChatRoom 테이블과의 관계

    @ManyToOne
    @JoinColumn(name = "senderId", referencedColumnName = "userId")
    private User sender;               // 메세지를 보낸 User
}