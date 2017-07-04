package io.vilya.boreas;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author iamaprin
 * @time 2017年7月4日 下午11:31:01
 */
public class Test1 {

    public static void main(String[] args) {
        
        ConnectionConfiguration configuration = new ConnectionConfiguration()
                .setBrokerURL("tcp://127.0.0.1:61613")
                .setUsername("admin")
                .setPassword("1");
        
        IBoreasJMSService service = new BoreasJMSServiceImpl();
        service.addMessageListener(new BoreasJMSListener() {
            
            @Subscribe(topic="/test")
            public void test(TextMessage message) throws JMSException {
                System.out.println(message.getText());
            }
            
            
        });
        service.init(configuration);
    }
    
}
