package io.vilya.boreas;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
/**
 * @author iamaprin
 * @time 2017年6月18日 下午9:39:35
 */
public @interface Subscribe {
    
    String topic();
    
}
