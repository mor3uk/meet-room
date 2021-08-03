package com.test.meetroom.validation;

import com.test.meetroom.controller.dto.EventDtoRequest;
import com.test.meetroom.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EventPeriodValidator implements ConstraintValidator<ValidEventPeriod, EventDtoRequest> {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void initialize(ValidEventPeriod constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EventDtoRequest eventDtoRequest, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        if (eventDtoRequest.getStartDate() != null && eventDtoRequest.getEndDate() != null) {
            long overlappedEventsCount = eventRepository.countAllOverlapped(
                    eventDtoRequest.getStartDate(),
                    eventDtoRequest.getEndDate()
            );
            isValid = overlappedEventsCount == 0;
        }

        return isValid;
    }
}
