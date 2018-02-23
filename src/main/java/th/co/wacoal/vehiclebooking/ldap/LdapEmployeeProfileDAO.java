/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.vehiclebooking.ldap;

import java.util.List;

/**
 *
 * @author user
 */
public interface LdapEmployeeProfileDAO {

    public boolean bindUser(String userDN, String userPassword);

    public LdapEmployeeProfile findUser(String userId);

    public List<LdapEmployeeProfile> findUserStartsWith(String userId);
}
