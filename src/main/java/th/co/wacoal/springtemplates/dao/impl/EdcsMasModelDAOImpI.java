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
            EdcsMasModel p = new EdcsMasModel();

            p.setModelId((int) map.get("MODEL_ID"));
            p.setMeasureId((int) map.get("MEASURE_ID"));
            p.setLocationBy((String) map.get("LOCATION_BY"));
            p.setLocationReturn((String) map.get("LOCATION_RETURN"));
            p.setCerOn((String) map.get("CER_ON"));

            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
            ret.add(p);
        }
        return ret;
    }

    @Override
    public int delete(int id, String userId) {
        String sql = "update EDCS_MAS_MODEL SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE MODEL_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int deleteMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_MODEL SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE MODEL_ID IN (" + group + ")";
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
                + "CER_ON =?,MODEL_CODE=?"
                + " where MODEL_ID=?";

        int rs = db.update(sql,
                model.getChangeBy(),
                model.getMeasureId(),
                model.getLocationBy(),
                model.getLocationReturn(),
                model.getCerOn(), model.getModelCode(),
                model.getModelId()
        );
        return rs;
    }

    @Override
    public int add(EdcsMasModel model) {

        // insert
        String sql = "INSERT INTO EDCS_MAS_MODEL "
                + "("
                + " CREATE_BY,CREATE_ON,"
                + "CHANGE_BY,CHANGE_ON,"
                + "MEASURE_ID,"
                + "LOCATION_BY,LOCATION_RETURN,"
                + "CER_ON,MODEL_CODE,"
                + "FLAG_DEL)"
                + " VALUES ("
                + "?,(getdate()),"
                + "?,(getdate()),"
                + "?,?,?,?,?"
                + ",0)";
        int res = db.add(sql,
                model.getCreateBy(), model.getChangeBy(),
                model.getMeasureId(),
                model.getLocationBy(), model.getLocationReturn(),
                model.getCerOn(), model.getModelCode()
        );
        return res;
    }

    @Override
    public EdcsMasModel find(int id) {
        String sql = "select * from EDCS_MAS_MODEL where MODEL_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasModel p = new EdcsMasModel();
        if (map != null) {
            p.setModelId((int) map.get("MODEL_ID"));
            p.setMeasureId((int) map.get("MEASURE_ID"));
            p.setLocationBy((String) map.get("LOCATION_BY"));
            p.setLocationReturn((String) map.get("LOCATION_RETURN"));
            p.setCerOn((String) map.get("CER_ON"));

            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
            p.setModelCode((String) map.get("MODEL_CODE"));
        }
        return p;
    }

    @Override
    public List<EdcsMasModel> findByFlag(int flag) {
        String sql = "select * from EDCS_MAS_MODEL where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        List<EdcsMasModel> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasModel p = new EdcsMasModel();

            p.setModelId((int) map.get("MODEL_ID"));
            p.setMeasureId((int) map.get("MEASURE_ID"));
            p.setLocationBy((String) map.get("LOCATION_BY"));
            p.setLocationReturn((String) map.get("LOCATION_RETURN"));
            p.setCerOn((String) map.get("CER_ON"));
            p.setModelCode((String) map.get("MODEL_CODE"));
            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));

            ret.add(p);
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
    public List<EdcsMasModel> findByMeasureIdByFlag(int id, String flag) {

        String sql = "select * from EDCS_MAS_MODEL where FLAG_DEL=? AND MEASURE_ID=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag, id);
        List<EdcsMasModel> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasModel p = new EdcsMasModel();

            p.setModelId((int) map.get("MODEL_ID"));
            p.setMeasureId((int) map.get("MEASURE_ID"));
            p.setLocationBy((String) map.get("LOCATION_BY"));
            p.setLocationReturn((String) map.get("LOCATION_RETURN"));
            p.setCerOn((String) map.get("CER_ON"));
            p.setModelCode((String) map.get("MODEL_CODE"));
            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));

            ret.add(p);
        }
        return ret;
    }
}
