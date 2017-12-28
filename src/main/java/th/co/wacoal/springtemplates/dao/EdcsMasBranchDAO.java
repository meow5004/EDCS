/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import th.co.wacoal.springtemplates.domain.EdcsMasBranch;

/**
 *
 * @author admin
 */
public interface EdcsMasBranchDAO {

    public List<EdcsMasBranch> findAll();

    public int delete(int id, String userId);

    public int deleteMutiple(Integer[] ids, String userId);

    public int realDeleteMutiple(Integer[] ids);

    public int update(EdcsMasBranch object);

    public int add(EdcsMasBranch object);

    public String formatFullName(String thaiName, String engName);

    public EdcsMasBranch find(int id);

    public List<EdcsMasBranch> findByFlag(int flag);

    public int reuse(int id, String userId);

    public int reuseMutiple(Integer[] ids, String userId);

    public int isExistCount(String nameTh, String nameEn, String id);
}
