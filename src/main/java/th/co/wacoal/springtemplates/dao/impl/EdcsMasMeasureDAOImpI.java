/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasure;

/**
 *
 * @author admin
 */
public class EdcsMasMeasureDAOImpI implements EdcsMasMeasureDAO {

    private final Database db;

    public EdcsMasMeasureDAOImpI(Database db) {
        this.db = db;
    }

   

    @Override
    public List<EdcsMasMeasure> findAll() {
        String sql = "select * from EDCS_MAS_MEASURE";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<EdcsMasMeasure> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasMeasure p = new EdcsMasMeasure();
            p.setMeasureGroupId((int) map.get("MEASURE_GROUP_ID"));
            p.setMeasureId((int) map.get("MEASURE_ID"));
            p.setMeasureCode((String) map.get("MEASURE_CODE"));
            p.setMeasureNameTh((String) map.get("MEASURE_NAME_TH"));
            p.setMeasureNameEn((String) map.get("MEASURE_NAME_EN"));
            p.setRangeMin((double) map.get("RANGE_MIN"));
            p.setRangeMax((double) map.get("RANGE_MAX"));
            p.setUseRangeMin((double) map.get("USE_RANGE_MIN"));
            p.setUseRangeMax((double) map.get("USE_RANGE_MAX"));
            p.setDescription((String) map.get("DESCRIPTION"));
            p.setMeasureTimes((int) map.get("MEASURE_TIMES"));
            p.setAbType((String) map.get("AB_TYPE"));

            p.setCalpointId((int) map.get("CALPOINT_ID"));
            p.setCalpointId((int) map.get("MEASURE_GROUP_ID"));
            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
            ret.add(p);
        }
        return ret;
    }

    @Override
    public int delete(int id, String userId) {
        String sql = "update EDCS_MAS_MEASURE SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE MEASURE_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int deleteMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_MEASURE SET FLAG_DEL = 1,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE MEASURE_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int realDeleteMutiple(Integer[] ids) {
        String group = StringUtils.join(ids, ",");
        String sql = "delete from EDCS_MAS_MEASURE  WHERE MEASURE_ID IN (" + group + ")";
        int result = db.update(sql);
        return result;
    }

    @Override
    public int reuse(int id, String userId) {
        String sql = "update EDCS_MAS_MEASURE SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE MEASURE_ID = ?";
        int result = db.update(sql, userId, id);
        return result;
    }

    @Override
    public int reuseMutiple(Integer[] ids, String userId) {
        String group = StringUtils.join(ids, ",");
        String sql = "update EDCS_MAS_MEASURE SET FLAG_DEL = 0,CHANGE_ON=(getdate()),CHANGE_BY=? WHERE MEASURE_ID IN (" + group + ")";
        int result = db.update(sql, userId);
        return result;
    }

    @Override
    public int update(EdcsMasMeasure measure) {

        String sql = "update EDCS_MAS_MEASURE  "
                + "SET MEASURE_NAME_TH =?,MEASURE_NAME_EN =?,"
                + "CHANGE_BY=?,CHANGE_ON=(getdate()),"
                + "MEASURE_CODE =?,"
                + "RANGE_MIN =?,RANGE_MAX =? ,"
                + "USE_RANGE_MIN =?,USE_RANGE_MAX =?,"
                + "DESCRIPTION =?,"
                + "AB_TYPE =?,"
                + "CALPOINT_ID =?,"
                + "MEASURE_GROUP_ID=? "
                + " where MEASURE_ID=?";

        int rs = db.update(sql,
                measure.getMeasureNameTh(),
                measure.getMeasureNameEn(),
                measure.getChangeBy(),
                measure.getMeasureCode(),
                measure.getRangeMin(),
                measure.getRangeMax(),
                measure.getUseRangeMin(),
                measure.getUseRangeMax(),
                measure.getDescription(),
                measure.getAbType(),
                measure.getCalpointId(),
                measure.getMeasureGroupId(),
                measure.getMeasureId());
        return rs;
    }

    @Override
    public int add(EdcsMasMeasure measure) {
        // insert

        String sql = "INSERT INTO EDCS_MAS_MEASURE "
                + "(MEASURE_NAME_TH,MEASURE_NAME_EN,"
                + "CREATE_BY,CREATE_ON,"
                + "CHANGE_BY,CHANGE_ON,"
                + "MEASURE_CODE,"
                + "RANGE_MIN,RANGE_MAX,"
                + "USE_RANGE_MIN,USE_RANGE_MAX,"
                + "DESCRIPTION,"
                + "AB_TYPE,"
                + "CALPOINT_ID,"
                + "MEASURE_GROUP_ID,"
                + "MEASURE_TIMES,"
                + "FLAG_DEL)"
                + " VALUES (?,?,"
                + "?,(getdate()),"
                + "?,(getdate()),"
                + "?,?,?,?,?,?,?,?,?,"
                + "1,0)";
        int res = db.add(sql, measure.getMeasureNameTh(), measure.getMeasureNameEn(),
                measure.getCreateBy(), measure.getChangeBy(),
                measure.getMeasureCode(),
                measure.getRangeMin(), measure.getRangeMax(),
                measure.getUseRangeMin(), measure.getUseRangeMax(),
                measure.getDescription(),
                measure.getAbType(),
                measure.getCalpointId(),
                measure.getMeasureGroupId()
        );
        return res;
    }

    @Override
    public EdcsMasMeasure find(int id) {
        String sql = "select * from EDCS_MAS_MEASURE where MEASURE_ID = ?";
        Map<String, Object> map = db.querySingle(sql, id);
        EdcsMasMeasure p = new EdcsMasMeasure();
        if (map != null) {
            p.setMeasureGroupId((int) map.get("MEASURE_GROUP_ID"));
            p.setMeasureId((int) map.get("MEASURE_ID"));
            p.setMeasureCode((String) map.get("MEASURE_CODE"));
            p.setMeasureNameTh((String) map.get("MEASURE_NAME_TH"));
            p.setMeasureNameEn((String) map.get("MEASURE_NAME_EN"));
            p.setRangeMin((double) map.get("RANGE_MIN"));
            p.setRangeMax((double) map.get("RANGE_MAX"));
            p.setUseRangeMin((double) map.get("USE_RANGE_MIN"));
            p.setUseRangeMax((double) map.get("USE_RANGE_MAX"));
            p.setDescription((String) map.get("DESCRIPTION"));
            p.setMeasureTimes((int) map.get("MEASURE_TIMES"));
            p.setAbType((String) map.get("AB_TYPE"));

            p.setCalpointId((int) map.get("CALPOINT_ID"));
            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
        }
        return p;
    }

    @Override
    public List<EdcsMasMeasure> findByFlag(int flag) {
        String sql = "select * from EDCS_MAS_MEASURE where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        List<EdcsMasMeasure> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasMeasure p = new EdcsMasMeasure();
            p.setMeasureGroupId((int) map.get("MEASURE_GROUP_ID"));
            p.setMeasureId((int) map.get("MEASURE_ID"));
            p.setMeasureCode((String) map.get("MEASURE_CODE"));
            p.setMeasureNameTh((String) map.get("MEASURE_NAME_TH"));
            p.setMeasureNameEn((String) map.get("MEASURE_NAME_EN"));
            p.setRangeMin((double) map.get("RANGE_MIN"));
            p.setRangeMax((double) map.get("RANGE_MAX"));
            p.setUseRangeMin((double) map.get("USE_RANGE_MIN"));
            p.setUseRangeMax((double) map.get("USE_RANGE_MAX"));
            p.setDescription((String) map.get("DESCRIPTION"));
            p.setMeasureTimes((int) map.get("MEASURE_TIMES"));
            p.setAbType((String) map.get("AB_TYPE"));

            p.setCalpointId((int) map.get("CALPOINT_ID"));

            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));

            ret.add(p);
        }
        return ret;
    }

    @Override
    public Map<Integer, EdcsMasMeasure> findByFlagListMappingById(int flag) {
        String sql = "select * from EDCS_MAS_MEASURE where FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, flag);
        Map<Integer, EdcsMasMeasure> ret = new HashMap<>();
        for (Map<String, Object> map : rs) {
            EdcsMasMeasure p = new EdcsMasMeasure();
            int id = (int) map.get("MEASURE_ID");
            p.setMeasureId(id);
            p.setMeasureGroupId((int) map.get("MEASURE_GROUP_ID"));
            p.setMeasureCode((String) map.get("MEASURE_CODE"));
            p.setMeasureNameTh((String) map.get("MEASURE_NAME_TH"));
            p.setMeasureNameEn((String) map.get("MEASURE_NAME_EN"));
            p.setRangeMin((double) map.get("RANGE_MIN"));
            p.setRangeMax((double) map.get("RANGE_MAX"));
            p.setUseRangeMin((double) map.get("USE_RANGE_MIN"));
            p.setUseRangeMax((double) map.get("USE_RANGE_MAX"));
            p.setDescription((String) map.get("DESCRIPTION"));
            p.setMeasureTimes((int) map.get("MEASURE_TIMES"));
            p.setAbType((String) map.get("AB_TYPE"));

            p.setCalpointId((int) map.get("CALPOINT_ID"));

            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));
            ret.put(id, p);
        }
        return ret;
    }

    @Override
    public List<EdcsMasMeasure> findByCalpointIdByFlag(int id, String flag) {
        String sql = "select * from EDCS_MAS_MEASURE where CALPOINT_ID=? AND FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, id, flag);
        List<EdcsMasMeasure> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasMeasure p = new EdcsMasMeasure();
            p.setMeasureGroupId((int) map.get("MEASURE_GROUP_ID"));
            p.setMeasureId((int) map.get("MEASURE_ID"));
            p.setMeasureCode((String) map.get("MEASURE_CODE"));
            p.setMeasureNameTh((String) map.get("MEASURE_NAME_TH"));
            p.setMeasureNameEn((String) map.get("MEASURE_NAME_EN"));
            p.setRangeMin((double) map.get("RANGE_MIN"));
            p.setRangeMax((double) map.get("RANGE_MAX"));
            p.setUseRangeMin((double) map.get("USE_RANGE_MIN"));
            p.setUseRangeMax((double) map.get("USE_RANGE_MAX"));
            p.setDescription((String) map.get("DESCRIPTION"));
            p.setMeasureTimes((int) map.get("MEASURE_TIMES"));
            p.setAbType((String) map.get("AB_TYPE"));

            p.setCalpointId((int) map.get("CALPOINT_ID"));

            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));

            ret.add(p);
        }
        return ret;
    }

    @Override
    public List<EdcsMasMeasure> findByGroupIdByFlag(int id, String flag) {
        String sql = "select * from EDCS_MAS_MEASURE where MEASURE_GROUP_ID=? AND FLAG_DEL=?";
        List<Map<String, Object>> rs = db.queryList(sql, id, flag);
        List<EdcsMasMeasure> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            EdcsMasMeasure p = new EdcsMasMeasure();
            p.setMeasureGroupId((int) map.get("MEASURE_GROUP_ID"));
            p.setMeasureId((int) map.get("MEASURE_ID"));
            p.setMeasureCode((String) map.get("MEASURE_CODE"));
            p.setMeasureNameTh((String) map.get("MEASURE_NAME_TH"));
            p.setMeasureNameEn((String) map.get("MEASURE_NAME_EN"));
            p.setRangeMin((double) map.get("RANGE_MIN"));
            p.setRangeMax((double) map.get("RANGE_MAX"));
            p.setUseRangeMin((double) map.get("USE_RANGE_MIN"));
            p.setUseRangeMax((double) map.get("USE_RANGE_MAX"));
            p.setDescription((String) map.get("DESCRIPTION"));
            p.setMeasureTimes((int) map.get("MEASURE_TIMES"));
            p.setAbType((String) map.get("AB_TYPE"));

            p.setCalpointId((int) map.get("CALPOINT_ID"));

            p.setCreateBy((String) map.get("CREATE_BY"));
            p.setCreateOn((Date) map.get("CREATE_ON"));
            p.setChangeBy((String) map.get("CHANGE_BY"));
            p.setChangeOn((Date) map.get("CHANGE_ON"));
            p.setFlagDel((String) map.get("FLAG_DEL"));

            ret.add(p);
        }
        return ret;
    }

    @Override
    public int isExistCount(String nameTh, String nameEn, String id) {
        if (id == null) {
            id = "0";
        }
        int idInt = Integer.parseInt(id);
        String sql = "select count(*) as count from EDCS_MAS_MEASURE where MEASURE_NAME_TH=? AND MEASURE_NAME_EN=?  AND MEASURE_ID !=?";
        Map<String, Object> map = db.querySingle(sql, nameTh, nameEn, idInt);
        int count = 0;
        if (map != null) {
            count = (int) map.get("count");
        }
        return count;
    }

}
