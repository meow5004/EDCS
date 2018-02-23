/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.domain.EdcsMasUserType;

/**
 *
 * @author admin
 */
public interface EdcsMasUserTypeDAO {

    public List<EdcsMasUserType> findAll();

    public int delete(int authId);

    public int deleteMutiple(Integer[] authId);

    public int update(EdcsMasUserType object);

    public int add(EdcsMasUserType object);

    public EdcsMasUserType find(int id);

    public int isExistCount(EdcsMasUserType userType);

    public List<EdcsMasUserType> showTypeByempId(String empId);

    public EdcsMasUserType mappingResultSet(Map<String, Object> map);

}
