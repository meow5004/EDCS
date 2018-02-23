/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao.impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationAttachHeadDAO;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasCalageDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasDepartmentDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasEquipconDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureUnitDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasModelDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasProcessDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasStatusCaldocDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasUserDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsCalibration;
import th.co.wacoal.springtemplates.domain.EdcsMasCalage;
import th.co.wacoal.springtemplates.domain.EdcsMasDepartment;
import th.co.wacoal.springtemplates.domain.EdcsMasEquipcon;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasure;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasureUnit;
import th.co.wacoal.springtemplates.domain.EdcsMasModel;
import th.co.wacoal.springtemplates.domain.EdcsMasProcess;
import th.co.wacoal.springtemplates.domain.EdcsMasStatusCaldoc;
import th.co.wacoal.springtemplates.domain.EdcsMasUser;
import th.co.wacoal.springtemplates.domain.calibrationDeviceCheckModel;
import th.co.wacoal.springtemplates.domain.calibrationRequestModel;

/**
 *
 * @author admin
 */
public class EdcsCalibrationDAOImpI implements EdcsCalibrationDAO {

    private Database db;

    EdcsMasMeasureDAO masDao = null;
    EdcsMasCalageDAO calageDAO = null;
    EdcsMasStatusCaldocDAO statusCalDocDAO = null;
    EdcsMasEquipconDAO equipConDAO = null;
    EdcsMasMeasureUnitDAO measureUnitDAO = null;
    EdcsMasProcessDAO processDAO = null;
    EdcsMasModelDAO modelDAO = null;
    EdcsMasDepartmentDAO depDAO = null;
    EdcsCalibrationAttachHeadDAO headDAO = null;
    EdcsMasUserDAO userDAO = null;

