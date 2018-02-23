/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.domain.EdcsCalibrationAttachHead;

/**
 *
 * @author admin
 */
public interface EdcsCalibrationAttachHeadDAO {

    public List<EdcsCalibrationAttachHead> findAll();

    public int delete(int id);

    public int deleteMutiple(Integer[] ids);

    public int update(EdcsCalibrationAttachHead object);

    public int addAndReturnId(EdcsCalibrationAttachHead object);

    public EdcsCalibrationAttachHead find(int id);

    public List<EdcsCalibrationAttachHead> findByCalibrationId(int calId);

    public EdcsCalibrationAttachHead mappingResult(Map<String, Object> map);

}
