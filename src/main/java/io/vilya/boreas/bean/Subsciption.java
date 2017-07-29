package io.vilya.boreas.bean;

import java.lang.reflect.Method;

import javax.validation.constraints.NotNull;

/**
 * @author iamaprin
 * @time 2017年6月18日 下午10:46:34
 */
@SuppressWarnings("serial")
public class Subsciption extends BaseBean {

    @NotNull
    private String topic;
    
    @NotNull
    private Method method;
    
    @NotNull
    private Object instance;
    
    
    /**
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic the topic to set
     */
    public Subsciption setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    /**
     * @return the method
     */
    public Method getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public Subsciption setMethod(Method method) {
        this.method = method;
        return this;
    }

    /**
     * @return the instance
     */
    public Object getInstance() {
        return instance;
    }

    /**
     * @param instance the instance to set
     */
    public Subsciption setInstance(Object instance) {
        this.instance = instance;
        return this;
    } 
}
