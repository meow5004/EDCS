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
import th.co.wacoal.springtemplates.dao.EdcsMasDepartmentDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasUserAuthTypeDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasUserDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasUserTypeDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasDepartment;
import th.co.wacoal.springtemplates.domain.EdcsMasUser;
import th.co.wacoal.springtemplates.domain.EdcsMasUserAuthType;
import th.co.wacoal.springtemplates.domain.EdcsMasUserType;

/**
 *
 * @author admin
 */
public class EdcsMasUserDAOImpI implements EdcsMasUserDAO {

    private final Database db;

    public EdcsMasUserDAOImpI(Database db) {
        this.db = db;
    }

    @Override
    public List<EdcsMasUser> findAll() {
        String sql = "select * from EDCS_MAS_USER";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsMasUser> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasUser p = new EdcsMasUser();
            p = mappingResultSet(map);
            ret.add(p);
        }
        return ret;
    }

    @Override
    public List<EdcsMasUser> findByViewableDepId(String depId) {
        String sql = "select * from EDCS_MAS_USER WHERE EMP_ID IN ( SELECT EMP_ID FROM EDCS_USER_ROLE WHERE DEP_ID=?)";
        List<Map<String, Object>> rs = db.queryList(sql, depId);
        List<EdcsMasUser> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasUser p = new EdcsMasUser();
            p = mappingResultSet(map);
            ret.add(p);
        }
        return ret;
    }

    @Override
    public int delete(String empId) {
        String sql = "DELETE FROM EDCS_MAS_USER Where EMP_ID=?";
        int result = db.update(sql, empId);
        return result;
    }

    @Override
    public int deleteMutiple(String[] empIds) {
        String group = StringUtils.join(empIds, ",");
        String sql = "DELETE FROM EDCS_MAS_USER Where EMP_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int update(EdcsMasUser user) {

        String sql = "update EDCS_MAS_USER  "
                + "SET "
                + "CHANGE_BY=?,CHANGE_ON=(getdate()),"
                + "USER_NAME =?,SYS_NAME=?,"
                + "PHONE =?,DEP_ID =? "
                + " where EMP_ID=?";

        int res = 0;
        try {
            PreparedStatement pstmt = db.connect.prepareStatement(sql);

            pstmt.setString(1, user.getChangeBy());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getSysName());
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getDepId());
            pstmt.setString(6, user.getEmpId());

            res = pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return res;

    }

    @Override
    public int add(EdcsMasUser user) {

        // insert
        String sql = "INSERT INTO EDCS_MAS_USER "
                + "("
                + " EMP_ID,"
                + " CREATE_BY,CREATE_ON,"
                + "CHANGE_BY,CHANGE_ON,"
                + "USER_NAME ,SYS_NAME,"
                + "PHONE ,DEP_ID "
                + ")"
                + " VALUES ("
                + " ?,"
                + "?,(getdate()),"
                + "?,(getdate()),"
                + "?,?,"
                + "?,?"
                + ")";
        int res = 0;
        try {
            PreparedStatement pstmt = db.connect.prepareStatement(sql);

            pstmt.setString(1, user.getEmpId());
            pstmt.setString(2, user.getCreateBy());
            pstmt.setString(3, user.getChangeBy());
            pstmt.setString(4, user.getUserName());
            pstmt.setString(5, user.getSysName());
            pstmt.setString(6, user.getPhone());
            pstmt.setString(7, user.getDepId());

            res = pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public EdcsMasUser find(String id) {
        String sql = "select * from EDCS_MAS_USER where EMP_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasUser p = new EdcsMasUser();
        if (map != null) {
            p = mappingResultSet(map);
        }
        return p;
    }

    @Override
    public int isExistCount(EdcsMasUser user) {
        int count = 0;
        String sql = "select count(*) as count from EDCS_MAS_USER where EMP_ID=? ";
        try {
            PreparedStatement pstmt = db.connect.prepareStatement(sql);
            pstmt.setString(1, user.getEmpId());

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
    public EdcsMasUser mappingResultSet(Map<String, Object> map) {
        EdcsMasUser p = new EdcsMasUser();
        String empId = (String) map.get("EMP_ID");
        p.setEmpId(empId);
        p.setDepId((String) map.get("DEP_ID"));
        p.setUserName((String) map.get("USER_NAME"));
        p.setSysName((String) map.get("SYS_NAME"));
        p.setPhone((String) map.get("PHONE"));
        p.setCreateOn((Date) map.get("CREATE_ON"));
        p.setCreateBy((String) map.get("CREATE_BY"));
        p.setChangeOn((Date) map.get("CHANGE_ON"));
        p.setCreateBy((String) map.get("CHANGE_BY"));

        EdcsMasDepartmentDAO depDAO = new EdcsMasDepartmentDAOImpl(db);
        EdcsMasUserAuthTypeDAO authDAO = new EdcsMasUserAuthTypeDAOImpI(db);
        EdcsMasUserTypeDAO userTypeDAO = new EdcsMasUserTypeDAOImpI(db);

        if (empId != null && empId.length() > 0) {
            List<EdcsMasUserAuthType> authTypes = authDAO.showAuthByempId(empId);
            List<EdcsMasUserType> userTypes = userTypeDAO.showTypeByempId(empId);
            List<EdcsMasDepartment> deps = depDAO.showViewableDepartmentsByempId(empId);

            p.setUserAuthTypes(authTypes);
            p.setUserTypes(userTypes);
            p.setViewableDepartments(deps);

        }

        return p;
    }

    public Map<String, EdcsMasUser> findAllMappingKeyByEmpId() {
        String sql = "select * from EDCS_MAS_USER";
        List<Map<String, Object>> rs = db.queryList(sql);
        Map<String, EdcsMasUser> retMap = new HashMap<String, EdcsMasUser>();
        for (Map<String, Object> map : rs) {
            EdcsMasUser p = new EdcsMasUser();
            p = mappingResultSet(map);
            retMap.put(p.getEmpId(), p);
        }
        return retMap;
    }
;

}
