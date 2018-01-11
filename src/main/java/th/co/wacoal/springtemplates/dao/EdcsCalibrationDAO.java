/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.domain.EdcsCalibration;

/**
 *
 * @author admin
 */
public interface EdcsCalibrationDAO {

    public List<EdcsCalibration> findAll();

    public int update(EdcsCalibration object);

    public int add(EdcsCalibration object);

    public int addNewCalibrationByMeasureIdAndPreviousCalibration(int measureId);

    public EdcsCalibration find(int id);

    public List<EdcsCalibration> findByFlag(int flag);

    public List<EdcsCalibration> getNewAndNearExpireCalibrationMoreThanDays(int dayCountToExpired);

    public List<EdcsCalibration> getRequestedCalibration();

    public EdcsCalibration mappingResultSetToCalibration(Map<String, Object> map);

    public String getRunningCode();

    public void approverRequested(int calId);

    public List<EdcsCalibration> getApprovedDevice();

    public void receivedDevice(int thisCalId);

    public List<EdcsCalibration> getApprovedAndReceiverdDevice();

}
