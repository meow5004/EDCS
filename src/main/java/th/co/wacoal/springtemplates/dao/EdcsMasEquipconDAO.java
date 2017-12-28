/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import th.co.wacoal.springtemplates.domain.EdcsMasEquipcon;

/**
 *
 * @author admin
 */
public interface EdcsMasEquipconDAO {

    public List<EdcsMasEquipcon> findAll();

    public int delete(int id, String userId);

    public int deleteMutiple(Integer[] ids, String userId);

    public int realDeleteMutiple(Integer[] ids);

    public int update(EdcsMasEquipcon object);

    public int add(EdcsMasEquipcon object);

    public EdcsMasEquipcon find(int id);

    public List<EdcsMasEquipcon> findByFlag(int flag);

    public int reuse(int id, String userId);

    public int reuseMutiple(Integer[] ids, String userId);

    public int isExistCount(String nameTh, String nameEn, String id);
    
    public String formatFullName(String thaiName, String engName);
    
  public int flaggedComment(int id, String userid) ;
}
