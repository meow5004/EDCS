/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.domain.EdcsMasUserAuthType;

/**
 *
 * @author admin
 */
public interface EdcsMasUserAuthTypeDAO {

    public List<EdcsMasUserAuthType> findAll();

    public int delete(int authId);

    public int deleteMutiple(Integer[] authId);

    public int update(EdcsMasUserAuthType object);

    public int add(EdcsMasUserAuthType object);

    public EdcsMasUserAuthType find(int id);

    public List<EdcsMasUserAuthType> showAuthByempId(String empId);

    public int isExistCount(EdcsMasUserAuthType userAuth);

    public EdcsMasUserAuthType mappingResultSet(Map<String, Object> map);

}
