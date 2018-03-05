<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>



<div class="row">
    <div class="col-lg-12" style="text-align: left;">
        <form:form id="previewForm"   modelAttribute="calibration" >        
            <input type="hidden"  id="standardUncertainty" value="0.05"/>
            <form:hidden path="calId" value="${calId}"/>

            <div class="box box-primary" id="loading-example">
                <div class="box-header">
                    <!-- tools box -->
                    <i class="fa fa-cloud"></i>

                    <h3 class="box-title">รายละเอียดผลการสอบเทียบ </h3>
                </div><!-- /.box-header -->
                <div class="col-sm-12">
                    <hr>
                </div>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        อ้างอิงรายงานหมายเลข
                    </div>
                    <div class="col-sm-2">
                        <input style="font-weight: bolder;font-size: 1.5em;color:red" disabled value="${calibration.calCode}"/>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        เครื่องวัด/ทดสอบ รหัส 
                    </div>
                    <div class="col-sm-4 ">
                        <input class="important-data-level-2" disabled value="${calibration.associateMeasure.measureCode}">
                    </div>
                    <div class="col-sm-4">
                        <input class="important-data-level-2" disabled value="${calibration.associateMeasure.fullName}">
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-1">
                        สถานะเครื่อง 
                    </div>
                    <div class="col-sm-2 ">
                        <input class="important-data-level-1" disabled value="${calibration.associateEquipCon.fullName}">
                    </div>
                    <c:if test="${calibration.conditionComment !=null&&calibration.conditionComment !='' }">
                        <div class="col-sm-1 col-sm-offset-1">
                            เนื่องจาก
                        </div>
                        <div class="col-sm-4 ">
                            <textarea style="width: 100%" class="important-data-level-1" disabled>${calibration.conditionComment}</textarea>
                        </div>
                    </c:if>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        สอบเทียบที่  
                    </div>
                    <div class="col-sm-4">
                        <c:if test="${calibration.calibrationLocation =='inside'}">
                            <input class="important-data-level-2" disabled value="สถานสอบเทียบภายใน">
                        </c:if>
                        <c:if test="${calibration.calibrationLocation =='outside'}">
                            <input class="important-data-level-2" disabled value="สถานสอบเทียบภายนอก">
                        </c:if>

                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        แม่แบบ รหัส 
                    </div>
                    <div class="col-sm-4 ">
                        <input class="important-data-level-1" disabled value="${calibration.associateModel.modelCode}">
                    </div>
                    <div class="col-sm-4">
                        <input class="important-data-level-1" disabled value="${calibration.associateModelMeasure.fullName}">
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        รายงานหมายเลข
                    </div>
                    <div class="col-sm-2 ">
                        <input class="important-data-level-1" disabled value="${calibration.associateModel.cerNo}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        ออกโดย
                    </div>
                    <div class="col-sm-4">
                        <input class="important-data-level-1" disabled value="${calibration.associateModel.locationBy}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-1">
                        การสอบกลับ
                    </div>
                    <div class="col-sm-4">
                        <input class="important-data-level-1" disabled value="${calibration.associateModel.locationReturn}">
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        วิธีวัด 
                    </div>
                    <div class="col-sm-2">
                        <input class="important-data-level-1" value="${calibration.associateProcess.processCode}" disabled/> 
                    </div>
                    <div class="col-sm-1 ">
                        เรื่อง
                    </div>
                    <div class="col-sm-4">
                        <input class="important-data-level-1" value="${calibration.associateProcess.processSubject}" disabled/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        วัดโดย
                    </div>
                    <div class="col-sm-8">
                        <textarea style="width: 100%" class="important-data-level-1" disabled>${calibration.associateProcess.processBy}</textarea>
                    </div>
                </div>
                <div class="box-body container">

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="pad">


                                <div class="col-sm-12">
                                    <hr>
                                </div>
                                <c:set var="measureTimes" value="${calibration.associateMeasure.measureTimes}"/>
                                <c:set var="useRangeMax" value="${calibration.associateMeasure.useRangeMax}"/>
                                <c:set var="useRangeMin" value="${calibration.associateMeasure.useRangeMin}"/>
                                <c:set var="diffRange" value="${useRangeMax-useRangeMin}"/>
                                <div class="row">
                                    <div class="col-md-10 col-md-offset-1">
                                        <c:set var="tableAmount" value="${calibration.edcsCalibrationAttachHeadList.size()}" scope="page" />
                                        <div id="summaryPreview" class="table-responsive">
                                            <table class="summaryTable">
                                                <thead>
                                                    <tr>
                                                        <th class="rowSpanDependOnInputTable">ค่าระบุ</th>
                                                        <th  class="spanDependOnInputTable" >ค่าวัดได้</th>
                                                        <th  class="spanDependOnInputTable" >ค่าความผิดพลาด( ค่าแก้ )</th>                                     
                                                        <th  class="rowSpanDependOnInputTable" >ค่าความไม่แน่นอน</th>
                                                    </tr>
                                                    <c:if test="${tableAmount > 1}">
                                                        <tr>
                                                            <th>A</th>
                                                            <th>B</th>
                                                            <th>A</th>
                                                            <th>B</th>
                                                        </tr>
                                                    </c:if>
                                                </thead>
                                                <tbody>


                                                    <c:forEach var = "s" begin = "1" end = "10">
                                                        <tr>
                                                            <td>
                                                                <fmt:formatNumber value="${useRangeMin}"
                                                                                  maxFractionDigits="0" />
                                                                -
                                                                <fmt:formatNumber value="${useRangeMin+(s*(diffRange/10))}"
                                                                                  maxFractionDigits="0" />
                                                            </td>
                                                            <c:forEach var = "t" begin = "1" end = "${tableAmount}" varStatus="tStat">

                                                                <!--                                                                tableId start at zero-->
                                                                <c:choose>
                                                                    <c:when test="${tStat.count == 1}">
                                                                        <td class="table-${tStat.count-1}row-${s}-medianCell joinedColumnLeft ">
                                                                        </td>
                                                                    </c:when>  
                                                                    <c:when test="${tStat.count == 2}">
                                                                        <td class="table-${tStat.count-1}row-${s}-medianCell joinedColumnRight">
                                                                        </td>
                                                                    </c:when>  
                                                                    <c:otherwise>
                                                                        <!--  /for future use when input table > 2 now it is only palceholder -->
                                                                        <td class="table-${tStat.count-1}row-${s}-medianCell joinedMiddle">  
                                                                        </td>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                            </c:forEach>

                                                            <c:forEach var = "u" begin = "1" end = "${tableAmount}" varStatus="uStat">

                                                                <c:choose>
                                                                    <c:when test="${uStat.count == 1}">
                                                                        <td class="table-${uStat.count-1}row-${s}-errorCell joinedColumnLeft">
                                                                        </td>
                                                                    </c:when>  
                                                                    <c:when test="${uStat.count == 2}">
                                                                        <td class="table-${uStat.count-1}row-${s}-errorCell joinedColumnRight">
                                                                        </td>
                                                                    </c:when>  
                                                                    <c:otherwise>
                                                                        <!--                                                                            /for future use when input table > 2-->
                                                                        <td class="table-${uStat.count-1}row-${s}-errorCell joinedMiddle">
                                                                        </td>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>

                                                            <td class="row-${s}-uncertaintyCell">
                                                            </td>

                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                </div>
                                <br>
                                <div class="col-sm-12">

                                    <div class="col-sm-3">
                                        ค่าความผิดพลาดที่ยอมรับได้</div>
                                    <div class="col-sm-1 important-data-level-1">
                                        &PlusMinus;${calibration.calError}
                                        <form:hidden path="calError" />
                                    </div>
                                    <div class="unitLabel">
                                        ${calibration.associateUnit.unitNameTh}<%--${calibration.associateUnit.unitNameEn}--%>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="col-sm-2">ผลสรุป</div>
                                    <span class="important-data-level-1">
                                        ${calibration.associateStatusCaldoc.statusCaldocName}  
                                        <c:if test="${calibration.comment!=null&&calibration.comment.trim() !=''}">
                                            ( ${calibration.comment} )
                                        </c:if>
                                    </span>
                                </div>
                                <div class="col-sm-6">
                                    <div style="width:100%; float: left;" >ปีที่ใช้งานผลสอบเทียบ <span class="important-data-level-1"><fmt:formatNumber maxFractionDigits="0" value="${calibration.associateCalage.calAge}"></fmt:formatNumber></span> ปี</div>
                                    </div>
                                    <br>
                                    <br>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-6" style="border-right: 1px solid grey">
                                            <span class="important-data-level-1">
                                                ผู้สอบเทียบ/ผู้ตรวจสอบ
                                            </span>
                                        </div>
                                        <div class="col-sm-6" style="border-left: 1px solid grey">
                                            <span class="important-data-level-1">
                                                ผู้อนุมัติ
                                            </span>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6" style="border-right: 1px solid grey">
                                            <span>
                                            ${calibration.calibrationStatusBy}
                                            ${calibration.associateCalibrationStatusByUser.userName}
                                        </span>
                                    </div>
                                    <div class="col-sm-6" style="border-left: 1px solid grey">
                                        <span>
                                            ${calibration.approveStatusBy}
                                            ${calibration.associateApproveStatusByUser.userName}
                                        </span>
                                    </div>
                                </div>
                                <hr>
                                <div class="panel-group">
                                    <c:forEach items="${calibration.edcsCalibrationAttachHeadList}" var="head" varStatus="theBigCount">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" href="#collapse${theBigCount.index}">
                                                        เอกสารแนบ หน้า ${calibration.edcsCalibrationAttachHeadList[theBigCount.index].abType}
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapse${theBigCount.index}" class="panel-collapse collapse">
                                                <div class="panel-body container-fluid">
                                                    <div class="row">
                                                        <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].abType" required="required"/>
                                                        <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].calAttachHeadId" required="required"/>
                                                        <div class="col-md-3 col-md-offset-5 no-padding important-data-level-1">เอกสารแนบ หน้า ${calibration.edcsCalibrationAttachHeadList[theBigCount.index].abType} (หน่วย : ${calibration.associateUnit.unitShortEn})</div>
                                                        <br/>
                                                    </div>
                                                    <div class="row">
                                                        <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].calId"  class="form-control" required="required" value="${calibration.calId}" />
                                                        <div class="col-md-1 col-md-offset-2 no-padding">อุณหภูมิ</div><div class="col-md-1 no-padding">${calibration.edcsCalibrationAttachHeadList[theBigCount.index].temperature}°C</div>
                                                        <div class="col-md-2 no-padding col-md-offset-1">ความชื้นสัมพันธ์</div><div class="col-md-1 no-padding">${calibration.edcsCalibrationAttachHeadList[theBigCount.index].humidity} % RH</div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-1 col-md-offset-2 no-padding">วันที่สอบเทียบ</div><div class="col-md-2 no-padding">${calibration.edcsCalibrationAttachHeadList[theBigCount.index].calDate}</div> 
                                                        <div class="col-md-2 col-md-offset-1 no-padding">สภาวะการสอบเทียบ</div><div class="col-md-1 no-padding">${calibration.edcsCalibrationAttachHeadList[theBigCount.index].calState} </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-md-1 col-md-offset-2 no-padding">พิสัยใช้งาน</div><div class="col-md-2 no-padding">${calibration.edcsCalibrationAttachHeadList[theBigCount.index].activeRange} </div>
                                                        <div class="col-md-2 no-padding col-md-offset-1">
                                                            พิกัด</div><div class="col-md-1 no-padding">
                                                            ${calibration.edcsCalibrationAttachHeadList[theBigCount.index].coordinate} 
                                                        </div>
                                                    </div>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-md-1 col-md-offset-2 no-padding">ค่าการยอมรับ</div>
                                                        <div class="col-md-1 important-data-level-1">
                                                            &PlusMinus;${calibration.calError}  
                                                            <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].acceptance"  class="form-control" required="required"   value="${calibration.calError}"/>
                                                        </div> 
                                                        <div class="col-md-1">
                                                            ${calibration.associateUnit.unitNameTh}  <%--${calibration.associateUnit.unitNameEn}--%>
                                                        </div> 

                                                    </div>
                                                    <br/>
                                                    <div class="row">
                                                        <div class="col-md-10 col-md-offset-1 table-responsive">
                                                            <table class="inputTable table${calibration.edcsCalibrationAttachHeadList[theBigCount.index].abType}">
                                                                <thead>
                                                                    <tr>
                                                                        <th>
                                                                            จุดสอบเทียบ
                                                                        </th>
                                                                        <c:forEach var = "h" begin = "1" end = "${measureTimes}">
                                                                            <th>X${h}</th>
                                                                            </c:forEach>
                                                                        <th>ค่าเฉลี่ย</th>
                                                                        <th>ค่าความผิดพลาด( ค่าแก้ )</th>
                                                                        <th>ค่าความเบี่ยงเบนมาตรฐาน</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:set var="count" value="0" scope="page" />
                                                                    <c:forEach var = "i" begin = "1" end = "10">
                                                                        <tr>
                                                                            <td>
                                                                                <fmt:formatNumber value="${useRangeMin}"
                                                                                                  maxFractionDigits="0" />
                                                                                -
                                                                                <fmt:formatNumber value="${useRangeMin+(i*(diffRange/10))}"
                                                                                                  maxFractionDigits="0" />
                                                                                <input type="hidden" class="table-${theBigCount.index}row-${i}-DesignateValue" value="${useRangeMin+(i*(diffRange/10))}" disabled/>
                                                                            </td>
                                                                            <c:forEach var = "j" begin = "1" end = "${measureTimes}">
                                                                                <td>
                                                                                    ${calibration.edcsCalibrationAttachHeadList[theBigCount.index].edcsCalibrationAttachItemList[count].calpointValue}
                                                                                    <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].edcsCalibrationAttachItemList[${count}].calpointValue" required="required" class="table-${theBigCount.index}row-${i}col-${j}-inputCell"/>
                                                                                    <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].edcsCalibrationAttachItemList[${count}].edcsCalibrationAttachItemPK.calAttachLine" />
                                                                                    <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].edcsCalibrationAttachItemList[${count}].edcsCalibrationAttachItemPK.calTime" />
                                                                                    <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].edcsCalibrationAttachItemList[${count}].edcsCalibrationAttachItemPK.calAttachHeadId" />
                                                                                    <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].edcsCalibrationAttachItemList[${count}].calpointMin"/>
                                                                                    <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].edcsCalibrationAttachItemList[${count}].calpointMax" />
                                                                                </td>
                                                                                <c:set var="count"  scope="page" value="${count+1}"/>
                                                                            </c:forEach>
                                                                            <td class="table-${theBigCount.index}row-${i}-medianCell">
                                                                            </td>

                                                                            <td class="table-${theBigCount.index}row-${i}-errorCell">
                                                                            </td>

                                                                            <td class="table-${theBigCount.index}row-${i}-stdCell">
                                                                            </td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div> 
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="col-sm-12">
                                    <hr>
                                </div>


                                <div class="col-sm-12">
                                    <hr>
                                </div>

                            </div>
                        </div>
                    </div><!-- /.row - inside box -->
                </div><!-- /.box-body -->

                <div class="box-footer">
                </div><!-- /.box-footer -->
            </div><!-- /.box -->        
        </form:form>
    </div>

