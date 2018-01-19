/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "EDCS_CALIBRATION_ATTACH_ITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsCalibrationAttachItem.findAll", query = "SELECT e FROM EdcsCalibrationAttachItem e")})
public class EdcsCalibrationAttachItem implements Serializable {

    @JoinColumn(name = "CAL_ATTACH_HEAD_ID", referencedColumnName = "CAL_ATTACH_HEAD_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EdcsCalibrationAttachHead edcsCalibrationAttachHead;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EdcsCalibrationAttachItemPK edcsCalibrationAttachItemPK;
    @Column(name = "CALPOINT_MIN")
    private Integer calpointMin;
    @Column(name = "CALPOINT_MAX")
    private Integer calpointMax;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CALPOINT_VALUE")
    private Double calpointValue;

    public EdcsCalibrationAttachItem() {
    }

    public EdcsCalibrationAttachItem(EdcsCalibrationAttachItemPK edcsCalibrationAttachItemPK) {
        this.edcsCalibrationAttachItemPK = edcsCalibrationAttachItemPK;
    }

    public EdcsCalibrationAttachItem(int calAttachHeadId, int calAttachLine, int calTime) {
        this.edcsCalibrationAttachItemPK = new EdcsCalibrationAttachItemPK(calAttachHeadId, calAttachLine, calTime);
    }

    public EdcsCalibrationAttachItemPK getEdcsCalibrationAttachItemPK() {
        return edcsCalibrationAttachItemPK;
    }

    public void setEdcsCalibrationAttachItemPK(EdcsCalibrationAttachItemPK edcsCalibrationAttachItemPK) {
        this.edcsCalibrationAttachItemPK = edcsCalibrationAttachItemPK;
    }

    public Integer getCalpointMin() {
        return calpointMin;
    }

    public void setCalpointMin(Integer calpointMin) {
        this.calpointMin = calpointMin;
    }

    public Integer getCalpointMax() {
        return calpointMax;
    }

    public void setCalpointMax(Integer calpointMax) {
        this.calpointMax = calpointMax;
    }

    public Double getCalpointValue() {
        return calpointValue;
    }

    public void setCalpointValue(Double calpointValue) {
        this.calpointValue = calpointValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (edcsCalibrationAttachItemPK != null ? edcsCalibrationAttachItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsCalibrationAttachItem)) {
            return false;
        }
        EdcsCalibrationAttachItem other = (EdcsCalibrationAttachItem) object;
        if ((this.edcsCalibrationAttachItemPK == null && other.edcsCalibrationAttachItemPK != null) || (this.edcsCalibrationAttachItemPK != null && !this.edcsCalibrationAttachItemPK.equals(other.edcsCalibrationAttachItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsCalibrationAttachItem[ edcsCalibrationAttachItemPK=" + edcsCalibrationAttachItemPK + " ]";
    }

    public EdcsCalibrationAttachHead getEdcsCalibrationAttachHead() {
        return edcsCalibrationAttachHead;
    }

    public void setEdcsCalibrationAttachHead(EdcsCalibrationAttachHead edcsCalibrationAttachHead) {
        this.edcsCalibrationAttachHead = edcsCalibrationAttachHead;
    }
    
}
