/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationAttachItemDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsCalibration;
import th.co.wacoal.springtemplates.domain.EdcsCalibrationAttachItem;
import th.co.wacoal.springtemplates.domain.EdcsCalibrationAttachItemPK;
import th.co.wacoal.springtemplates.domain.lineCalculationModel;

/**
 *
 * @author admin
 */
public class EdcsCalibrationAttachitemDAOImpl implements EdcsCalibrationAttachItemDAO {

    private final Database db;

    public EdcsCalibrationAttachitemDAOImpl(Database db) {
        this.db = db;
    }

    @Override
    public int add(EdcsCalibrationAttachItem attachItem) {

        // insert
        String sql = "INSERT INTO EDCS_CALIBRATION_ATTACH_ITEM "
                + "("
                + "CAL_ATTACH_HEAD_ID,"
                + "CAL_ATTACH_LINE,"
                + "CAL_TIME,"
                + "CALPOINT_MIN,"
                + "CALPOINT_MAX,"
                + "CALPOINT_VALUE,"
                + "LINE_EXPAND_UNCERTAINTY )"
                + " VALUES ("
                + " ? , ? ,? , ? , ? , ? ,?"
                + ")";
        int res = db.add(sql,
                attachItem.getEdcsCalibrationAttachItemPK().getCalAttachHeadId(),
                attachItem.getEdcsCalibrationAttachItemPK().getCalAttachLine(),
                attachItem.getEdcsCalibrationAttachItemPK().getCalTime(),
                attachItem.getCalpointMin(),
                attachItem.getCalpointMax(),
                attachItem.getCalpointValue(),
                attachItem.getLineExpandUncertainty());

        return res;
    }

    @Override
    public int update(EdcsCalibrationAttachItem attachItem) {

        String sql = "update EDCS_CALIBRATION_ATTACH_ITEM  "
                + "SET "
                + "CALPOINT_MIN =?,CALPOINT_MAX=?,"
                + "CALPOINT_VALUE =? ,LINE_EXPAND_UNCERTAINTY=?"
                + " where CAL_ATTACH_HEAD_ID=? AND"
                + " CAL_ATTACH_LINE=? AND"
                + " CAL_TIME=?";

        int res = db.update(sql,
                attachItem.getCalpointMin(),
                attachItem.getCalpointMax(),
                attachItem.getCalpointValue(),
                attachItem.getLineExpandUncertainty(),
                attachItem.getEdcsCalibrationAttachItemPK().getCalAttachHeadId(),
                attachItem.getEdcsCalibrationAttachItemPK().getCalAttachLine(),
                attachItem.getEdcsCalibrationAttachItemPK().getCalTime()
        );

        return res;

    }

    @Override
    public EdcsCalibrationAttachItem mappingResult(Map<String, Object> map) {
        EdcsCalibrationAttachItem p = new EdcsCalibrationAttachItem();
        EdcsCalibrationAttachItemPK pk = new EdcsCalibrationAttachItemPK();
        p.setCalpointMax((Integer) map.get("CALPOINT_MAX"));
        p.setCalpointMin((Integer) map.get("CALPOINT_MIN"));
        p.setCalpointValue((Double) map.get("CALPOINT_VALUE"));
        p.setLineExpandUncertainty((Double) map.get("LINE_EXPAND_UNCERTAINTY"));
        pk.setCalAttachHeadId((Integer) map.get("CAL_ATTACH_HEAD_ID"));
        pk.setCalAttachLine((Integer) map.get("CAL_ATTACH_LINE"));
        pk.setCalTime((Integer) map.get("CAL_TIME"));
        p.setEdcsCalibrationAttachItemPK(pk);

        return p;
    }

    @Override
    public List<EdcsCalibrationAttachItem> findByHeaderId(int headId) {
        String sql = "select * from EDCS_CALIBRATION_ATTACH_ITEM where CAL_ATTACH_HEAD_ID = ?";
        List<Map<String, Object>> rs = db.queryList(sql, headId);
        List<EdcsCalibrationAttachItem> p = new ArrayList<EdcsCalibrationAttachItem>();
        for (Map<String, Object> map : rs) {
            if (map != null) {
                p.add(mappingResult(map));
            }
        }
        return p;
    }

    @Override
    public List<EdcsCalibrationAttachItem> createAttachItemInputTemplateForAttachHeadModelFromCalibration(EdcsCalibration calibration) {
        int count = 0;
        int measureTimes = calibration.getAssociateMeasure().getMeasureTimes();
        Double useRangeMax = calibration.getAssociateMeasure().getUseRangeMax();
        Double useRangeMin = calibration.getAssociateMeasure().getRangeMin();
        Double diffRange = useRangeMax - useRangeMin;
        List<EdcsCalibrationAttachItem> lists = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= measureTimes; j++) {
                EdcsCalibrationAttachItem item = new EdcsCalibrationAttachItem();
                EdcsCalibrationAttachItemPK pk = new EdcsCalibrationAttachItemPK();
                pk.setCalAttachLine(i);
                pk.setCalTime(j);
                item.setCalpointMin(useRangeMin.intValue());
                Double currentMax = useRangeMin + (i * diffRange);
                item.setCalpointMax(currentMax.intValue());
                item.setEdcsCalibrationAttachItemPK(pk);

                lists.add(count, item);
                count++;
            }
        }
        return lists;
    }

    @Override
    public List<lineCalculationModel> listLineByHeaderId(int headId, String side) {
        String sql = "select cast(AVG(CALPOINT_VALUE)as decimal(10,2)) as MEAN , cast(AVG(LINE_EXPAND_UNCERTAINTY)as decimal(10,2)) as LINE_EXPAND_UNCERTAINTY,CAL_ATTACH_LINE from EDCS_CALIBRATION_ATTACH_ITEM where CAL_ATTACH_HEAD_ID = ? GROUP BY CAL_ATTACH_LINE ORDER BY CAL_ATTACH_LINE ASC";
        List<Map<String, Object>> rs = db.queryList(sql, headId);
        List<lineCalculationModel> p = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            if (map != null) {
                lineCalculationModel line = new lineCalculationModel();
                line.setLine((Integer) map.get("CAL_ATTACH_LINE"));
                line.setUncertaintyCombined(((BigDecimal) map.get("LINE_EXPAND_UNCERTAINTY")).doubleValue());
                line.setMean(((BigDecimal) map.get("MEAN")).doubleValue());
                line.setABtype(side);
                line.setHeadId(headId);
                p.add(line);
            }
        }
        return p;
    }

}
