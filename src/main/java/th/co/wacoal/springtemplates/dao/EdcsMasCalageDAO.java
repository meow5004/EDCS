/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import th.co.wacoal.springtemplates.domain.EdcsMasCalage;

/**
 *
 * @author admin
 */
public interface EdcsMasCalageDAO {

    public List<EdcsMasCalage> findAll();

    public int delete(int id, String userId);

    public int deleteMutiple(Integer[] ids, String userId);

    public int realDeleteMutiple(Integer[] ids);

    public int update(EdcsMasCalage object);

    public int add(EdcsMasCalage object);

    public EdcsMasCalage find(int id);

    public List<EdcsMasCalage> findByFlag(int flag);

    public int reuse(int id, String userId);

    public int reuseMutiple(Integer[] ids, String userId);

    public int isExistCount(EdcsMasCalage calage);

}
