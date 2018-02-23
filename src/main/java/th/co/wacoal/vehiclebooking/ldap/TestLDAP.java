/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.vehiclebooking.ldap;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 *
 * @author user
 */
public class TestLDAP {

    public static void main(String[] args) {
        boolean isSuccessful = false;
        String userId = "supachai.run";
        String userPassword = "111";
            LdapEmployeeProfileDAO ldapEmployeeProfileDAO = new LdapEmployeeProfileDAOImpl();
            LdapEmployeeProfile profile = ldapEmployeeProfileDAO.findUser(userId);
            System.out.println(profile.getUid()+" "+profile.getEmpNameTh()+" "+profile.getSn()+" "+profile.getEmpDept());
            if(profile != null){

                isSuccessful = ldapEmployeeProfileDAO.bindUser("uid="+profile.getUid()+",ou=user,dc=wacoal,dc=co,dc=th",userPassword);


                System.out.println("Authenticate UsernamePasswordCredential");
                
            }else{

                System.out.println("Cannot find the profile for ");
                
            }

                System.out.println(isSuccessful);

    }
}
