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
import th.co.wacoal.springtemplates.dao.EdcsMasStatusCaldocDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasStatusCaldoc;

/**
 *
 * @author admin
 */
public class EdcsMasStatusCaldocDAOImpl implements EdcsMasStatusCaldocDAO {

    private final Database db;

    public EdcsMasStatusCaldocDAOImpl(Database db) {
        this.db = db;
    }

    @Override
    public List<EdcsMasStatusCaldoc> findAll() {
        String sql = "select * from EDCS_MAS_STATUS_CALDOC";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsMasStatusCaldoc> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasStatusCaldoc p = new EdcsMasStatusCaldoc();

            p.setStatusCaldocId((int) map.get("STATUS_CALDOC_ID"));
            p.setStatusCaldocName((String) map.get("STATUS_CALDOC_NAME"));
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
        String sql = "update EDCS_MAS_STATUS_CALDOC SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE STATUS_CALDOC_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int deleteMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_STATUS_CALDOC SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE STATUS_CALDOC_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int realDeleteMutiple(Integer[] ids) {
        String group = StringUtils.join(ids, ",");
        String sql = "delete from EDCS_MAS_STATUS_CALDOC  WHERE STATUS_CALDOC_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int reuse(int id, String userId) {
        String sql = "update EDCS_MAS_STATUS_CALDOC SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE STATUS_CALDOC_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int reuseMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_STATUS_CALDOC SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE STATUS_CALDOC_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int update(EdcsMasStatusCaldoc statuscaldoc) {
        String sql = "update EDCS_MAS_STATUS_CALDOC  SET STATUS_CALDOC_NAME=?, CHANGE_BY=?,CHANGE_ON=(getdate())"
                + "where STATUS_CALDOC_ID=?";

        int rs = db.update(sql,
                statuscaldoc.getStatusCaldocName(),
                statuscaldoc.getChangeBy(),
                statuscaldoc.getStatusCaldocId());
        return rs;
    }

    @Override
    public int add(EdcsMasStatusCaldoc statuscaldoc) {
        // insert
        String sql = "INSERT INTO EDCS_MAS_STATUS_CALDOC (STATUS_CALDOC_NAME, CREATE_BY,CHANGE_BY,CREATE_ON,CHANGE_ON,FLAG_DEL)"
                + " VALUES (?,?,?,(getdate()),(getdate()),0)";
        int res = db.add(sql, statuscaldoc.getStatusCaldocName(), statuscaldoc.getCreateBy(), statuscaldoc.getChangeBy());
        return res;
    }

    @Override
    public EdcsMasStatusCaldoc find(int id) {
        String sql = "select * from EDCS_MAS_STATUS_CALDOC where STATUS_CALDOC_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasStatusCaldoc p = new EdcsMasStatusCaldoc();
        if (map != null) {
            p.setStatusCaldocId((Integer) map.get("STATUS_CALDOC_ID"));
            p.setStatusCaldocName((String) map.get("STATUS_CALDOC_NAME"));
            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
        }
        return p;
    }

    @Override
    public List<EdcsMasStatusCaldoc> findByFlag(int flag) {
        String sql = "select * from EDCS_MAS_STATUS_CALDOC where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        List<EdcsMasStatusCaldoc> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasStatusCaldoc p = new EdcsMasStatusCaldoc();

            p.setStatusCaldocId((int) map.get("STATUS_CALDOC_ID"));
            p.setStatusCaldocName((String) map.get("STATUS_CALDOC_NAME"));
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
    public int isExistCount(String name, String id) {
        if (id == null) {
            id = "0";
        }
        int idInt = Integer.parseInt(id);
        String sql = "select count(*) as count from EDCS_MAS_STATUS_CALDOC where STATUS_CALDOC_NAME=?  AND STATUS_CALDOC_ID !=?";
        Map<String, Object> map = db.querySingle(sql, name, idInt);
        int count = 0;
        if (map != null) {
            count = (int) map.get("count");
        }
        return count;
    }

}
