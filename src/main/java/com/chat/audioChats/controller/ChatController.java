package com.chat.audioChats.controller;

import com.chat.audioChats.model.Chat;

import com.chat.audioChats.model.Message;
import com.chat.audioChats.model.Person;
import com.chat.audioChats.repo.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final PersonRepository personRepository;

    private final List<Chat> chats;

    @GetMapping("/")
    public List<Chat> allChats() {
//        logging
        System.out.println(" Chat List : " + chats);

        return chats;
    }

    @GetMapping({"/chat", "/chat/"})
    public Chat findChat(@RequestParam String chatName){

        for (Chat chat : chats){
            if (chat.getChatName().equals(chatName)){
                return chat;
            }
        }
        return null;
    }

    @PostMapping({"/register", "/register/"})
    public Chat chatRegistering(@RequestBody Chat chat ) throws Exception {

//        logging
        System.out.println("Adding chat : " + chat);

        if (chat.getPersonList().isEmpty()){
            throw new Exception(" No User in chat ");
        }
        for (Person person : chat.getPersonList()){
            if (!personRepository.existsByUsername(person.getUsername())){
                throw new Exception(" User isn't exist : " + person.getUsername());
            }
        }

        chats.add(chat);
        return chat;
    }


    @GetMapping({"/messages", "/messages/"})
    public List<Message> getMessages(@RequestParam String chatName){

        List<Message> messageList = null;
        for (Chat chat : chats){
            if (chat.getChatName().equals(chatName)){
                System.out.println(chat.getMessageList());
                messageList = chat.getMessageList();
            }
        }
        return messageList;
    }


    @PostMapping({"/sendMessage", "/sendMessage/"})
    public Chat sendMessage(@RequestBody Message message) throws Exception {

        Chat chat = null;
        for (Chat chat1 : chats) {
            if (chat1.getChatName().equals(message.getChatName())) {
                chat = chat1;
            }
        }

        for (Person person : chat.getPersonList()){
            if (!person.getUsername().equals(message.getMessageSender())){
                throw new Exception("User isn't in chat ");
            }
        }

//        chat.sendMessage(Message.builder()
//                .chat(chat)
//                .messageSender(messageSender)
//                .messagedUser(messagedUser)
//                .messageText(message)
//                .build());
        System.out.println(LocalDateTime.now());
        message.setMessageTime(LocalDateTime.now());
        chat.sendMessage(message);

        System.out.println(chat.getMessageList());

        return chat;
    }


}
