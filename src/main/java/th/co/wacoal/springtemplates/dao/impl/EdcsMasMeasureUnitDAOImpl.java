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
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureUnitDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasureUnit;

/**
 *
 * @author admin
 */
public class EdcsMasMeasureUnitDAOImpl implements EdcsMasMeasureUnitDAO {

    private Database db;

    public EdcsMasMeasureUnitDAOImpl(Database db) {
        this.db = db;
    }

    @Override
    public List<EdcsMasMeasureUnit> findAll() {
        String sql = "select * from EDCS_MAS_MEASURE_UNIT";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsMasMeasureUnit> ret = new ArrayList<EdcsMasMeasureUnit>();
        for (Map<String, Object> map : rs) {
            EdcsMasMeasureUnit p = new EdcsMasMeasureUnit();

            p.setUnitId((int) map.get("UNIT_ID"));
            p.setUnitNameTh((String) map.get("UNIT_NAME_TH"));
            p.setUnitNameEn((String) map.get("UNIT_NAME_EN"));
            p.setUnitShortTh((String) map.get("UNIT_SHORT_TH"));
            p.setUnitShortEn((String) map.get("UNIT_SHORT_EN"));
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
        String sql = "update EDCS_MAS_MEASURE_UNIT SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE UNIT_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int deleteMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_MEASURE_UNIT SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE UNIT_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int realDeleteMutiple(Integer[] ids) {
        String group = StringUtils.join(ids, ",");
        String sql = "delete from EDCS_MAS_MEASURE_UNIT  WHERE UNIT_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int reuse(int id, String userId) {
        String sql = "update EDCS_MAS_MEASURE_UNIT SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE UNIT_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int reuseMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_MEASURE_UNIT SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE UNIT_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int update(EdcsMasMeasureUnit measureUnit) {
        String sql = "update EDCS_MAS_MEASURE_UNIT  SET UNIT_NAME_TH=?, UNIT_NAME_EN=?,UNIT_SHORT_TH=?,UNIT_SHORT_EN=?, CHANGE_BY=?,CHANGE_ON=(getdate())"
                + "where UNIT_ID=?";

        int rs = db.update(sql,
                measureUnit.getUnitNameTh(),
                measureUnit.getUnitNameEn(),
                measureUnit.getUnitShortTh(),
                measureUnit.getUnitShortEn(),
                measureUnit.getChangeBy(),
                measureUnit.getUnitId());
        return rs;
    }

    @Override
    public int add(EdcsMasMeasureUnit measureUnit) {
        // insert
        String sql = "INSERT INTO EDCS_MAS_MEASURE_UNIT (UNIT_NAME_TH, UNIT_NAME_EN,UNIT_SHORT_TH,UNIT_SHORT_EN, CREATE_BY,CHANGE_BY,CREATE_ON,CHANGE_ON,FLAG_DEL)"
                + " VALUES (?,?,?,?,?,?,(getdate()),(getdate()),0)";
        int res = db.add(sql,
                measureUnit.getUnitNameTh(),
                measureUnit.getUnitNameEn(),
                measureUnit.getUnitShortTh(),
                measureUnit.getUnitShortEn(),
                measureUnit.getCreateBy(),
                measureUnit.getChangeBy());
        return res;
    }

    @Override
    public EdcsMasMeasureUnit find(int id) {
        String sql = "select * from EDCS_MAS_MEASURE_UNIT where UNIT_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasMeasureUnit p = new EdcsMasMeasureUnit();
        if (map != null) {
            p.setUnitId((int) map.get("UNIT_ID"));
            p.setUnitNameTh((String) map.get("UNIT_NAME_TH"));
            p.setUnitNameEn((String) map.get("UNIT_NAME_EN"));
            p.setUnitShortTh((String) map.get("UNIT_SHORT_TH"));
            p.setUnitShortEn((String) map.get("UNIT_SHORT_EN"));
            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
        }
        return p;
    }

    @Override
    public List<EdcsMasMeasureUnit> findByFlag(int flag) {
        String sql = "select * from EDCS_MAS_MEASURE_UNIT where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        List<EdcsMasMeasureUnit> ret = new ArrayList<EdcsMasMeasureUnit>();
        for (Map<String, Object> map : rs) {
            EdcsMasMeasureUnit p = new EdcsMasMeasureUnit();

            p.setUnitId((int) map.get("UNIT_ID"));
            p.setUnitNameTh((String) map.get("UNIT_NAME_TH"));
            p.setUnitNameEn((String) map.get("UNIT_NAME_EN"));
            p.setUnitShortTh((String) map.get("UNIT_SHORT_TH"));
            p.setUnitShortEn((String) map.get("UNIT_SHORT_EN"));
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
    public String formatFullName(String thaiName,String shortThaiName, String engName,String shortEngName) {
        String fullName = "";
        if (thaiName != null && thaiName.trim().length() > 0) {
            if (engName != null && engName.trim().length() > 0) {
                //format thai(eng)
                fullName = thaiName + " ( " + shortThaiName + " ) " + engName + " ( " + shortEngName + " ) ";
            } else {
                //format thai
                fullName = thaiName + " ( " + shortThaiName + " ) ";
            }
        } else if (engName != null && engName.trim().length() > 0) {
            //format eng
            fullName = engName + " ( " + shortEngName + " ) ";
        }
        return fullName;
    }
    
    @Override
    public int isExistCount(String nameTh, String nameEn, String id) {
        if (id == null) {
            id = "0";
        }
        int idInt = Integer.parseInt(id);

        String sql = "select count(*) as count from EDCS_MAS_MEASURE_UNIT where (( UNIT_NAME_TH=? AND  UNIT_NAME_TH !='') OR (UNIT_NAME_EN=? AND  UNIT_NAME_EN !='' )) AND UNIT_ID !=?";
        Map<String, Object> map = db.querySingle(sql, nameTh, nameEn, idInt);
        int count = 0;
        if (map != null) {
            count = (int) map.get("count");
        }
        return count;
    }
}
