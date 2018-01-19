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
@Table(name = "EDCS_MAS_MODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsMasModel.findAll", query = "SELECT e FROM EdcsMasModel e")})
public class EdcsMasModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MODEL_ID")
    private Integer modelId;
    @Size(max = 20)
    @Column(name = "MODEL_CODE")
    private String modelCode;
    @Column(name = "MEASURE_ID")
    private Integer measureId;
    @Size(max = 200)
    @Column(name = "LOCATION_BY")
    private String locationBy;
    @Size(max = 200)
    @Column(name = "LOCATION_RETURN")
    private String locationReturn;
    @Size(max = 50)
    @Column(name = "CER_NO")
    private String cerNo;
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

    public EdcsMasModel() {
    }

    public EdcsMasModel(Integer modelId) {
        this.modelId = modelId;
    }

    public EdcsMasModel(Integer modelId, Date createOn, Date changeOn) {
        this.modelId = modelId;
        this.createOn = createOn;
        this.changeOn = changeOn;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public Integer getMeasureId() {
        return measureId;
    }

    public void setMeasureId(Integer measureId) {
        this.measureId = measureId;
    }

    public String getLocationBy() {
        return locationBy;
    }

    public void setLocationBy(String locationBy) {
        this.locationBy = locationBy;
    }

    public String getLocationReturn() {
        return locationReturn;
    }

    public void setLocationReturn(String locationReturn) {
        this.locationReturn = locationReturn;
    }

    public String getcerNo() {
        return cerNo;
    }

    public void setcerNo(String cerNo) {
        this.cerNo = cerNo;
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
        hash += (modelId != null ? modelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsMasModel)) {
            return false;
        }
        EdcsMasModel other = (EdcsMasModel) object;
        if ((this.modelId == null && other.modelId != null) || (this.modelId != null && !this.modelId.equals(other.modelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsMasModel[ modelId=" + modelId + " ]";
    }
    
}
