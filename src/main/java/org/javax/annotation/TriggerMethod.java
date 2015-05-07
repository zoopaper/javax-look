package org.javax.annotation;

import java.lang.annotation.*;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/5/7
 * Time: 21:57
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface TriggerMethod {

    String value() default "";
}
