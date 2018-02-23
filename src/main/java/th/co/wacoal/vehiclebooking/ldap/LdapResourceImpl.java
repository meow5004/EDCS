/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.vehiclebooking.ldap;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;


/**
 *
 * @author user
 */
public class LdapResourceImpl implements LdapResource {
    private static final String CONNECTION_POOL_KEY = "com.sun.jndi.ldap.connect.pool";

    private static final String ERROR_INVALID_USERNAME_PASSWORD = "error.invalid.username.password";
    private final static Logger logger = Logger.getLogger(LdapResourceImpl.class.getName()); 
    private boolean useConnectionPool;

    private ContextSource contextSource = new ContextSource();

    private LdapResultSetBuilder ldapResultSetBuilder = new LdapResultSetBuilderImpl();

    private String trustStore;

    private String trustStorePassword;

    private String trustStoreType;

    
    public final void setLdapResultSetBuilder(LdapResultSetBuilder ldapResultSetBuilder) {
        this.ldapResultSetBuilder = ldapResultSetBuilder;
    }


    public final void setContextSource(ContextSource contextSource) {
        this.contextSource = contextSource;
    }

    @Override
    public DirContext bind(Hashtable env) throws NamingException {
        if (logger.isLoggable(Level.ALL)) {
            logger.log(Level.INFO,"LdapResource bind Directory Context");
            
           
        }

        DirContext ctx = new InitialDirContext(env);

        return ctx;
    }

    @Override
    public DirContext bind(String userDN, String userPassword) throws NamingException {
        if (userDN == null || userPassword == null)
            throw new NamingException(ERROR_INVALID_USERNAME_PASSWORD);

        Hashtable env = this.getUserEnvironment(userDN, userPassword);

        return this.bind(env);
    }


    @Override
    public List<LdapResultSet> search(String filter, String[] attributesToReturn) throws NamingException {

        return this.search(contextSource.getBase(), filter, attributesToReturn);
    }

    @Override
    public List<LdapResultSet> search(String base, String filter, String[] attributesToReturn) throws NamingException {
        if (logger.isLoggable(Level.ALL)) {
            StringBuffer sb = new StringBuffer();
            if (attributesToReturn != null) {
                for (String attr : attributesToReturn) {
                    sb.append(attr).append(" ");
                }
            }
            
            logger.log(Level.INFO,"LdapResource search(" + "base = " + base + ", filter = "+ filter + " attributesToReturn = " + sb.toString());
        } else {
        }

        Hashtable env = this.getManagerEnvironment();
        DirContext ctx = this.bind(env);

        List<LdapResultSet> list = new ArrayList<LdapResultSet>();
        NamingEnumeration results = null;

        try {
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            controls.setReturningAttributes(attributesToReturn);
            results = ctx.search(base, filter, controls);

            while (results.hasMore()) {
                SearchResult result = (SearchResult) results.next();
                LdapResultSet resultSet = ldapResultSetBuilder.buildLdapResultSet(result, attributesToReturn);
                list.add(resultSet);
            }
        } catch (NameNotFoundException e) {
            //base context not found
            logger.log(Level.INFO,"Base Context " + base + " not found with Exception " + e.getLocalizedMessage());
            
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (Exception e) {
                    //Do nothing here
                    logger.warning("Exception during closing result sets " + e.getLocalizedMessage());
                }
            }

            if (ctx != null) {
                try {
                    ctx.close();
                } catch (Exception e) {
                    //Do nothing here
                    logger.warning("Exception during closing Directory Context " + e.getLocalizedMessage());
                }
            }
        }

        return list;
    }


    @Override
    public void unbind(DirContext ctx) {

        if (ctx != null) {
            try {
                ctx.close();
            } catch (Exception e) {
                //Do nothing here
                logger.warning("Exception during closing Directory Context " + e.getLocalizedMessage());
            }
        }
    }

    protected Hashtable getManagerEnvironment() {
        Hashtable<String, String> env = new Hashtable<String, String>();

        env.put(Context.INITIAL_CONTEXT_FACTORY, contextSource.getContextFactory());
        env.put(Context.PROVIDER_URL, contextSource.getProviderUrl());
        env.put(Context.SECURITY_AUTHENTICATION, contextSource.getAuthenticationType());
        env.put(Context.SECURITY_PRINCIPAL, contextSource.getManagerDN());
        env.put(Context.SECURITY_CREDENTIALS, contextSource.getManagerPassword());

        String protocol = contextSource.getSecurityProtocol();
        //use white list, ignore all others that are not "ssl"
        if (protocol != null && protocol.equalsIgnoreCase("ssl")) {
            env.put(Context.SECURITY_PROTOCOL, "ssl");
            if (this.trustStore != null)
                System.setProperty("javax.net.ssl.trustStore", this.trustStore);
            if (this.trustStoreType != null)
                System.setProperty("javax.net.ssl.trustStoreType", this.trustStoreType);
            if (this.trustStorePassword != null)
                System.setProperty("javax.net.ssl.trustStorePassword", this.trustStorePassword);
        }

        if (useConnectionPool) {
            env.put(CONNECTION_POOL_KEY, "true");
        }

        return env;
    }

    protected Hashtable getUserEnvironment(String userDN, String userPassword) {
        Hashtable<String, String> env = new Hashtable<String, String>();

        env.put(Context.INITIAL_CONTEXT_FACTORY, contextSource.getContextFactory());
        env.put(Context.PROVIDER_URL, contextSource.getProviderUrl());
        env.put(Context.SECURITY_AUTHENTICATION, contextSource.getAuthenticationType());
        env.put(Context.SECURITY_PRINCIPAL, userDN);
        env.put(Context.SECURITY_CREDENTIALS, userPassword);

        String protocol = contextSource.getSecurityProtocol();
        if (protocol != null && protocol.equalsIgnoreCase("ssl")) {
            env.put(Context.SECURITY_PROTOCOL, "ssl");
            if (this.trustStore != null)
                System.setProperty("javax.net.ssl.trustStore", this.trustStore);
            if (this.trustStoreType != null)
                System.setProperty("javax.net.ssl.trustStoreType", this.trustStoreType);
            if (this.trustStorePassword != null)
                System.setProperty("javax.net.ssl.trustStorePassword", this.trustStorePassword);
        }

        return env;
    }
}
