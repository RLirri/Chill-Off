package com.music.anotation;

import java.lang.annotation.*;

/**
 *
 * @author Huang Ruixin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowired {
    String value() default "";
}
