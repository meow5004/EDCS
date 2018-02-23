/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.vehiclebooking.ldap;

import java.util.Hashtable;
import java.util.List;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;

/**
 *
 * @author user
 */
public interface LdapResource {

    public DirContext bind(Hashtable env) throws NamingException;

    public DirContext bind(String userDN, String userPassword) throws NamingException;

    public List<LdapResultSet> search(String filter, String[] attributesToReturn) throws NamingException;

    public List<LdapResultSet> search(String base, String filter, String[] attributesToReturn) throws NamingException;

    public void unbind(DirContext ctx);
}
