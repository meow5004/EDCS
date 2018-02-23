/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.vehiclebooking.ldap;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;

/**
 *
 * @author user
 */
public class LdapEmployeeProfileDAOImpl implements LdapEmployeeProfileDAO {

    private final static Logger logger = Logger.getLogger(LdapEmployeeProfileDAOImpl.class.getName());
    private LdapResource ldapResource;
    private LdapEmployeeProfileBuilder ldapEmployeeProfileBuilder = new LdapEmployeeProfileBuilder();
    private String[] attributesToReturn;

    public LdapEmployeeProfileDAOImpl() {
        this.attributesToReturn = new String[]{"uid", "empNameTh", "empSurnameTh", "sn", "empDept"};
        setLdapResource(new LdapResourceImpl());
    }

    public final LdapEmployeeProfileBuilder getLdapEmployeeProfileBuilder() {
        return ldapEmployeeProfileBuilder;
    }

    public final void setLdapEmployeeProfileBuilder(
            LdapEmployeeProfileBuilder ldapEmployeeProfileBuilder) {
        this.ldapEmployeeProfileBuilder = ldapEmployeeProfileBuilder;
    }

    public final void setLdapResource(LdapResource ldapResource) {
        this.ldapResource = ldapResource;
    }

    @Override
    public boolean bindUser(String userDN, String userPassword) {
        boolean isSuccessful = false;
        DirContext ctx = null;

        try {
            ctx = ldapResource.bind(userDN, userPassword);
            isSuccessful = true;
        } catch (NamingException e) {
            logger.warning("Cannot bind user with userDN = " + userDN + " with exception " + e.getLocalizedMessage());
        } finally {

            //unbind and close connection
            if (ctx != null) {
                ldapResource.unbind(ctx);
            }
        }

        return isSuccessful;
    }

    @Override
    public LdapEmployeeProfile findUser(String userId) {
        if (userId == null) {
            if (logger.isLoggable(Level.ALL)) {
                logger.log(Level.INFO, "Cannot find user with Null userId = ");
            }

            return null;
        }

        //TODO: add filter builders
        String filter = "(&(uid=" + userId + "))";
        //FILTER.replaceFirst(FILTER_TOKEN, userId);
        LdapEmployeeProfile profile = null;

        try {
            List<LdapResultSet> list = ldapResource.search(filter, attributesToReturn);
            if (list != null) {
                if (list.size() == 0) {
                    if (logger.isLoggable(Level.ALL)) {
                        logger.log(Level.INFO, "Cannot find user with userId = " + userId);
                    }

                    return null;
                }

                if (list.size() > 1) {
                    logger.warning("Duplicated records found for user with userId = " + userId);
                    //if duplicated, only return the first record
                } else if (logger.isLoggable(Level.ALL)) {
                    logger.log(Level.INFO, "One record found for user with userId = " + userId);
                }

                LdapResultSet resultSet = list.get(0);

                profile = ldapEmployeeProfileBuilder.buildLdapEmployeeProfile(resultSet);
            }
        } catch (NamingException e) {
            logger.warning("Cannot find user with userId = " + userId
                    + " with exception " + e.getLocalizedMessage());
        }

        if (logger.isLoggable(Level.ALL)) {
            if (profile != null) {
                logger.log(Level.INFO, "Found user: " + profile.toString());
            }
        }

        return profile;
    }

    @Override
    public List<LdapEmployeeProfile> findUserStartsWith(String userId) {
        if (userId == null) {
            if (logger.isLoggable(Level.ALL)) {
                logger.log(Level.INFO, "Cannot find user with Null userId = ");
            }

            return null;
        }

        //TODO: add filter builders
        String filter = "(&(uid=" + userId + "*))";
        //FILTER.replaceFirst(FILTER_TOKEN, userId);
        List<LdapEmployeeProfile> profiles = new ArrayList<>();
        try {
            List<LdapResultSet> list = ldapResource.search(filter, attributesToReturn);
            if (list != null) {
                if (list.size() == 0) {
                    if (logger.isLoggable(Level.ALL)) {
                        logger.log(Level.INFO, "Cannot find user with userId = " + userId);
                    }

                    return null;
                }

                int index = 0;
                for (LdapResultSet single : list) {
                    LdapEmployeeProfile profile = ldapEmployeeProfileBuilder.buildLdapEmployeeProfile(list.get(index));
                    profiles.add(profile);
                    index++;
                }

            }
        } catch (NamingException e) {
            logger.warning("Cannot find user with userId = " + userId
                    + " with exception " + e.getLocalizedMessage());
        }



        return profiles;
    }
}
