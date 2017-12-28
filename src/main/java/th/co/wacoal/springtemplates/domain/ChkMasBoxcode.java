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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "CHK_MAS_BOXCODE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChkMasBoxcode_1.findAll", query = "SELECT c FROM ChkMasBoxcode_1 c")})
public class ChkMasBoxcode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BOX_CODE")
    private String boxCode;
    @Column(name = "BOX_CODE_NAME")
    private String boxCodeName;

    public ChkMasBoxcode() {
    }

    public ChkMasBoxcode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getBoxCodeName() {
        return boxCodeName;
    }

    public void setBoxCodeName(String boxCodeName) {
        this.boxCodeName = boxCodeName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (boxCode != null ? boxCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChkMasBoxcode)) {
            return false;
        }
        ChkMasBoxcode other = (ChkMasBoxcode) object;
        if ((this.boxCode == null && other.boxCode != null) || (this.boxCode != null && !this.boxCode.equals(other.boxCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplate.domain.ChkMasBoxcode_1[ boxCode=" + boxCode + " ]";
    }
    
}
