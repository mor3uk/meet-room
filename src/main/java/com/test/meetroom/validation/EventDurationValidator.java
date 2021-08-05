package com.test.meetroom.validation;

import com.test.meetroom.controller.dto.EventDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.concurrent.TimeUnit;

public class EventDurationValidator implements ConstraintValidator<ValidEventDuration, EventDto> {

    private int minMinutes;

    private int maxHours;

    @Override
    public void initialize(ValidEventDuration constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        minMinutes = constraintAnnotation.minMinutes();
        maxHours = constraintAnnotation.maxHours();
    }

    @Override
    public boolean isValid(EventDto eventDto, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        if (eventDto.getStartDate() != null && eventDto.getEndDate() != null) {
            long diffInMs = eventDto.getEndDate().getTime() - eventDto.getStartDate().getTime();
            long diffInMinutes = TimeUnit.MINUTES.convert(diffInMs, TimeUnit.MILLISECONDS);
            long diffInHours = TimeUnit.HOURS.convert(diffInMs, TimeUnit.MILLISECONDS);

            if (diffInMinutes < minMinutes || diffInHours > maxHours) {
                isValid = false;
            }
        }

        return isValid;
    }
}
