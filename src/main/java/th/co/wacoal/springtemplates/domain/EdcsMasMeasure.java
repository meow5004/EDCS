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
@Table(name = "EDCS_MAS_MEASURE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsMasMeasure.findAll", query = "SELECT e FROM EdcsMasMeasure e")})
public class EdcsMasMeasure implements Serializable {

    @Size(max = 50)
    @Column(name = "BRAND")
    private String brand;

    @Size(max = 50)
    @Column(name = "DEP_ID")
    private String depId;

    @Column(name = "MEASURE_GROUP_ID")
    private Integer measureGroupId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "RANGE_MIN")
    private double rangeMin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RANGE_MAX")
    private double rangeMax;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "USE_RANGE_MIN")
    private Double useRangeMin;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEASURE_ID")
    private Integer measureId;
    @Size(max = 50)
    @Column(name = "MEASURE_CODE")
    private String measureCode;
    @Size(max = 50)
    @Column(name = "MEASURE_NAME_TH")
    private String measureNameTh;
    @Size(max = 50)
    @Column(name = "MEASURE_NAME_EN")
    private String measureNameEn;
    @Column(name = "USE_RANGE_MAX")
    private Double useRangeMax;
    @Size(max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "MEASURE_TIMES")
    private Integer measureTimes;
    @Size(max = 10)
    @Column(name = "AB_TYPE")
    private String abType;
    @Column(name = "EQUIP_CON_ID")
    private Integer equipConId;
    @Column(name = "CALPOINT_ID")
    private Integer calpointId;
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

    public EdcsMasMeasure() {
    }

    public EdcsMasMeasure(Integer measureId) {
        this.measureId = measureId;
    }

    public EdcsMasMeasure(Integer measureId, Double rangeMin, Double rangeMax, Date createOn, Date changeOn) {
        this.measureId = measureId;
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
        this.createOn = createOn;
        this.changeOn = changeOn;
    }

    public Integer getMeasureId() {
        return measureId;
    }

    public void setMeasureId(Integer measureId) {
        this.measureId = measureId;
    }

    public String getMeasureCode() {
        return measureCode;
    }

    public void setMeasureCode(String measureCode) {
        this.measureCode = measureCode;
    }

    public String getMeasureNameTh() {
        return measureNameTh;
    }

    public void setMeasureNameTh(String measureNameTh) {
        this.measureNameTh = measureNameTh;
    }

    public String getMeasureNameEn() {
        return measureNameEn;
    }

    public void setMeasureNameEn(String measureNameEn) {
        this.measureNameEn = measureNameEn;
    }

    public Double getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(Double rangeMin) {
        this.rangeMin = rangeMin;
    }

    public Double getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(Double rangeMax) {
        this.rangeMax = rangeMax;
    }

    public Double getUseRangeMin() {
        return useRangeMin;
    }

    public void setUseRangeMin(Double useRangeMin) {
        this.useRangeMin = useRangeMin;
    }

    public Double getUseRangeMax() {
        return useRangeMax;
    }

    public void setUseRangeMax(Double useRangeMax) {
        this.useRangeMax = useRangeMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMeasureTimes() {
        return measureTimes;
    }

    public void setMeasureTimes(Integer measureTimes) {
        this.measureTimes = measureTimes;
    }

    public String getAbType() {
        return abType;
    }

    public void setAbType(String abType) {
        this.abType = abType;
    }

    public Integer getEquipConId() {
        return equipConId;
    }

    public void setEquipConId(Integer equipConId) {
        this.equipConId = equipConId;
    }

    public Integer getCalpointId() {
        return calpointId;
    }

    public void setCalpointId(Integer calpointId) {
        this.calpointId = calpointId;
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
        if (measureNameTh != null&&measureNameTh.trim().length() > 0) {
            name += measureNameTh;
            if (measureNameEn != null&&measureNameEn.trim().length() > 0) {
                name += "( " + measureNameEn + " )";
            }
        } else {
            name += measureNameEn;
        }
        return name;
    }

    public String getRange() {
        return rangeMin + " - " + rangeMax;
    }

    public String getUseRange() {
        return useRangeMin + " - " + useRangeMax;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (measureId != null ? measureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsMasMeasure)) {
            return false;
        }
        EdcsMasMeasure other = (EdcsMasMeasure) object;
        if ((this.measureId == null && other.measureId != null) || (this.measureId != null && !this.measureId.equals(other.measureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsMasMeasure[ measureId=" + measureId + " ]";
    }

    public Integer getMeasureGroupId() {
        return measureGroupId;
    }

    public void setMeasureGroupId(Integer measureGroupId) {
        this.measureGroupId = measureGroupId;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
