/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationAttachHeadDAO;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationAttachItemDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsCalibrationAttachHead;

/**
 *
 * @author admin
 */
public class EdcsCalibrationAttachHeadDAOImpI implements EdcsCalibrationAttachHeadDAO {

    private final Database db;

    public EdcsCalibrationAttachHeadDAOImpI(Database db) {
        this.db = db;
    }

    @Override
    public List<EdcsCalibrationAttachHead> findAll() {
        String sql = "select * from EDCS_CALIBRATION_ATTACH_HEAD";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsCalibrationAttachHead> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            ret.add(mappingResult(map));
        }
        return ret;
    }

    @Override
    public int delete(int id) {
        String sql = "delete from EDCS_CALIBRATION_ATTACH_HEAD WHERE CAL_ATTACH_HEAD_ID = ?";
        int result = db.update(sql, id);
        return result;
    }

    @Override
    public int deleteMutiple(Integer[] ids) {
        String group = StringUtils.join(ids, ",");
        String sql = "delete from EDCS_CALIBRATION_ATTACH_HEAD  WHERE CAL_ATTACH_HEAD_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int update(EdcsCalibrationAttachHead header) {

        String sql = "update EDCS_CALIBRATION_ATTACH_HEAD  "
                + "SET "
                + " CAL_ID=? ,CAL_STATE=? ,"
                + "TEMPERATURE=? ,HUMIDITY=? ,"
                + "COORDINATE=? ,ACTIVE_RANGE=?,"
                + "ACCEPTANCE =?,AB_TYPE =?  ,"
                + "CAL_DATE=(getdate())"
                + " where CAL_ATTACH_HEAD_ID=?";

        int res = db.update(sql,
                header.getCalId(), header.getCalState(),
                header.getTemperature(), header.getHumidity(),
                header.getCoordinate(), header.getActiveRange(),
                header.getAcceptance(), header.getAbType(),
                header.getCalAttachHeadId());
        return res;
    }

    @Override
    public int addAndReturnId(EdcsCalibrationAttachHead header) {
        // insert
        String sql = "INSERT INTO EDCS_CALIBRATION_ATTACH_HEAD "
                + "("
                + " CAL_ID,CAL_STATE,"
                + "TEMPERATURE,HUMIDITY,"
                + "COORDINATE,ACTIVE_RANGE,"
                + "ACCEPTANCE ,AB_TYPE ,"
                + "CAL_DATE)"
                + " VALUES ("
                + "?,?,"
                + "?,?,"
                + "?,?,"
                + "?,?,"
                + "(getdate()))";
        int rowAffected = db.add(sql,
                header.getCalId(), header.getCalState(),
                header.getTemperature(), header.getHumidity(),
                header.getCoordinate(), header.getActiveRange(),
                header.getAcceptance(), header.getAbType());

        String sqlReturnId = "SELECT CAL_ATTACH_HEAD_ID FROM EDCS_CALIBRATION_ATTACH_HEAD WHERE CAL_ID=? AND AB_TYPE=?";
        Map<String, Object> map = db.querySingle(sqlReturnId, header.getCalId(), header.getAbType());
        int id = 0;
        if (map != null) {
            EdcsCalibrationAttachHead added = mappingResult(map);
            id = added.getCalAttachHeadId();
        }

        return id;
    }

    @Override
    public EdcsCalibrationAttachHead find(int id) {
        String sql = "select * from EDCS_CALIBRATION_ATTACH_HEAD where CAL_ATTACH_HEAD_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsCalibrationAttachHead p = new EdcsCalibrationAttachHead();
        if (map != null) {
            p = mappingResult(map);
        }
        return p;
    }

    @Override
    public List<EdcsCalibrationAttachHead> findByCalibrationId(int calId) {
        String sql = "select * from EDCS_CALIBRATION_ATTACH_HEAD where CAL_ID=?";
        List<Map<String, Object>> rs = db.queryList(sql, calId);
        List<EdcsCalibrationAttachHead> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            ret.add(mappingResult(map));
        }
        return ret;
    }

    @Override
    public EdcsCalibrationAttachHead mappingResult(Map<String, Object> map) {
        EdcsCalibrationAttachHead head = new EdcsCalibrationAttachHead();
        EdcsCalibrationAttachItemDAO itemDAO = new EdcsCalibrationAttachItemDAOImpl(db);
        Integer headerId = (Integer) map.get("CAL_ATTACH_HEAD_ID");
        head.setCalAttachHeadId(headerId);
        head.setAbType((String) map.get("AB_TYPE"));
        head.setAcceptance((Double) map.get("ACCEPTANCE"));
        head.setActiveRange((String) map.get("ACTIVE_RANGE"));
        head.setCalDate((Date) map.get("CAL_DATE"));
        head.setCalId((Integer) map.get("CAL_ID"));
        head.setCalState((String) map.get("CAL_STATE"));
        head.setCoordinate((String) map.get("COORDINATE"));
        head.setHumidity((String) map.get("HUMIDITY"));
        head.setTemperature((String) map.get("TEMPERATURE"));

        head.setEdcsCalibrationAttachItemList(itemDAO.findByHeaderId(headerId));
        return head;
    }

}