</div>

<script>
    $(document).ready(function () {


//adjust result table colspan
        $(".summaryTable .spanDependOnInputTable").attr("colspan", $("table.inputTable ").length);
        $(".summaryTable .rowSpanDependOnInputTable").attr("rowspan", $("table.inputTable ").length);



//create median column when input

        var tableCount = $(".inputTable").length;

        for (tableC = 0; tableC < tableCount; tableC++) {
            for (measureC = 1; measureC <= 10; measureC++) {
                var rowPointer = "table-" + tableC + "row-" + measureC;
                var row = rowPointer.match(/row-(.*)/)[1];
                //get value in row
                var sumOfCalValue = 0;
                var count = 0;
                var dataArray = [];
                $("input:regex(" + rowPointer + "col-(10|[0-9])-inputCell)").each(function (index) {
                    var value = $.trim($(this).val());
                    if (value.length > 0) {
                        sumOfCalValue += parseFloat(value);
                        dataArray.push(parseFloat(value));
                        count++;
                    }
                });
                if (sumOfCalValue > 0 && count > 0) {
                    var medianCell = $("td[class*='" + rowPointer + "-medianCell']");
                    var errorCell = $("td[class*='" + rowPointer + "-errorCell']");
                    var stdCell = $("td[class*='" + rowPointer + "-stdCell']");


                    var designateValueHiddenInput = $("input[class*='" + rowPointer + "-DesignateValue']");
                    //decimal to 2 remove unneeded 0
                    var median = parseFloat(sumOfCalValue / count);
                    //add col to selector to differentiate between row 1 and row 10 td
                    $(medianCell).text(median.toFixed(2).replace(/\.?0*$/g, ''));
                    var desinagteValue = parseFloat($(designateValueHiddenInput).val());
                    var error = desinagteValue - median;
                    $(errorCell).text(error.toFixed(2).replace(/\.?0*$/g, ''));
                    var acceptance = parseFloat($("#calError").val());

                    if (Math.abs(error) > acceptance) {
                        $(errorCell).addClass("alert");
                    } else {
                        $(errorCell).removeClass("alert");
                    }

                    //calculate variance
                    var sumOfDataMinusMeanPowerOfTwo = 0;
                    for (var i = 0, l = dataArray.length; i < l; i++) {
                        sumOfDataMinusMeanPowerOfTwo += Math.pow(dataArray[i] - median, 2);
                    }
                    var variance = sumOfDataMinusMeanPowerOfTwo / count;
                    var std = Math.sqrt(variance);
                    $(stdCell).text(std.toFixed(2).replace(/\.?0*$/g, ''));
                    //calculate uncertainly
                    uncertainlyCalculate(row);
                }
            }
        }
    });
    function uncertainlyCalculate(rowTocalculate/*1-10*/) {
        //get value in row
        var sumOfCalValue = 0;
        var count = 0;
        var dataArray = [];
        $("input:regex(table-(10|[0-9])row-" + rowTocalculate + "col-(10|[0-9])-inputCell)").each(function (index) {
            var value = $.trim($(this).val());
            if (value.length > 0) {
                sumOfCalValue += parseFloat(value);
                dataArray.push(parseFloat(value));
                count++;
            }
        });
        if (sumOfCalValue > 0 && count > 0) {
            var uncertaintyCell = $("td[class*='row-" + rowTocalculate + "-uncertaintyCell']");
            var median = parseFloat(sumOfCalValue / count);
            //calculate variance
            var sumOfDataMinusMeanPowerOfTwo = 0;
            for (var i = 0, l = dataArray.length; i < l; i++) {
                sumOfDataMinusMeanPowerOfTwo += Math.pow(dataArray[i] - median, 2);
            }
            var variance = sumOfDataMinusMeanPowerOfTwo / count;
            var stdUncertainty = $("#standardUncertainty").val();
            var uncertainty = Math.sqrt((variance + Math.pow((parseFloat(stdUncertainty) / 1.96), 2))) * 2.15;
            $(uncertaintyCell).text(uncertainty.toFixed(2).replace(/\.?0*$/g, ''));
        }
    }


</script>
<jsp:include page="include/include_footer.jsp" flush="true"></jsp:include>

