/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import th.co.wacoal.springtemplates.dao.EdcsMasCalageDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasCalage;

/**
 *
 * @author admin
 */
public class EdcsMasCalageDAOImpI implements EdcsMasCalageDAO {

    private final Database db;

    public EdcsMasCalageDAOImpI(Database db) {
        this.db = db;
    }

    @Override
    public List<EdcsMasCalage> findAll() {
        String sql = "select * from EDCS_MAS_CALAGE";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsMasCalage> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasCalage p = new EdcsMasCalage();

            p.setCalAgeId((int) map.get("CAL_AGE_ID"));
            p.setCalAge((Double) map.get("CAL_AGE"));
            p.setStartDate((Date) map.get("START_DATE"));
            p.setEndDate((Date) map.get("END_DATE"));

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
        String sql = "update EDCS_MAS_CALAGE SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE CAL_AGE_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int deleteMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_CALAGE SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE CAL_AGE_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int realDeleteMutiple(Integer[] ids) {
        String group = StringUtils.join(ids, ",");
        String sql = "delete from EDCS_MAS_CALAGE  WHERE CAL_AGE_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int reuse(int id, String userId) {
        String sql = "update EDCS_MAS_CALAGE SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE CAL_AGE_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int reuseMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_CALAGE SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE CAL_AGE_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int update(EdcsMasCalage calage) {

        String sql = "update EDCS_MAS_CALAGE  "
                + "SET "
                + "CHANGE_BY=?,CHANGE_ON=(getdate()),"
                + "CAL_AGE =?,"
                + "START_DATE =?,END_DATE =? "
                + " where CAL_AGE_ID=?";

        int res = 0;
        try {
            PreparedStatement pstmt = db.connect.prepareStatement(sql);

            pstmt.setString(1, calage.getChangeBy());
            pstmt.setDouble(2, calage.getCalAge());
            pstmt.setTimestamp(3, new java.sql.Timestamp(calage.getStartDate().getTime()));
            pstmt.setTimestamp(4, new java.sql.Timestamp(calage.getEndDate().getTime()));
            pstmt.setInt(5, calage.getCalAgeId());

            res = pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return res;

    }

    @Override
    public int add(EdcsMasCalage calage) {

        // insert
        String sql = "INSERT INTO EDCS_MAS_CALAGE "
                + "("
                + " CREATE_BY,CREATE_ON,"
                + "CHANGE_BY,CHANGE_ON,"
                + "CAL_AGE,"
                + "START_DATE ,END_DATE ,"
                + "FLAG_DEL)"
                + " VALUES ("
                + "?,(getdate()),"
                + "?,(getdate()),"
                + "?,?,?,"
                + "0)";
        int res = 0;
        try {
            PreparedStatement pstmt = db.connect.prepareStatement(sql);

            pstmt.setString(1, calage.getCreateBy());
            pstmt.setString(2, calage.getChangeBy());
            pstmt.setDouble(3, calage.getCalAge());
            pstmt.setTimestamp(4, new java.sql.Timestamp(calage.getStartDate().getTime()));
            pstmt.setTimestamp(5, new java.sql.Timestamp(calage.getEndDate().getTime()));

            res = pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public EdcsMasCalage find(int id) {
        String sql = "select * from EDCS_MAS_CALAGE where CAL_AGE_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasCalage p = new EdcsMasCalage();
        if (map != null) {
            p.setCalAgeId((int) map.get("CAL_AGE_ID"));
            p.setCalAge((Double) map.get("CAL_AGE"));
            p.setStartDate((Date) map.get("START_DATE"));
            p.setEndDate((Date) map.get("END_DATE"));

            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
        }
        return p;
    }

    @Override
    public List<EdcsMasCalage> findByFlag(int flag) {
        String sql = "select * from EDCS_MAS_CALAGE where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        List<EdcsMasCalage> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasCalage p = new EdcsMasCalage();

            p.setCalAgeId((int) map.get("CAL_AGE_ID"));
            p.setCalAge((Double) map.get("CAL_AGE"));
            p.setStartDate((Date) map.get("START_DATE"));
            p.setEndDate((Date) map.get("END_DATE"));

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
    public int isExistCount(EdcsMasCalage calage) {
        int count = 0;
        String sql = "select count(*) as count from EDCS_MAS_CALAGE where CAL_AGE=? AND START_DATE=? AND END_DATE=? AND CAL_AGE_ID !=?";
        try {
            PreparedStatement pstmt = db.connect.prepareStatement(sql);
            pstmt.setDouble(1, calage.getCalAge());
            pstmt.setTimestamp(2, new java.sql.Timestamp(calage.getStartDate().getTime()));
            pstmt.setTimestamp(3, new java.sql.Timestamp(calage.getEndDate().getTime()));
            if (calage.getCalAgeId() != null) {
                pstmt.setInt(4, calage.getCalAgeId());
            } else {
                pstmt.setInt(4, 0);
            }
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            if (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();

                for (int i = 1; i <= md.getColumnCount(); i++) {
                    map.put(md.getColumnLabel(i), rs.getObject(i));
                }
                count = (int) map.get("count");
                rs.close();
                pstmt.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count;
    }

}
