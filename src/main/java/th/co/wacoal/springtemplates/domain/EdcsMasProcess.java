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
@Table(name = "EDCS_MAS_PROCESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsMasProcess.findAll", query = "SELECT e FROM EdcsMasProcess e")})
public class EdcsMasProcess implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROCESS_ID")
    private Integer processId;
    @Size(max = 50)
    @Column(name = "PROCESS_NAME_TH")
    private String processNameTh;
    @Size(max = 50)
    @Column(name = "PROCESS_NAME_EN")
    private String processNameEn;
    @Size(max = 50)
    @Column(name = "PROCESS_SUBJECT_TH")
    private String processSubjectTh;
    @Size(max = 50)
    @Column(name = "PROCESS_SUBJECT_EN")
    private String processSubjectEn;
    @Size(max = 50)
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "CREATE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;
    @Size(max = 50)
    @Column(name = "CHANGE_BY")
    private String changeBy;
    @Column(name = "CHANGE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeOn;
    @Size(max = 50)
    @Column(name = "FLAG_DEL")
    private String flagDel;

    public EdcsMasProcess() {
    }

    public EdcsMasProcess(Integer processId) {
        this.processId = processId;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public String getProcessNameTh() {
        return processNameTh;
    }

    public void setProcessNameTh(String processNameTh) {
        this.processNameTh = processNameTh;
    }

    public String getProcessNameEn() {
        return processNameEn;
    }

    public void setProcessNameEn(String processNameEn) {
        this.processNameEn = processNameEn;
    }

    public String getProcessSubjectTh() {
        return processSubjectTh;
    }

    public void setProcessSubjectTh(String processSubjectTh) {
        this.processSubjectTh = processSubjectTh;
    }

    public String getProcessSubjectEn() {
        return processSubjectEn;
    }

    public void setProcessSubjectEn(String processSubjectEn) {
        this.processSubjectEn = processSubjectEn;
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
        String name = "process ";
        if (processNameTh != null) {
            name += processNameTh;
            if (processNameEn != null) {
                name += "( " + processNameEn + " )";
            }
        } else {
            name += processNameEn;
        }
        //subject
        String subject = "subject ";
        if (processSubjectTh != null) {
            subject += processSubjectTh;
            if (processSubjectEn != null) {
                subject += "( " + processSubjectEn + " )";
            }
        } else {
            subject += processSubjectEn;
        }

        return name + " " + subject;
    }

    public String getFullProcessName() {
        String name = "process ";
        if (processNameTh != null) {
            name += processNameTh;
            if (processNameEn != null) {
                name += "( " + processNameEn + " )";
            }
        } else {
            name += processNameEn;
        }
        return name;
    }

    ;
    
    public String getFullSubjectName() {
        String subject = "subject ";
        if (processSubjectTh != null) {
            subject += processSubjectTh;
            if (processSubjectEn != null) {
                subject += "( " + processSubjectEn + " )";
            }
        } else {
            subject += processSubjectEn;
        }
        return subject;
    }

    ;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (processId != null ? processId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsMasProcess)) {
            return false;
        }
        EdcsMasProcess other = (EdcsMasProcess) object;
        if ((this.processId == null && other.processId != null) || (this.processId != null && !this.processId.equals(other.processId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsMasProcess[ processId=" + processId + " ]";
    }

}
