/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.domain;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author admin
 */
public class calibrationDeviceCheckModelsList implements Serializable {

    private List<calibrationDeviceCheckModel> calibrationDeviceCheckModels;

    public List<calibrationDeviceCheckModel> getCalibrationDeviceCheckModels() {
        return calibrationDeviceCheckModels;
    }

    public void setCalibrationDeviceCheckModels(List<calibrationDeviceCheckModel> calibrationDeviceCheckModels) {
        this.calibrationDeviceCheckModels = calibrationDeviceCheckModels;
    }

}
