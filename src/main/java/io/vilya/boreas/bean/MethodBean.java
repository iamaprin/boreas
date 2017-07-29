package io.vilya.boreas.bean;

import java.lang.reflect.Method;

import io.vilya.boreas.listener.BoreasJMSListener;

/**
 * @author iamaprin
 * @time 2017年7月4日 下午10:51:11
 */
@SuppressWarnings("serial")
public class MethodBean extends BaseBean {
    
    private BoreasJMSListener object;
    
    private Method method;

    /**
     * @return the object
     */
    public BoreasJMSListener getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public MethodBean setObject(BoreasJMSListener object) {
        this.object = object;
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
    public MethodBean setMethod(Method method) {
        this.method = method;
        return this;
    }
    
    
    
}
