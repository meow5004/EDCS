/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "STICKER_RESERVE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StickerReserve.findAll", query = "SELECT s FROM StickerReserve s")})
public class StickerReserve implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "STICKER_ID")
    private Integer stickerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAL_ID")
    private int calId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMP_ID")
    private String empId;
    @Column(name = "PRINT_ORDER")
    private Integer printOrder;

    private EdcsMasUser associateUser;
    private EdcsCalibration associateCalibration;

    public StickerReserve() {
    }

    public StickerReserve(Integer stickerId) {
        this.stickerId = stickerId;
    }

    public StickerReserve(Integer stickerId, int calId, String empId) {
        this.stickerId = stickerId;
        this.calId = calId;
        this.empId = empId;
    }

    public Integer getStickerId() {
        return stickerId;
    }

    public void setStickerId(Integer stickerId) {
        this.stickerId = stickerId;
    }

    public int getCalId() {
        return calId;
    }

    public void setCalId(int calId) {
        this.calId = calId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Integer getPrintOrder() {
        return printOrder;
    }

    public void setPrintOrder(Integer printOrder) {
        this.printOrder = printOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stickerId != null ? stickerId.hashCode() : 0);
        return hash;
    }

    public EdcsMasUser getAssociateUser() {
        return associateUser;
    }

    public void setAssociateUser(EdcsMasUser associateUser) {
        this.associateUser = associateUser;
    }

    public EdcsCalibration getAssociateCalibration() {
        return associateCalibration;
    }

    public void setAssociateCalibration(EdcsCalibration associateCalibration) {
        this.associateCalibration = associateCalibration;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StickerReserve)) {
            return false;
        }
        StickerReserve other = (StickerReserve) object;
        if ((this.stickerId == null && other.stickerId != null) || (this.stickerId != null && !this.stickerId.equals(other.stickerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.StickerReserve[ stickerId=" + stickerId + " ]";
    }

}
