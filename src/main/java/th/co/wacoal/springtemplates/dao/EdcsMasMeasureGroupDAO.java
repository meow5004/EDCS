/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasureGroup;

/**
 *
 * @author admin
 */
public interface EdcsMasMeasureGroupDAO {

    public List<EdcsMasMeasureGroup> findAll();

    public int delete(int id, String userId);

    public int deleteMutiple(Integer[] ids, String userId);

    public int realDeleteMutiple(Integer[] ids);

    public int update(EdcsMasMeasureGroup object);

    public int add(EdcsMasMeasureGroup object);

    public EdcsMasMeasureGroup find(int id);

    public List<EdcsMasMeasureGroup> findByFlag(int flag);

    public int reuse(int id, String userId);

    public int reuseMutiple(Integer[] ids, String userId);

    public int isExistCount(String nameTh,String nameEn, String id);
}
