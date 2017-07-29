package io.vilya.boreas.listener;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vilya.boreas.annotation.Subscribe;
import io.vilya.boreas.bean.BoreasMessage;
import io.vilya.boreas.bean.Subsciption;

/**
 * @author iamaprin
 * @time 2017年6月18日 下午9:34:50
 */
public class ListenerHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerHandler.class);
    
    private List<Subsciption> subsciptions;
    
    public ListenerHandler() {
        subsciptions = new LinkedList<>();
    }
    
    public void handle(List<BoreasJMSListener> listeners) {
        if (listeners == null) {
            throw new IllegalArgumentException("listeners must not be null");
        }
        
        for (BoreasJMSListener listener : listeners) {
            handleOne(listener);
        }
    }
    
    private void handleOne(BoreasJMSListener listener) {
        Class<? extends BoreasJMSListener> clazz = listener.getClass();
        
        Method[] methods = clazz.getDeclaredMethods();
        Subscribe annotation;
        String topic;
        Class<?>[] parameterTypes;
        for (Method method : methods) {
            if (!method.isAnnotationPresent(Subscribe.class)) {
                continue;
            }
            
            // check annotation
            annotation = method.getAnnotation(Subscribe.class);
            topic = annotation.topic();
            if (topic.length() == 0) {
                LOGGER.warn("the topic is empty in {}.{}",clazz.getName(), method.getName());
                continue;
            }
            
            // check parameter
            parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 0
                    || BoreasMessage.class.isAssignableFrom(parameterTypes[0])) {
                LOGGER.warn("invalid parameters in {}.{}",clazz.getName(), method.getName());
                continue;
            }
            
            subsciptions.add(new Subsciption()
                    .setTopic(topic)
                    .setInstance(listener)
                    .setMethod(method));  
        }
    }
}
