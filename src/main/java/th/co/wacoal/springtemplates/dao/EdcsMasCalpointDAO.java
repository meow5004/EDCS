/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import th.co.wacoal.springtemplates.domain.EdcsMasCalpoint;

/**
 *
 * @author admin
 */
public interface EdcsMasCalpointDAO {

    public List<EdcsMasCalpoint> findAll();

    public int delete(int id, String userId);

    public int deleteMutiple(Integer[] ids, String userId);

    public int realDeleteMutiple(Integer[] ids);

    public int update(EdcsMasCalpoint object);

    public int add(EdcsMasCalpoint object);

    public EdcsMasCalpoint find(int id);

    public List<EdcsMasCalpoint> findByFlag(int flag);

    public int reuse(int id, String userId);

    public int reuseMutiple(Integer[] ids, String userId);

    public int isExistCount(String min, String max,String id);
}
