package com.test.meetroom.controller;

import com.test.meetroom.dto.EventDtoRequest;
import com.test.meetroom.dto.EventDtoResponse;
import com.test.meetroom.entity.Event;
import com.test.meetroom.entity.User;
import com.test.meetroom.mapper.EventMapper;
import com.test.meetroom.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventMapper eventMapper;

    @GetMapping("/event/add")
    public String eventPage(
            EventDtoRequest eventDtoRequest,
            Authentication authentication,
            Model model
    ) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUsername());
        return "event";
    }

    @PostMapping("/event/add")
    public String addEvent(
            @Valid EventDtoRequest eventDtoRequest,
            BindingResult bindingResult,
            Authentication authentication,
            Model model
    ) throws ParseException {
        if (bindingResult.hasErrors()) {
            User user = (User) authentication.getPrincipal();
            model.addAttribute("username", user.getUsername());
            return "event";
        }
        User user = (User) authentication.getPrincipal();
        Event event = eventMapper.mapToEventEntity(eventDtoRequest);
        eventService.createEvent(event, user);
        return "redirect:/";
    }

    @GetMapping("/event/list")
    public @ResponseBody List<EventDtoResponse> getEvents(
            @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
            @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return eventService.getEventsFilteredByDates(startDate, endDate)
                .stream()
                .map((event) -> eventMapper.mapToEventDtoResponse(event, user.getId()))
                .collect(Collectors.toList());
    }
}
