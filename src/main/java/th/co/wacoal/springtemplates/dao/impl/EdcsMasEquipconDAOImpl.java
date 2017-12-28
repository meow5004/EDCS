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
import th.co.wacoal.springtemplates.dao.EdcsMasEquipconDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasEquipcon;

/**
 *
 * @author admin
 */
public class EdcsMasEquipconDAOImpl implements EdcsMasEquipconDAO {

    private Database db;

    public EdcsMasEquipconDAOImpl(Database db) {
        this.db = db;
    }

    @Override
    public List<EdcsMasEquipcon> findAll() {
        String sql = "select * from EDCS_MAS_EQUIPCON";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsMasEquipcon> ret = new ArrayList<EdcsMasEquipcon>();
        for (Map<String, Object> map : rs) {
            EdcsMasEquipcon p = new EdcsMasEquipcon();

            p.setEquipConId((int) map.get("EQUIP_CON_ID"));
            p.setEquipConNameTh((String) map.get("EQUIP_CON_NAME_TH"));
            p.setEquipConNameEn((String) map.get("EQUIP_CON_NAME_EN"));
            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
            p.setFlagComment((String) map.get("FLAG_COMMENT"));
           
            ret.add(p);
        }
        return ret;
    }

    @Override
    public int delete(int id, String userId) {
        String sql = "update EDCS_MAS_EQUIPCON SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE EQUIP_CON_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int deleteMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_EQUIPCON SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE EQUIP_CON_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int realDeleteMutiple(Integer[] ids) {
        String group = StringUtils.join(ids, ",");
        String sql = "delete from EDCS_MAS_EQUIPCON  WHERE EQUIP_CON_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int reuse(int id, String userId) {
        String sql = "update EDCS_MAS_EQUIPCON SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=?,FLAG_COMMENT='' WHERE EQUIP_CON_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int reuseMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_EQUIPCON SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=?,FLAG_COMMENT='' WHERE EQUIP_CON_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int update(EdcsMasEquipcon equipcon) {
        String sql = "update EDCS_MAS_EQUIPCON  SET EQUIP_CON_NAME_TH=?, EQUIP_CON_NAME_EN=?, CHANGE_BY=?,CHANGE_ON=(getdate())"
                + "where EQUIP_CON_ID=?";

        int rs = db.update(sql,
                equipcon.getEquipConNameTh(),
                equipcon.getEquipConNameEn(),
                equipcon.getChangeBy(),
                equipcon.getEquipConId());
        return rs;
    }

    @Override
    public int add(EdcsMasEquipcon equipcon) {
        // insert
        String sql = "INSERT INTO EDCS_MAS_EQUIPCON (EQUIP_CON_NAME_TH, EQUIP_CON_NAME_EN, CREATE_BY,CHANGE_BY,CREATE_ON,CHANGE_ON,FLAG_DEL,FLAG_COMMENT)"
                + " VALUES (?, ?, ?,?,(getdate()),(getdate()),0,0)";
        int res = db.add(sql, equipcon.getEquipConNameTh(), equipcon.getEquipConNameEn(), equipcon.getCreateBy(), equipcon.getChangeBy());
        return res;
    }

    @Override
    public EdcsMasEquipcon find(int id) {
        String sql = "select * from EDCS_MAS_EQUIPCON where EQUIP_CON_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasEquipcon p = new EdcsMasEquipcon();
        if (map != null) {
            p.setEquipConId((Integer) map.get("EQUIP_CON_ID"));
            p.setEquipConNameTh((String) map.get("EQUIP_CON_NAME_TH"));
            p.setEquipConNameEn((String) map.get("EQUIP_CON_NAME_EN"));
            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
            p.setFlagComment((String) map.get("FLAG_COMMENT"));
          

        }
        return p;
    }

    @Override
    public List<EdcsMasEquipcon> findByFlag(int flag) {
        String sql = "select * from EDCS_MAS_EQUIPCON where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        List<EdcsMasEquipcon> ret = new ArrayList<EdcsMasEquipcon>();
        for (Map<String, Object> map : rs) {
            EdcsMasEquipcon p = new EdcsMasEquipcon();

            p.setEquipConId((int) map.get("EQUIP_CON_ID"));
            p.setEquipConNameTh((String) map.get("EQUIP_CON_NAME_TH"));
            p.setEquipConNameEn((String) map.get("EQUIP_CON_NAME_EN"));
            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
            p.setFlagComment((String) map.get("FLAG_COMMENT"));
           
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
    public int flaggedComment(int id, String userid) {
        String sql = "update EDCS_MAS_EQUIPCON  SET FLAG_COMMENT=?, CHANGE_BY=?,CHANGE_ON=(getdate())"
                + "where EQUIP_CON_ID=?";

        int result = 0;
        EdcsMasEquipcon toFlag = find(id);
        if ("1".equals(toFlag.getFlagComment())) {
            db.update(sql, "0", userid, id);
            result = 0;
        } else {
            db.update(sql, "1", userid, id);
               result = 1;
        }
        return result;
    }

    ;
    @Override
    public int isExistCount(String nameTh, String nameEn, String id) {
        if (id == null) {
            id = "0";
        }
        nameTh = nameTh.trim();
        nameEn = nameEn.trim();
        int idInt = Integer.parseInt(id);
        String sql = "select count(*) as count from EDCS_MAS_EQUIPCON where ( ( EQUIP_CON_NAME_TH=? AND EQUIP_CON_NAME_TH != '' ) OR ( EQUIP_CON_NAME_EN=? AND EQUIP_CON_NAME_EN != '' )) AND  EQUIP_CON_ID !=?";
        Map<String, Object> map = db.querySingle(sql, nameTh, nameEn, idInt);
        int count = 0;
        if (map != null) {
            count = (int) map.get("count");
        }
        return count;
    }
}
