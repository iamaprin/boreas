package io.vilya.boreas;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author iamaprin
 * @time 2017年6月17日 上午8:04:04
 */
public class BoreasProducer {
    
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61613");
        Connection conn = factory.createConnection("admin", "1");
        
        Session sess = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
     
        Destination dest = sess.createQueue("SampleQueue");
     
        MessageProducer prod = sess.createProducer(dest);
     
        Message msg = sess.createTextMessage("Simples Assim");
     
        prod.send(msg);
    }
    
}
