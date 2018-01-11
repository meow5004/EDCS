/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsCalibration;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasure;

/**
 *
 * @author admin
 */
public class EdcsCalibrationDAOImpI implements EdcsCalibrationDAO {

    private final Database db;
    EdcsMasMeasureDAO masDao = null;

    public EdcsCalibrationDAOImpI(Database db) {
        this.db = db;
        masDao = new EdcsMasMeasureDAOImpI(db);
    }

    @Override
    public int update(EdcsCalibration calib) {

        String sql = "";

        int rs = db.update(sql);

        return rs;
    }

    @Override
    public int add(EdcsCalibration calib) {
        // insert

        String sql = "INSERT INTO [dbo].[EDCS_CALIBRATION]"
                + " ("
                + " [CAL_CODE]"
                + " ,[CREATE_ON]"
                + " ,[CREATE_BY]"
                + " ,[DEP_ID]"
                + " ,[MEASURE_ID]"
                + " ,[MODEL_ID]"
                + " ,[PROCESS_ID]"
                + " ,[UNIT_ID]"
                + " ,[EQUIP_CON_ID]"
                + " ,[CONDITION_COMMENT]"
                + " ,[STATUS_CALDOC_ID]"
                + " ,[DUE_DATE]"
                + " ,[REQUEST_BY]"
                + " ,[REQUEST_COMMNET]"
                + " ,[REQUEST_STATUS]"
                + " ,[REQUEST_ON]"
                + " ,[COMMENT]"
                + " ,[CAL_AGE_ID]"
                + " ,[CAL_ERROR])"
                + " VALUES"
                + " ("
                + " ?"
                + " ,getdate()"
                + " ,?"
                + " ,?"
                + " ,?"
                + " ,?"
                + " ,?"
                + " ,?"
                + " ,?"
                + " ,?"
                + " ,?"
                + " ,?"
                + " ,?"
                + " ,?"
                + " ,?"
                + " ,getdate()"
                + " ,?"
                + " ,?"
                + " ,?)";
        int res = db.add(sql,
                calib.getCalCode(), calib.getCreateBy(), calib.getDepId(), calib.getMeasureId(),
                calib.getModelId(), calib.getProcessId(), calib.getUnitId(), calib.getEquipConId(),
                calib.getConditionComment(), calib.getStatusCaldocId(), calib.getDueDate(), calib.getRequestBy(),
                calib.getRequestCommnet(), calib.getRequestStatus(), calib.getComment(), calib.getCalAgeId(), calib.getCalError()
        );
        return res;
    }

