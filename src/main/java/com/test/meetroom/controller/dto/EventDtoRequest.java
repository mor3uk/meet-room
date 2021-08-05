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

    @NotEmpty(message = "{event.title.validation.notEmpty.message}")
    @Length(min = 5, max = 50, message = "{event.title.validation.length.message}")
    private String title;

    @NotNull(message = "{event.date.validation.notEmpty.message}")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = "{event.date.validation.futureOrPresent.message}")
    private Date startDate;

    @NotNull(message = "{event.date.validation.notEmpty.message}")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = "{event.date.validation.futureOrPresent.message}")
    private Date endDate;

    private List<@NotEmpty(message = "{event.members.validation.notEmpty.message}") @Email(message = "{event.members.validation.email.message}") String> members;

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
