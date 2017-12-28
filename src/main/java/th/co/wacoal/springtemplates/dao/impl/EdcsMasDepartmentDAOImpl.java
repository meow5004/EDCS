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
import th.co.wacoal.springtemplates.dao.EdcsMasBranchDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasDepartmentDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasDepartment;

/**
 *
 * @author admin
 */
public class EdcsMasDepartmentDAOImpl implements EdcsMasDepartmentDAO {

    private Database db;
    private EdcsMasBranchDAO branchDAO;

    public EdcsMasDepartmentDAOImpl(Database db) {
        this.db = db;
        this.branchDAO = new EdcsMasBranchDAOImpl(db);
    }

    @Override
    public int add(EdcsMasDepartment dept) {
        String sql = "insert into EDCS_MAS_DEPARTMENT (DEP_ID, DEP_NAME_TH, DEP_NAME_EN,BRANCH_ID,CREATE_BY,CHANGE_BY,CREATE_ON,CHANGE_ON,FLAG_DEL) "
                + "VALUES (?,?,?,?,?,?,(getdate()),(getdate()),0);";

        int rs = db.add(
                sql, dept.getDepId(),
                dept.getDepNameTh(),
                dept.getDepNameEn(),
                dept.getBranchId(),
                dept.getCreateBy(),
                dept.getChangeBy());
        return rs;
    }

    @Override
    public int update(EdcsMasDepartment dept, String oldDepId) {
        String sql = "update EDCS_MAS_DEPARTMENT  SET DEP_ID=?, DEP_NAME_TH=?, DEP_NAME_EN=?,BRANCH_ID=?,CHANGE_BY=?,CHANGE_ON=(getdate())"
                + " WHERE DEP_ID = ?";

        int rs = db.update(
                sql, dept.getDepId(),
                dept.getDepNameTh(),
                dept.getDepNameEn(),
                dept.getBranchId(),
                dept.getChangeBy(),
                oldDepId);
        return rs;
    }

    @Override
    public int delete(String id, String userId) {
        String sql = "update EDCS_MAS_DEPARTMENT SET FLAG_DEL = '1',CHANGE_ON=(getdate()),CHANGE_BY=? WHERE DEP_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int deleteMutiple(String[] ids, String userId) {
        for (int i = 0; i < ids.length; i++) {
            //add ' ' if id is string
            ids[i] = String.format("%s%s%1$s", "'", ids[i]);
        }
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_DEPARTMENT SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE DEP_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int realDeleteMutiple(String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            //add ' ' if id is string
            ids[i] = String.format("%s%s%1$s", "'", ids[i]);
        }
        String group = StringUtils.join(ids, ",");
        String sql = "delete from EDCS_MAS_DEPARTMENT  WHERE DEP_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int reuse(String id, String userId) {
        String sql = "update EDCS_MAS_DEPARTMENT SET FLAG_DEL = '0',CHANGE_ON=(getdate()),CHANGE_BY=? WHERE DEP_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int reuseMutiple(String[] ids, String userId) {
        for (int i = 0; i < ids.length; i++) {
            //add ' ' if id is string
            ids[i] = String.format("%s%s%1$s", "'", ids[i]);
        }
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_DEPARTMENT SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE DEP_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public List<EdcsMasDepartment> findAll() {
        String sql = "select * from EDCS_MAS_DEPARTMENT";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsMasDepartment> ret = new ArrayList<EdcsMasDepartment>();
        for (Map<String, Object> map : rs) {
            EdcsMasDepartment p = new EdcsMasDepartment();
            p.setBranchId((int) map.get("BRANCH_ID"));
            p.setDepId((String) map.get("DEP_ID"));
            p.setDepNameTh((String) map.get("DEP_NAME_TH"));
            p.setDepNameEn((String) map.get("DEP_NAME_EN"));
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
    public EdcsMasDepartment find(String id) {
        String sql = "select * from EDCS_MAS_DEPARTMENT WHERE DEP_ID=?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasDepartment p = new EdcsMasDepartment();
        if (map != null) {
            p.setBranchId((int) map.get("BRANCH_ID"));
            p.setDepId((String) map.get("DEP_ID"));
            p.setDepNameTh((String) map.get("DEP_NAME_TH"));
            p.setDepNameEn((String) map.get("DEP_NAME_EN"));
            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
        }
        return p;
    }

    @Override
    public List<EdcsMasDepartment> findByBranchId(int id) {
        String sql = "select * from EDCS_MAS_DEPARTMENT where BRANCH_ID=?";
        List<Map<String, Object>> rs = db.queryList(sql, id);
        List<EdcsMasDepartment> ret = new ArrayList<EdcsMasDepartment>();
        for (Map<String, Object> map : rs) {
            EdcsMasDepartment p = new EdcsMasDepartment();

            p.setBranchId((int) map.get("BRANCH_ID"));
            p.setDepId((String) map.get("DEP_ID"));
            p.setDepNameTh((String) map.get("DEP_NAME_TH"));
            p.setDepNameEn((String) map.get("DEP_NAME_EN"));
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
    public List<EdcsMasDepartment> findByBranchIdByFlag(int id, String flag) {
        String sql = "select * from EDCS_MAS_DEPARTMENT where BRANCH_ID=? AND FLAG_DEL = ?";
        List<Map<String, Object>> rs = db.queryList(sql, id, flag);
        List<EdcsMasDepartment> ret = new ArrayList<EdcsMasDepartment>();
        for (Map<String, Object> map : rs) {
            EdcsMasDepartment p = new EdcsMasDepartment();

            p.setBranchId((int) map.get("BRANCH_ID"));
            p.setDepId((String) map.get("DEP_ID"));
            p.setDepNameTh((String) map.get("DEP_NAME_TH"));
            p.setDepNameEn((String) map.get("DEP_NAME_EN"));
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
    public List<EdcsMasDepartment> findByFlag(int flag) {
        String sql = "select * from EDCS_MAS_DEPARTMENT where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        List<EdcsMasDepartment> ret = new ArrayList<EdcsMasDepartment>();
        for (Map<String, Object> map : rs) {
            EdcsMasDepartment p = new EdcsMasDepartment();

            p.setBranchId((int) map.get("BRANCH_ID"));
            p.setDepId((String) map.get("DEP_ID"));
            p.setDepNameTh((String) map.get("DEP_NAME_TH"));
            p.setDepNameEn((String) map.get("DEP_NAME_EN"));
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
