/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "EDCS_CALIBRATION_ATTACH_HEAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsCalibrationAttachHead.findAll", query = "SELECT e FROM EdcsCalibrationAttachHead e")})
public class EdcsCalibrationAttachHead implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAL_ATTACH_HEAD_ID")
    private Integer calAttachHeadId;
    @Column(name = "CAL_ID")
    private Integer calId;
    @Size(max = 200)
    @Column(name = "CAL_STATE")
    private String calState;
    @Size(max = 50)
    @Column(name = "TEMPERATURE")
    private String temperature;
    @Size(max = 50)
    @Column(name = "HUMIDITY")
    private String humidity;
    @Size(max = 50)
    @Column(name = "COORDINATE")
    private String coordinate;
    @Size(max = 50)
    @Column(name = "ACTIVE_RANGE")
    private String activeRange;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ACCEPTANCE")
    private Double acceptance;
    @Column(name = "CAL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date calDate;
    @Size(max = 10)
    @Column(name = "AB_TYPE")
    private String abType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "edcsCalibrationAttachHead")
    private List<EdcsCalibrationAttachItem> edcsCalibrationAttachItemList;

    public EdcsCalibrationAttachHead() {
    }

    public EdcsCalibrationAttachHead(Integer calAttachHeadId) {
        this.calAttachHeadId = calAttachHeadId;
    }

    public Integer getCalAttachHeadId() {
        return calAttachHeadId;
    }

    public void setCalAttachHeadId(Integer calAttachHeadId) {
        this.calAttachHeadId = calAttachHeadId;
    }

    public Integer getCalId() {
        return calId;
    }

    public void setCalId(Integer calId) {
        this.calId = calId;
    }

    public String getCalState() {
        return calState;
    }

    public void setCalState(String calState) {
        this.calState = calState;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getActiveRange() {
        return activeRange;
    }

    public void setActiveRange(String activeRange) {
        this.activeRange = activeRange;
    }

    public Double getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(Double acceptance) {
        this.acceptance = acceptance;
    }

    public Date getCalDate() {
        return calDate;
    }

    public void setCalDate(Date calDate) {
        this.calDate = calDate;
    }

    public String getAbType() {
        return abType;
    }

    public void setAbType(String abType) {
        this.abType = abType;
    }

    @XmlTransient
    public List<EdcsCalibrationAttachItem> getEdcsCalibrationAttachItemList() {
        return edcsCalibrationAttachItemList;
    }

    public void setEdcsCalibrationAttachItemList(List<EdcsCalibrationAttachItem> edcsCalibrationAttachItemList) {
        this.edcsCalibrationAttachItemList = edcsCalibrationAttachItemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calAttachHeadId != null ? calAttachHeadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsCalibrationAttachHead)) {
            return false;
        }
        EdcsCalibrationAttachHead other = (EdcsCalibrationAttachHead) object;
        if ((this.calAttachHeadId == null && other.calAttachHeadId != null) || (this.calAttachHeadId != null && !this.calAttachHeadId.equals(other.calAttachHeadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsCalibrationAttachHead[ calAttachHeadId=" + calAttachHeadId + " ]";
    }

}
