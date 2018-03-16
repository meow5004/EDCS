/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.domain;

/**
 *
 * @author admin
 */
public class lineCalculationModel {

    private Integer headId;
    private String ABtype;
    private Integer line;
    private Double mean;
    private Double errorVal;
    private Double uncertaintyCombined;

    public lineCalculationModel() {
    }


    public Double getErrorVal() {
        return errorVal;
    }

    public void setErrorVal(Double errorVal) {
        this.errorVal = errorVal;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Double getMean() {
        return mean;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    public Integer getHeadId() {
        return headId;
    }

    public void setHeadId(Integer headId) {
        this.headId = headId;
    }

    

    public String getABtype() {
        return ABtype;
    }

    public void setABtype(String ABtype) {
        this.ABtype = ABtype;
    }



    public Double getUncertaintyCombined() {
        return uncertaintyCombined;
    }

    public void setUncertaintyCombined(Double uncertaintyCombined) {
        this.uncertaintyCombined = uncertaintyCombined;
    }

}
