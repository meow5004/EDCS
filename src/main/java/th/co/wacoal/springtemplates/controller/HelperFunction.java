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

//    protected static List<uncertaintyCalculationModelLine> calculationCalibrationReport(EdcsCalibration calib, String side) {
//        Double resolution = calib.getAssociateModel().getResolution();
//        Double uncertainty = calib.getAssociateModel().getUncertainty();
//        Integer measureTime = calib.getAssociateMeasure().getMeasureTimes();
//        List<uncertaintyCalculationModelLine> result = new ArrayList<>();
////get sum of line of side A/B
//        for (EdcsCalibrationAttachHead head : calib.getEdcsCalibrationAttachHeadList()) {
//            if (head.getAbType().equals("side")) {
//                for (int cal = 1; cal <= 10; cal++) {
//                    uncertaintyCalculationModelLine resultLine = new uncertaintyCalculationModelLine();
//                    for (int i = 1; i <= measureTime; i++) {
//                        int sum = 0;
//                        for (EdcsCalibrationAttachItem item : head.getEdcsCalibrationAttachItemList()) {
//                            if (item.getEdcsCalibrationAttachItemPK().getCalAttachLine() == i) {
//                                sum += item.getCalpointValue();
//                            }
//                            Integer mean = sum / measureTime;
//                            Double Sx = 0.0;
//                            int sumOfXValueminusMean = 0;
//                            for (int j = 1; j <= measureTime; j++) {
//                                for (EdcsCalibrationAttachItem item2 : head.getEdcsCalibrationAttachItemList()) {
//                                    if (item.getEdcsCalibrationAttachItemPK().getCalAttachLine() == i) {
//                                        sumOfXValueminusMean += item2.getCalpointValue() - mean;
//                                    }
//                                }
//                            }
//                            Sx = (Math.pow(sumOfXValueminusMean, 2)) / (measureTime - 1);
//                            Double typeAUncertainty = Sx / Math.sqrt(measureTime);
//                            Double resolutionUr1 = (resolution / 2) * Math.sqrt(3);
//                            Double certResolutionUr2 = (uncertainty) / 1.96;//coverage factor95%
//                            Double combinedUncertainty = Math.pow(typeAUncertainty, 2) + Math.pow(resolutionUr1, 2) + Math.pow(certResolutionUr2, 2);
//                            Double effCombinedUncertainy = combinedUncertainty * 2.15;
//                            resultLine.setABtype(side);
//                            resultLine.setLine(cal);
//                            resultLine.setMean(mean.doubleValue());
//                            resultLine.setUncertaintyA(typeAUncertainty);
//                            resultLine.setUncertaintyB(Math.pow(typeAUncertainty, 2) + Math.pow(resolutionUr1, 2));
//                            resultLine.setUncertaintyCombined(effCombinedUncertainy);
//                        }
//                        result.add(resultLine);
//                    }
//
//                }
//            }
//        }
//
//        return result;
//    }
}
