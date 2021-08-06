package com.test.meetroom.controller.mapper;

import com.test.meetroom.controller.dto.EventDto;
import com.test.meetroom.entity.Event;
import com.test.meetroom.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventMapper {

    public Event mapToEventEntity(EventDto eventDto) {
        Event event = new Event();
        updateEventFromDto(event, eventDto);
        return event;
    }

    public EventDto mapToEventDto(Event event) {
        EventDto eventDto = new EventDto();

        if (event.getMembers() != null) {
            List<String> members = event.getMembers()
                    .stream()
                    .map(Member::getEmail)
                    .collect(Collectors.toList());
            eventDto.setMembers(members);
        }

        eventDto.setId(event.getId());
        eventDto.setTitle(event.getTitle());
        eventDto.setStartDate(event.getStartDate());
        eventDto.setEndDate(event.getEndDate());

        return eventDto;
    }

    public EventDto mapToEventDto(Event event, Long currentUserId) {
        EventDto eventDto = mapToEventDto(event);
        eventDto.setCanEdit(currentUserId.equals(event.getUser().getId()));
        return eventDto;
    }

    public void updateEventFromDto(Event event, EventDto eventDto) {
        event.setTitle(eventDto.getTitle());
        event.setStartDate(eventDto.getStartDate());
        event.setEndDate(eventDto.getEndDate());

        Set<Member> members = null;
        if (eventDto.getMembers() != null) {
            members = eventDto.getMembers()
                    .stream()
                    .map((email) -> new Member(email, event))
                    .collect(Collectors.toSet());
        }
        event.setMembers(members);
    }
}
