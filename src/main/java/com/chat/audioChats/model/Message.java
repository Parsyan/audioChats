package com.chat.audioChats.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

//@Entity
@Data
@Builder
public class Message {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private String messageText;
    private String messageSender;
    private String messagedUser;
    private LocalDateTime messageTime;

//    @ManyToOne
//    @JoinColumn(name = "chat_id", nullable = false)
//    private Chat chat;
    private String chatName;

    @Override
    public String toString() {
        return "Message{" +
//                "id=" + id +
                ", messageText='" + messageText + '\'' +
                ", messageSender='" + messageSender + '\'' +
                ", messagedUser='" + messagedUser + '\'' +
                ", messageTime='" + messageTime + '\'' +
                ", chatName='" + chatName + '\'' +
//                ", chat=" + chat.getChatName() +
                '}';
    }
}
