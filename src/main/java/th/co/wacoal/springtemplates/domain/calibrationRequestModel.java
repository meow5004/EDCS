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
public class calibrationRequestModel implements Serializable {

    private int measureId;
    private String requestComment;
    private String approverId;
    private int calId;

    public Integer getCalId() {
        return calId;
    }

    public void setCalId(Integer calId) {
        this.calId = calId;
    }

    public int getMeasureId() {
        return measureId;
    }

    public void setMeasureId(int measureId) {
        this.measureId = measureId;
    }


    public String getRequestComment() {
        return requestComment;
    }

    public void setRequestComment(String requestComment) {
        this.requestComment = requestComment;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

}
