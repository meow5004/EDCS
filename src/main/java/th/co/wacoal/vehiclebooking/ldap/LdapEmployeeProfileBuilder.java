/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.vehiclebooking.ldap;

/**
 *
 * @author user
 */
public class LdapEmployeeProfileBuilder {

    LdapEmployeeProfile buildLdapEmployeeProfile(LdapResultSet rs) {

        if (rs != null) {
            LdapEmployeeProfile p = new LdapEmployeeProfile();
            p.setUid((String) rs.getAttribute("uid"));
            p.setEmpNameTh((String)rs.getAttribute("empNameTh"));
            p.setEmpSurnameTh((String)rs.getAttribute("empSurnameTh"));
            p.setSn((String)rs.getAttribute(("sn")));
            p.setEmpDept((String)rs.getAttribute("empDept"));
            return p;
        } else {
            return null;
        }
    }
}
