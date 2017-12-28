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
@Table(name = "EDCS_CALIBRATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EdcsCalibration.findAll", query = "SELECT e FROM EdcsCalibration e")})
public class EdcsCalibration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAL_ID")
    private Integer calId;
    @Size(max = 10)
    @Column(name = "CREATE_ON")
    private String createOn;
    @Size(max = 10)
    @Column(name = "CREATE_BY")
    private String createBy;
    @Size(max = 10)
    @Column(name = "DEP_ID")
    private String depId;
    @Size(max = 10)
    @Column(name = "DEVICE_ID")
    private String deviceId;
    @Size(max = 10)
    @Column(name = "ARCHETYPE_ID")
    private String archetypeId;
    @Size(max = 10)
    @Column(name = "PROCESS_ID")
    private String processId;
    @Size(max = 10)
    @Column(name = "UNIT_ID")
    private String unitId;
    @Size(max = 10)
    @Column(name = "CONDITION_ID")
    private String conditionId;
    @Size(max = 10)
    @Column(name = "CONDITION_COMMENT")
    private String conditionComment;
    @Size(max = 10)
    @Column(name = "STATUS_ID")
    private String statusId;
    @Size(max = 10)
    @Column(name = "DUE_DATE")
    private String dueDate;
    @Size(max = 10)
    @Column(name = "TEMPERATURE")
    private String temperature;
    @Size(max = 10)
    @Column(name = "HUMIDITY")
    private String humidity;
    @Size(max = 10)
    @Column(name = "COORDINATE")
    private String coordinate;
    @Size(max = 10)
    @Column(name = "RANGE")
    private String range;
    @Size(max = 10)
    @Column(name = "CALIBRATOR_BY")
    private String calibratorBy;
    @Size(max = 10)
    @Column(name = "CALIBRATOR_ON")
    private String calibratorOn;
    @Size(max = 10)
    @Column(name = "APPROVER_BY")
    private String approverBy;
    @Size(max = 10)
    @Column(name = "APPROVER_ON")
    private String approverOn;
    @Size(max = 10)
    @Column(name = "RECEIVE_STATUS")
    private String receiveStatus;
    @Size(max = 10)
    @Column(name = "RECEIVE_STATUS_ON")
    private String receiveStatusOn;
    @Size(max = 10)
    @Column(name = "RECEIVE_STATUS_BY")
    private String receiveStatusBy;
    @Size(max = 10)
    @Column(name = "CALIBRATION_STATUS")
    private String calibrationStatus;
    @Size(max = 10)
    @Column(name = "CALIBRATION_STATUS_ON")
    private String calibrationStatusOn;
    @Size(max = 10)
    @Column(name = "CALIBRATION_STATUS_BY")
    private String calibrationStatusBy;
    @Size(max = 10)
    @Column(name = "APPROVE_STATUS")
    private String approveStatus;
    @Size(max = 10)
    @Column(name = "APPROVE_STATUS_ON")
    private String approveStatusOn;
    @Size(max = 10)
    @Column(name = "APPROVE_STATUS_BY")
    private String approveStatusBy;
    @Size(max = 10)
    @Column(name = "STICKER_STATUS")
    private String stickerStatus;
    @Size(max = 10)
    @Column(name = "STICKER_STATUS_ON")
    private String stickerStatusOn;
    @Size(max = 10)
    @Column(name = "STICKER_STATUS_BY")
    private String stickerStatusBy;
    @Size(max = 10)
    @Column(name = "STICKER_PRINT")
    private String stickerPrint;
    @Size(max = 10)
    @Column(name = "RETURN_STATUS")
    private String returnStatus;
    @Size(max = 10)
    @Column(name = "RETURN_STATUS_ON")
    private String returnStatusOn;
    @Size(max = 10)
    @Column(name = "RETURN_STATUS_BY")
    private String returnStatusBy;
    @Size(max = 10)
    @Column(name = "REQUEST_BY")
    private String requestBy;
    @Size(max = 10)
    @Column(name = "REQUEST_COMMNET")
    private String requestCommnet;
    @Size(max = 10)
    @Column(name = "REQUEST_ON")
    private String requestOn;
    @Size(max = 10)
    @Column(name = "REQUEST_APPROVER_BY")
    private String requestApproverBy;
    @Size(max = 10)
    @Column(name = "REQUEST_APPROVER_ON")
    private String requestApproverOn;
    @Size(max = 10)
    @Column(name = "COMMENT")
    private String comment;
    @Size(max = 10)
    @Column(name = "CAL_AGE_ID")
    private String calAgeId;

    public EdcsCalibration() {
    }

    public EdcsCalibration(Integer calId) {
        this.calId = calId;
    }

    public Integer getCalId() {
        return calId;
    }

    public void setCalId(Integer calId) {
        this.calId = calId;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getArchetypeId() {
        return archetypeId;
    }

    public void setArchetypeId(String archetypeId) {
        this.archetypeId = archetypeId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public String getConditionComment() {
        return conditionComment;
    }

    public void setConditionComment(String conditionComment) {
        this.conditionComment = conditionComment;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getCalibratorBy() {
        return calibratorBy;
    }

    public void setCalibratorBy(String calibratorBy) {
        this.calibratorBy = calibratorBy;
    }

    public String getCalibratorOn() {
        return calibratorOn;
    }

    public void setCalibratorOn(String calibratorOn) {
        this.calibratorOn = calibratorOn;
    }

    public String getApproverBy() {
        return approverBy;
    }

    public void setApproverBy(String approverBy) {
        this.approverBy = approverBy;
    }

    public String getApproverOn() {
        return approverOn;
    }

    public void setApproverOn(String approverOn) {
        this.approverOn = approverOn;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getReceiveStatusOn() {
        return receiveStatusOn;
    }

    public void setReceiveStatusOn(String receiveStatusOn) {
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

    public String getCalibrationStatusOn() {
        return calibrationStatusOn;
    }

    public void setCalibrationStatusOn(String calibrationStatusOn) {
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

    public String getApproveStatusOn() {
        return approveStatusOn;
    }

    public void setApproveStatusOn(String approveStatusOn) {
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

    public String getStickerStatusOn() {
        return stickerStatusOn;
    }

    public void setStickerStatusOn(String stickerStatusOn) {
        this.stickerStatusOn = stickerStatusOn;
    }

    public String getStickerStatusBy() {
        return stickerStatusBy;
    }

    public void setStickerStatusBy(String stickerStatusBy) {
        this.stickerStatusBy = stickerStatusBy;
    }

    public String getStickerPrint() {
        return stickerPrint;
    }

    public void setStickerPrint(String stickerPrint) {
        this.stickerPrint = stickerPrint;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnStatusOn() {
        return returnStatusOn;
    }

    public void setReturnStatusOn(String returnStatusOn) {
        this.returnStatusOn = returnStatusOn;
    }

    public String getReturnStatusBy() {
        return returnStatusBy;
    }

    public void setReturnStatusBy(String returnStatusBy) {
        this.returnStatusBy = returnStatusBy;
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

    public String getRequestOn() {
        return requestOn;
    }

    public void setRequestOn(String requestOn) {
        this.requestOn = requestOn;
    }

    public String getRequestApproverBy() {
        return requestApproverBy;
    }

    public void setRequestApproverBy(String requestApproverBy) {
        this.requestApproverBy = requestApproverBy;
    }

    public String getRequestApproverOn() {
        return requestApproverOn;
    }

    public void setRequestApproverOn(String requestApproverOn) {
        this.requestApproverOn = requestApproverOn;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCalAgeId() {
        return calAgeId;
    }

    public void setCalAgeId(String calAgeId) {
        this.calAgeId = calAgeId;
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
    
}
