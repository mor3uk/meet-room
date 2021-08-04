package com.test.meetroom.service;

import com.test.meetroom.entity.Event;
import com.test.meetroom.entity.User;
import com.test.meetroom.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void createEvent(Event event, User user) {
        event.setUser(user);
        eventRepository.save(event);
    }

    public List<Event> getEventsFilteredByDates(Date startDate, Date endDate) {
        return eventRepository.findAllByStartDateAfterAndEndDateBefore(startDate, endDate);
    }
}
