package com.test.meetroom.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EventDurationValidator.class)
public @interface ValidEventDuration {

    int minMinutes() default 30;

    int maxHours() default 24;

    String message() default "{event.duration.validation.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
