package com.test.meetroom.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EventDurationValidator.class)
public @interface ValidEventDuration {

    String message() default "Event duration should not exceed 24 hours or be less than 30 minutes";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
