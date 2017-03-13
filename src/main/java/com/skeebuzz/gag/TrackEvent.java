package com.skeebuzz.gag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackEvent {

    String trackerId() default "";

    String category() default "category";

    String action() default "action";

    String label() default "label";

    int value() default 0;

    boolean nonInteraction() default true;
}
