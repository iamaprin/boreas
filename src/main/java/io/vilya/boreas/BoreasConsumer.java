package io.vilya.boreas;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author iamaprin
 * @time 2017年6月17日 上午8:04:56
 */
public class BoreasConsumer {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61613");
        Connection connection = factory.createConnection("admin", "1");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        Destination dest = session.createQueue("SampleQueue");
        MessageConsumer consumer = session.createConsumer(dest);
        consumer.setMessageListener((message) -> {
            
            TextMessage tm = (TextMessage) message;
            try {
                System.out.println(tm.getText());
            } catch (JMSException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        
        connection.start();
    }
    
}
