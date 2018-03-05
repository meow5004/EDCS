/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasUserDAO;
import th.co.wacoal.springtemplates.dao.stickerReserveDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.StickerReserve;

/**
 *
 * @author admin
 */
public class stickerReserveDAOImpI implements stickerReserveDAO {

    private final Database db;
    EdcsMasUserDAO userDAO = null;
    EdcsCalibrationDAO calibDAO = null;

    public stickerReserveDAOImpI(Database db) {
        this.db = db;
        userDAO = new EdcsMasUserDAOImpI(db);
        calibDAO = new EdcsCalibrationDAOImpI(db);

    }

    @Override
    public List<StickerReserve> findStickersByUserId(String userId) {
        String sql = "select * from STICKER_RESERVE WHERE EMP_ID=? ORDER_BY PRINT_ORDER";
        List<Map<String, Object>> rs = db.queryList(sql, userId);
        List<StickerReserve> ret = new ArrayList<>();
        for (Map<String, Object> map : rs) {
            StickerReserve p = new StickerReserve();
            p = mappingResultSet(map);
            ret.add(p);
        }
        return ret;
    }

    @Override
    public void clearStickerByUserId(String userId) {
        String sql = "delete from STICKER_RESERVE WHERE EMP_ID=?";
        db.update(sql, userId);
    }

    @Override
    public void addStickerToReserve(StickerReserve sticker) {
        String sql = "insert into STICKER_RESERVE (EMP_ID,CAL_ID,PRINT_ORDER) VALUES(?,?,?)";
        db.add(sql, sticker.getEmpId(), sticker.getCalId(), sticker.getPrintOrder());
    }

    @Override
    public void addStickersToReserve(List<StickerReserve> stickers) {
        for (StickerReserve sticker : stickers) {
            addStickerToReserve(sticker);
        }
    }

    private StickerReserve mappingResultSet(Map<String, Object> map) {
        String userId = (String) map.get("EMP_ID");
        Integer calId = (Integer) map.get("CAL_ID");
        Integer order = (Integer) map.get("PRINT_ORDER");
        Integer id = (Integer) map.get("STICKER_ID");

        StickerReserve sticker = new StickerReserve();
        sticker.setStickerId(id);
        sticker.setPrintOrder(order);
        sticker.setEmpId(userId);
        sticker.setCalId(calId);

        sticker.setAssociateUser(userDAO.find(userId));
        sticker.setAssociateCalibration(calibDAO.find(calId));

        return sticker;
    }

}
