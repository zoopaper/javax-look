package org.javax.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/5/7
 * Time: 21:53
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Component
public @interface TriggerType {
    String value() default "";

    /**
     * 定义定时器触发时间
     */
    String cronExpression() default "";
}
