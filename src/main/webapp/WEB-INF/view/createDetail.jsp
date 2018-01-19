<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>



<div class="row">

    <div class="col-lg-12" style="text-align: left;">
        <form:form id="attachAddForm"   action="../calibration/createCalibrationAttach.htm" method="post" modelAttribute="calibration" >        
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
                    <div class="col-sm-12 col-md-offset-1">
                        อ้างอิงรายงานหมายเลข <span style="font-weight: bolder;font-size: 1.5em;color:red">${calibration.calCode}</span>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-12 col-md-offset-1">
                        เครื่องวัด/ทดสอบ รหัส <span class="important-data-level-2">${calibration.associateMeasure.measureCode}</span>
                        ( <span class="important-data-level-2">${calibration.associateMeasure.fullName}</span> )
                        สถานะเครื่อง <span class="important-data-level-1">${calibration.associateEquipCon.fullName}</span>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-12 col-md-offset-1">
                        แม่แบบ รหัส <span class="important-data-level-1">${calibration.associateModel.modelCode}</span>
                        ( <span class="important-data-level-1">${modelMeasure.fullName}</span> )
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 col-md-offset-1">
                        รายงานหมายเลข <span class="important-data-level-1">${calibration.associateModel.cerNo}</span>
                        ออกโดย <span class="important-data-level-1">${calibration.associateModel.locationBy}</span>
                        การสอบกลับ <span class="important-data-level-1">${calibration.associateModel.locationReturn}</span>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-12 col-md-offset-1">
                        วิธีวัด <span class="important-data-level-1">${calibration.associateProcess.processCode}</span> เรื่อง <span class="important-data-level-1">${calibration.associateProcess.processSubject}</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 col-md-offset-1">
                        โดย <span class="important-data-level-1">${calibration.associateProcess.processBy}</span>
                    </div>
                </div>
                <div class="box-body no-padding">

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="pad">
                                <jsp:useBean id="now" class="java.util.Date" />
                                <fmt:formatDate value="${now}"  
                                                type="date" 
                                                pattern="yyyy-MM-dd"
                                                var="theFormattedDate" />
                                <hr>
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="col-md-6">วันที่สอบเทียบ</div><div class="col-md-6">${theFormattedDate}</div>
                                    </div>
                                    <!--                                    <div class="col-md-4">
                                                                            <div class="col-md-4">อุณหภูมิ</div><div class="col-md-4"><form:input path="temperature"  class="form-control" required="required"  type="number" /></div> °C
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="col-md-4">ความชื้นสัมพันธ์</div><div class="col-md-4"> <form:input path="humidity"  class="form-control" required="required"  type="number"/> </div>% RH
                                                                        </div>-->
                                </div>
                                <div class="col-sm-12">
                                    <div style="width:100%; float: left;">ผลการสอบเทียบ (หน่วยวัด : ${calibration.associateUnit.unitShortEn})</div>
                                </div>
                                <br><br>

                                <div class="col-sm-12">
                                    <hr>
                                </div>
                                <c:set var="measureTimes" value="${calibration.associateMeasure.measureTimes}"/>
                                <c:set var="useRangeMax" value="${calibration.associateMeasure.useRangeMax}"/>
                                <c:set var="useRangeMin" value="${calibration.associateMeasure.useRangeMin}"/>
                                <c:set var="diffRange" value="${useRangeMax-useRangeMin}"/>
                                <c:forEach items="${calibration.edcsCalibrationAttachHeadCollection}" var="head" varStatus="theBigCount">
                                    <div class="col-md-8 col-md-offset-2">
                                        <table class="inpuTable">
                                            <thead>
                                                <tr>
                                                    <th>
                                                        จุดสอบเทียบ
                                                    </th>
                                                    <c:forEach var = "h" begin = "1" end = "${measureTimes}">
                                                        <th>X${h}</th>
                                                        </c:forEach>
                                                    <th>ค่าเฉลี่ย</th>
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
                                                            <fmt:formatNumber value="${useRangeMin+(i*diffRange)}"
                                                                              maxFractionDigits="0" />
                                                        </td>
                                                        <c:forEach var = "j" begin = "1" end = "${measureTimes}">
                                                            <td>
                                                                <form:input path="edcsCalibrationAttachHeadCollection[${theBigCount.index}].edcsCalibrationAttachItemCollection[${count}].calpointValue" type="number" required="required"/>
                                                                <form:hidden path="edcsCalibrationAttachHeadCollection[${theBigCount.index}].edcsCalibrationAttachItemCollection[${count}].edcsCalibrationAttachItemPK.calAttachLine" />
                                                                <form:hidden path="edcsCalibrationAttachHeadCollection[${theBigCount.index}].edcsCalibrationAttachItemCollection[${count}].edcsCalibrationAttachItemPK.calTime" />
                                                                <form:hidden path="edcsCalibrationAttachHeadCollection[${theBigCount.index}].edcsCalibrationAttachItemCollection[${count}].calpointMin"/>
                                                                <form:hidden path="edcsCalibrationAttachHeadCollection[${theBigCount.index}].edcsCalibrationAttachItemCollection[${count}].calpointMax" />
                                                            </td>
                                                            <c:set var="count"  scope="page" value="${count+1}"/>
                                                        </c:forEach>
                                                        <td>
                                                        </td>

                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div> 
                                </c:forEach>
                                <!--                                <table>
                                                                    <thead>
                                                                        <tr>
                                                                            <th style="text-align: center;">ค่าระบุ</th>
                                                                            <th style="text-align: center;">
                                                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                                                ค่าวัดได้
                                                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                                                            </th>
                                                                            <th style="text-align: center;">
                                                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                                                ค่าความผิดพลาด(ค่าแก้)
                                                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                                                            </th>
                                                                            <th style="text-align: center;">ค่าความไม่แน่นอน(+/-)</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <tr>
                                                                            <td style="text-align: center;"><input type="text" style="width:50%"></td>
                                                                            <td>
                                                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                                                            </td>
                                                                            <td>
                                                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                                                            </td>
                                                                            <td style="text-align: center;"><input type="text" style="width:50%"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td style="text-align: center;"><input type="text" style="width:50%"></td>
                                                                            <td>
                                                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                                                            </td>
                                                                            <td>
                                                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                                                            </td>
                                                                            <td style="text-align: center;"><input type="text" style="width:50%"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td style="text-align: center;"><input type="text" style="width:50%"></td>
                                                                            <td>
                                                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                                                            </td>
                                                                            <td>
                                                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                                                            </td>
                                                                            <td style="text-align: center;"><input type="text" style="width:50%"></td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>-->

                                <div class="col-sm-12">
                                    <hr>
                                </div>
                                <div class="col-sm-12">
                                    <div style="width:100%; float: left;">ค่าความผิดพลาดที่ยอมรับได้ น้อยกว่า หรือ เท่ากับ : (+ / -) <input type="text" value="" style="width:30%;"></div>
                                </div>
                                <br><br><br><br>
                                <div class="col-sm-6">
                                    <div style="width:20%; float: left; margin-top: 8px;">สรุป</div>
                                    <div style="width:30%; float: left;"><input type="checkbox" style="height: 20px; width: 20px;"> ผ่าน</div>
                                    <div style="width:50%; float: left;"><input type="checkbox" style="height: 20px; width: 20px;"> ไม่ผ่าน <input type="text" value=""></div>
                                </div>
                                <div class="col-sm-6">
                                    <div style="width:100%; float: left;">วันที่หมดอายุ (DUE Date) DATE <input type="text" value="" style="width:50%;"></div>
                                </div>
                            </div>
                        </div>
                    </div><!-- /.row - inside box -->
                </div><!-- /.box-body -->

                <div class="box-footer">
                    <div class="row">
                        <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                            <div class="col-sm-6">
                                <input type="submit" value="บันทึกผลการสอบเทียบ">
                                <!--                                <button class="btn btn-primary btn-lg" id="cancelSubmit">บันทึกผลการสอบเทียบ</button>-->
                            </div>
                        </div>
                    </div><!-- /.row -->
                </div><!-- /.box-footer -->
            </div><!-- /.box -->        
        </form:form>
    </div>

</div>

<script>
    $(document).ready(function () {
        $('.selectpicker').selectpicker();
        $('.selectpicker').selectpicker('refresh');
    });
</script>
<jsp:include page="include/include_footer.jsp" flush="true"></jsp:include>

