/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.domain.EdcsMasUser;

/**
 *
 * @author admin
 */
public interface EdcsMasUserDAO {

    public List<EdcsMasUser> findAll();

    public int delete(String empId);

    public int deleteMutiple(String[] empId);

    public int update(EdcsMasUser object);

    public int add(EdcsMasUser object);

    public EdcsMasUser find(String id);

    public int isExistCount(EdcsMasUser user);

    public EdcsMasUser mappingResultSet(Map<String, Object> map);

    public List<EdcsMasUser> findByViewableDepId(String depId);
    
    public Map<String,EdcsMasUser>findAllMappingKeyByEmpId();

}
