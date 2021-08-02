package com.test.meetroom.mapper;

import com.test.meetroom.dto.EventDtoRequest;
import com.test.meetroom.dto.EventDtoResponse;
import com.test.meetroom.entity.Event;
import com.test.meetroom.entity.Member;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventMapper {

    public Event mapToEventEntity(EventDtoRequest eventDtoRequest) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Event event = new Event();

        if (eventDtoRequest.getMembers() != null) {
            Set<Member> members = eventDtoRequest.getMembers()
                    .stream()
                    .map((email) -> new Member(email, event))
                    .collect(Collectors.toSet());
            event.setMembers(members);
        }

        event.setTitle(eventDtoRequest.getTitle());
        event.setStartDate(dateFormatter.parse(eventDtoRequest.getStartDate()));
        event.setEndDate(dateFormatter.parse(eventDtoRequest.getEndDate()));
        return event;
    }

    public EventDtoResponse mapToEventDtoResponse(Event event, Long currentUserId) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
        eventDtoResponse.setStartDate(dateFormatter.format(event.getStartDate()));
        eventDtoResponse.setEndDate(dateFormatter.format(event.getEndDate()));

        return eventDtoResponse;
    }
}
