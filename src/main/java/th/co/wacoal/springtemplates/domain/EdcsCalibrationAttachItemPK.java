/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author admin
 */
@Embeddable
public class EdcsCalibrationAttachItemPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CAL_ATTACH_HEAD_ID")
    private int calAttachHeadId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAL_ATTACH_LINE")
    private int calAttachLine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAL_TIME")
    private int calTime;

    public EdcsCalibrationAttachItemPK() {
    }

    public EdcsCalibrationAttachItemPK(int calAttachHeadId, int calAttachLine, int calTime) {
        this.calAttachHeadId = calAttachHeadId;
        this.calAttachLine = calAttachLine;
        this.calTime = calTime;
    }

    public int getCalAttachHeadId() {
        return calAttachHeadId;
    }

    public void setCalAttachHeadId(int calAttachHeadId) {
        this.calAttachHeadId = calAttachHeadId;
    }

    public int getCalAttachLine() {
        return calAttachLine;
    }

    public void setCalAttachLine(int calAttachLine) {
        this.calAttachLine = calAttachLine;
    }

    public int getCalTime() {
        return calTime;
    }

    public void setCalTime(int calTime) {
        this.calTime = calTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) calAttachHeadId;
        hash += (int) calAttachLine;
        hash += (int) calTime;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsCalibrationAttachItemPK)) {
            return false;
        }
        EdcsCalibrationAttachItemPK other = (EdcsCalibrationAttachItemPK) object;
        if (this.calAttachHeadId != other.calAttachHeadId) {
            return false;
        }
        if (this.calAttachLine != other.calAttachLine) {
            return false;
        }
        if (this.calTime != other.calTime) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsCalibrationAttachItemPK[ calAttachHeadId=" + calAttachHeadId + ", calAttachLine=" + calAttachLine + ", calTime=" + calTime + " ]";
    }
    
}
