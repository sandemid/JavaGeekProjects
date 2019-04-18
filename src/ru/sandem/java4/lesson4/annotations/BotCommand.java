package ru.sandem.java4.lesson4.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BotCommand {
    String name();
    String args();
    int minArgs() default 0;
    int maxArgs() default Integer.MAX_VALUE;
    String desc();
    boolean showInHelp() default true;
    String[] aliases();
}
