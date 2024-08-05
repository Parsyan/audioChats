package com.chat.audioChats.repo;

import com.chat.audioChats.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByUsername(String username);
    boolean existsByUsername(String username);
}
