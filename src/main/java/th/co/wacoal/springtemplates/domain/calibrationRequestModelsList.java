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
public class calibrationRequestModelsList implements Serializable {

    private List<calibrationRequestModel> calibrationRequestModels;

    public List<calibrationRequestModel> getCalibrationRequestModels() {
        return calibrationRequestModels;
    }

    public void setCalibrationRequestModels(List<calibrationRequestModel> calibrationRequestModels) {
        this.calibrationRequestModels = calibrationRequestModels;
    }

}
