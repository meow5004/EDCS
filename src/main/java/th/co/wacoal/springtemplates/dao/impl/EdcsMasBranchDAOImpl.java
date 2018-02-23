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
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasBranch;

/**
 *
 * @author admin
 */
public class EdcsMasBranchDAOImpl implements EdcsMasBranchDAO {

    private Database db;

    public EdcsMasBranchDAOImpl(Database db) {
        this.db = db;
    }

    @Override
    public List<EdcsMasBranch> findAll() {
        String sql = "select * from EDCS_MAS_BRANCH";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsMasBranch> ret = new ArrayList<EdcsMasBranch>();
        for (Map<String, Object> map : rs) {
            EdcsMasBranch p = new EdcsMasBranch();

            p.setBranchId((int) map.get("BRANCH_ID"));
            p.setBranchNameTh((String) map.get("BRANCH_NAME_TH"));
            p.setBranchNameEn((String) map.get("BRANCH_NAME_EN"));
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
        String sql = "update EDCS_MAS_BRANCH SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE BRANCH_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int deleteMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_BRANCH SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE BRANCH_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int realDeleteMutiple(Integer[] ids) {
        String group = StringUtils.join(ids, ",");
        String sql = "delete from EDCS_MAS_BRANCH  WHERE BRANCH_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int reuse(int id, String userId) {
        String sql = "update EDCS_MAS_BRANCH SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE BRANCH_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int reuseMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_BRANCH SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE BRANCH_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int update(EdcsMasBranch branch) {
        String sql = "update EDCS_MAS_BRANCH  SET BRANCH_NAME_TH=?, BRANCH_NAME_EN=?, CHANGE_BY=?,CHANGE_ON=(getdate())"
                + "where BRANCH_ID=?";

        int rs = db.update(sql,
                branch.getBranchNameTh(),
                branch.getBranchNameEn(),
                branch.getChangeBy(),
                branch.getBranchId());
        return rs;
    }

    @Override
    public int add(EdcsMasBranch branch) {
        // insert
        String sql = "INSERT INTO EDCS_MAS_BRANCH (BRANCH_NAME_TH, BRANCH_NAME_EN, CREATE_BY,CHANGE_BY,CREATE_ON,CHANGE_ON,FLAG_DEL)"
                + " VALUES (?, ?, ?,?,(getdate()),(getdate()),0)";
        int res = db.add(sql, branch.getBranchNameTh(), branch.getBranchNameEn(), branch.getCreateBy(), branch.getChangeBy());
        return res;
    }

    @Override
    public EdcsMasBranch find(int id) {
        String sql = "select * from EDCS_MAS_BRANCH where BRANCH_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasBranch p = new EdcsMasBranch();
        if (map != null) {
            p.setBranchId((Integer) map.get("BRANCH_ID"));
            p.setBranchNameTh((String) map.get("BRANCH_NAME_TH"));
            p.setBranchNameEn((String) map.get("BRANCH_NAME_EN"));
            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));

        }
        return p;
    }

    @Override
    public List<EdcsMasBranch> findByFlag(int flag) {
        String sql = "select * from EDCS_MAS_BRANCH where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        List<EdcsMasBranch> ret = new ArrayList<EdcsMasBranch>();
        for (Map<String, Object> map : rs) {
            EdcsMasBranch p = new EdcsMasBranch();

            p.setBranchId((int) map.get("BRANCH_ID"));
            p.setBranchNameTh((String) map.get("BRANCH_NAME_TH"));
            p.setBranchNameEn((String) map.get("BRANCH_NAME_EN"));
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
    public String formatFullName(String thaiName, String engName) {
        String fullName = "";
        if (thaiName != null && thaiName.trim().length() > 0) {
            if (engName != null && engName.trim().length() > 0) {
                //format thai(eng)
                fullName = thaiName + " ( " + engName + " ) ";
            } else {
                //format thai
                fullName = thaiName;
            }
        } else if (engName != null && engName.trim().length() > 0) {
            //format eng
            fullName = engName;
        }
        return fullName;
    }

    @Override
    public int isExistCount(String nameTh, String nameEn, String id) {
        if (id == null) {
            id = "0";
        }
        int idInt = Integer.parseInt(id);
        String sql = "select count(*) as count from EDCS_MAS_BRANCH where (( BRANCH_NAME_TH=? AND BRANCH_NAME_TH !='' ) OR ( BRANCH_NAME_EN=? AND BRANCH_NAME_EN != '')) AND BRANCH_ID !=?";
        Map<String, Object> map = db.querySingle(sql, nameTh, nameEn, idInt);
        int count = 0;
        if (map != null) {
            count = (int) map.get("count");
        }
        return count;
    }

}
