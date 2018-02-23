/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "EDCS_MAS_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsMasUser.findAll", query = "SELECT e FROM EdcsMasUser e")})
public class EdcsMasUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMP_ID")
    private String empId;
    @Size(max = 100)
    @Column(name = "USER_NAME")
    private String userName;
    @Size(max = 100)
    @Column(name = "SYS_NAME")
    private String sysName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "PHONE")
    private String phone;
    @Size(max = 50)
    @Column(name = "DEP_ID")
    private String depId;
    @Column(name = "CREATE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;
    @Size(max = 50)
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "CHANGE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeOn;
    @Size(max = 50)
    @Column(name = "CHANGE_BY")
    private String changeBy;

    //custom property
    private List<EdcsMasUserAuthType> userAuthTypes;
    private List<EdcsMasUserType> userTypes;
    private List<EdcsMasDepartment> viewableDepartments;
    private List<Integer> userAuthTypeIds;
    private List<Integer> userTypeIds;
    private List<String> viewableDepartmentIds;
    private Integer[] userRoleIds;

    public EdcsMasUser() {
    }

    public EdcsMasUser(String empId) {
        this.empId = empId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getChangeOn() {
        return changeOn;
    }

    public void setChangeOn(Date changeOn) {
        this.changeOn = changeOn;
    }

    public String getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(String changeBy) {
        this.changeBy = changeBy;
    }

    public List<EdcsMasUserAuthType> getUserAuthTypes() {
        return userAuthTypes;
    }

    public void setUserAuthTypes(List<EdcsMasUserAuthType> userAuthTypes) {
        this.userAuthTypes = userAuthTypes;
        List<Integer> userAuthTypeIdsTemp = new ArrayList<>();
        for (EdcsMasUserAuthType userAuthType : userAuthTypes) {
            userAuthTypeIdsTemp.add(userAuthType.getAuthTypeId());
        }
        this.userAuthTypeIds = userAuthTypeIdsTemp;
    }

    public List<EdcsMasUserType> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<EdcsMasUserType> userTypes) {
        this.userTypes = userTypes;
        List<Integer> userTypeIdsTemp = new ArrayList<>();
        for (EdcsMasUserType userType : userTypes) {
            userTypeIdsTemp.add(userType.getUserTypeId());
        }
        this.userTypeIds = userTypeIdsTemp;
    }

    public List<EdcsMasDepartment> getViewableDepartments() {
        return viewableDepartments;
    }

    public void setViewableDepartments(List<EdcsMasDepartment> viewableDepartments) {
        List<String> viewableDepartmentIdsTemp = new ArrayList<>();
        this.viewableDepartments = viewableDepartments;
        for (EdcsMasDepartment dep : viewableDepartments) {
            viewableDepartmentIdsTemp.add(dep.getDepId());
        }
        this.viewableDepartmentIds = viewableDepartmentIdsTemp;
    }

    public List<Integer> getUserAuthTypeIds() {
        return userAuthTypeIds;
    }

    public void setUserAuthTypeIds(List<Integer> userAuthTypeIds) {
        this.userAuthTypeIds = userAuthTypeIds;
    }

    public List<Integer> getUserTypeIds() {
        return userTypeIds;
    }

    public void setUserTypeIds(List<Integer> userTypeIds) {
        this.userTypeIds = userTypeIds;
    }

    public List<String> getViewableDepartmentIds() {
        return viewableDepartmentIds;
    }

    public void setViewableDepartmentIds(List<String> viewableDepartmentIds) {
        this.viewableDepartmentIds = viewableDepartmentIds;
    }

    public Integer[] getUserRoleIds() {
        return userRoleIds;
    }

    public void setUserRoleIds(Integer[] userRoleIds) {
        this.userRoleIds = userRoleIds;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsMasUser)) {
            return false;
        }
        EdcsMasUser other = (EdcsMasUser) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsMasUser[ empId=" + empId + " ]";
    }

}
