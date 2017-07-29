package io.vilya.boreas.bean;

import javax.validation.constraints.NotNull;

/**
 * @author iamaprin
 * @time 2017年6月18日 下午9:13:37
 */
@SuppressWarnings("serial")
public class BoreasMessage extends BaseBean {
    
    @NotNull
    private String topic;
    
    @NotNull
    private String message;

    /**
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic the topic to set
     */
    public BoreasMessage setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public BoreasMessage setMessage(String message) {
        this.message = message;
        return this;
    }    
}
