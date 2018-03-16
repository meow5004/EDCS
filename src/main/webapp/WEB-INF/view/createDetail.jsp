<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>



<div class="row">
    <div class="col-lg-12" style="text-align: left;">
        <form:form id="attachAddForm"   action="../calibration/saveCalibrationReportAttachmentByCalibration.htm" method="post" modelAttribute="calibration" >        
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
                        งานหมายเลข
                    </div>
                    <div class="col-sm-2">
                        <input disabled value="${calibration.calCode}"/>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        เครื่องวัด/ทดสอบ รหัส 
                    </div>
                    <div class="col-sm-4 ">
                        <input class="" disabled value="${calibration.associateMeasure.measureCode}">
                    </div>
                    <div class="col-sm-4">
                        <input class="" disabled value="${calibration.associateMeasure.fullName}">
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-1">
                        สถานะเครื่อง 
                    </div>
                    <div class="col-sm-2 ">
                        <input class="" disabled value="${calibration.associateEquipCon.fullName}">
                    </div>
                    <c:if test="${calibration.conditionComment !=null&&calibration.conditionComment !='' }">
                        <div class="col-sm-1 col-sm-offset-1">
                            เนื่องจาก
                        </div>
                        <div class="col-sm-4 ">
                            <textarea style="width: 100%" class="" disabled>${calibration.conditionComment}</textarea>
                        </div>
                    </c:if>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        สอบเทียบที่  
                    </div>
                    <div class="col-sm-4">
                        <c:if test="${calibration.calibrationLocation =='inside'}">
                            <input class="" disabled value="สถานสอบเทียบภายใน">
                        </c:if>
                        <c:if test="${calibration.calibrationLocation =='outside'}">
                            <input class="" disabled value="สถานสอบเทียบภายนอก">
                        </c:if>

                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        แม่แบบ รหัส 
                    </div>
                    <div class="col-sm-4 ">
                        <input class="" disabled value="${calibration.associateModel.modelCode}">
                    </div>
                    <div class="col-sm-4">
                        <input class="" disabled value="${calibration.associateModelMeasure.fullName}">
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        รายงานหมายเลข
                    </div>
                    <div class="col-sm-2 ">
                        <input class="" disabled value="${calibration.associateModel.cerNo}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        ออกโดย
                    </div>
                    <div class="col-sm-4">
                        <input class="" disabled value="${calibration.associateModel.locationBy}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-1">
                        การสอบกลับ
                    </div>
                    <div class="col-sm-4">
                        <input class="" disabled value="${calibration.associateModel.locationReturn}">
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        วิธีวัด 
                    </div>
                    <div class="col-sm-2">
                        <input class="" value="${calibration.associateProcess.processCode}" disabled/> 
                    </div>
                    <div class="col-sm-1 col-md-offset-1">
                        เรื่อง
                    </div>
                    <div class="col-sm-4">
                        <input class="" value="${calibration.associateProcess.processSubject}" disabled/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2 col-md-offset-1">
                        วัดโดย
                    </div>
                    <div class="col-sm-8">
                        <textarea style="width: 100%" class="" disabled>${calibration.associateProcess.processBy}</textarea>
                    </div>
                </div>
                <div class="box-body container">

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="pad">


                                <div class="col-sm-12">
                                    <hr>
                                </div>
                                <c:set var="resolution" value="${calibration.associateModel.resolution}"/>
                                <c:set var="uncertainty" value="${calibration.associateModel.uncertainty}"/>
                                <input type="hidden"  id="resolution" value="${resolution}"/>
                                <input type="hidden"  id="uncertainty" value="${uncertainty}"/>
                                <c:set var="measureTimes" value="${calibration.associateMeasure.measureTimes}"/>
                                <fmt:parseNumber var = "useRangeMax"  integerOnly = "true"  type = "number" value = "${calibration.associateMeasure.useRangeMax}" />
                                <fmt:parseNumber var = "useRangeMin"  integerOnly = "true"  type = "number" value = "${calibration.associateMeasure.useRangeMin}" />
                                <fmt:parseNumber var = "diffRange"  integerOnly = "true"  type = "number" value = "${useRangeMax-useRangeMin}" />
                                <c:forEach items="${calibration.edcsCalibrationAttachHeadList}" var="head" varStatus="theBigCount">
                                    <hr  style="width: 60%;border: 1px solid black">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].abType" required="required"/>
                                            <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].calDate" />
                                            <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].calAttachHeadId" required="required"/>
                                            <div class="col-md-3 col-md-offset-5 no-padding important-data-level-1">เอกสารแนบ หน้า ${calibration.edcsCalibrationAttachHeadList[theBigCount.index].abType} (หน่วย : ${calibration.associateUnit.unitShortEn})</div>
                                        </div>
                                        <br/>
                                        <div class="row">
                                            <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].calId"  class="form-control" required="required" value="${calibration.calId}" />
                                            <div class="col-md-1 col-md-offset-2 no-padding">อุณหภูมิ</div><div class="col-md-1 no-padding"><form:input path="edcsCalibrationAttachHeadList[${theBigCount.index}].temperature"  class="form-control" required="required"  type="number" step="any"/></div> <div class="col-md-1">°C</div>
                                            <div class="col-md-2 no-padding col-md-offset-1">ความชื้นสัมพันธ์</div><div class="col-md-1 no-padding"><form:input path="edcsCalibrationAttachHeadList[${theBigCount.index}].humidity"  class="form-control" required="required"  type="number" step="any"/> </div><div class="col-md-1">% RH</div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-2 col-md-offset-1 no-padding">สภาวะการสอบเทียบ</div><div class="col-md-1 no-padding"><form:input path="edcsCalibrationAttachHeadList[${theBigCount.index}].calState"  class="form-control" /> </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-1 col-md-offset-2 no-padding">พิสัยใช้งาน</div><div class="col-md-2 no-padding"><form:input path="edcsCalibrationAttachHeadList[${theBigCount.index}].activeRange"  readonly="true" class="form-control" required="required"/> </div>
                                            <div class="col-md-2 no-padding col-md-offset-1">
                                                พิกัด</div><div class="col-md-1 no-padding">
                                                <form:input path="edcsCalibrationAttachHeadList[${theBigCount.index}].coordinate"  class="form-control" required="required"  type="text"/> 
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
                                            <div class="col-sm-12  table-responsive">
                                                <table class="inputTable table${calibration.edcsCalibrationAttachHeadList[theBigCount.index].abType} dataTable hover cell-border nowrap">
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
                                                                    <fmt:parseNumber var = "blockMax"  integerOnly = "true"  type = "number" value = "${useRangeMin+(i*(diffRange/10))}" />
                                                                    <fmt:formatNumber value="${useRangeMin}"
                                                                                      maxFractionDigits="0" />
                                                                    -
                                                                    <fmt:formatNumber value="${useRangeMin+(i*(diffRange/10))}"
                                                                                      maxFractionDigits="0" />
                                                                    <input type="hidden" class="table-${theBigCount.index}row-${i}-DesignateValue" value="${useRangeMin+(i*(diffRange/10))}" disabled/>
                                                                </td>
                                                                <c:forEach var = "j" begin = "1" end = "${measureTimes}">
                                                                    <td>
                                                                        <form:input path="edcsCalibrationAttachHeadList[${theBigCount.index}].edcsCalibrationAttachItemList[${count}].calpointValue" type="tel" required="required" class="table-${theBigCount.index}row-${i}col-${j}-inputCell"/>

                                                                        <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].edcsCalibrationAttachItemList[${count}].lineExpandUncertainty" required="required" class="table-${theBigCount.index}row-${i}-inputUncertaintyCell"/>
                                                                        <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].edcsCalibrationAttachItemList[${count}].calpointMin" value="${useRangeMin}"/>
                                                                        <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].edcsCalibrationAttachItemList[${count}].calpointMax" value="${blockMax}"/>
                                                                        <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].edcsCalibrationAttachItemList[${count}].edcsCalibrationAttachItemPK.calAttachLine" />
                                                                        <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].edcsCalibrationAttachItemList[${count}].edcsCalibrationAttachItemPK.calTime" />
                                                                        <form:hidden path="edcsCalibrationAttachHeadList[${theBigCount.index}].edcsCalibrationAttachItemList[${count}].edcsCalibrationAttachItemPK.calAttachHeadId" />
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
                                    </c:forEach>
                                    <div class="col-sm-12">
                                        <hr>
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                    <input type="hidden"  id="standardUncertainty" value="0.05"/>
                                </div>
                                <div class="row">
                                    <div class="col-md-10 col-md-offset-1">
                                        <c:set var="tableAmount" value="${calibration.edcsCalibrationAttachHeadList.size()}" scope="page" />
                                        <div id="summaryPreview" class=" table-responsive">
                                            <table class="summaryTable">
                                                <thead>
                                                    <tr>
                                                        <th class="rowSpanDependOnInputTable">ค่าระบุ</th>
                                                        <th  class="spanDependOnInputTable" >ค่าวัดได้</th>
                                                        <th  class="spanDependOnInputTable" >ค่าความผิดพลาด( ค่าแก้ )</th>                                     
                                                        <th  class="spanDependOnInputTable" >ค่าความไม่แน่นอน</th>
                                                    </tr>
                                                    <c:if test="${tableAmount > 1}">
                                                        <tr>
                                                            <th>A</th>
                                                            <th>B</th>
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
                                                            <c:forEach var = "v" begin = "1" end = "${tableAmount}" varStatus="vStat">

                                                                <c:choose>
                                                                    <c:when test="${vStat.count == 1}">
                                                                        <td class="table-${vStat.count-1}row-${s}-uncertaintyCell joinedColumnLeft">
                                                                        </td>
                                                                    </c:when>  
                                                                    <c:when test="${vStat.count == 2}">
                                                                        <td class="table-${vStat.count-1}row-${s}-uncertaintyCell joinedColumnRight">
                                                                        </td>
                                                                    </c:when>  
                                                                    <c:otherwise>
                                                                        <!--                                                                            /for fstsre sse when inpst table > 2-->
                                                                        <td class="table-${vStat.count-1}row-${s}-uncertaintyCell joinedMiddle">
                                                                        </td>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>


                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                </div>
                                <div class="col-sm-12">
                                    <hr>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">
                                        ค่าความผิดพลาดที่ยอมรับได้</div>
                                    <div class="col-sm-1 important-data-level-1">
                                        &PlusMinus;${calibration.calError}
                                        <form:hidden path="calError" />
                                    </div>
                                    <div class="unitLabel">
                                        ${calibration.associateUnit.unitNameTh}<%--${calibration.associateUnit.unitNameEn}--%>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-sm-2">ผลสรุป</div>
                                    <div class="col-sm-1">
                                        <form:select  path="statusCaldocId" items="${calDocConditions}" itemLabel="statusCaldocName" itemValue="statusCaldocId" required="required" />   
                                    </div>
                                    <div class="col-sm-1 no-padding" >
                                        เนื่องจาก   
                                    </div>
                                    <div class="col-sm-4 no-padding" >
                                        <form:textarea style="width:100%" path="comment" />
                                    </div>
                                </div>
                                <br/>
                                <div class="row">
                                    <div class="col-sm-2">วันสอบเทียบ</div>
                                    <div class="col-sm-2">
                                        <form:input path="calibratorOn"   class="form-control" required="required"/>
                                    </div>
                                </div>
                                <br/>
                                <div class="row">
                                    <div class="col-sm-2">อายุผลสอบเทียบ</div>
                                    <div class="col-sm-1">
                                        <select name="calAgeId">
                                            <c:forEach var="calage" items="${calAges}" >
                                                <c:choose>
                                                    <c:when test="${calage.calAgeId == calibration.calAgeId}">
                                                        <option value="${calage.calAgeId}" selected>
                                                            <fmt:formatNumber value="${calage.calAge}"
                                                                              maxFractionDigits="0" />
                                                        </option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${calage.calAgeId}">
                                                            <fmt:formatNumber value="${calage.calAge}"
                                                                              maxFractionDigits="0" />
                                                        </option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-4 no-padding">
                                        <span id="calculaTedDueDate"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div><!-- /.row - inside box -->
                </div><!-- /.box-body -->

                <div class="box-footer">
                    <div class="row">

                        <div class="col-sm-4">
                            <input type="submit" class="btn btn-primary" value="บันทึกผลการสอบเทียบ">
                        </div>
                        <div class="col-sm-6">
                            <button  class="btn" onclick="window.history.back()">กลับไปหน้าที่แล้ว</button>
                        </div>
                    </div><!-- /.row -->
                </div><!-- /.box-footer -->
            </div><!-- /.box -->        
        </form:form>
    </div>

