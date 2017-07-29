package io.vilya.boreas.bean;

import javax.validation.constraints.NotNull;

/**
 * @author iamaprin
 * @time 2017年6月18日 下午8:38:41
 */
@SuppressWarnings("serial")
public class ConnectionConfiguration extends BaseBean {
    
    @NotNull
    private String brokerURL;
    
    @NotNull
    private String username;
    
    @NotNull
    private String password;

    /**
     * @return the brokerURL
     */
    public String getBrokerURL() {
        return brokerURL;
    }

    /**
     * @param brokerURL the brokerURL to set
     */
    public ConnectionConfiguration setBrokerURL(String brokerURL) {
        this.brokerURL = brokerURL;
        return this;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public ConnectionConfiguration setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public ConnectionConfiguration setPassword(String password) {
        this.password = password;
        return this;
    }
  
}
