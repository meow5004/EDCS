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
import th.co.wacoal.springtemplates.dao.EdcsMasUserTypeDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasUserType;

/**
 *
 * @author admin
 */
public class EdcsMasUserTypeDAOImpI implements EdcsMasUserTypeDAO {

    private final Database db;

    public EdcsMasUserTypeDAOImpI(Database db) {
        this.db = db;
    }

    @Override
    public List<EdcsMasUserType> findAll() {
        String sql = "select * from EDCS_MAS_USER_TYPE";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsMasUserType> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasUserType p = new EdcsMasUserType();
            p = mappingResultSet(map);
            ret.add(p);
        }
        return ret;
    }

    @Override
    public int delete(int authId) {
        String sql = "DELETE FROM EDCS_MAS_USER_TYPE Where USER_TYPE_ID=?";
        int result = db.update(sql, authId);
        return result;
    }

    @Override
    public int deleteMutiple(Integer[] authIds) {
        String group = StringUtils.join(authIds, ",");
        String sql = "DELETE FROM EDCS_MAS_USER_TYPE Where USER_TYPE_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int update(EdcsMasUserType authType) {

        String sql = "update EDCS_MAS_USER_TYPE  "
                + "SET "
                + "CHANGE_BY=?,CHANGE_ON=(getdate()),"
                + "USER_TYPE_NAME_TH =?,USER_TYPE_NAME_EN=?"
                + " where USER_TYPE_ID=?";

        int res = 0;
        try {
            PreparedStatement pstmt = db.connect.prepareStatement(sql);

            pstmt.setString(1, authType.getChangeBy());
            pstmt.setString(2, authType.getUserTypeNameTh());
            pstmt.setString(3, authType.getUserTypeNameEn());
            pstmt.setInt(4, authType.getUserTypeId());

            res = pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return res;

    }

    @Override
    public int add(EdcsMasUserType authType) {

        // insert
        String sql = "INSERT INTO EDCS_MAS_USER_TYPE "
                + "("
                + " CREATE_BY,CREATE_ON,"
                + "CHANGE_BY,CHANGE_ON,"
                + "USER_TYPE_NAME_TH ,USER_TYPE_NAME_EN"
                + ")"
                + " VALUES ("
                + "?,(getdate()),"
                + "?,(getdate()),"
                + "?,?"
                + ")";
        int res = 0;
        try {
            PreparedStatement pstmt = db.connect.prepareStatement(sql);

            pstmt.setString(1, authType.getCreateBy());
            pstmt.setString(2, authType.getChangeBy());
            pstmt.setString(3, authType.getUserTypeNameTh());
            pstmt.setString(4, authType.getUserTypeNameEn());

            res = pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public EdcsMasUserType find(int id) {
        String sql = "select * from EDCS_MAS_USER_TYPE where USER_TYPE_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasUserType p = new EdcsMasUserType();
        if (map != null) {
            p = mappingResultSet(map);
        }
        return p;
    }

    @Override
    public int isExistCount(EdcsMasUserType authType) {
        int count = 0;
        String sql = "select count(*) as count from EDCS_MAS_USER_TYPE where USER_TYPE_NAME_TH=? AND  USER_TYPE_NAME_EN=? AND USER_TYPE_ID!=? ";
        try {
            int id = 0;
            if (authType != null & authType.getUserTypeId() != null) {
                id = authType.getUserTypeId();
            }
            PreparedStatement pstmt = db.connect.prepareStatement(sql);
            pstmt.setString(1, authType.getUserTypeNameTh());
            pstmt.setString(2, authType.getUserTypeNameEn());
            pstmt.setInt(3, id);

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

    @Override
    public EdcsMasUserType mappingResultSet(Map<String, Object> map) {
        EdcsMasUserType p = new EdcsMasUserType();
        p.setUserTypeId((Integer) map.get("USER_TYPE_ID"));
        p.setUserTypeNameTh((String) map.get("USER_TYPE_NAME_TH"));
        p.setUserTypeNameEn((String) map.get("USER_TYPE_NAME_EN"));
        p.setCreateOn((Date) map.get("CREATE_ON"));
        p.setCreateBy((String) map.get("CREATE_BY"));
        p.setChangeOn((Date) map.get("CHANGE_ON"));
        p.setCreateBy((String) map.get("CHANGE_BY"));

        return p;
    }

    @Override
    public List<EdcsMasUserType> showTypeByempId(String empId) {
        String sql = "SELECT * FROM EDCS_MAS_USER_TYPE WHERE USER_TYPE_ID IN ("
                + "SELECT distinct USER_TYPE_ID"
                + "  FROM EDCS_USER_ROLE"
                + "  where EMP_ID=?"
                + "  )";
        List<Map<String, Object>> rs = db.queryList(sql, empId);
        List<EdcsMasUserType> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasUserType p = new EdcsMasUserType();
            p = mappingResultSet(map);
            ret.add(p);
        }
        return ret;
    }
}
