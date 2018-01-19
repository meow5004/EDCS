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
@Table(name = "EDCS_MAS_EQUIPCON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsMasEquipcon.findAll", query = "SELECT e FROM EdcsMasEquipcon e"),
    @NamedQuery(name = "EdcsMasEquipcon.findByEquipConId", query = "SELECT e FROM EdcsMasEquipcon e WHERE e.equipConId = :equipConId"),
    @NamedQuery(name = "EdcsMasEquipcon.findByEquipConNameTh", query = "SELECT e FROM EdcsMasEquipcon e WHERE e.equipConNameTh = :equipConNameTh"),
    @NamedQuery(name = "EdcsMasEquipcon.findByEquipConNameEn", query = "SELECT e FROM EdcsMasEquipcon e WHERE e.equipConNameEn = :equipConNameEn"),
    @NamedQuery(name = "EdcsMasEquipcon.findByCreateBy", query = "SELECT e FROM EdcsMasEquipcon e WHERE e.createBy = :createBy"),
    @NamedQuery(name = "EdcsMasEquipcon.findByCreateOn", query = "SELECT e FROM EdcsMasEquipcon e WHERE e.createOn = :createOn"),
    @NamedQuery(name = "EdcsMasEquipcon.findByChangeBy", query = "SELECT e FROM EdcsMasEquipcon e WHERE e.changeBy = :changeBy"),
    @NamedQuery(name = "EdcsMasEquipcon.findByChangeOn", query = "SELECT e FROM EdcsMasEquipcon e WHERE e.changeOn = :changeOn"),
    @NamedQuery(name = "EdcsMasEquipcon.findByFlagDel", query = "SELECT e FROM EdcsMasEquipcon e WHERE e.flagDel = :flagDel"),
    @NamedQuery(name = "EdcsMasEquipcon.findByFlagComment", query = "SELECT e FROM EdcsMasEquipcon e WHERE e.flagComment = :flagComment")})
public class EdcsMasEquipcon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EQUIP_CON_ID")
    private Integer equipConId;
    @Size(max = 50)
    @Column(name = "EQUIP_CON_NAME_TH")
    private String equipConNameTh;
    @Size(max = 50)
    @Column(name = "EQUIP_CON_NAME_EN")
    private String equipConNameEn;
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
    @Size(max = 50)
    @Column(name = "FLAG_COMMENT")
    private String flagComment;

    private transient String fullName;

    public EdcsMasEquipcon() {
    }

    public EdcsMasEquipcon(Integer equipConId) {
        this.equipConId = equipConId;
        fullName = getFullName();
    }

    public EdcsMasEquipcon(Integer equipConId, Date createOn, Date changeOn) {
        this.equipConId = equipConId;
        this.createOn = createOn;
        this.changeOn = changeOn;
        fullName = getFullName();
    }

    public Integer getEquipConId() {
        return equipConId;
    }

    public void setEquipConId(Integer equipConId) {
        this.equipConId = equipConId;
    }

    public String getEquipConNameTh() {
        return equipConNameTh;
    }

    public void setEquipConNameTh(String equipConNameTh) {
        this.equipConNameTh = equipConNameTh;
        fullName = getFullName();
    }

    public String getEquipConNameEn() {
        return equipConNameEn;
    }

    public void setEquipConNameEn(String equipConNameEn) {
        this.equipConNameEn = equipConNameEn;
        fullName = getFullName();
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

    public String getFlagComment() {
        return flagComment;
    }

    public void setFlagComment(String flagComment) {
        this.flagComment = flagComment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipConId != null ? equipConId.hashCode() : 0);
        return hash;
    }

    public String getFullName() {
        String name = " ";
        if (equipConNameTh != null&&equipConNameTh.trim().length() > 0) {
            name += equipConNameTh;
            if (equipConNameEn != null&&equipConNameEn.trim().length() > 0) {
                name += "( " + equipConNameEn + " )";
            }
        } else {
            name += equipConNameEn;
        }
        return name;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsMasEquipcon)) {
            return false;
        }
        EdcsMasEquipcon other = (EdcsMasEquipcon) object;
        if ((this.equipConId == null && other.equipConId != null) || (this.equipConId != null && !this.equipConId.equals(other.equipConId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsMasEquipcon[ equipConId=" + equipConId + " ]";
    }

}
