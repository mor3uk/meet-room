package com.test.meetroom.validation;

import com.test.meetroom.controller.dto.EventDtoRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.concurrent.TimeUnit;

public class EventDurationValidator implements ConstraintValidator<ValidEventDuration, EventDtoRequest> {

    @Override
    public void initialize(ValidEventDuration constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EventDtoRequest eventDtoRequest, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        if (eventDtoRequest.getStartDate() != null && eventDtoRequest.getEndDate() != null) {
            long diffInMs = eventDtoRequest.getEndDate().getTime() - eventDtoRequest.getStartDate().getTime();
            long diffInMinutes = TimeUnit.MINUTES.convert(diffInMs, TimeUnit.MILLISECONDS);
            long diffInHours = TimeUnit.HOURS.convert(diffInMs, TimeUnit.MILLISECONDS);

            if (diffInMinutes < 30 || diffInHours > 24) {
                isValid = false;
            }
        }

        return isValid;
    }
}
