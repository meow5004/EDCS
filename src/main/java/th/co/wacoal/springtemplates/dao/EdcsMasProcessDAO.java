/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import th.co.wacoal.springtemplates.domain.EdcsMasProcess;

/**
 *
 * @author admin
 */
public interface EdcsMasProcessDAO {

    public List<EdcsMasProcess> findAll();

    public int delete(int id, String userId);

    public int deleteMutiple(Integer[] ids, String userId);

    public int realDeleteMutiple(Integer[] ids);

    public int update(EdcsMasProcess object);

    public int add(EdcsMasProcess object);

    public EdcsMasProcess find(int id);

    public List<EdcsMasProcess> findByFlag(int flag);

    public int reuse(int id, String userId);

    public int reuseMutiple(Integer[] ids, String userId);

    public int isExistCount(EdcsMasProcess process);
}
