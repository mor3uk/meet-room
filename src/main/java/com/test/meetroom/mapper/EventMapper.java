package com.test.meetroom.mapper;

import com.test.meetroom.dto.EventDto;
import com.test.meetroom.entity.Event;
import com.test.meetroom.entity.Member;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventMapper {

    public Event mapToEventEntity(EventDto eventDto) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Event event = new Event();

        if (eventDto.getMembers() != null) {
            Set<Member> members = eventDto.getMembers()
                    .stream()
                    .map((email) -> new Member(email, event))
                    .collect(Collectors.toSet());
            event.setMembers(members);
        }

        event.setTitle(eventDto.getTitle());
        event.setStartDate(dateFormat.parse(eventDto.getStartDate()));
        event.setEndDate(dateFormat.parse(eventDto.getEndDate()));
        return event;
    }
}
