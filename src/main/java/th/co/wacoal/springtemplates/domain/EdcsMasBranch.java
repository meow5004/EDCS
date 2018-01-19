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
@Table(name = "EDCS_MAS_BRANCH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsMasBranch.findAll", query = "SELECT e FROM EdcsMasBranch e"),
    @NamedQuery(name = "EdcsMasBranch.findByBranchId", query = "SELECT e FROM EdcsMasBranch e WHERE e.branchId = :branchId"),
    @NamedQuery(name = "EdcsMasBranch.findByBranchNameTh", query = "SELECT e FROM EdcsMasBranch e WHERE e.branchNameTh = :branchNameTh"),
    @NamedQuery(name = "EdcsMasBranch.findByBranchNameEn", query = "SELECT e FROM EdcsMasBranch e WHERE e.branchNameEn = :branchNameEn"),
    @NamedQuery(name = "EdcsMasBranch.findByCreateBy", query = "SELECT e FROM EdcsMasBranch e WHERE e.createBy = :createBy"),
    @NamedQuery(name = "EdcsMasBranch.findByCreateOn", query = "SELECT e FROM EdcsMasBranch e WHERE e.createOn = :createOn"),
    @NamedQuery(name = "EdcsMasBranch.findByChangeBy", query = "SELECT e FROM EdcsMasBranch e WHERE e.changeBy = :changeBy"),
    @NamedQuery(name = "EdcsMasBranch.findByChangeOn", query = "SELECT e FROM EdcsMasBranch e WHERE e.changeOn = :changeOn"),
    @NamedQuery(name = "EdcsMasBranch.findByFlagDel", query = "SELECT e FROM EdcsMasBranch e WHERE e.flagDel = :flagDel")})
public class EdcsMasBranch implements Serializable {

    @Size(max = 50)
    @Column(name = "BRANCH_CODE")
    private String branchCode;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BRANCH_ID")
    private Integer branchId;
    @Size(max = 50)
    @Column(name = "BRANCH_NAME_TH")
    private String branchNameTh;
    @Size(max = 50)
    @Column(name = "BRANCH_NAME_EN")
    private String branchNameEn;
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

    private transient String fullName;

    public EdcsMasBranch() {
    }

    public EdcsMasBranch(Integer branchId) {
        this.branchId = branchId;
        fullName = getFullName();
    }

    public EdcsMasBranch(Integer branchId, Date createOn, Date changeOn) {
        this.branchId = branchId;
        this.createOn = createOn;
        this.changeOn = changeOn;
          fullName = getFullName();
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchNameTh() {
        return branchNameTh;
    }

    public void setBranchNameTh(String branchNameTh) {
        this.branchNameTh = branchNameTh;
          fullName = getFullName();
    }

    public String getBranchNameEn() {
        return branchNameEn;
    }

    public void setBranchNameEn(String branchNameEn) {
        this.branchNameEn = branchNameEn;
          fullName = getFullName();
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
        hash += (branchId != null ? branchId.hashCode() : 0);
        return hash;
    }

    public String getFullName() {
        String name = " ";
        if (branchNameTh != null&&branchNameTh.trim().length() > 0) {
            name += branchNameTh;
            if (branchNameEn != null&&branchNameEn.trim().length() > 0) {
                name += "( " + branchNameEn + " )";
            }
        } else {
            name += branchNameEn;
        }
        return name;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsMasBranch)) {
            return false;
        }
        EdcsMasBranch other = (EdcsMasBranch) object;
        if ((this.branchId == null && other.branchId != null) || (this.branchId != null && !this.branchId.equals(other.branchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsMasBranch[ branchId=" + branchId + " ]";
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

}
