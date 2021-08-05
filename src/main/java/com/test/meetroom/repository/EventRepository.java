package com.test.meetroom.repository;

import com.test.meetroom.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByStartDateAfterAndEndDateBefore(Date startDate, Date endDate);

    @Query("SELECT e FROM Event e where e.startDate <= :endDate AND e.startDate >= :startDate OR e.startDate <= :startDate AND e.endDate >= :startDate OR e.endDate >= :endDate AND e.startDate <= :endDate")
    List<Event> findAllOverlapped(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
