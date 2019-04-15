package ru.sandem.java4.lesson7.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    int priority() default 10;
    int minPriority() default 0;
    int maxPriority() default 10;
    String methodName();
    Class classForTest();
}
