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
import th.co.wacoal.springtemplates.dao.EdcsMasCalpointDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasCalpoint;

/**
 *
 * @author admin
 */
public class EdcsMasCalpointDAOImpl implements EdcsMasCalpointDAO {

    private Database db;

    public EdcsMasCalpointDAOImpl(Database db) {
        this.db = db;
    }

    @Override
    public List<EdcsMasCalpoint> findAll() {
        String sql = "select * from EDCS_MAS_CALPOINT";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsMasCalpoint> ret = new ArrayList<EdcsMasCalpoint>();
        for (Map<String, Object> map : rs) {
            EdcsMasCalpoint p = new EdcsMasCalpoint();

            p.setCalpointId((int) map.get("CALPOINT_ID"));
            p.setCalpointMax((Integer) map.get("CALPOINT_MAX"));
            p.setCalpointMin((Integer) map.get("CALPOINT_MIN"));
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
        String sql = "update EDCS_MAS_CALPOINT SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE CALPOINT_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int deleteMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_CALPOINT SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE CALPOINT_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int realDeleteMutiple(Integer[] ids) {
        String group = StringUtils.join(ids, ",");
        String sql = "delete from EDCS_MAS_CALPOINT  WHERE CALPOINT_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int reuse(int id, String userId) {
        String sql = "update EDCS_MAS_CALPOINT SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE CALPOINT_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int reuseMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_CALPOINT SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE CALPOINT_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int update(EdcsMasCalpoint calpoint) {
        String sql = "update EDCS_MAS_CALPOINT  SET CALPOINT_MAX=?, CALPOINT_MIN=?, CHANGE_BY=?,CHANGE_ON=(getdate())"
                + "where CALPOINT_ID=?";

        int rs = db.update(sql,
                calpoint.getCalpointMax(),
                calpoint.getCalpointMin(),
                calpoint.getChangeBy(),
                calpoint.getCalpointId());
        return rs;
    }

    @Override
    public int add(EdcsMasCalpoint calpoint) {
        // insert
        String sql = "INSERT INTO EDCS_MAS_CALPOINT (CALPOINT_MAX, CALPOINT_MIN, CREATE_BY,CHANGE_BY,CREATE_ON,CHANGE_ON,FLAG_DEL)"
                + " VALUES (?, ?, ?,?,(getdate()),(getdate()),0)";
        int res = db.add(sql, calpoint.getCalpointMax(), calpoint.getCalpointMin(), calpoint.getCreateBy(), calpoint.getChangeBy());
        return res;
    }

    @Override
    public EdcsMasCalpoint find(int id) {
        String sql = "select * from EDCS_MAS_CALPOINT where CALPOINT_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasCalpoint p = new EdcsMasCalpoint();
        if (map != null) {
            p.setCalpointId((Integer) map.get("CALPOINT_ID"));
            p.setCalpointMax((Integer) map.get("CALPOINT_MAX"));
            p.setCalpointMin((Integer) map.get("CALPOINT_MIN"));
            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
        }
        return p;
    }

    @Override
    public List<EdcsMasCalpoint> findByFlag(int flag) {
        String sql = "select * from EDCS_MAS_CALPOINT where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        List<EdcsMasCalpoint> ret = new ArrayList<EdcsMasCalpoint>();
        for (Map<String, Object> map : rs) {
            EdcsMasCalpoint p = new EdcsMasCalpoint();

            p.setCalpointId((Integer) map.get("CALPOINT_ID"));
            p.setCalpointMax((Integer) map.get("CALPOINT_MAX"));
            p.setCalpointMin((Integer) map.get("CALPOINT_MIN"));
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
    public int isExistCount(String min, String max, String id) {
        if (id == null) {
            id = "0";
        }
        int idInt = Integer.parseInt(id);

        String sql = "select count(*) as count from EDCS_MAS_CALPOINT where CALPOINT_MIN=? AND CALPOINT_MAX=? AND CALPOINT_ID !=?";
        Map<String, Object> map = db.querySingle(sql, min, max, idInt);
        int count = 0;
        if (map != null) {
            count = (int) map.get("count");
        }
        return count;
    }

}
