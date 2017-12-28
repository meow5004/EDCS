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
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasureGroup;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureGroupDAO;

/**
 *
 * @author admin
 */
public class EdcsMasMeasureGroupDAOImpI implements EdcsMasMeasureGroupDAO {

    private final Database db;

    public EdcsMasMeasureGroupDAOImpI(Database db) {
        this.db = db;
    }

    @Override
    public List<EdcsMasMeasureGroup> findAll() {
        String sql = "select * from EDCS_MAS_MEASURE_GROUP";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsMasMeasureGroup> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasMeasureGroup p = new EdcsMasMeasureGroup();

            p.setMeasureGroupId((int) map.get("MEASURE_GROUP_ID"));
            p.setMeasureGroupNameTh((String) map.get("MEASURE_GROUP_NAME_TH"));
            p.setMeasureGroupNameEn((String) map.get("MEASURE_GROUP_NAME_EN"));
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
        String sql = "update EDCS_MAS_MEASURE_GROUP SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE MEASURE_GROUP_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int deleteMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_MEASURE_GROUP SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE MEASURE_GROUP_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int realDeleteMutiple(Integer[] ids) {
        String group = StringUtils.join(ids, ",");
        String sql = "delete from EDCS_MAS_MEASURE_GROUP  WHERE MEASURE_GROUP_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int reuse(int id, String userId) {
        String sql = "update EDCS_MAS_MEASURE_GROUP SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE MEASURE_GROUP_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int reuseMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_MEASURE_GROUP SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE MEASURE_GROUP_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int update(EdcsMasMeasureGroup measuregroupud) {
        String sql = "update EDCS_MAS_MEASURE_GROUP  SET MEASURE_GROUP_NAME_TH =?,MEASURE_GROUP_NAME_EN =?, CHANGE_BY=?,CHANGE_ON=(getdate())"
                + "where MEASURE_GROUP_ID=?";

        int rs = db.update(sql,
                measuregroupud.getMeasureGroupNameTh(),
                measuregroupud.getMeasureGroupNameEn(),
                measuregroupud.getChangeBy(),
                measuregroupud.getMeasureGroupId());
        return rs;
    }

    @Override
    public int add(EdcsMasMeasureGroup measuregroupud) {
        // insert
        String sql = "INSERT INTO EDCS_MAS_MEASURE_GROUP (MEASURE_GROUP_NAME_TH,MEASURE_GROUP_NAME_EN, CREATE_BY,CHANGE_BY,CREATE_ON,CHANGE_ON,FLAG_DEL)"
                + " VALUES (?,?,?,?,(getdate()),(getdate()),0)";
        int res = db.add(sql, measuregroupud.getMeasureGroupNameTh(), measuregroupud.getMeasureGroupNameEn(), measuregroupud.getCreateBy(), measuregroupud.getChangeBy());
        return res;
    }

    @Override
    public EdcsMasMeasureGroup find(int id) {
        String sql = "select * from EDCS_MAS_MEASURE_GROUP where MEASURE_GROUP_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasMeasureGroup p = new EdcsMasMeasureGroup();
        if (map != null) {
            p.setMeasureGroupId((Integer) map.get("MEASURE_GROUP_ID"));
            p.setMeasureGroupNameTh((String) map.get("MEASURE_GROUP_NAME_TH"));
            p.setMeasureGroupNameEn((String) map.get("MEASURE_GROUP_NAME_EN"));
            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
        }
        return p;
    }

    @Override
    public List<EdcsMasMeasureGroup> findByFlag(int flag) {
        String sql = "select * from EDCS_MAS_MEASURE_GROUP where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        List<EdcsMasMeasureGroup> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasMeasureGroup p = new EdcsMasMeasureGroup();

            p.setMeasureGroupId((int) map.get("MEASURE_GROUP_ID"));
            p.setMeasureGroupNameTh((String) map.get("MEASURE_GROUP_NAME_TH"));
            p.setMeasureGroupNameEn((String) map.get("MEASURE_GROUP_NAME_EN"));
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
    public int isExistCount(String nameTh,String nameEn, String id) {
        if (id == null) {
            id = "0";
        }
        int idInt = Integer.parseInt(id);
        String sql = "select count(*) as count from EDCS_MAS_MEASURE_GROUP where MEASURE_GROUP_NAME_TH=? AND MEASURE_GROUP_NAME_EN=?  AND MEASURE_GROUP_ID !=?";
        Map<String, Object> map = db.querySingle(sql, nameTh,nameEn, idInt);
        int count = 0;
        if (map != null) {
            count = (int) map.get("count");
        }
        return count;
    }

}
