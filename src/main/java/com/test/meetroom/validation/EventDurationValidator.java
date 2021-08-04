package com.test.meetroom.validation;

import com.test.meetroom.controller.dto.EventDtoRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.concurrent.TimeUnit;

public class EventDurationValidator implements ConstraintValidator<ValidEventDuration, EventDtoRequest> {

    private int minMinutes;

    private int maxHours;

    @Override
    public void initialize(ValidEventDuration constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        minMinutes = constraintAnnotation.minMinutes();
        maxHours = constraintAnnotation.maxHours();
    }

    @Override
    public boolean isValid(EventDtoRequest eventDtoRequest, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        if (eventDtoRequest.getStartDate() != null && eventDtoRequest.getEndDate() != null) {
            long diffInMs = eventDtoRequest.getEndDate().getTime() - eventDtoRequest.getStartDate().getTime();
            long diffInMinutes = TimeUnit.MINUTES.convert(diffInMs, TimeUnit.MILLISECONDS);
            long diffInHours = TimeUnit.HOURS.convert(diffInMs, TimeUnit.MILLISECONDS);

            if (diffInMinutes < minMinutes || diffInHours > maxHours) {
                isValid = false;
            }
        }

        return isValid;
    }
}
