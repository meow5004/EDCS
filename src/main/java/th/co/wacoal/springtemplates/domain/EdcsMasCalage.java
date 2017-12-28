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
@Table(name = "EDCS_MAS_CALAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsMasCalage.findAll", query = "SELECT e FROM EdcsMasCalage e")})
public class EdcsMasCalage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAL_AGE_ID")
    private Integer calAgeId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CAL_AGE")
    private Double calAge;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
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
    @Size(max = 50)
    @Column(name = "FLAG_DEL")
    private String flagDel;

    public EdcsMasCalage() {
    }

    public EdcsMasCalage(Integer calAgeId) {
        this.calAgeId = calAgeId;
    }

    public EdcsMasCalage(Integer calAgeId, Date createOn, Date changeOn) {
        this.calAgeId = calAgeId;
        this.createOn = createOn;
        this.changeOn = changeOn;
    }

    public Integer getCalAgeId() {
        return calAgeId;
    }

    public void setCalAgeId(Integer calAgeId) {
        this.calAgeId = calAgeId;
    }

    public Double getCalAge() {
        return calAge;
    }

    public void setCalAge(Double calAge) {
        this.calAge = calAge;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        hash += (calAgeId != null ? calAgeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsMasCalage)) {
            return false;
        }
        EdcsMasCalage other = (EdcsMasCalage) object;
        if ((this.calAgeId == null && other.calAgeId != null) || (this.calAgeId != null && !this.calAgeId.equals(other.calAgeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "calculation age  อายุ " + calAge + " ปี เริ่มใช้ " + startDate + "-" + endDate;
    }

}
