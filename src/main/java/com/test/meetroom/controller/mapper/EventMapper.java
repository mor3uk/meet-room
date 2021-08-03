package com.test.meetroom.controller.mapper;

import com.test.meetroom.controller.dto.EventDtoRequest;
import com.test.meetroom.controller.dto.EventDtoResponse;
import com.test.meetroom.entity.Event;
import com.test.meetroom.entity.Member;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventMapper {

    public Event mapToEventEntity(EventDtoRequest eventDtoRequest) throws ParseException {
        Event event = new Event();

        if (eventDtoRequest.getMembers() != null) {
            Set<Member> members = eventDtoRequest.getMembers()
                    .stream()
                    .map((email) -> new Member(email, event))
                    .collect(Collectors.toSet());
            event.setMembers(members);
        }

        event.setTitle(eventDtoRequest.getTitle());
        event.setStartDate(eventDtoRequest.getStartDate());
        event.setEndDate(eventDtoRequest.getEndDate());
        return event;
    }

    public EventDtoResponse mapToEventDtoResponse(Event event, Long currentUserId) {
        EventDtoResponse eventDtoResponse = new EventDtoResponse();

        if (event.getMembers() != null) {
            List<String> members = event.getMembers()
                    .stream()
                    .map(Member::getEmail)
                    .collect(Collectors.toList());
            eventDtoResponse.setMembers(members);
        }

        eventDtoResponse.setId(event.getId());
        eventDtoResponse.setCanEdit(currentUserId.equals(event.getUser().getId()));
        eventDtoResponse.setTitle(event.getTitle());
        eventDtoResponse.setStartDate(event.getStartDate());
        eventDtoResponse.setEndDate(event.getEndDate());

        return eventDtoResponse;
    }
}
