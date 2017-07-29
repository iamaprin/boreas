package io.vilya.boreas.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author iamaprin
 * @time 2017年6月18日 下午9:39:35
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Subscribe {
    
    String topic();
    
}
