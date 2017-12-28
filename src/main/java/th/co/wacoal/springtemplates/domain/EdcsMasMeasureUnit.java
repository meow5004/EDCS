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
@Table(name = "EDCS_MAS_MEASURE_UNIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsMasMeasureUnit.findAll", query = "SELECT e FROM EdcsMasMeasureUnit e"),
    @NamedQuery(name = "EdcsMasMeasureUnit.findByUnitId", query = "SELECT e FROM EdcsMasMeasureUnit e WHERE e.unitId = :unitId"),
    @NamedQuery(name = "EdcsMasMeasureUnit.findByUnitNameTh", query = "SELECT e FROM EdcsMasMeasureUnit e WHERE e.unitNameTh = :unitNameTh"),
    @NamedQuery(name = "EdcsMasMeasureUnit.findByUnitShortTh", query = "SELECT e FROM EdcsMasMeasureUnit e WHERE e.unitShortTh = :unitShortTh"),
    @NamedQuery(name = "EdcsMasMeasureUnit.findByUnitNameEn", query = "SELECT e FROM EdcsMasMeasureUnit e WHERE e.unitNameEn = :unitNameEn"),
    @NamedQuery(name = "EdcsMasMeasureUnit.findByUnitShortEn", query = "SELECT e FROM EdcsMasMeasureUnit e WHERE e.unitShortEn = :unitShortEn"),
    @NamedQuery(name = "EdcsMasMeasureUnit.findByCreateBy", query = "SELECT e FROM EdcsMasMeasureUnit e WHERE e.createBy = :createBy"),
    @NamedQuery(name = "EdcsMasMeasureUnit.findByCreateOn", query = "SELECT e FROM EdcsMasMeasureUnit e WHERE e.createOn = :createOn"),
    @NamedQuery(name = "EdcsMasMeasureUnit.findByChangeBy", query = "SELECT e FROM EdcsMasMeasureUnit e WHERE e.changeBy = :changeBy"),
    @NamedQuery(name = "EdcsMasMeasureUnit.findByChangeOn", query = "SELECT e FROM EdcsMasMeasureUnit e WHERE e.changeOn = :changeOn"),
    @NamedQuery(name = "EdcsMasMeasureUnit.findByFlagDel", query = "SELECT e FROM EdcsMasMeasureUnit e WHERE e.flagDel = :flagDel")})
public class EdcsMasMeasureUnit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UNIT_ID")
    private Integer unitId;
    @Size(max = 50)
    @Column(name = "UNIT_NAME_TH")
    private String unitNameTh;
    @Size(max = 50)
    @Column(name = "UNIT_SHORT_TH")
    private String unitShortTh;
    @Size(max = 50)
    @Column(name = "UNIT_NAME_EN")
    private String unitNameEn;
    @Size(max = 50)
    @Column(name = "UNIT_SHORT_EN")
    private String unitShortEn;
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

    public EdcsMasMeasureUnit() {
    }

    public EdcsMasMeasureUnit(Integer unitId) {
        this.unitId = unitId;
    }

    public EdcsMasMeasureUnit(Integer unitId, Date createOn, Date changeOn) {
        this.unitId = unitId;
        this.createOn = createOn;
        this.changeOn = changeOn;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitNameTh() {
        return unitNameTh;
    }

    public void setUnitNameTh(String unitNameTh) {
        this.unitNameTh = unitNameTh;
    }

    public String getUnitShortTh() {
        return unitShortTh;
    }

    public void setUnitShortTh(String unitShortTh) {
        this.unitShortTh = unitShortTh;
    }

    public String getUnitNameEn() {
        return unitNameEn;
    }

    public void setUnitNameEn(String unitNameEn) {
        this.unitNameEn = unitNameEn;
    }

    public String getUnitShortEn() {
        return unitShortEn;
    }

    public void setUnitShortEn(String unitShortEn) {
        this.unitShortEn = unitShortEn;
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
        hash += (unitId != null ? unitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsMasMeasureUnit)) {
            return false;
        }
        EdcsMasMeasureUnit other = (EdcsMasMeasureUnit) object;
        if ((this.unitId == null && other.unitId != null) || (this.unitId != null && !this.unitId.equals(other.unitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsMasMeasureUnit[ unitId=" + unitId + " ]";
    }
    
}
