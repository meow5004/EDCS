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
@Table(name = "EDCS_MAS_CALPOINT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsMasCalpoint.findAll", query = "SELECT e FROM EdcsMasCalpoint e"),
    @NamedQuery(name = "EdcsMasCalpoint.findByCalpointId", query = "SELECT e FROM EdcsMasCalpoint e WHERE e.calpointId = :calpointId"),
    @NamedQuery(name = "EdcsMasCalpoint.findByCalpointMin", query = "SELECT e FROM EdcsMasCalpoint e WHERE e.calpointMin = :calpointMin"),
    @NamedQuery(name = "EdcsMasCalpoint.findByCalpointMax", query = "SELECT e FROM EdcsMasCalpoint e WHERE e.calpointMax = :calpointMax"),
    @NamedQuery(name = "EdcsMasCalpoint.findByCreateBy", query = "SELECT e FROM EdcsMasCalpoint e WHERE e.createBy = :createBy"),
    @NamedQuery(name = "EdcsMasCalpoint.findByCreateOn", query = "SELECT e FROM EdcsMasCalpoint e WHERE e.createOn = :createOn"),
    @NamedQuery(name = "EdcsMasCalpoint.findByChangeBy", query = "SELECT e FROM EdcsMasCalpoint e WHERE e.changeBy = :changeBy"),
    @NamedQuery(name = "EdcsMasCalpoint.findByChangeOn", query = "SELECT e FROM EdcsMasCalpoint e WHERE e.changeOn = :changeOn"),
    @NamedQuery(name = "EdcsMasCalpoint.findByFlagDel", query = "SELECT e FROM EdcsMasCalpoint e WHERE e.flagDel = :flagDel")})
public class EdcsMasCalpoint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CALPOINT_ID")
    private Integer calpointId;
    @Column(name = "CALPOINT_MIN")
    private Integer calpointMin;
    @Column(name = "CALPOINT_MAX")
    private Integer calpointMax;
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

    private String calpoint;

    public EdcsMasCalpoint() {
    }

    public EdcsMasCalpoint(Integer calpointId) {
        this.calpointId = calpointId;
    }

    public EdcsMasCalpoint(Integer calpointId, Date createOn, Date changeOn) {
        this.calpointId = calpointId;
        this.createOn = createOn;
        this.changeOn = changeOn;
    }

    public Integer getCalpointId() {
        return calpointId;
    }

    public void setCalpointId(Integer calpointId) {
        this.calpointId = calpointId;
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
        hash += (calpointId != null ? calpointId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsMasCalpoint)) {
            return false;
        }
        EdcsMasCalpoint other = (EdcsMasCalpoint) object;
        if ((this.calpointId == null && other.calpointId != null) || (this.calpointId != null && !this.calpointId.equals(other.calpointId))) {
            return false;
        }
        return true;
    }

    public String getCalpoint() {
        
        return calpointMin+" - "+calpointMax;
    }


    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsMasCalpoint[ calpointId=" + calpointId + " ]";
    }

}
