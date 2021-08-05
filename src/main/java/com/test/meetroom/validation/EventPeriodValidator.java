package com.test.meetroom.validation;

import com.test.meetroom.controller.dto.EventDto;
import com.test.meetroom.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EventPeriodValidator implements ConstraintValidator<ValidEventPeriod, EventDto> {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void initialize(ValidEventPeriod constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EventDto eventDto, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        if (eventDto.getStartDate() != null && eventDto.getEndDate() != null) {
            long overlappedEventsCount = eventRepository.countAllOverlapped(
                    eventDto.getStartDate(),
                    eventDto.getEndDate()
            );
            isValid = overlappedEventsCount == 0;
        }

        return isValid;
    }
}
