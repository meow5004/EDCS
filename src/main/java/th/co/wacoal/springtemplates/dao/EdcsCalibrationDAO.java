/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.domain.EdcsCalibration;
import th.co.wacoal.springtemplates.domain.calibrationDeviceCheckModel;
import th.co.wacoal.springtemplates.domain.calibrationRequestModel;

/**
 *
 * @author admin
 */
public interface EdcsCalibrationDAO {

    public int update(EdcsCalibration object);

    public int add(EdcsCalibration object);

    public int addNewCalibrationCombineWithPreviousCalibrationIfAny(EdcsCalibration baseCalib);

    public List<EdcsCalibration> getNewAndNearExpireCalibration(int dayCountToExpired);

    public List<EdcsCalibration> getRequestedApprover(String approverId);

    public void saveCalibrationHeader(EdcsCalibration calibration);

    public void finalizeDataAndMarkCalibrationAsComplete(EdcsCalibration calib);

    public void approverRequested(calibrationRequestModel approvingReqModel);

    //search function
    public EdcsCalibration find(int id);

    public List<EdcsCalibration> findByFlag(int flag);

    public EdcsCalibration findByCode(int id);

    public List<EdcsCalibration> findAll();

    public String getRunningCode();

    public List<EdcsCalibration> getApprovedDevice();

    public List<EdcsCalibration> getCalibrationListInSystem();

    public List<EdcsCalibration> listNonFinishCalibration();

    public EdcsCalibration mappingResultSetToCalibration(Map<String, Object> map);

    public void markCalibrationForApproval(int thisCalId, String approverId);

    public List<EdcsCalibration> listStickeredDevicesCalibration();

    public List<EdcsCalibration> listLabAppovedCalibration();

    public void approveLabResult(int calId, String userId);

    public void disapproveLabResult(int calId, String userId);

    public void printedStickerCheck(int calId, String userId);

    public void returnedDeviceCheck(int calId, String userId);

    public List<EdcsCalibration> listDisapprovedCalibration();

    public List<EdcsCalibration> listFinishCalibrationWaitForApproval(String approverId);

    public void receivedDevice(calibrationDeviceCheckModel recModel);

    public void returnedDeviceCheck(calibrationDeviceCheckModel recModel);

    List<EdcsCalibration> listOldCalibStickerPrintedBetween(Date start, Date end);

}
