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
import th.co.wacoal.springtemplates.dao.EdcsMasUserAuthTypeDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasUserAuthType;

/**
 *
 * @author admin
 */
public class EdcsMasUserAuthTypeDAOImpI implements EdcsMasUserAuthTypeDAO {

    private final Database db;

    public EdcsMasUserAuthTypeDAOImpI(Database db) {
        this.db = db;
    }

    @Override
    public List<EdcsMasUserAuthType> findAll() {
        String sql = "select * from EDCS_MAS_USER_AUTH_TYPE";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsMasUserAuthType> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasUserAuthType p = new EdcsMasUserAuthType();
            p = mappingResultSet(map);
            ret.add(p);
        }
        return ret;
    }

    @Override
    public int delete(int authId) {
        String sql = "DELETE FROM EDCS_MAS_USER_AUTH_TYPE Where AUTH_TYPE_ID=?";
        int result = db.update(sql, authId);
        return result;
    }

    @Override
    public int deleteMutiple(Integer[] authIds) {
        String group = StringUtils.join(authIds, ",");
        String sql = "DELETE FROM EDCS_MAS_USER_AUTH_TYPE Where AUTH_TYPE_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int update(EdcsMasUserAuthType authType) {

        String sql = "update EDCS_MAS_USER_AUTH_TYPE  "
                + "SET "
                + "CHANGE_BY=?,CHANGE_ON=(getdate()),"
                + "AUTH_TYPE_NAME_TH =?,AUTH_TYPE_NAME_EN=?"
                + " where AUTH_TYPE_ID=?";

        int res = 0;
        try {
            PreparedStatement pstmt = db.connect.prepareStatement(sql);

            pstmt.setString(1, authType.getChangeBy());
            pstmt.setString(2, authType.getAuthTypeNameTh());
            pstmt.setString(3, authType.getAuthTypeNameEn());
            pstmt.setInt(4, authType.getAuthTypeId());

            res = pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return res;

    }

    @Override
    public int add(EdcsMasUserAuthType authType) {

        // insert
        String sql = "INSERT INTO EDCS_MAS_USER_AUTH_TYPE "
                + "("
                + " CREATE_BY,CREATE_ON,"
                + "CHANGE_BY,CHANGE_ON,"
                + "AUTH_TYPE_NAME_TH ,AUTH_TYPE_NAME_EN"
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
            pstmt.setString(3, authType.getAuthTypeNameTh());
            pstmt.setString(4, authType.getAuthTypeNameEn());

            res = pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public EdcsMasUserAuthType find(int id) {
        String sql = "select * from EDCS_MAS_USER_AUTH_TYPE where AUTH_TYPE_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasUserAuthType p = new EdcsMasUserAuthType();
        if (map != null) {
            p = mappingResultSet(map);
        }
        return p;
    }

    @Override
    public int isExistCount(EdcsMasUserAuthType authType) {
        int count = 0;
        String sql = "select count(*) as count from EDCS_MAS_USER_AUTH_TYPE where AUTH_TYPE_NAME_TH=? AND AUTH_TYPE_NAME_EN=? AND AUTH_TYPE_ID !=?";
        try {
            int authId = 0;
            if (authType != null && authType.getAuthTypeId() != null) {
                authId = authType.getAuthTypeId();
            }
            PreparedStatement pstmt = db.connect.prepareStatement(sql);
            pstmt.setString(1, authType.getAuthTypeNameTh());
            pstmt.setString(2, authType.getAuthTypeNameEn());
            pstmt.setInt(3, authId);
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
    public EdcsMasUserAuthType mappingResultSet(Map<String, Object> map) {
        EdcsMasUserAuthType p = new EdcsMasUserAuthType();
        p.setAuthTypeId((Integer) map.get("AUTH_TYPE_ID"));
        p.setAuthTypeNameTh((String) map.get("AUTH_TYPE_NAME_TH"));
        p.setAuthTypeNameEn((String) map.get("AUTH_TYPE_NAME_EN"));
        p.setCreateOn((Date) map.get("CREATE_ON"));
        p.setCreateBy((String) map.get("CREATE_BY"));
        p.setChangeOn((Date) map.get("CHANGE_ON"));
        p.setCreateBy((String) map.get("CHANGE_BY"));

        return p;
    }

    public List<EdcsMasUserAuthType> showAuthByempId(String empId) {
        String sql = "SELECT * FROM EDCS_MAS_USER_AUTH_TYPE WHERE AUTH_TYPE_ID IN ("
                + "SELECT distinct AUTH_TYPE_ID"
                + "  FROM EDCS_USER_ROLE"
                + "  where EMP_ID=?"
                + "  )";
        List<Map<String, Object>> rs = db.queryList(sql, empId);
        List<EdcsMasUserAuthType> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasUserAuthType p = new EdcsMasUserAuthType();
            p = mappingResultSet(map);
            ret.add(p);
        }
        return ret;
    }
}
