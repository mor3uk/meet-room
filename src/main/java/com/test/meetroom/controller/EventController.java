package com.test.meetroom.controller;

import com.test.meetroom.controller.dto.EventDto;
import com.test.meetroom.entity.Event;
import com.test.meetroom.entity.User;
import com.test.meetroom.controller.mapper.EventMapper;
import com.test.meetroom.service.EventService;
import javassist.NotFoundException;
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
    public String addEventPage(
            EventDto eventDto,
            Model model
    ) {
        model.addAttribute("mode", "add");
        return "event";
    }

    @GetMapping("/event/edit/{id}")
    public String editEventPage(
            @PathVariable("id") Long id,
            Authentication authentication,
            Model model
    ) {
        User user = (User) authentication.getPrincipal();
        try {
            Event event = eventService.getEventById(id);
            if (!event.getUser().getId().equals(user.getId())) {
                return "redirect:/event/view/" + id;
            }

            EventDto eventDto = eventMapper.mapToEventDto(event);
            model.addAttribute("eventDto", eventDto);
            model.addAttribute("eventTitle", event.getTitle());
            model.addAttribute("mode", "edit");
        } catch (NotFoundException e) {
            return "error/404";
        }
        return "event";
    }

    @GetMapping("/event/view/{id}")
    public String viewEventPage(
            @PathVariable("id") Long id,
            Authentication authentication,
            Model model
    ) {
        User user = (User) authentication.getPrincipal();
        try {
            Event event = eventService.getEventById(id);
            if (event.getUser().getId().equals(user.getId())) {
                return "redirect:/event/edit/" + id;
            }

            EventDto eventDto = eventMapper.mapToEventDto(event);
            model.addAttribute("eventDto", eventDto);
            model.addAttribute("eventTitle", event.getTitle());
            model.addAttribute("mode", "view");
        } catch (NotFoundException e) {
            return "error/404";
        }
        return "event";
    }

    @GetMapping("/event/delete/{id}")
    public String deleteEvent(@PathVariable("id") Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        try {
            Event event = eventService.getEventById(id);
            if (!event.getUser().getId().equals(user.getId())) {
                return "redirect:/event/view/" + id;
            }
            eventService.deleteEvent(event);
        } catch (NotFoundException e) {
            return "error/404";
        }

        return "redirect:/?successEventDeletion";
    }

    @GetMapping("/event/list")
    public @ResponseBody List<EventDto> getEvents(
            @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
            @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return eventService.getEventsFilteredByDates(startDate, endDate)
                .stream()
                .map((event) -> eventMapper.mapToEventDto(event, user.getId()))
                .collect(Collectors.toList());
    }

    @PostMapping("/event/add")
    public String addEvent(
            @Valid EventDto eventDto,
            BindingResult bindingResult,
            Authentication authentication,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "add");
            return "event";
        }
        User user = (User) authentication.getPrincipal();
        Event event = eventMapper.mapToEventEntity(eventDto);
        eventService.createEvent(event, user);
        return "redirect:/?successEventAddition";
    }

    @PostMapping("/event/edit/{id}")
    public String editEvent(
            @PathVariable("id") Long id,
            @Valid EventDto eventDto,
            BindingResult bindingResult,
            Model model
    ) {
        try {
            Event event = eventService.getEventById(id);

            if (bindingResult.hasErrors()) {
                model.addAttribute("eventTitle", event.getTitle());
                model.addAttribute("mode", "edit");
                return "event";
            }

            eventMapper.updateEventFromDto(event, eventDto);
            eventService.updateEvent(event);
        } catch (NotFoundException e) {
            return "error/404";
        }
        return "redirect:/?successEventEdition";
    }
}
