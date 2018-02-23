/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.domain.EdcsUserRole;

/**
 *
 * @author admin
 */
public interface EdcsUserRoleDAO {

    public List<EdcsUserRole> findAll();

    public int delete(String empId);

    public int deleteMutiple(String[] empId);

    public int add(EdcsUserRole object);

    public EdcsUserRole find(String id);

    public List<EdcsUserRole> findByEmpId(String empid);

    public int isExistCount(EdcsUserRole user);

    public void recreateUserRole(List<EdcsUserRole> roles);

    public EdcsUserRole mappingResultSet(Map<String, Object> map);

}
