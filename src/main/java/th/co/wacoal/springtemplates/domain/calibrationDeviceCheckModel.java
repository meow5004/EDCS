/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.domain;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class calibrationDeviceCheckModel implements Serializable {

    private int equipConId;
    private String conditionComment;
    private String inspector;
    private int calId;
    private int measureId;

    public int getCalId() {
        return calId;
    }

    public void setCalId(int calId) {
        this.calId = calId;
    }

    public int getMeasureId() {
        return measureId;
    }

    public void setMeasureId(int measureId) {
        this.measureId = measureId;
    }

    public int getEquipConId() {
        return equipConId;
    }

    public void setEquipConId(int equipConId) {
        this.equipConId = equipConId;
    }

    public String getConditionComment() {
        return conditionComment;
    }

    public void setConditionComment(String conditionComment) {
        this.conditionComment = conditionComment;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

}
