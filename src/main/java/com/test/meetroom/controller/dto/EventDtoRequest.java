package com.test.meetroom.controller.dto;

import com.test.meetroom.validation.ValidEventDuration;
import com.test.meetroom.validation.ValidEventPeriod;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@ValidEventDuration
@ValidEventPeriod
public class EventDtoRequest {

    @NotEmpty(message = "Title must not be empty")
    @Length(min = 5, message = "Title length must be at least 5 characters")
    private String title;

    @NotNull(message = "Date must not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = "Date must be present or future")
    private Date startDate;

    @NotNull(message = "Date must not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = "Date must be present or future")
    private Date endDate;

    private List<@Email(message = "Invalid email") String> members;

    public EventDtoRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
