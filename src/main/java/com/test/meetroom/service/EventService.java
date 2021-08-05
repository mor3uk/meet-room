package com.test.meetroom.service;

import com.test.meetroom.entity.Event;
import com.test.meetroom.entity.User;
import com.test.meetroom.repository.EventRepository;
import com.test.meetroom.repository.MemberRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MemberRepository memberRepository;

    public void createEvent(Event event, User user) {
        event.setUser(user);
        eventRepository.save(event);
    }

    public List<Event> getEventsFilteredByDates(Date startDate, Date endDate) {
        return eventRepository.findAllByStartDateAfterAndEndDateBefore(startDate, endDate);
    }

    public Event getEventById(Long id) throws NotFoundException {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event not found"));
    }

    @Transactional
    public void updateEvent(Event event) {
        eventRepository.save(event);
        memberRepository.deleteAllByEvent_Id(event.getId());
    }

    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }
}
