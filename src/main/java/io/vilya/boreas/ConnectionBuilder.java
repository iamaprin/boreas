package io.vilya.boreas;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author iamaprin
 * @time 2017年6月18日 下午8:36:49
 */
public class ConnectionBuilder {
    
    private ConnectionConfiguration configuration;
    
    public ConnectionBuilder setConfiguration(ConnectionConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }
    
    public Connection build() {
        if (configuration == null) {
            throw new BoreasException("configuration not set.");
        }
        
        Connection connection;
        try {
            connection = _build();
        } catch (JMSException e) {
            throw new BoreasException(e);
        }
        
        return connection;
    }
    
    private Connection _build() throws JMSException {
        configuration.verify();
        
        ConnectionFactory factory = 
                new ActiveMQConnectionFactory(configuration.getBrokerURL());
        return factory.createConnection(configuration.getUsername(), configuration.getPassword());
    }
    
    
    
    
    
    
    
    
}
