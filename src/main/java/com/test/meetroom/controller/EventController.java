package com.test.meetroom.controller;

import com.test.meetroom.dto.EventDto;
import com.test.meetroom.entity.Event;
import com.test.meetroom.entity.User;
import com.test.meetroom.mapper.EventMapper;
import com.test.meetroom.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventMapper eventMapper;

    @GetMapping("/event/add")
    public String eventPage(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUsername());
        return "event";
    }

    @PostMapping("/event/add")
    public String addEvent(@ModelAttribute EventDto eventDto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        try {
            Event event = eventMapper.mapToEventEntity(eventDto);
            eventService.createEvent(event, user);
        } catch (ParseException e) {
            return "redirect:/event/add";
        }
        return "redirect:/";
    }
}
