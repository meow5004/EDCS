/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.domain.EdcsCalibration;
import th.co.wacoal.springtemplates.domain.EdcsCalibrationAttachItem;

/**
 *
 * @author admin
 */
public interface EdcsCalibrationAttachItemDAO {

    public int update(EdcsCalibrationAttachItem object);

    public int add(EdcsCalibrationAttachItem object);

    public List<EdcsCalibrationAttachItem> findByHeaderId(int headId);

    public List<EdcsCalibrationAttachItem> createAttachItemInputTemplateForAttachHeadModelFromCalibration(EdcsCalibration calibration);

    public EdcsCalibrationAttachItem mappingResult(Map<String, Object> map);

}
