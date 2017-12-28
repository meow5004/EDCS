/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasure;

/**
 *
 * @author admin
 */
public interface EdcsMasMeasureDAO {

    public List<EdcsMasMeasure> findAll();

    public int delete(int id, String userId);

    public int deleteMutiple(Integer[] ids, String userId);

    public int realDeleteMutiple(Integer[] ids);

    public int update(EdcsMasMeasure object);

    public int add(EdcsMasMeasure object);

    public EdcsMasMeasure find(int id);

    public List<EdcsMasMeasure> findByFlag(int flag);

    public int reuse(int id, String userId);

    public int reuseMutiple(Integer[] ids, String userId);

    public int isExistCount(String nameTh, String nameEn, String id);

    public List<EdcsMasMeasure> findByEquipconIdByFlag(int id, String flag);

    public List<EdcsMasMeasure> findByCalpointIdByFlag(int id, String flag);
    
    public List<EdcsMasMeasure> findByGroupIdByFlag(int id, String flag);
}
