/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.domain;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "EDCS_CALIBRATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsCalibration.findAll", query = "SELECT e FROM EdcsCalibration e")})
public class EdcsCalibration implements Serializable {

    @Size(max = 50)
    @Column(name = "CALIBRATION_ATTACH_STATUS")
    private String calibrationAttachStatus;
    @Column(name = "CALIBRATION_ATTACH_STATUS_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date calibrationAttachStatusOn;
    @Size(max = 50)
    @Column(name = "CALIBRATION_ATTACH_STATUS_BY")
    private String calibrationAttachStatusBy;

    @Size(max = 50)
    @Column(name = "REQUEST_STATUS")
    private String requestStatus;
    @Size(max = 50)
    @Column(name = "REQUEST_APPROVER_STATUS")
    private String requestApproverStatus;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAL_ID")
    private Integer calId;
    @Size(max = 20)
    @Column(name = "CAL_CODE")
    private String calCode;
    @Column(name = "CREATE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;
    @Size(max = 50)
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "DEP_ID")
    private String depId;
    @Column(name = "MEASURE_ID")
    private Integer measureId;
    @Column(name = "MODEL_ID")
    private Integer modelId;
    @Column(name = "PROCESS_ID")
    private Integer processId;
    @Column(name = "UNIT_ID")
    private Integer unitId;
    @Column(name = "EQUIP_CON_ID")
    private Integer equipConId;
    @Size(max = 200)
    @Column(name = "CONDITION_COMMENT")
    private String conditionComment;
    @Column(name = "STATUS_CALDOC_ID")
    private Integer statusCaldocId;
    @Column(name = "DUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @Size(max = 50)
    @Column(name = "REQUEST_BY")
    private String requestBy;
    @Size(max = 200)
    @Column(name = "REQUEST_COMMNET")
    private String requestCommnet;
    @Column(name = "REQUEST_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestOn;
    @Size(max = 50)
    @Column(name = "REQUEST_APPROVER_BY")
    private String requestApproverBy;
    @Column(name = "REQUEST_APPROVER_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestApproverOn;
    @Size(max = 50)
    @Column(name = "CALIBRATOR_BY")
    private String calibratorBy;
    @Column(name = "CALIBRATOR_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date calibratorOn;
    @Size(max = 50)
    @Column(name = "RECEIVE_STATUS")
    private String receiveStatus;
    @Column(name = "RECEIVE_STATUS_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveStatusOn;
    @Size(max = 50)
    @Column(name = "RECEIVE_STATUS_BY")
    private String receiveStatusBy;
    @Size(max = 50)
    @Column(name = "CALIBRATION_STATUS")
    private String calibrationStatus;
    @Column(name = "CALIBRATION_STATUS_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date calibrationStatusOn;
    @Size(max = 50)
    @Column(name = "CALIBRATION_STATUS_BY")
    private String calibrationStatusBy;
    @Size(max = 50)
    @Column(name = "APPROVE_STATUS")
    private String approveStatus;
    @Column(name = "APPROVE_STATUS_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approveStatusOn;
    @Size(max = 50)
    @Column(name = "APPROVE_STATUS_BY")
    private String approveStatusBy;
    @Size(max = 50)
    @Column(name = "STICKER_STATUS")
    private String stickerStatus;
    @Column(name = "STICKER_STATUS_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stickerStatusOn;
    @Size(max = 50)
    @Column(name = "STICKER_STATUS_BY")
    private String stickerStatusBy;
    @Column(name = "STICKER_PRINT")
    private Integer stickerPrint;
    @Size(max = 50)
    @Column(name = "RETURN_STATUS")
    private String returnStatus;
    @Column(name = "RETURN_STATUS_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnStatusOn;
    @Size(max = 50)
    @Column(name = "RETURN_STATUS_BY")
    private String returnStatusBy;
    @Size(max = 200)
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "CAL_AGE_ID")
    private Integer calAgeId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CAL_ERROR")
    private Double calError;

    //extended propeties
    private EdcsMasMeasure associateMeasure;
    private EdcsMasCalage associateCalage;
    private EdcsMasStatusCaldoc associateStatusCaldoc;
    private EdcsMasEquipcon associateEquipCon;
    private EdcsMasMeasureUnit associateUnit;
    private EdcsMasProcess associateProcess;
    private EdcsMasModel associateModel;
    private EdcsMasDepartment associateDep;
    private Collection<EdcsCalibrationAttachHead> edcsCalibrationAttachHeadCollection;

    public EdcsCalibration() {
    }

    public EdcsCalibration(Integer calId) {
        this.calId = calId;
    }

    public Collection<EdcsCalibrationAttachHead> getEdcsCalibrationAttachHeadCollection() {
        return edcsCalibrationAttachHeadCollection;
    }

    public void setEdcsCalibrationAttachHeadCollection(Collection<EdcsCalibrationAttachHead> edcsCalibrationAttachHeadCollection) {
        this.edcsCalibrationAttachHeadCollection = edcsCalibrationAttachHeadCollection;
    }

    public Integer getCalId() {
        return calId;
    }

    public void setCalId(Integer calId) {
        this.calId = calId;
    }

    public String getCalCode() {
        return calCode;
    }

    public void setCalCode(String calCode) {
        this.calCode = calCode;
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

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public Integer getMeasureId() {
        return measureId;
    }

    public void setMeasureId(Integer measureId) {
        this.measureId = measureId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getEquipConId() {
        return equipConId;
    }

    public void setEquipConId(Integer equipConId) {
        this.equipConId = equipConId;
    }

    public String getConditionComment() {
        return conditionComment;
    }

    public void setConditionComment(String conditionComment) {
        this.conditionComment = conditionComment;
    }

    public Integer getStatusCaldocId() {
        return statusCaldocId;
    }

    public void setStatusCaldocId(Integer statusCaldocId) {
        this.statusCaldocId = statusCaldocId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public String getRequestCommnet() {
        return requestCommnet;
    }

    public void setRequestCommnet(String requestCommnet) {
        this.requestCommnet = requestCommnet;
    }

    public Date getRequestOn() {
        return requestOn;
    }

    public void setRequestOn(Date requestOn) {
        this.requestOn = requestOn;
    }

    public String getRequestApproverBy() {
        return requestApproverBy;
    }

    public void setRequestApproverBy(String requestApproverBy) {
        this.requestApproverBy = requestApproverBy;
    }

    public Date getRequestApproverOn() {
        return requestApproverOn;
    }

    public void setRequestApproverOn(Date requestApproverOn) {
        this.requestApproverOn = requestApproverOn;
    }

    public String getCalibratorBy() {
        return calibratorBy;
    }

    public void setCalibratorBy(String calibratorBy) {
        this.calibratorBy = calibratorBy;
    }

    public Date getCalibratorOn() {
        return calibratorOn;
    }

    public void setCalibratorOn(Date calibratorOn) {
        this.calibratorOn = calibratorOn;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public Date getReceiveStatusOn() {
        return receiveStatusOn;
    }

    public void setReceiveStatusOn(Date receiveStatusOn) {
        this.receiveStatusOn = receiveStatusOn;
    }

    public String getReceiveStatusBy() {
        return receiveStatusBy;
    }

    public void setReceiveStatusBy(String receiveStatusBy) {
        this.receiveStatusBy = receiveStatusBy;
    }

    public String getCalibrationStatus() {
        return calibrationStatus;
    }

    public void setCalibrationStatus(String calibrationStatus) {
        this.calibrationStatus = calibrationStatus;
    }

    public Date getCalibrationStatusOn() {
        return calibrationStatusOn;
    }

    public void setCalibrationStatusOn(Date calibrationStatusOn) {
        this.calibrationStatusOn = calibrationStatusOn;
    }

    public String getCalibrationStatusBy() {
        return calibrationStatusBy;
    }

    public void setCalibrationStatusBy(String calibrationStatusBy) {
        this.calibrationStatusBy = calibrationStatusBy;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Date getApproveStatusOn() {
        return approveStatusOn;
    }

    public void setApproveStatusOn(Date approveStatusOn) {
        this.approveStatusOn = approveStatusOn;
    }

    public String getApproveStatusBy() {
        return approveStatusBy;
    }

    public void setApproveStatusBy(String approveStatusBy) {
        this.approveStatusBy = approveStatusBy;
    }

    public String getStickerStatus() {
        return stickerStatus;
    }

    public void setStickerStatus(String stickerStatus) {
        this.stickerStatus = stickerStatus;
    }

    public Date getStickerStatusOn() {
        return stickerStatusOn;
    }

    public void setStickerStatusOn(Date stickerStatusOn) {
        this.stickerStatusOn = stickerStatusOn;
    }

    public String getStickerStatusBy() {
        return stickerStatusBy;
    }

    public void setStickerStatusBy(String stickerStatusBy) {
        this.stickerStatusBy = stickerStatusBy;
    }

    public Integer getStickerPrint() {
        return stickerPrint;
    }

    public void setStickerPrint(Integer stickerPrint) {
        this.stickerPrint = stickerPrint;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Date getReturnStatusOn() {
        return returnStatusOn;
    }

    public void setReturnStatusOn(Date returnStatusOn) {
        this.returnStatusOn = returnStatusOn;
    }

    public String getReturnStatusBy() {
        return returnStatusBy;
    }

    public void setReturnStatusBy(String returnStatusBy) {
        this.returnStatusBy = returnStatusBy;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCalAgeId() {
        return calAgeId;
    }

    public void setCalAgeId(Integer calAgeId) {
        this.calAgeId = calAgeId;
    }

    public Double getCalError() {
        return calError;
    }

    public void setCalError(Double calError) {
        this.calError = calError;
    }

    public EdcsMasMeasure getAssociateMeasure() {
        return associateMeasure;
    }

    public void setAssociateMeasure(EdcsMasMeasure associateMeasure) {
        this.associateMeasure = associateMeasure;
    }

    public EdcsMasCalage getAssociateCalage() {
        return associateCalage;
    }

    public void setAssociateCalage(EdcsMasCalage associateCalage) {
        this.associateCalage = associateCalage;
    }

    public EdcsMasStatusCaldoc getAssociateStatusCaldoc() {
        return associateStatusCaldoc;
    }

    public void setAssociateStatusCaldoc(EdcsMasStatusCaldoc associateStatusCaldoc) {
        this.associateStatusCaldoc = associateStatusCaldoc;
    }

    public EdcsMasEquipcon getAssociateEquipCon() {
        return associateEquipCon;
    }

    public void setAssociateEquipCon(EdcsMasEquipcon associateEquipCon) {
        this.associateEquipCon = associateEquipCon;
    }

    public EdcsMasMeasureUnit getAssociateUnit() {
        return associateUnit;
    }

    public void setAssociateUnit(EdcsMasMeasureUnit associateUnit) {
        this.associateUnit = associateUnit;
    }

    public EdcsMasProcess getAssociateProcess() {
        return associateProcess;
    }

    public void setAssociateProcess(EdcsMasProcess associateProcess) {
        this.associateProcess = associateProcess;
    }

    public EdcsMasModel getAssociateModel() {
        return associateModel;
    }

    public void setAssociateModel(EdcsMasModel associateModel) {
        this.associateModel = associateModel;
    }

    public EdcsMasDepartment getAssociateDep() {
        return associateDep;
    }

    public void setAssociateDep(EdcsMasDepartment associateDep) {
        this.associateDep = associateDep;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calId != null ? calId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EdcsCalibration)) {
            return false;
        }
        EdcsCalibration other = (EdcsCalibration) object;
        if ((this.calId == null && other.calId != null) || (this.calId != null && !this.calId.equals(other.calId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "th.co.wacoal.springtemplates.domain.EdcsCalibration[ calId=" + calId + " ]";
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestApproverStatus() {
        return requestApproverStatus;
    }

    public void setRequestApproverStatus(String requestApproverStatus) {
        this.requestApproverStatus = requestApproverStatus;
    }

    public String getCalibrationAttachStatus() {
        return calibrationAttachStatus;
    }

    public void setCalibrationAttachStatus(String calibrationAttachStatus) {
        this.calibrationAttachStatus = calibrationAttachStatus;
    }

    public Date getCalibrationAttachStatusOn() {
        return calibrationAttachStatusOn;
    }

    public void setCalibrationAttachStatusOn(Date calibrationAttachStatusOn) {
        this.calibrationAttachStatusOn = calibrationAttachStatusOn;
    }

    public String getCalibrationAttachStatusBy() {
        return calibrationAttachStatusBy;
    }

    public void setCalibrationAttachStatusBy(String calibrationAttachStatusBy) {
        this.calibrationAttachStatusBy = calibrationAttachStatusBy;
    }

}