    @Override
    public int addNewCalibrationByMeasureIdAndPreviousCalibration(int measureId) {
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);
        //find old calib
        String sql = "select top 1 * "
                + "from EDCS_CALIBRATION t "
                + "WHERE t.APPROVE_STATUS_ON = (select max(t2.APPROVE_STATUS_ON) from EDCS_CALIBRATION t2) "
                + "AND MEASURE_ID=?";
        Map<String, Object> map = db.querySingle(sql, measureId);
        EdcsCalibration p = new EdcsCalibration();
        if (map != null) {
            p.setDepId((Integer) map.get("DEP_ID"));

            int MeasureId = (Integer) map.get("MEASURE_ID");
            p.setMeasureId(MeasureId);

            p.setModelId((Integer) map.get("MODEL_ID"));
            p.setProcessId((Integer) map.get("PROCESS_ID"));
            p.setUnitId((Integer) map.get("UNIT_ID"));
            //extended properties 
            p.setAssociateMeasure(measureList.get(MeasureId));
        } else {
            p.setMeasureId(measureId);
            //extended properties 
            p.setAssociateMeasure(measureList.get(measureId));
        }
        p.setCalCode(getRunningCode());
        p.setRequestBy("");
        p.setRequestOn(new Date());
        p.setRequestStatus("1");
        p.setRequestCommnet("");
        add(p);
        return 1;
    }

    @Override
    public void approverRequested(int calId) {
        //find old calib
        String requestApproverBy = "";
        String sql = "UPDATE EDCS_CALIBRATION SET REQUEST_APPROVER_BY=?,REQUEST_APPROVER_ON=(getdate()),REQUEST_APPROVER_STATUS=1 WHERE CAL_ID=?";
        int result = db.update(sql, requestApproverBy, calId);
    }

    @Override
    public void receivedDevice(int calId) {
        //find old calib
        String requestApproverBy = "";
        String sql = "UPDATE EDCS_CALIBRATION SET RECEIVE_STATUS_BY=?,RECEIVE_STATUS_ON=(getdate()),RECEIVE_STATUS=1 WHERE CAL_ID=?";
        int result = db.update(sql, requestApproverBy, calId);
    }

    @Override
    public List<EdcsCalibration> findAll() {
        String sql = "select * from EDCS_MAS_CALIBRATION";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsCalibration> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsCalibration p = mappingResultSetToCalibration(map);
            ret.add(p);
        }
        return ret;
    }

    @Override
    public EdcsCalibration find(int id) {
        String sql = "select * from EDCS_MAS_CALIBRATION where CAL_CODE = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsCalibration p = new EdcsCalibration();
        if (map != null) {
            p = mappingResultSetToCalibration(map);
        }
        return p;
    }

    @Override
    public List<EdcsCalibration> findByFlag(int flag) {
        String sql = "select * from EDCS_MAS_CALIBRATION where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        List<EdcsCalibration> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsCalibration p = mappingResultSetToCalibration(map);
            ret.add(p);
        }
        return ret;
    }

    @Override
    public List<EdcsCalibration> getNewAndNearExpireCalibrationMoreThanDays(int dayCountToExpired) {
        Database db = new Database("sqlServer");
        EdcsMasMeasureDAO masDao = new EdcsMasMeasureDAOImpI(db);
        //get measure Mapping data
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);

        int DayCountInverse = -dayCountToExpired;
        String sql = "select * from [EDocCalService].[dbo].[EDCS_CALIBRATION] WHERE DUE_DATE >= DATEADD(DAY,?,GETDATE()) AND MEASURE_ID IN (SELECT MEASURE_ID FROM EDCS_MAS_MEASURE WHERE FLAG_DEL=0)";
        List<Map<String, Object>> rs = db.queryList(sql, DayCountInverse);
        List<EdcsCalibration> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsCalibration temp = mappingResultSetToCalibration(map);
            temp.setAssociateMeasure(measureList.get(temp.getMeasureId()));
            ret.add(temp);
        }
        //new measure equipment
        String sqlNEW = "select MEASURE_ID from [EDocCalService].[dbo].[EDCS_MAS_MEASURE] WHERE MEASURE_ID not in (select MEASURE_ID from [EDocCalService].[dbo].[EDCS_CALIBRATION]) AND  FLAG_DEL=0";
        List<Map<String, Object>> rsNew = db.queryList(sqlNEW);
        for (Map<String, Object> mapNew : rsNew) {
            EdcsCalibration p = new EdcsCalibration();
            int MeasureId = (Integer) mapNew.get("MEASURE_ID");
            p.setMeasureId(MeasureId);
            p.setAssociateMeasure(measureList.get(MeasureId));
            ret.add(p);
        }

        return ret;
    }

    @Override
    public List<EdcsCalibration> getRequestedCalibration() {
        Database db = new Database("sqlServer");
        EdcsMasMeasureDAO masDao = new EdcsMasMeasureDAOImpI(db);
        //get measure Mapping data
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);

        String sql = "select * from EDCS_CALIBRATION WHERE DUE_DATE IS NULL  AND REQUEST_APPROVER_STATUS IS NULL AND REQUEST_STATUS = 1";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsCalibration> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsCalibration temp = mappingResultSetToCalibration(map);
            temp.setAssociateMeasure(measureList.get(temp.getMeasureId()));
            System.out.println(temp);
            ret.add(temp);
        }

        return ret;
    }

    @Override
    public List<EdcsCalibration> getApprovedDevice() {
        Database db = new Database("sqlServer");
        EdcsMasMeasureDAO masDao = new EdcsMasMeasureDAOImpI(db);
        //get measure Mapping data
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);

        String sql = "select * from EDCS_CALIBRATION WHERE DUE_DATE IS NULL  AND RECEIVE_STATUS IS NULL AND REQUEST_APPROVER_STATUS = 1";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsCalibration> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsCalibration temp = mappingResultSetToCalibration(map);
            temp.setAssociateMeasure(measureList.get(temp.getMeasureId()));
            System.out.println(temp);
            ret.add(temp);
        }

        return ret;
    }

    @Override
    public List<EdcsCalibration> getApprovedAndReceiverdDevice() {
        Database db = new Database("sqlServer");
        EdcsMasMeasureDAO masDao = new EdcsMasMeasureDAOImpI(db);
        //get measure Mapping data
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);

        String sql = "select * from EDCS_CALIBRATION WHERE DUE_DATE IS NULL  AND RECEIVE_STATUS = 1";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsCalibration> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsCalibration temp = mappingResultSetToCalibration(map);
            temp.setAssociateMeasure(measureList.get(temp.getMeasureId()));
            ret.add(temp);
        }

        return ret;
    }

    @Override
    public EdcsCalibration mappingResultSetToCalibration(Map<String, Object> map) {
        EdcsCalibration p = new EdcsCalibration();
        //get measure Mapping data
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);
        p.setCalId((Integer) map.get("CAL_ID"));
        p.setCalCode((String) map.get("CAL_CODE"));
        p.setCalAgeId((Integer) map.get("CAL_AGE_ID"));
        p.setCalError((Double) map.get("CAL_ERROR"));

        p.setDepId((Integer) map.get("DEP_ID"));

        int MeasureId = (Integer) map.get("MEASURE_ID");
        p.setMeasureId(MeasureId);

        p.setModelId((Integer) map.get("MODEL_ID"));
        p.setProcessId((Integer) map.get("PROCESS_ID"));
        p.setUnitId((Integer) map.get("UNIT_ID"));

        p.setDueDate((Date) map.get("DUE_DATE"));
        p.setEquipConId((Integer) map.get("EQUIP_CON_ID"));
        p.setStatusCaldocId((Integer) map.get("STATUS_CALDOC_ID"));
        p.setComment((String) map.get("COMMENT"));
        p.setConditionComment((String) map.get("CONDITION_COMMENT"));

        p.setApproveStatus((String) map.get("APPROVE_STATUS"));
        p.setApproveStatusBy((String) map.get("APPROVE_STATUS_BY"));
        p.setApproveStatusOn((Date) map.get("APPROVE_STATUS_ON"));

        p.setCalibrationStatus((String) map.get("CALIBRATION_STATUS"));
        p.setCalibrationStatusBy((String) map.get("CALIBRATION_STATUS_BY"));
        p.setCalibrationStatusOn((Date) map.get("CALIBRATION_STATUS_ON"));
        p.setCalibratorBy((String) map.get("CALIBRATOR_BY"));
        p.setCalibratorOn((Date) map.get("CALIBRATOR_ON"));

        p.setCreateBy((String) map.get("CREATE_BY"));
        p.setCreateOn((Date) map.get("CREATE_ON"));

        p.setReceiveStatus((String) map.get("RECEIVE_STATUS"));
        p.setReceiveStatusBy((String) map.get("RECEIVE_STATUS_BY"));
        p.setReceiveStatusOn((Date) map.get("RECEIVE_STATUS_ON"));

        p.setRequestApproverBy((String) map.get("REQUEST_APPROVER_BY"));
        p.setRequestApproverOn((Date) map.get("REQUEST_APPROVER_ON"));
        p.setRequestApproverStatus((String) map.get("REQUEST_APPROVER_STATUS"));

        p.setRequestBy((String) map.get("REQUEST_BY"));
        p.setRequestOn((Date) map.get("REQUEST_ON"));
        p.setRequestStatus((String) map.get("REQUEST_STATUS"));
        p.setRequestCommnet((String) map.get("REQUEST_COMMNET"));

        p.setReturnStatus((String) map.get("RETURN_STATUS"));
        p.setReturnStatusBy((String) map.get("RETURN_STATUS_BY"));
        p.setReturnStatusOn((Date) map.get("RETURN_STATUS_ON"));

        p.setStickerPrint((Integer) map.get("STICKER_PRINT"));
        p.setStickerStatus((String) map.get("STICKER_STATUS"));
        p.setStickerStatusBy((String) map.get("STICKER_STATUS_BY"));
        p.setStickerStatusOn((Date) map.get("STICKER_STATUS_ON"));

        //extended properties 
        p.setAssociateMeasure(measureList.get(MeasureId));
        return p;
    }

    @Override
    public String getRunningCode() {
        String code = "";
        Calendar c = new GregorianCalendar();
        String year = String.valueOf(c.get(Calendar.YEAR)).substring(2);
        code += year; //dummy
        code += 2;
        //code startwith 2 last digit of year
        year = year.substring(2);
        String sql = "SELECT right(CAL_CODE,5) AS running FROM EDCS_CALIBRATION where ( right(CAL_CODE,5) = (select max(CAST(right(CAL_CODE,5) AS int)) FROM EDCS_CALIBRATION WHERE CAL_CODE like cast(RIGHT(YEAR(GETDATE()),2) as varchar(20))+'%') ) AND (CAL_CODE like cast(RIGHT(YEAR(GETDATE()),2) as varchar(20))+'%')";
        Map<String, Object> rs = db.querySingle(sql);
        if (rs != null) {
            int currentRunning = Integer.valueOf((String) rs.get("running"));
            currentRunning++;
            String formatted = String.format("%05d", currentRunning);
            code += formatted;
        } else {
            code += "00001";
        }
        return code;
    }

}
