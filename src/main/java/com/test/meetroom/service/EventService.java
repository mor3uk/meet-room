package com.test.meetroom.service;

import com.test.meetroom.entity.Event;
import com.test.meetroom.entity.User;
import com.test.meetroom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class EventService {

    @Autowired
    private UserRepository userRepository;

    public void createEvent(Event event, User user) throws ParseException {
        event.setUser(user);
        user.addEvent(event);
        userRepository.save(user);
    }
}
