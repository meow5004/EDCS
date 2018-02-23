/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.domain.EdcsMasDepartment;

/**
 *
 * @author admin
 */
public interface EdcsMasDepartmentDAO {

    public List<EdcsMasDepartment> findAll();

    public int update(EdcsMasDepartment object, String oldDepId);

    public int add(EdcsMasDepartment object);

    public int delete(String id, String userId);

    public int deleteMutiple(String[] ids, String userId);

    public int realDeleteMutiple(String[] ids);

    public EdcsMasDepartment find(String id);

    public List<EdcsMasDepartment> findByBranchId(int id);

    public List<EdcsMasDepartment> findByBranchIdByFlag(int id, String flag);

    public List<EdcsMasDepartment> findByFlag(int flag);

    public List<EdcsMasDepartment> showViewableDepartmentsByempId(String empId);

    public int reuse(String id, String userId);

    public int reuseMutiple(String[] ids, String userId);

    public EdcsMasDepartment mappingResultSet(Map<String, Object> map); 

    public Map<String, EdcsMasDepartment> findByFlagListMappingById(int flag);
}
