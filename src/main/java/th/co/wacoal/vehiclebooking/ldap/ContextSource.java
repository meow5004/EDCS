/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.vehiclebooking.ldap;

/**
 *
 * @author user
 */
public class ContextSource {
    private final String contextFactory = "com.sun.jndi.ldap.LdapCtxFactory";
    private final String providerUrl ="ldap://10.11.9.135:389/";
    private final String authenticationType="simple";
    private final String managerDN="cn=manager,dc=wacoal,dc=co,dc=th";
    private final String managerPassword="secret";
    private final String base="ou=user,dc=wacoal,dc=co,dc=th";
    private String securityProtocol;

    public String getContextFactory() {
        return contextFactory;
    }

    public String getProviderUrl() {
        return providerUrl;
    }
    
    public String getAuthenticationType() {
        return authenticationType;
    }

    public String getManagerDN() {
        return managerDN;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public String getBase() {
        return base;
    }

    public String getSecurityProtocol() {
        return securityProtocol;
    }
    
    
}
