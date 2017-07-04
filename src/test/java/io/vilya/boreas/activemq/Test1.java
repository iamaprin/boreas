package io.vilya.boreas.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import io.vilya.boreas.BoreasMessage;

/**
 * @author iamaprin
 * @time 2017年6月16日 下午10:10:41
 */
public class Test1 {

    @Test
    public void test1() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61613");
        Connection conn = factory.createConnection("admin", "1");
        
        Session sess = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
     
        Destination dest = sess.createQueue("SampleQueue");
     
        MessageProducer prod = sess.createProducer(dest);
     
        Message msg = sess.createTextMessage("Simples Assim");
     
        prod.send(msg);
    }
    
    
    @Test
    public void test2() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61613");
        Connection connection = factory.createConnection("admin", "1");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        Destination dest = session.createQueue("SampleQueue");
        MessageConsumer consumer = session.createConsumer(dest);
        consumer.setMessageListener(new MessageListener() {
            
            @Override
            public void onMessage(Message message) {
                System.out.println("receive message:");
                System.out.println(message);
            }
        });
        
        connection.start();
    }
    
    @Test
    public void test4() {
        
        TestBean test = new TestBean();
        test.verify();
        
    }
    
    
    
    
}
