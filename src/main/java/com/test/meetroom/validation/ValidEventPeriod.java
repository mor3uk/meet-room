package com.test.meetroom.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EventPeriodValidator.class)
public @interface ValidEventPeriod {

    String message() default "Events should not overlap each other in time";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
