package com.test.meetroom.repository;

import com.test.meetroom.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByStartDateAfterAndEndDateBefore(Date startDate, Date endDate);
}
