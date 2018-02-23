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
import th.co.wacoal.springtemplates.dao.EdcsUserRoleDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsUserRole;

/**
 *
 * @author admin
 */
public class EdcsUserRoleDAOImpI implements EdcsUserRoleDAO {

    private final Database db;
    EdcsMasUserDAOImpI userDAO = null;
    EdcsMasDepartmentDAO depDAO = null;
    EdcsMasUserAuthTypeDAOImpI authDAO = null;
    EdcsMasUserTypeDAOImpI userTypeDAO = null;

    public EdcsUserRoleDAOImpI(Database db) {
        this.db = db;
    }

    @Override
    public List<EdcsUserRole> findAll() {
        String sql = "select * from EDCS_USER_ROLE";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsUserRole> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsUserRole p = new EdcsUserRole();
            p = mappingResultSet(map);
            ret.add(p);
        }
        return ret;
    }

    @Override
    public int delete(String roleId) {
        String sql = "DELETE FROM EDCS_USER_ROLE Where ROLE_ID=?";
        int result = db.update(sql, roleId);
        return result;
    }

    @Override
    public int deleteMutiple(String[] roleIds) {
        String group = StringUtils.join(roleIds, ",");
        String sql = "DELETE FROM EDCS_USER_ROLE Where ROLE_ID IN (" + group + ")";
        int result = db.update(sql, roleIds);
        return result;
    }

    @Override
    public int add(EdcsUserRole user) {

        // insert
        String sql = "INSERT INTO EDCS_USER_ROLE "
                + "("
                + " CREATE_BY,CREATE_ON,"
                + "CHANGE_BY,CHANGE_ON,"
                + "EMP_ID ,DEP_ID,"
                + "AUTH_TYPE_ID ,USER_TYPE_ID "
                + ")"
                + " VALUES ("
                + "?,(getdate()),"
                + "?,(getdate()),"
                + "?,?,"
                + "?,?"
                + ")";
        int res = 0;
        try {
            PreparedStatement pstmt = db.connect.prepareStatement(sql);

            pstmt.setString(1, user.getCreateBy());
            pstmt.setString(2, user.getChangeBy());
            pstmt.setString(3, user.getEmpId());
            pstmt.setString(4, user.getDepId());
            pstmt.setInt(5, user.getAuthTypeId());
            pstmt.setInt(6, user.getUserTypeId());

            res = pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public EdcsUserRole find(String id) {
        String sql = "select * from EDCS_USER_ROLE where ROLE_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsUserRole p = new EdcsUserRole();
        if (map != null) {
            p = mappingResultSet(map);
        }
        return p;
    }

    @Override
    public int isExistCount(EdcsUserRole role) {
        int count = 0;
        String sql = "select count(*) as count from EDCS_USER_ROLE where EMP_ID=? AND AUTH_TYPE_ID=? AND DEP_ID=? AND=USER_TYPE_ID=?";
        try {
            PreparedStatement pstmt = db.connect.prepareStatement(sql);
            pstmt.setString(1, role.getEmpId());
            pstmt.setInt(1, role.getAuthTypeId());
            pstmt.setString(1, role.getDepId());
            pstmt.setInt(1, role.getUserTypeId());

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
    public EdcsUserRole mappingResultSet(Map<String, Object> map) {
        EdcsUserRole p = new EdcsUserRole();
        String empId = (String) map.get("EMP_ID");
        String depId = (String) map.get("DEP_ID");
        Integer userAuthTypeId = (Integer) map.get("AUTH_TYPE_ID");
        Integer userTypeId = (Integer) map.get("USER_TYPE_ID");

        p.setEmpId(empId);
        p.setDepId(depId);
        p.setAuthTypeId(userAuthTypeId);
        p.setUserTypeId(userTypeId);
        p.setAssociateUser(userDAO.find(empId));
        p.setAssociateDepartment(depDAO.find(depId));
        p.setAssociateUserAuthType(authDAO.find(userAuthTypeId));
        p.setAssociateUserType(userTypeDAO.find(userTypeId));

        p.setCreateOn((Date) map.get("CREATE_ON"));
        p.setCreateBy((String) map.get("CREATE_BY"));
        p.setChangeOn((Date) map.get("CHANGE_ON"));
        p.setCreateBy((String) map.get("CHANGE_BY"));

        return p;
    }

    @Override
    public List<EdcsUserRole> findByEmpId(String empid) {
        String sql = "select * from EDCS_USER_ROLE where EMP_ID = ?";
        List<Map<String, Object>> rs = db.queryList(sql, empid);
        List<EdcsUserRole> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsUserRole p = new EdcsUserRole();
            p = mappingResultSet(map);
            ret.add(p);
        }
        return ret;
    }

    @Override
    public void recreateUserRole(List<EdcsUserRole> roles) {
        try {
            String userId = roles.get(0).getEmpId();

            db.beginTransaction();
            //delete old role
            String sql = "DELETE FROM EDCS_USER_ROLE Where EMP_ID=?";
            db.update(sql, userId);

            //add new role
            sql = "INSERT INTO EDCS_USER_ROLE "
                    + "("
                    + " CREATE_BY,CREATE_ON,"
                    + "CHANGE_BY,CHANGE_ON,"
                    + "EMP_ID ,DEP_ID,"
                    + "AUTH_TYPE_ID ,USER_TYPE_ID "
                    + ")"
                    + " VALUES ("
                    + "?,(getdate()),"
                    + "?,(getdate()),"
                    + "?,?,"
                    + "?,?"
                    + ")";
            PreparedStatement pstmt = db.connect.prepareStatement(sql);
            try {
                for (EdcsUserRole role : roles) {
                    pstmt.setString(1, role.getCreateBy());
                    pstmt.setString(2, role.getChangeBy());
                    pstmt.setString(3, role.getEmpId());
                    pstmt.setString(4, role.getDepId());
                    pstmt.setInt(5, role.getAuthTypeId());
                    pstmt.setInt(6, role.getUserTypeId());

                    pstmt.executeUpdate();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pstmt.close();
            }
            db.commit();
        } catch (Exception e) {
            db.rollback();
        }
    }

}
