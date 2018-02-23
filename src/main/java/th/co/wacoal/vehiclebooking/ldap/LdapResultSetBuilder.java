/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.vehiclebooking.ldap;

import javax.naming.NamingException;
import javax.naming.directory.SearchResult;

/**
 *
 * @author user
 */
public interface LdapResultSetBuilder {
    LdapResultSet buildLdapResultSet(SearchResult result, String[] attributesToReturn) throws NamingException;
}
