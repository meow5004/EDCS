/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.vehiclebooking.ldap;

/**
 *
 * @author user
 */
public class LdapEmployeeProfile {
    private String uid;
    private String empNameTh;
    private String empSurnameTh;
    private String sn;
    private String empDept; 

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmpNameTh() {
        return empNameTh;
    }

    public void setEmpNameTh(String empNameTh) {
        this.empNameTh = empNameTh;
    }

    public String getEmpSurnameTh() {
        return empSurnameTh;
    }

    public void setEmpSurnameTh(String empSurnameTh) {
        this.empSurnameTh = empSurnameTh;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getEmpDept() {
        return empDept;
    }

    public void setEmpDept(String empDept) {
        this.empDept = empDept;
    }   
    
}
