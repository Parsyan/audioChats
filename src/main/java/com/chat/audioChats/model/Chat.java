package com.chat.audioChats.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
//@Entity
@Data
public class Chat implements Cloneable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @NotNull
    private String chatName;

//    @OneToMany(mappedBy = "chat")
    private List<Person> personList;

//    @OneToMany(mappedBy = "chat")
    private List<Message> messageList;

    public void addUser(Person user){
        if (personList.size() < 5) {

            personList.add(user);
        }
    }

    public boolean sendMessage(Message message){
        return messageList.add(message);
    }

    @Override
    public String toString() {
        return "Chat{" +
//                "id=" + id +
                ", chatName='" + chatName + '\'' +
                '}';
    }

}
