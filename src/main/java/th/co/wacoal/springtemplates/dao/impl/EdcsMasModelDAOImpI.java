/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao.impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import th.co.wacoal.springtemplates.dao.EdcsMasModelDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasModel;

/**
 *
 * @author admin
 */
public class EdcsMasModelDAOImpI implements EdcsMasModelDAO {

    private final Database db;

    public EdcsMasModelDAOImpI(Database db) {
        this.db = db;
    }

    @Override
    public List<EdcsMasModel> findAll() {
        String sql = "select * from EDCS_MAS_MODEL";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsMasModel> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            ret.add(mappingResultSet(map));
        }
        return ret;
    }

    @Override
    public int delete(int id, String userId) {
        String sql = "update EDCS_MAS_MODEL SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=?,FLAG_ACTIVE=0 WHERE MODEL_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int deleteMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_MODEL SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=?,FLAG_ACTIVE=0 WHERE MODEL_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int realDeleteMutiple(Integer[] ids) {
        String group = StringUtils.join(ids, ",");
        String sql = "delete from EDCS_MAS_MODEL  WHERE MODEL_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int reuse(int id, String userId) {
        String sql = "update EDCS_MAS_MODEL SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE MODEL_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int reuseMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_MODEL SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE MODEL_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int update(EdcsMasModel model) {

        String sql = "update EDCS_MAS_MODEL  "
                + "SET "
                + "CHANGE_BY=?,CHANGE_ON=(getdate()),"
                + "MEASURE_ID =?,"
                + "LOCATION_BY =?,LOCATION_RETURN =? ,"
                + "CER_NO =?,MODEL_CODE=?,"
                + "RESOLUTION=?,UNCERTAINTY=?,"
                + "DUE_DATE=?"
                + " where MODEL_ID=?";
        int rs = 0;
        try {
            PreparedStatement pstmt = db.connect.prepareStatement(sql);

            pstmt.setString(1, model.getChangeBy());
            pstmt.setInt(2, model.getMeasureId());
            pstmt.setString(3, model.getLocationBy());
            pstmt.setString(4, model.getLocationReturn());
            pstmt.setString(5, model.getcerNo());
            pstmt.setString(6, model.getModelCode());
            pstmt.setDouble(7, model.getResolution());
            pstmt.setDouble(8, model.getUncertainty());
            pstmt.setDate(9, new java.sql.Date(model.getDueDate().getTime()));
            pstmt.setInt(10, model.getModelId());

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    @Override
    public int add(EdcsMasModel model) {

        db.beginTransaction();
        int rs = 0;
        try {
            String disactivateOld = "UPDATE EDCS_MAS_MODEL SET FLAG_ACTIVE=0 WHERE MODEL_CODE=?";
            PreparedStatement pstmtDO = db.connect.prepareStatement(disactivateOld);
            pstmtDO.setString(1, model.getModelCode());
            pstmtDO.executeUpdate();
            // insert
            String sql = "INSERT INTO EDCS_MAS_MODEL "
                    + "("
                    + " CREATE_BY,CREATE_ON,"
                    + "CHANGE_BY,CHANGE_ON,"
                    + "MEASURE_ID,"
                    + "LOCATION_BY,LOCATION_RETURN,"
                    + "CER_NO,MODEL_CODE,"
                    + "RESOLUTION,UNCERTAINTY,"
                    + "DUE_DATE,"
                    + "FLAG_DEL,FLAG_ACTIVE)"
                    + " VALUES ("
                    + "?,(getdate()),"
                    + "?,(getdate()),"
                    + "?,"
                    + "?,?,"
                    + "?,?,"
                    + "?,?,"
                    + "?,"
                    + "0,1)";

            PreparedStatement pstmt = db.connect.prepareStatement(sql);
            pstmt.setString(1, model.getCreateBy());
            pstmt.setString(2, model.getChangeBy());
            pstmt.setInt(3, model.getMeasureId());
            pstmt.setString(4, model.getLocationBy());
            pstmt.setString(5, model.getLocationReturn());
            pstmt.setString(6, model.getcerNo());
            pstmt.setString(7, model.getModelCode());
            pstmt.setDouble(8, model.getResolution());
            pstmt.setDouble(9, model.getUncertainty());
            pstmt.setDate(10, new java.sql.Date(model.getDueDate().getTime()));

            pstmt.executeUpdate();
            db.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    @Override
    public void disactivate(int id) {
        String sql = "UPDATE EDCS_MAS_MODEL SET FLAG_ACTIVE=0 WHERE MODEL_ID=?";
        db.update(sql, id);
    }

    @Override
    public void activate(int id) {
        String sql = "UPDATE EDCS_MAS_MODEL SET FLAG_ACTIVE=1 WHERE MODEL_ID=?";
        db.update(sql, id);
    }

    @Override
    public EdcsMasModel find(int id) {
        String sql = "select * from EDCS_MAS_MODEL where MODEL_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasModel p = new EdcsMasModel();
        if (map != null) {
            p = mappingResultSet(map);
        }
        return p;
    }

    @Override
    public List<EdcsMasModel> findByFlag(int flag) {
        String sql = "select * from EDCS_MAS_MODEL where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        List<EdcsMasModel> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            ret.add(mappingResultSet(map));
        }
        return ret;
    }

    @Override
    public int isExistCount(String id) {
        if (id == null) {
            id = "0";
        }
        int idInt = Integer.parseInt(id);
        String sql = "select count(*) as count from EDCS_MAS_MODEL where MODEL_ID=?";
        Map<String, Object> map = db.querySingle(sql, idInt);
        int count = 0;
        if (map != null) {
            count = (int) map.get("count");
        }
        return count;
    }

    @Override
    public List<EdcsMasModel> findByMeasureIdByFlag(int id, int flag) {

        String sql = "select * from EDCS_MAS_MODEL where FLAG_DEL=? AND MEASURE_ID=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag, id);
        List<EdcsMasModel> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            ret.add(mappingResultSet(map));
        }
        return ret;
    }

    @Override
    public List<EdcsMasModel> findByMeasureGroupIdByFlag(int id, int flag) {

        String sql = "SELECT * "
                + "  FROM EDCS_MAS_MODEL model join (select MEASURE_ID,MEASURE_GROUP_ID from EDCS_MAS_MEASURE) measure on model.MEASURE_ID=measure.MEASURE_ID "
                + "  WHERE MEASURE_GROUP_ID = ? AND FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, id, flag);
        List<EdcsMasModel> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            ret.add(mappingResultSet(map));
        }
        return ret;
    }

    @Override
    public EdcsMasModel mappingResultSet(Map<String, Object> map) {
        EdcsMasModel p = new EdcsMasModel();
        p.setModelId((int) map.get("MODEL_ID"));
        p.setMeasureId((int) map.get("MEASURE_ID"));
        p.setLocationBy((String) map.get("LOCATION_BY"));
        p.setLocationReturn((String) map.get("LOCATION_RETURN"));
        p.setcerNo((String) map.get("CER_NO"));
        p.setModelCode((String) map.get("MODEL_CODE"));
        p.setCreateBy((String) map.get("CREATE_BY"));
        p.setCreateOn((Date) map.get("CREATE_ON"));
        p.setChangeBy((String) map.get("CHANGE_BY"));
        p.setChangeOn((Date) map.get("CHANGE_ON"));
        p.setFlagDel((String) map.get("FLAG_DEL"));

        p.setFlagActive((String) map.get("FLAG_ACTIVE"));
        p.setResolution((Double) map.get("RESOLUTION"));
        p.setUncertainty((Double) map.get("UNCERTAINTY"));
        p.setDueDate((Date) map.get("DUE_DATE"));
        return p;
    }
}
