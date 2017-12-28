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
@Table(name = "EDCS_MAS_STATUS_CALDOC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsMasStatusCaldoc.findAll", query = "SELECT e FROM EdcsMasStatusCaldoc e"),
    @NamedQuery(name = "EdcsMasStatusCaldoc.findByStatusCaldocId", query = "SELECT e FROM EdcsMasStatusCaldoc e WHERE e.statusCaldocId = :statusCaldocId"),
    @NamedQuery(name = "EdcsMasStatusCaldoc.findByStatusCaldocName", query = "SELECT e FROM EdcsMasStatusCaldoc e WHERE e.statusCaldocName = :statusCaldocName"),
    @NamedQuery(name = "EdcsMasStatusCaldoc.findByCreateBy", query = "SELECT e FROM EdcsMasStatusCaldoc e WHERE e.createBy = :createBy"),
    @NamedQuery(name = "EdcsMasStatusCaldoc.findByCreateOn", query = "SELECT e FROM EdcsMasStatusCaldoc e WHERE e.createOn = :createOn"),
    @NamedQuery(name = "EdcsMasStatusCaldoc.findByChangeBy", query = "SELECT e FROM EdcsMasStatusCaldoc e WHERE e.changeBy = :changeBy"),
    @NamedQuery(name = "EdcsMasStatusCaldoc.findByChangeOn", query = "SELECT e FROM EdcsMasStatusCaldoc e WHERE e.changeOn = :changeOn")})
public class EdcsMasStatusCaldoc implements Serializable {

    @Size(max = 50)
    @Column(name = "FLAG_DEL")
    private String flagDel;


    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS_CALDOC_ID")
    private Integer statusCaldocId;
    @Size(max = 50)
    @Column(name = "STATUS_CALDOC_NAME")
    private String statusCaldocName;
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

    public EdcsMasStatusCaldoc() {
    }

    public EdcsMasStatusCaldoc(Integer statusCaldocId) {
        this.statusCaldocId = statusCaldocId;
    }

    public EdcsMasStatusCaldoc(Integer statusCaldocId, Date createOn, Date changeOn) {
        this.statusCaldocId = statusCaldocId;
        this.createOn = createOn;
        this.changeOn = changeOn;
    }

    public Integer getStatusCaldocId() {
        return statusCaldocId;
    }

    public void setStatusCaldocId(Integer statusCaldocId) {
        this.statusCaldocId = statusCaldocId;
    }

    public String getStatusCaldocName() {
        return statusCaldocName;
    }

    public void setStatusCaldocName(String statusCaldocName) {
        this.statusCaldocName = statusCaldocName;
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


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusCaldocId != null ? statusCaldocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsMasStatusCaldoc)) {
            return false;
        }
        EdcsMasStatusCaldoc other = (EdcsMasStatusCaldoc) object;
        if ((this.statusCaldocId == null && other.statusCaldocId != null) || (this.statusCaldocId != null && !this.statusCaldocId.equals(other.statusCaldocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsMasStatusCaldoc[ statusCaldocId=" + statusCaldocId + " ]";
    }

    public String getFlagDel() {
        return flagDel;
    }

    public void setFlagDel(String flagDel) {
        this.flagDel = flagDel;
    }

    
}
