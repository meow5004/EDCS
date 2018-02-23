/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.controller;

import java.util.ArrayList;
import java.util.List;
import th.co.wacoal.springtemplates.domain.EdcsCalibration;
import th.co.wacoal.springtemplates.domain.EdcsMasUser;

/**
 *
 * @author admin
 */
public class HelperFunction {

    protected static Integer[] stringArrayToInt(String[] strings) {
        Integer[] intarray = new Integer[strings.length];
        int i = 0;
        for (String str : strings) {
            try {
                intarray[i] = Integer.parseInt(str);
                i++;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Not a number: " + str + " at index " + i, e);
            }
        }
        return intarray;
    }

    protected static List<EdcsCalibration> calibrationFilterPerUserViewableDepartments(EdcsMasUser user, List<EdcsCalibration> calibs) {
        List<String> ViewableDepId = user.getViewableDepartmentIds();
        List<EdcsCalibration> filteredCalib = new ArrayList<>();
        for (EdcsCalibration calib : calibs) {
            if (ViewableDepId.contains(calib.getDepId())) {
                filteredCalib.add(calib);
            }
        }
        return filteredCalib;
    }

    protected static List<EdcsCalibration> calibrationFilterByDepartments(String calibId, List<EdcsCalibration> calibs) {
        List<EdcsCalibration> filteredCalib = new ArrayList<>();
        for (EdcsCalibration calib : calibs) {
            if (calibId.equals(calib.getDepId())) {
                filteredCalib.add(calib);
            }
        }
        return filteredCalib;
    }

}