    public EdcsCalibrationDAOImpI(Database db) {
        this.db = db;

        masDao = new EdcsMasMeasureDAOImpI(db);
        calageDAO = new EdcsMasCalageDAOImpI(db);
        statusCalDocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        equipConDAO = new EdcsMasEquipconDAOImpl(db);
        measureUnitDAO = new EdcsMasMeasureUnitDAOImpl(db);
        processDAO = new EdcsMasProcessDAOImpI(db);
        modelDAO = new EdcsMasModelDAOImpI(db);
        depDAO = new EdcsMasDepartmentDAOImpl(db);
        headDAO = new EdcsCalibrationAttachHeadDAOImpI(db);
        userDAO = new EdcsMasUserDAOImpI(db);
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
                + " ,[REQUEST_COMMENT]"
                + " ,[REQUEST_STATUS]"
                + " ,[REQUEST_APPROVER_BY]"
                + " ,[REQUEST_APPROVER_STATUS]"
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
                calib.getRequestComment(), calib.getRequestStatus(), calib.getRequestApproverBy(), calib.getRequestApproverStatus(), calib.getComment(), calib.getCalAgeId(), calib.getCalError()
        );
        return res;
    }

    @Override
    public int addNewCalibrationCombineWithPreviousCalibrationIfAny(EdcsCalibration baseCalib) {
        int measureId = baseCalib.getMeasureId();
        //int measureId, String userId, String approverId
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);
        //find old calib
        String sql = "select top 1 * "
                + "from EDCS_CALIBRATION t "
                + "WHERE t.APPROVE_STATUS_ON = (select max(t2.APPROVE_STATUS_ON) from EDCS_CALIBRATION t2) "
                + "AND MEASURE_ID=?";
        Map<String, Object> map = db.querySingle(sql, measureId);
        EdcsCalibration p = baseCalib;
        if (map != null) {
            p.setDepId((String) map.get("DEP_ID"));
            p.setModelId((Integer) map.get("MODEL_ID"));
            p.setProcessId((Integer) map.get("PROCESS_ID"));
            p.setUnitId((Integer) map.get("UNIT_ID"));
            //extended properties 
            p.setAssociateMeasure(measureList.get(measureId));
        } else {
            p.setMeasureId(measureId);
            //extended properties 
            p.setAssociateMeasure(measureList.get(measureId));
            p.setDepId(p.getAssociateMeasure().getDepId());
        }
        p.setCalCode(getRunningCode());

        p.setRequestOn(new Date());

        p.setRequestApproverStatus("0");

        add(p);
        return 1;
    }

    @Override
    public void saveCalibrationHeader(EdcsCalibration calibration) {
        String sql = "UPDATE EDCS_CALIBRATION SET "
                + "CALIBRATION_STATUS_BY=?"
                + ",MEASURE_ID=?"
                + ",MODEL_ID=?"
                + ",PROCESS_ID=?"
                + ",UNIT_ID=?"
                + ",EQUIP_CON_ID=?"
                + ",REQUEST_COMMENT=?"
                + ",CAL_ERROR=?"
                + ",CALIBRATION_STATUS_ON=(getdate())"
                + ",CALIBRATION_STATUS=1"
                + ",CALIBRATOR_BY=? "
                + ", APPROVE_STATUS_BY=?"
                + ",CALIBRATION_LOCATION=?"
                + " WHERE CAL_ID=?";
        int result = db.update(sql,
                calibration.getCalibrationStatusBy(),
                calibration.getMeasureId(),
                calibration.getModelId(),
                calibration.getProcessId(),
                calibration.getUnitId(),
                calibration.getEquipConId(),
                calibration.getRequestComment(),
                calibration.getCalError(),
                calibration.getCalibratorBy(),
                calibration.getApproveStatusBy(),
                calibration.getCalibrationLocation(),
                calibration.getCalId());
    }

    @Override
    public void finalizeDataAndMarkCalibrationAsComplete(EdcsCalibration calib) {
        String findCalage = "select CAL_AGE FROM EDCS_MAS_CALAGE WHERE CAL_AGE_ID=?";
        Double calageYear = 0.0;
        Map<String, Object> map = db.querySingle(findCalage, calib.getCalAgeId());
        if (map != null) {
            calageYear = (double) map.get("CAL_AGE");
        }
        //find old calib
        String sql = "UPDATE EDCS_CALIBRATION SET STATUS_CALDOC_ID=?,"
                + "COMMENT=?,"
                + "CALIBRATION_ATTACH_STATUS=1,"
                + "CALIBRATION_ATTACH_STATUS_BY=?,"
                + "CALIBRATION_ATTACH_STATUS_ON=(getdate()),"
                + "CALIBRATOR_ON=(getdate()),"
                + "CAL_AGE_ID=?,"
                + "DUE_DATE=DATEADD(year," + calageYear.intValue() + ",getdate()) "
                + "WHERE CAL_ID=?";
        try {
            PreparedStatement pstmt = db.connect.prepareStatement(sql);

            pstmt.setInt(1, calib.getStatusCaldocId());
            pstmt.setString(2, calib.getComment());
            pstmt.setString(3, calib.getCalibrationAttachStatusBy());
            pstmt.setInt(4, calib.getCalAgeId());
            pstmt.setInt(5, calib.getCalId());
            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void markCalibrationForApproval(int thisCalId, String approverId) {
        String sql = "";
        if (approverId != null && approverId.length() > 0) {
            sql = "UPDATE EDCS_CALIBRATION SET APPROVE_STATUS=0,APPROVE_STATUS_BY=? WHERE CAL_ID=?";
            db.update(sql, thisCalId, approverId);
        } else {
            sql = "UPDATE EDCS_CALIBRATION SET APPROVE_STATUS=0 WHERE CAL_ID=?";
            db.update(sql, thisCalId);
        }
    }

    @Override
    public void disapproveLabResult(int calId, String userId) {
        String sql = "UPDATE EDCS_CALIBRATION SET APPROVE_STATUS=4,APPROVE_STATUS_BY =? WHERE CAL_ID=?";
        int result = db.update(sql, userId, calId);
    }

    @Override
    public void approveLabResult(int calId, String userId) {
        db.beginTransaction();
        String deactivateOldCalibration = "update EDCS_CALIBRATION set FLAG_ACTIVE=0 where MEASURE_ID = (select MEASURE_ID FROM EDCS_CALIBRATION where CAL_ID=?)";
        db.update(deactivateOldCalibration, calId);
        //deactivate old and activate lastest
        String sql = "update EDCS_CALIBRATION "
                + "set "
                + "APPROVE_STATUS=1,"
                + "FLAG_ACTIVE=1,"
                + "APPROVE_STATUS_ON=getdate(),"
                + "APPROVE_STATUS_BY=? "
                + "WHERE CAL_ID=?";
        db.update(sql, userId, calId);
        db.commit();
    }

    @Override
    public void approverRequested(calibrationRequestModel approvingReqModel) {
        String reqComment = approvingReqModel.getRequestComment();
        String approver = approvingReqModel.getApproverId();
        int calId = approvingReqModel.getCalId();
        String sql = "UPDATE EDCS_CALIBRATION SET REQUEST_COMMENT=?,REQUEST_APPROVER_BY=?,REQUEST_APPROVER_ON=(getdate()),REQUEST_APPROVER_STATUS=1 WHERE CAL_ID=?";
        int result = db.update(sql, reqComment, approver, calId);
    }

    @Override
    public void receivedDevice(calibrationDeviceCheckModel recModel) {
        //find old calib
        String sql = "UPDATE EDCS_CALIBRATION SET RECEIVE_STATUS_BY=?,RECEIVE_STATUS_ON=(getdate()),RECEIVE_STATUS=1,EQUIP_CON_ID=?,CONDITION_COMMENT=? WHERE CAL_ID=?";
        int result = db.update(sql, recModel.getInspector(), recModel.getEquipConId(), recModel.getConditionComment(), recModel.getCalId());
    }

    @Override
    public void printedStickerCheck(int calId, String userId) {
        String stickerStatusBy = userId;
        String sql = "UPDATE EDCS_CALIBRATION SET STICKER_STATUS=1,STICKER_STATUS_BY=?,STICKER_STATUS_ON=(getdate()) WHERE CAL_ID=?";
        int result = db.update(sql, stickerStatusBy, calId);
    }

    @Override
    public void returnedDeviceCheck(int calId, String userId) {
        String returnBy = userId;
        String sql = "UPDATE EDCS_CALIBRATION SET RETURN_STATUS_BY=?,RETURN_STATUS_ON=(getdate()),RETURN_STATUS=1 WHERE CAL_ID=?";
        int result = db.update(sql, returnBy, calId);
    }

    @Override
    public void returnedDeviceCheck(calibrationDeviceCheckModel checkedModel) {
        String sql = "UPDATE EDCS_CALIBRATION SET ,EQUIP_CON_ID=?,CONDITION_COMMENT=?,RETURN_STATUS_BY=?,RETURN_STATUS_ON=(getdate()),RETURN_STATUS=1 WHERE CAL_ID=?";
        int result = db.update(sql, checkedModel.getEquipConId(), checkedModel.getConditionComment(), checkedModel.getInspector());
    }

    @Override
    public List<EdcsCalibration> findAll() {
        String sql = "select * from EDCS_CALIBRATION";
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
        String sql = "select * from EDCS_CALIBRATION where CAL_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsCalibration p = new EdcsCalibration();
        if (map != null) {
            p = mappingResultSetToCalibration(map);
        }
        return p;
    }

    @Override
    public EdcsCalibration findByCode(int id) {
        String sql = "select * from EDCS_CALIBRATION where CAL_CODE = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsCalibration p = new EdcsCalibration();
        if (map != null) {
            p = mappingResultSetToCalibration(map);
        }
        return p;
    }

    @Override
    public List<EdcsCalibration> findByFlag(int flag) {
        String sql = "select * from EDCS_CALIBRATION where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        List<EdcsCalibration> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsCalibration p = mappingResultSetToCalibration(map);
            ret.add(p);
        }
        return ret;
    }

    @Override
    public List<EdcsCalibration> getNewAndNearExpireCalibration(int dayCountToExpired) {
        Database db = new Database("sqlServer");
        EdcsMasMeasureDAO masDao = new EdcsMasMeasureDAOImpI(db);
        EdcsMasDepartmentDAO depDAO = new EdcsMasDepartmentDAOImpl(db);
        //get  Mapping data
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);
        Map<String, EdcsMasDepartment> depList = depDAO.findByFlagListMappingById(0);

        //select latest old calibration from flag
        String sql = "SELECT * "
                + "FROM EDCS_CALIBRATION calib "
                + "WHERE CAST(DUE_DATE AS Date) <= DATEADD(DAY,?,GETDATE()) "
                + "AND MEASURE_ID IN (SELECT MEASURE_ID FROM EDCS_MAS_MEASURE WHERE FLAG_DEL=0 ) "
                + "AND FLAG_ACTIVE=1 ";
        List<Map<String, Object>> rs = db.queryList(sql, dayCountToExpired);
        List<EdcsCalibration> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsCalibration temp = mappingResultSetToCalibration(map);
            temp.setAssociateMeasure(measureList.get(temp.getMeasureId()));
            temp.setDepId(temp.getAssociateMeasure().getDepId());
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
            p.setDepId(p.getAssociateMeasure().getDepId());
            p.setAssociateDep(depList.get(p.getAssociateMeasure().getDepId()));
            ret.add(p);
        }

        return ret;
    }

    @Override
    public List<EdcsCalibration> getRequestedApprover(String approverId) {
        Database db = new Database("sqlServer");
        EdcsMasMeasureDAO masDao = new EdcsMasMeasureDAOImpI(db);
        //get measure Mapping data
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);

        String sql = "select * from EDCS_CALIBRATION WHERE DUE_DATE IS NULL  AND REQUEST_APPROVER_BY=? AND REQUEST_APPROVER_STATUS =0 AND REQUEST_STATUS = 1";
        List<Map<String, Object>> rs = db.queryList(sql, approverId);
        List<EdcsCalibration> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsCalibration temp = mappingResultSetToCalibration(map);
            temp.setAssociateMeasure(measureList.get(temp.getMeasureId()));
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
    public List<EdcsCalibration> listNonFinishCalibration() {
        Database db = new Database("sqlServer");
        EdcsMasMeasureDAO masDao = new EdcsMasMeasureDAOImpI(db);
        //get measure Mapping data
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);

        String sql = "select * from EDCS_CALIBRATION WHERE RECEIVE_STATUS =1  AND APPROVE_STATUS IS NULL";
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
    public List<EdcsCalibration> listDisapprovedCalibration() {
        Database db = new Database("sqlServer");
        EdcsMasMeasureDAO masDao = new EdcsMasMeasureDAOImpI(db);
        //get measure Mapping data
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);

        String sql = "select * from EDCS_CALIBRATION WHERE  APPROVE_STATUS =4";
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
    public List<EdcsCalibration> listFinishCalibrationWaitForApproval(String approverId) {
        Database db = new Database("sqlServer");
        EdcsMasMeasureDAO masDao = new EdcsMasMeasureDAOImpI(db);
        //get measure Mapping data
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);

        String sql = "select * from EDCS_CALIBRATION WHERE APPROVE_STATUS =0 AND APPROVE_STATUS_BY=?";
        List<Map<String, Object>> rs = db.queryList(sql, approverId);
        List<EdcsCalibration> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsCalibration temp = mappingResultSetToCalibration(map);
            temp.setAssociateMeasure(measureList.get(temp.getMeasureId()));
            ret.add(temp);
        }

        return ret;
    }

    @Override
    public List<EdcsCalibration> listLabAppovedCalibration() {
        Database db = new Database("sqlServer");
        EdcsMasMeasureDAO masDao = new EdcsMasMeasureDAOImpI(db);
        //get measure Mapping data
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);

        String sql = "select * from EDCS_CALIBRATION WHERE APPROVE_STATUS =1 AND ( STICKER_STATUS=0 OR STICKER_STATUS IS NULL )";
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
    public List<EdcsCalibration> listStickeredDevicesCalibration() {
        Database db = new Database("sqlServer");
        EdcsMasMeasureDAO masDao = new EdcsMasMeasureDAOImpI(db);
        //get measure Mapping data
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);

        String sql = "select * from EDCS_CALIBRATION WHERE STICKER_STATUS=1 AND ( RETURN_STATUS=0 OR RETURN_STATUS IS NULL )";
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
    public List<EdcsCalibration> getCalibrationListInSystem() {
        Database db = new Database("sqlServer");
        EdcsMasMeasureDAO masDao = new EdcsMasMeasureDAOImpI(db);
        //get measure Mapping data
        Map<Integer, EdcsMasMeasure> measureList = masDao.findByFlagListMappingById(0);

        String sql = "select * from EDCS_CALIBRATION WHERE REQUEST_STATUS = 1";
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

        Integer MeasureId = map.get("MEASURE_ID") != null ? (Integer) map.get("MEASURE_ID") : null;
        Integer calageId = map.get("CAL_AGE_ID") != null ? (Integer) map.get("CAL_AGE_ID") : null;
        Integer statDocId = map.get("STATUS_CALDOC_ID") != null ? (Integer) map.get("STATUS_CALDOC_ID") : null;
        Integer equipConId = map.get("EQUIP_CON_ID") != null ? (Integer) map.get("EQUIP_CON_ID") : null;
        Integer unitId = map.get("UNIT_ID") != null ? (Integer) map.get("UNIT_ID") : null;
        Integer processId = map.get("PROCESS_ID") != null ? (Integer) map.get("PROCESS_ID") : null;
        Integer modelId = map.get("MODEL_ID") != null ? (Integer) map.get("MODEL_ID") : null;
        String depId = map.get("DEP_ID") != null ? (String) map.get("DEP_ID") : null;
//set various user
        String requestByUser = map.get("REQUEST_BY") != null ? (String) map.get("REQUEST_BY") : null;
        String calibrationAttachStatusByUser = map.get("CALIBRATION_ATTACH_STATUS_BY") != null ? (String) map.get("CALIBRATION_ATTACH_STATUS_BY") : null;
        String requestApproverByUser = map.get("REQUEST_APPROVER_BY") != null ? (String) map.get("REQUEST_APPROVER_BY") : null;
        String calibratorByUser = map.get("CALIBRATOR_BY") != null ? (String) map.get("CALIBRATOR_BY") : null;
        String receiveStatusByUser = map.get("RECEIVE_STATUS_BY") != null ? (String) map.get("RECEIVE_STATUS_BY") : null;
        String calibrationStatusByUser = map.get("CALIBRATION_STATUS_BY") != null ? (String) map.get("CALIBRATION_STATUS_BY") : null;
        String approveStatusByUser = map.get("APPROVE_STATUS_BY") != null ? (String) map.get("APPROVE_STATUS_BY") : null;
        String stickerStatusByUser = map.get("STICKER_STATUS_BY") != null ? (String) map.get("STICKER_STATUS_BY") : null;
        String returnStatusByUser = map.get("RETURN_STATUS_BY") != null ? (String) map.get("RETURN_STATUS_BY") : null;
//get Mapping
        Map<String, EdcsMasUser> userMap = userDAO.findAllMappingKeyByEmpId();

//set user
        if (requestByUser != null) {
            p.setAssociateRequestByUser(userMap.get(requestByUser));
        }

        if (calibrationAttachStatusByUser != null) {
            p.setAssociateCalibrationAttachStatusByUser(userMap.get(calibrationAttachStatusByUser));
        }

        if (requestApproverByUser != null) {
            p.setAssociateRequestApproverByUser(userMap.get(requestApproverByUser));
        }

        if (calibratorByUser != null) {
            p.setAssociateCalibratorByUser(userMap.get(calibratorByUser));
        }

        if (receiveStatusByUser != null) {
            p.setAssociateReceiveStatusByUser(userMap.get(receiveStatusByUser));
        }

        if (calibrationStatusByUser != null) {
            p.setAssociateCalibrationStatusByUser(userMap.get(calibrationStatusByUser));
        }

        if (approveStatusByUser != null) {
            p.setAssociateApproveStatusByUser(userMap.get(approveStatusByUser));
        }

        if (stickerStatusByUser != null) {
            p.setAssociateStickerStatusByUser(userMap.get(stickerStatusByUser));
        }

        if (returnStatusByUser != null) {
            p.setAssociateReturnStatusByUser(userMap.get(returnStatusByUser));
        }
//end set user
        p.setCalId((Integer) map.get("CAL_ID"));
        p.setCalCode((String) map.get("CAL_CODE"));
        p.setCalAgeId(calageId);
        p.setCalError((Double) map.get("CAL_ERROR"));

        p.setDepId(depId);

        p.setMeasureId(MeasureId);

        p.setModelId(modelId);
        p.setProcessId(processId);
        p.setUnitId(unitId);

        p.setCalibrationLocation((String) map.get("CALIBRATION_LOCATION"));

        p.setDueDate((Date) map.get("DUE_DATE"));
        p.setEquipConId(equipConId);
        p.setStatusCaldocId(statDocId);
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
        p.setRequestComment((String) map.get("REQUEST_COMMENT"));

        p.setReturnStatus((String) map.get("RETURN_STATUS"));
        p.setReturnStatusBy((String) map.get("RETURN_STATUS_BY"));
        p.setReturnStatusOn((Date) map.get("RETURN_STATUS_ON"));

        p.setStickerPrint((Integer) map.get("STICKER_PRINT"));
        p.setStickerStatus((String) map.get("STICKER_STATUS"));
        p.setStickerStatusBy((String) map.get("STICKER_STATUS_BY"));
        p.setStickerStatusOn((Date) map.get("STICKER_STATUS_ON"));

        p.setCalibrationAttachStatus((String) map.get("CALIBRATION_ATTACH_STATUS"));
        p.setCalibrationAttachStatusBy((String) map.get("CALIBRATION_ATTACH_STATUS_BY"));
        p.setCalibrationAttachStatusOn((Date) map.get("CALIBRATION_ATTACH_STATUS_ON"));
        //extended properties 
        if (MeasureId != null) {
            p.setAssociateMeasure(masDao.find(MeasureId));
        } else {
            p.setAssociateMeasure(new EdcsMasMeasure());
        }
        if (calageId != null) {
            p.setAssociateCalage(calageDAO.find(calageId));
        } else {
            p.setAssociateCalage(new EdcsMasCalage());
        }

        if (statDocId != null) {
            p.setAssociateStatusCaldoc(statusCalDocDAO.find(statDocId));
        } else {
            p.setAssociateStatusCaldoc(new EdcsMasStatusCaldoc());
        }

        if (equipConId != null) {
            p.setAssociateEquipCon(equipConDAO.find(equipConId));
        } else {
            p.setAssociateEquipCon(new EdcsMasEquipcon());
        }

        if (unitId != null) {
            p.setAssociateUnit(measureUnitDAO.find(unitId));
        } else {
            p.setAssociateUnit(new EdcsMasMeasureUnit());
        }

        if (processId != null) {
            p.setAssociateProcess(processDAO.find(processId));
        } else {
            p.setAssociateProcess(new EdcsMasProcess());
        }

        if (modelId != null) {
            EdcsMasModel thisModel = modelDAO.find(modelId);
            p.setAssociateModel(thisModel);
            if (thisModel != null) {
                p.setAssociateModelMeasure(masDao.find(thisModel.getMeasureId()));
            } else {
                p.setAssociateModelMeasure(new EdcsMasMeasure());
            }

        } else {
            p.setAssociateModel(new EdcsMasModel());
            p.setAssociateModelMeasure(new EdcsMasMeasure());
        }

        if (depId != null) {
            p.setAssociateDep(depDAO.find(depId));
        } else {
            p.setAssociateDep(new EdcsMasDepartment());
        }

        p.setEdcsCalibrationAttachHeadList(headDAO.findByCalibrationId(p.getCalId()));
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
