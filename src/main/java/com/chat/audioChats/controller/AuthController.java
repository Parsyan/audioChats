package com.chat.audioChats.controller;

import com.chat.audioChats.model.Person;
import com.chat.audioChats.repo.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PersonRepository personRepository;

    @GetMapping({"/users", "/users/"})
    public List<Person> allUser() {
        return personRepository.findAll();
    }

    @GetMapping({"/user", "/user/"})
    public ResponseEntity findPerson(@RequestParam String username) {

//        Person person =
//        return (person == null) ? null : person;
        return new ResponseEntity( personRepository.findByUsername(username), HttpStatus.MULTI_STATUS);
    }

    @PostMapping({ "/user", "/user/" })
    public ResponseEntity personRegister(@RequestBody Person person){
        String error = " Unique Name Error ";
        if (personRepository.existsByUsername(person.getUsername())) {
            error = " Unique Name Error ";
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } else {
            personRepository.save(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
    }



}
