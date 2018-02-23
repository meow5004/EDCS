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
@Table(name = "EDCS_MAS_MEASURE_GROUP")
@XmlRootElement
public class EdcsMasMeasureGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEASURE_GROUP_ID")
    private Integer measureGroupId;
    @Size(max = 50)
    @Column(name = "MEASURE_GROUP_NAME_TH")
    private String measureGroupNameTh;
    @Size(max = 50)
    @Column(name = "MEASURE_GROUP_NAME_EN")
    private String measureGroupNameEn;
    @Size(max = 50)
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "CREATE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;
    @Size(max = 50)
    @Column(name = "CHANGE_BY")
    private String changeBy;
    @Column(name = "CHANGE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeOn;
    @Size(max = 10)
    @Column(name = "FLAG_DEL")
    private String flagDel;
    private transient String fullName;

    public EdcsMasMeasureGroup() {
        fullName = getFullName();
    }

    public EdcsMasMeasureGroup(Integer measureGroupId) {
        fullName = getFullName();
        this.measureGroupId = measureGroupId;
    }

    public Integer getMeasureGroupId() {
        return measureGroupId;
    }

    public void setMeasureGroupId(Integer measureGroupId) {
        this.measureGroupId = measureGroupId;
    }

    public String getMeasureGroupNameTh() {
        return measureGroupNameTh;
    }

    public void setMeasureGroupNameTh(String measureGroupNameTh) {
        this.measureGroupNameTh = measureGroupNameTh;
        fullName = getFullName();
    }

    public String getMeasureGroupNameEn() {
        return measureGroupNameEn;
    }

    public void setMeasureGroupNameEn(String measureGroupNameEn) {
        this.measureGroupNameEn = measureGroupNameEn;
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

    public String getFullName() {
        String name = " ";
        if (measureGroupNameTh != null&&measureGroupNameTh.trim().length() > 0) {
            name += measureGroupNameTh;
            if (measureGroupNameEn != null&&measureGroupNameEn.trim().length() > 0) {
                name += "( " + measureGroupNameEn + " )";
            }
        } else {
            name += measureGroupNameEn;
        }
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (measureGroupId != null ? measureGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsMasMeasureGroup)) {
            return false;
        }
        EdcsMasMeasureGroup other = (EdcsMasMeasureGroup) object;
        if ((this.measureGroupId == null && other.measureGroupId != null) || (this.measureGroupId != null && !this.measureGroupId.equals(other.measureGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsMasMeasureGroupId[ measureGroupId=" + measureGroupId + " ]";
    }

}
