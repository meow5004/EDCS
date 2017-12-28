/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.domain;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "EDCS_MAS_DEPARTMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsMasDepartment.findAll", query = "SELECT e FROM EdcsMasDepartment e"),
    @NamedQuery(name = "EdcsMasDepartment.findByDepId", query = "SELECT e FROM EdcsMasDepartment e WHERE e.depId = :depId"),
    @NamedQuery(name = "EdcsMasDepartment.findByDepNameTh", query = "SELECT e FROM EdcsMasDepartment e WHERE e.depNameTh = :depNameTh"),
    @NamedQuery(name = "EdcsMasDepartment.findByDepNameEn", query = "SELECT e FROM EdcsMasDepartment e WHERE e.depNameEn = :depNameEn"),
    @NamedQuery(name = "EdcsMasDepartment.findByBranchId", query = "SELECT e FROM EdcsMasDepartment e WHERE e.branchId = :branchId"),
    @NamedQuery(name = "EdcsMasDepartment.findByCreateBy", query = "SELECT e FROM EdcsMasDepartment e WHERE e.createBy = :createBy"),
    @NamedQuery(name = "EdcsMasDepartment.findByCreateOn", query = "SELECT e FROM EdcsMasDepartment e WHERE e.createOn = :createOn"),
    @NamedQuery(name = "EdcsMasDepartment.findByChangeBy", query = "SELECT e FROM EdcsMasDepartment e WHERE e.changeBy = :changeBy"),
    @NamedQuery(name = "EdcsMasDepartment.findByChangeOn", query = "SELECT e FROM EdcsMasDepartment e WHERE e.changeOn = :changeOn"),
    @NamedQuery(name = "EdcsMasDepartment.findByFlagDel", query = "SELECT e FROM EdcsMasDepartment e WHERE e.flagDel = :flagDel")})
public class EdcsMasDepartment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DEP_ID")
    private String depId;
    @Size(max = 50)
    @Column(name = "DEP_NAME_TH")
    private String depNameTh;
    @Size(max = 50)
    @Column(name = "DEP_NAME_EN")
    private String depNameEn;
    @Column(name = "BRANCH_ID")
    private Integer branchId;
    @Size(max = 50)
    @Column(name = "CREATE_BY")
    private String createBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;
    @Size(max = 50)
    @Column(name = "CHANGE_BY")
    private String changeBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHANGE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeOn;
    @Size(max = 10)
    @Column(name = "FLAG_DEL")
    private String flagDel;

    public EdcsMasDepartment() {
    }

    public EdcsMasDepartment(String depId) {
        this.depId = depId;
    }

    public EdcsMasDepartment(String depId, Date createOn, Date changeOn) {
        this.depId = depId;
        this.createOn = createOn;
        this.changeOn = changeOn;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepNameTh() {
        return depNameTh;
    }

    public void setDepNameTh(String depNameTh) {
        this.depNameTh = depNameTh;
    }

    public String getDepNameEn() {
        return depNameEn;
    }

    public void setDepNameEn(String depNameEn) {
        this.depNameEn = depNameEn;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(String changeBy) {
        this.changeBy = changeBy;
    }

    public Date getChangeOn() {
        return changeOn;
    }

    public void setChangeOn(Date changeOn) {
        this.changeOn = changeOn;
    }

    public String getFlagDel() {
        return flagDel;
    }

    public void setFlagDel(String flagDel) {
        this.flagDel = flagDel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depId != null ? depId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsMasDepartment)) {
            return false;
        }
        EdcsMasDepartment other = (EdcsMasDepartment) object;
        if ((this.depId == null && other.depId != null) || (this.depId != null && !this.depId.equals(other.depId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsMasDepartment[ depId=" + depId + " ]";
    }
    
}
