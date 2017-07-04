package io.vilya.boreas;

/**
 * @author iamaprin
 * @time 2017年7月4日 下午11:31:57
 */
public interface IBoreasJMSService {

    void publish(BoreasMessage message);

    void addMessageListener(BoreasJMSListener listener);

    void removeMessageListener(BoreasJMSListener listener);

    void init(ConnectionConfiguration configuration);

}