</div>

<script>
    $(document).ready(function () {

        // make enter focus next input

        $(document).keydown(function (event) {
            if (event.keyCode == 13 && !$(document.activeElement).is('textarea')) {
                event.preventDefault();
                return false;
            }
        });
        //end
        $('input[id="calibratorOn"]').datepicker({
            dateFormat: 'dd/mm/yy',
            changeYear: true,
            changeMonth: true});
        $('select[name="calAgeId"],input[id="calibratorOn"]').on("change", function () {
            var date = $('input[id="calibratorOn"]').val();
            var year = $("select[name='calAgeId']").val();
            if (date != null && year != null) {
                var momentDueDate = moment(date, "DD/MM/YYYY").add('years', year);
                $("#calculaTedDueDate").text(momentDueDate.format('MM/DD/YYYY') + " ( หมดอายุในอีก " + momentDueDate.fromNow() + " )");
            }
        });
        //adjust result table colspan
        $(".summaryTable .spanDependOnInputTable").attr("colspan", $("table.inputTable ").length);
        $(".summaryTable .rowSpanDependOnInputTable").attr("rowspan", $("table.inputTable ").length);
        $('.selectpicker').selectpicker();
        $('.selectpicker').selectpicker('refresh');
        //create median column when input
        $('input[class*="inputCell"]').on("change", function () {
            var classInformation = $(this).attr("class");
            //            var cellPointer = classInformation.match(/table-(10|[0-9])row-(10|[0-9])col-(10|[0-9])/)[0];
            var rowPointer = classInformation.match(/table-(10|[0-9])row-(10|[0-9])/)[0];
            var row = rowPointer.match(/row-(.*)/)[1];
            var table = rowPointer.match(/table-(.*)row/)[1];
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
                var sumOfDataMinusMeanPowerOfTwo = 0;
                for (var i = 0, l = dataArray.length; i < l; i++) {
                    sumOfDataMinusMeanPowerOfTwo += Math.pow(dataArray[i] - median, 2);
                }

                var variance = sumOfDataMinusMeanPowerOfTwo / (count - 1);
                var std = Math.sqrt(variance);
                $(stdCell).text(std.toFixed(2).replace(/\.?0*$/g, ''));
                //calculate uncertainly
                uncertainlyCalculate(table, row);
            }
        });
        //syncro input
        var rexTemp = "input:regex(edcsCalibrationAttachHeadList(10|[0-9]).temperature)";
        $(document).on('change', rexTemp, function () {
            var mainValue = $.trim($(this).val());
            $(rexTemp).each(function (index) {
                var value = $.trim($(this).val());
                if (value.length <= 0) {
                    $(this).val(mainValue);
                }
            });
        });
        var rexhumid = "input:regex(edcsCalibrationAttachHeadList(10|[0-9]).humidity)";
        $(document).on('change', rexhumid, function () {
            var mainValue = $.trim($(this).val());
            $(rexhumid).each(function (index) {
                var value = $.trim($(this).val());
                if (value.length <= 0) {
                    $(this).val(mainValue);
                }
            });
        });
        var rexCal = "input:regex(edcsCalibrationAttachHeadList(10|[0-9]).calState)";
        $(document).on('change', rexCal, function () {
            var mainValue = $.trim($(this).val());
            $(rexCal).each(function (index) {
                var value = $.trim($(this).val());
                if (value.length <= 0) {
                    $(this).val(mainValue);
                }
            });
        });
        var rexCoo = "input:regex(edcsCalibrationAttachHeadList(10|[0-9]).coordinate)";
        $(document).on('change', rexCoo, function () {
            var mainValue = $.trim($(this).val());
            $(rexCoo).each(function (index) {
                var value = $.trim($(this).val());
                if (value.length <= 0) {
                    $(this).val(mainValue);
                }
            });
        });

        $('input[class*="inputCell"]').focus(function (e) {
            e.target.select();
            jQuery(e.target).one('mouseup', function (e) {
                e.preventDefault();
            });
        });
        $(document).on('keydown', "input[class*='inputCell']", ChangeCurrentCell);
        $(document).on('keyup change', "input[class*='inputCell']", enforceFloat);
        $("input[class*='inputCell']").trigger("change");
    });
    function uncertainlyCalculate(tableToCalculate, rowTocalculate/* table /row */) {
        //get value in row
        console.log(tableToCalculate + " row" + rowTocalculate);
        var resolution = $("#resolution").val();
        var uncertaintyModel = $("#uncertainty").val();
        var sumOfCalValue = 0;
        var count = 0;
        var dataArray = [];
        $("input:regex(table-" + tableToCalculate + "row-" + rowTocalculate + "col-(10|[0-9])-inputCell)").each(function (index) {
            var value = $.trim($(this).val());
            if (value.length > 0) {
                sumOfCalValue += parseFloat(value);
                dataArray.push(parseFloat(value));
                count++;
            }
        });
        if (sumOfCalValue > 0 && count > 0) {
            var uncertaintyCell = $("td[class*='table-" + tableToCalculate + "row-" + rowTocalculate + "-uncertaintyCell']");
            var inputUncertaintyCellClass = $("input[class*='table-" + tableToCalculate + "row-" + rowTocalculate + "-inputUncertaintyCell']");
            var median = parseFloat(sumOfCalValue / count);
            //calculate variance
            var sumOfDataMinusMeanPowerOfTwo = 0;
            for (var i = 0, l = dataArray.length; i < l; i++) {
                sumOfDataMinusMeanPowerOfTwo += Math.pow(dataArray[i] - median, 2);
            }
            var variance = sumOfDataMinusMeanPowerOfTwo / (count - 1);
            var std = Math.sqrt(variance);
            var Ua = std / Math.sqrt(count);
            var Ur1 = (resolution / 2) / Math.sqrt(3);
            var Ur2 = uncertaintyModel / 1.96;
//            console.log("std="+std+"&Ua="+Ua+"&ur1="+Ur1+"Ur2="+Ur2);
            var effUncertainty = Math.sqrt(Math.pow(Ua, 2) + Math.pow(Ur1, 2) + Math.pow(Ur2, 2)) * 2.15;
            var formattedEffUncertainty = effUncertainty.toFixed(7).replace(/\.?0*$/g, '');
            $(uncertaintyCell).text(formattedEffUncertainty);
            $(inputUncertaintyCellClass).val(formattedEffUncertainty);
        }
    }
    function ChangeCurrentCell(evt) {
        ///max row=10
        //col vary from jsp
        var maxCol = "${measureTimes}";
        var charCode = (evt.which) ? evt.which : event.keyCode;
        var className = $(this).attr("class");
        var currentRow = parseInt(className.match(/row-(.*?)col/)[1]);
        var currentCol = parseInt(className.match(/col-(.*?)-inputCell/)[1]);
        var newClassName = $(this).attr("class");
        if (charCode == 37) {
            currentCol--;
            if (currentCol < 1) {
                currentCol = maxCol;
            }
            newClassName = className.replace(/col-(.*?)-inputCell/, "col-" + currentCol + "-inputCell");
        }
        if (charCode == 38) {
            currentRow--;
            if (currentRow < 1) {
                currentRow = 10;
            }
            newClassName = className.replace(/row-(.*?)col/, "row-" + currentRow + "col");
        }
        if (charCode == 39) {
            currentCol++;
            if (currentCol > maxCol) {
                currentCol = 1;
            }
            newClassName = className.replace(/col-(.*?)-inputCell/, "col-" + currentCol + "-inputCell");
        }
        if (charCode == 40) {
            currentRow++;
            if (currentRow > 10) {
                currentRow = 1;
            }
            newClassName = className.replace(/row-(.*?)col/, "row-" + currentRow + "col");
        }
        if (charCode == 40 || charCode == 39 || charCode == 38 || charCode == 37) {
            var newClassSelector = "input[class*='" + newClassName + "']";
            if ($(newClassSelector).length > 0) {
                $(newClassSelector).trigger("focus");
                return false;
            }
        }
        return true;
    }

</script>
<jsp:include page="include/include_footer.jsp" flush="true"></jsp:include>

