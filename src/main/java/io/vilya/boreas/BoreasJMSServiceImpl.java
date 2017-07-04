package io.vilya.boreas;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author iamaprin
 * @time 2017年6月18日 下午9:11:16
 */
public class BoreasJMSServiceImpl implements IBoreasJMSService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BoreasJMSServiceImpl.class);
    
    private List<BoreasJMSListener> listeners = new LinkedList<>();
    private boolean isInitlized = false;
    private Connection connection;
    private Session session;
    
    private Map<String, List<MethodBean>> topics = new HashMap<>();
    
    @Override
    public void publish(BoreasMessage message) {
        throw new BoreasException("unfinished interface!");
    }
    
    @Override
    public void addMessageListener(BoreasJMSListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
            LOGGER.info("succeed in adding listener: {}", listener.getClass().getName());
        } else {
            LOGGER.info("fail in adding listener: {}", listener.getClass().getName());
        }
    }
    
    @Override
    public void removeMessageListener(BoreasJMSListener listener) {
        if (!listeners.contains(listener)) {
            listeners.remove(listener);
            LOGGER.info("succeed in removing listener: {}", listener.getClass().getName());
        } else {
            LOGGER.info("fail in removing listener: {}", listener.getClass().getName());
        }
    }
    
    @Override
    public void init(ConnectionConfiguration configuration) {
        connection = new ConnectionBuilder()
                .setConfiguration(configuration)
                .build();
        
        try {
            connection.start();
        } catch (JMSException e) {
            throw new BoreasException(e);
        }
        
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            throw new BoreasException(e);
        }
        
        if (listeners != null) {
            Class<? extends BoreasJMSListener> clazz;
            Method[] methods;
            for (BoreasJMSListener listener : listeners) {
                clazz = listener.getClass();
                methods = clazz.getDeclaredMethods();
                
                Subscribe annotation;
                String topic;
                for (Method method : methods) {
                    if (!method.isAnnotationPresent(Subscribe.class)) {
                        continue;
                    }
                    
                    annotation = method.getAnnotation(Subscribe.class);
                    topic = annotation.topic();
                    if (topic == null || topic.isEmpty()) {
                        if (LOGGER.isWarnEnabled()) {
                            LOGGER.warn("the topic in method[{}] is invalid", method.getName());
                        }
                        continue;
                    }
                    
                    handleTopic(topic, listener, method);
                }
            }
        }
        
        try {
            doSubscribe();
        } catch (JMSException e) {
            throw new BoreasException(e);
        }
        
        isInitlized = true;
    }
    
    
    private void handleTopic(String topic, BoreasJMSListener object, Method method) {
        MethodBean methodBean = new MethodBean()
                .setObject(object)
                .setMethod(method);
        
        List<MethodBean> methodBeans;
        if (topics.containsKey(topic)) {
            methodBeans = topics.get(topic);
            methodBeans.add(methodBean);
        } else {
            methodBeans = new ArrayList<>();
            methodBeans.add(methodBean);
            topics.put(topic, methodBeans);
        }
    }
    
    private void doSubscribe() throws JMSException {
        Topic _topic;
        MessageConsumer consumer;
        // String topic;
        // List<MethodBean> methodBeans;
        for (Entry<String, List<MethodBean>> entry : topics.entrySet()) {
            String topic = entry.getKey();
            List<MethodBean> methodBeans = entry.getValue();
            
            _topic = session.createTopic(topic);
            consumer = session.createConsumer(_topic);
            setMessageListener(consumer, topic, methodBeans);
            
            LOGGER.info("succeed in subscribing topic: {}", topic);
        }
    }
    
    private void setMessageListener(final MessageConsumer consumer, final String topic,
            final List<MethodBean> methodBeans) throws JMSException {
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (!(message instanceof TextMessage)) {
                    LOGGER.info("ignore message from topic[{}] for it's not a text message.", topic);
                    return;
                }
                
                TextMessage textMessage = (TextMessage)message;
                
                Method method;
                BoreasJMSListener object;
                Class<?>[] parameterTypes;
                Class<?> firstParameter;
                try {
                    for (MethodBean methodBean : methodBeans) {
                        method = methodBean.getMethod();
                        object = methodBean.getObject();
                        
                        parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length != 1) {
                            if (LOGGER.isWarnEnabled()) {
                                LOGGER.warn("method can only has one parameter");
                            }
                            continue;
                        }
                        
                        firstParameter = parameterTypes[0];
                        if (firstParameter.isAssignableFrom(TextMessage.class)) {
                            method.invoke(object, textMessage);
                        } else if(firstParameter.isAssignableFrom(BoreasMessage.class)) {
                            BoreasMessage boreasMessage = new BoreasMessage()
                                    .setTopic(topic)
                                    .setMessage(textMessage.getText());
                            method.invoke(object, boreasMessage);
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error("", e);
                }
            }
        });
    }
    
    
    
    
    
}
