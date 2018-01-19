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
import th.co.wacoal.springtemplates.dao.EdcsMasProcessDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasProcess;

/**
 *
 * @author admin
 */
public class EdcsMasProcessDAOImpI implements EdcsMasProcessDAO {

    private final Database db;

    public EdcsMasProcessDAOImpI(Database db) {
        this.db = db;
    }

    @Override
    public List<EdcsMasProcess> findAll() {
        String sql = "select * from EDCS_MAS_PROCESS";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsMasProcess> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasProcess p = new EdcsMasProcess();

            p.setProcessId((int) map.get("PROCESS_ID"));
            p.setProcessCode((String) map.get("PROCESS_CODE"));
            p.setProcessBy((String) map.get("PROCESS_BY"));
            p.setProcessSubject((String) map.get("PROCESS_SUBJECT"));
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
        String sql = "update EDCS_MAS_PROCESS SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE PROCESS_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int deleteMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_PROCESS SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE PROCESS_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int realDeleteMutiple(Integer[] ids) {
        String group = StringUtils.join(ids, ",");
        String sql = "delete from EDCS_MAS_PROCESS  WHERE PROCESS_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int reuse(int id, String userId) {
        String sql = "update EDCS_MAS_PROCESS SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE PROCESS_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int reuseMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_PROCESS SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE PROCESS_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int update(EdcsMasProcess process) {
        String sql = "update EDCS_MAS_PROCESS  SET PROCESS_CODE=?,PROCESS_BY=?,PROCESS_SUBJECT=?, CHANGE_BY=?,CHANGE_ON=(getdate())"
                + "where PROCESS_ID=?";

        int rs = db.update(sql,
                process.getProcessCode(),
                process.getProcessBy(),
                process.getProcessSubject(),
                process.getChangeBy(),
                process.getProcessId());
        return rs;
    }

    @Override
    public int add(EdcsMasProcess process) {
        // insert
        String sql = "INSERT INTO EDCS_MAS_PROCESS (PROCESS_CODE,PROCESS_BY,PROCESS_SUBJECT,CREATE_BY,CHANGE_BY,CREATE_ON,CHANGE_ON,FLAG_DEL)"
                + " VALUES (?,?,?,?,?,(getdate()),(getdate()),0)";
        int res = db.add(sql,
                process.getProcessCode(),
                process.getProcessBy(),
                process.getProcessSubject(),
                process.getCreateBy(),
                process.getChangeBy());
        return res;
    }

    @Override
    public EdcsMasProcess find(int id) {
        String sql = "select * from EDCS_MAS_PROCESS where PROCESS_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasProcess p = new EdcsMasProcess();
        if (map != null) {
            p.setProcessId((Integer) map.get("PROCESS_ID"));
            p.setProcessBy((String) map.get("PROCESS_BY"));
            p.setProcessCode((String) map.get("PROCESS_CODE"));
            p.setProcessSubject((String) map.get("PROCESS_SUBJECT"));
            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
        }
        return p;
    }

    @Override
    public List<EdcsMasProcess> findByFlag(int flag) {
        String sql = "select * from EDCS_MAS_PROCESS where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        List<EdcsMasProcess> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasProcess p = new EdcsMasProcess();

            p.setProcessId((int) map.get("PROCESS_ID"));
            p.setProcessCode((String) map.get("PROCESS_CODE"));
            p.setProcessBy((String) map.get("PROCESS_BY"));
            p.setProcessSubject((String) map.get("PROCESS_SUBJECT"));
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
    public int isExistCount(EdcsMasProcess process) {

        String sql = "select count(*) as count from EDCS_MAS_PROCESS where PROCESS_CODE=?  AND PROCESS_ID !=?";
        Map<String, Object> map = db.querySingle(sql, process.getProcessCode(), process.getProcessId());
        int count = 0;
        if (map != null) {
            count = (int) map.get("count");
        }
        return count;
    }

}
