/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.domain.EdcsMasModel;

/**
 *
 * @author admin
 */
public interface EdcsMasModelDAO {

    public List<EdcsMasModel> findAll();

    public int delete(int id, String userId);

    public int deleteMutiple(Integer[] ids, String userId);

    public int realDeleteMutiple(Integer[] ids);

    public int update(EdcsMasModel object);

    public int add(EdcsMasModel object);

    public EdcsMasModel find(int id);

    public List<EdcsMasModel> findByFlag(int flag);

    public int reuse(int id, String userId);

    public int reuseMutiple(Integer[] ids, String userId);

    public int isExistCount(String id);

    public List<EdcsMasModel> findByMeasureIdByFlag(int id, int flag);

    public EdcsMasModel mappingResultSet(Map<String, Object> map);

    public void disactivate(int id);

    public void activate(int id);

    public List<EdcsMasModel> findByMeasureGroupIdByFlag(int id, int flag);
}
