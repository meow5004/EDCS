/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasureUnit;

/**
 *
 * @author admin
 */
public interface EdcsMasMeasureUnitDAO {

    public List<EdcsMasMeasureUnit> findAll();

    public int delete(int id, String userId);

    public int deleteMutiple(Integer[] ids, String userId);

    public int realDeleteMutiple(Integer[] ids);

    public int update(EdcsMasMeasureUnit object);

    public int add(EdcsMasMeasureUnit object);

    public EdcsMasMeasureUnit find(int id);

    public List<EdcsMasMeasureUnit> findByFlag(int flag);

    public int reuse(int id, String userId);

    public int reuseMutiple(Integer[] ids, String userId);

    String formatFullName(String thaiName, String shortThaiName, String engName, String shortEngName);

    public int isExistCount(String nameTh, String nameEn, String id);
}
