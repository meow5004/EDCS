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
@Table(name = "EDCS_MAS_USER_AUTH_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsMasUserAuthType.findAll", query = "SELECT e FROM EdcsMasUserAuthType e")})
public class EdcsMasUserAuthType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUTH_TYPE_ID")
    private Integer authTypeId;
    @Size(max = 50)
    @Column(name = "AUTH_TYPE_NAME_TH")
    private String authTypeNameTh;
    @Size(max = 50)
    @Column(name = "AUTH_TYPE_NAME_EN")
    private String authTypeNameEn;
    @Column(name = "CREATE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;
    @Size(max = 50)
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "CHANGE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeOn;
    @Size(max = 50)
    @Column(name = "CHANGE_BY")
    private String changeBy;

    public EdcsMasUserAuthType() {
    }

    public EdcsMasUserAuthType(Integer authTypeId) {
        this.authTypeId = authTypeId;
    }

    public Integer getAuthTypeId() {
        return authTypeId;
    }

    public void setAuthTypeId(Integer authTypeId) {
        this.authTypeId = authTypeId;
    }

    public String getAuthTypeNameTh() {
        return authTypeNameTh;
    }

    public void setAuthTypeNameTh(String authTypeNameTh) {
        this.authTypeNameTh = authTypeNameTh;
    }

    public String getAuthTypeNameEn() {
        return authTypeNameEn;
    }

    public void setAuthTypeNameEn(String authTypeNameEn) {
        this.authTypeNameEn = authTypeNameEn;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getChangeOn() {
        return changeOn;
    }

    public void setChangeOn(Date changeOn) {
        this.changeOn = changeOn;
    }

    public String getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(String changeBy) {
        this.changeBy = changeBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authTypeId != null ? authTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsMasUserAuthType)) {
            return false;
        }
        EdcsMasUserAuthType other = (EdcsMasUserAuthType) object;
        if ((this.authTypeId == null && other.authTypeId != null) || (this.authTypeId != null && !this.authTypeId.equals(other.authTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsMasUserAuthType[ authTypeId=" + authTypeId + " ]";
    }
    
}
