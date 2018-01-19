<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>

    <div class="row">
        <div class="col-lg-12" style="text-align: left;">
        <form:form id="addForm"  class="form-inline" action="../calibration/createCalibrationReportHeader.htm" method="post" modelAttribute="calibration" >
            <div class="box box-primary" id="loading-example">
                <div class="box-header">
                    <!-- tools box -->
                    <i class="fa fa-cloud"></i>

                    <h3 class="box-title">รายงานผลการสอบเทียบ</h3>
                </div><!-- /.box-header -->
                <div class="box-body no-padding">
                    <jsp:useBean id="now" class="java.util.Date" />
                    <fmt:formatDate value="${now}"  
                                    type="date" 
                                    pattern="yyyy-MM-dd"
                                    var="theFormattedDate" />

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="pad">
                                <div class="col-sm-6">
                                    <div style="width:40%; float: left;"><b>เลขที่</b></div><div style="width:60%; float: left;"><form:input path="calId"  class="form-control" required="required"  readonly="true"/></div>
                                </div>
                                <div class="col-sm-6">
<!--                                    <div style="width:40%; float: left;"><b>วันที่รายงาน</b></div><div style="width:60%; float: left;"><form:input path="calibrationStatusOn" value="${theFormattedDate}" class="form-control" required="required"  readonly="true"/></div>-->
                                    <div style="width:40%; float: left;"><b>วันที่รายงาน</b></div><div style="width:60%; float: left;">${theFormattedDate}</div>
                                </div>


                                <div class="col-sm-12">
                                    <hr>
                                </div>
                                <div class="col-sm-6">
                                    <div style="width:40%; float: left;"><b>หน่วยงาน/บริษัท</b></div><div style="width:60%; float: left;"> 
                                        <form:select path="depId" class="form-control" required="required" >
                                            <form:options items="${departments}" itemLabel="fullName" itemValue="depId" />
                                        </form:select></div>
                                </div>



                                <div class="col-sm-12">
                                    <hr>
                                </div>
                                <form:input path="measureId"  class="form-control" required="required"  type="hidden"/>
                                <div class="col-sm-6">
                                    <div style="width:100%; float: left;"><b>ชื่อเครื่องวัด/ทดสอบ</b>
                                        <input type="text" name="measureFullName" style="width:100%;" value="${calibration.associateMeasure.fullName}" disabled>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div style="width:100%; float: left;"><b>รหัสเครื่องวัดและทดสอบ</b>
                                        <input type="text" name="measureCode" style="width:100%;" value=" ${calibration.associateMeasure.measureCode}" disabled>
                                    </div>
                                </div>

                                <div class="col-sm-12">
                                    <hr>
                                </div>
                                <div class="col-sm-12 row">
                                    <div style="col-sm-9">สภาพภายนอก :  
                                        <form:radiobuttons  path="equipConId" items="${equipConditions}" itemLabel="fullName" itemValue="equipConId" required="required" />
                                        <form:input type="text" path="conditionComment"/></div>
                                </div>
                            </div>

                            <div class="col-sm-12">
                                <hr>
                            </div>
                            <div><b>มาตรฐานเปรียบเทียบอ้างอิง</b></div>
                            <div class="col-sm-6">
                                <div style="width:40%; float: left;">รายงานเลขที่</div>
                                <div style="width:60%; float: left;"><input type="text" name="cerNo" style="width:100%;" disabled></div>
                                <div style="width:40%; float: left;">แม่แบบ รหัส</div>
                                <div style="width:60%; float: left;"> 
                                    <form:select path="modelId" class="form-control" required="required" >
                                        <form:options items="${models}" itemLabel="modelCode" itemValue="modelId" />
                                    </form:select>
                                </div>
                                <div style="width:40%; float: left;">การสอบกลับ</div>
                                <div style="width:60%; float: left;"><input type="text" name="locationReturn" style="width:100%;" value="สมาคมส่งเสริมเทคโนโลยี (ไทย-ญี่ปุ่น)" disabled></div>
                            </div>
                            <div class="col-sm-6">
                                <div style="width:40%; float: left;">โดย</div>
                                <div style="width:60%; float: left;"><input type="text" name="locationBy" style="width:100%;" value="สมาคมส่งเสริมเทคโนโลยี (ไทย-ญี่ปุ่น)" disabled></div>
                                <div style="width:40%; float: left;">ชื่อ</div>
                                <div style="width:60%; float: left;"><input type="text" name="measureName" style="width:100%;" value="STEEL RULE 7" disabled></div>
                                <div style="width:40%; float: left;">CER.NO</div>
                                <div style="width:60%; float: left;"><input type="text" name="cerNo" disabled style="width:100%;"></div>
                            </div>





                            <div class="col-sm-12">
                                <hr>
                            </div>
                            <div class="col-sm-6">
                                <div style="width:40%; float: left;">วิธีการวัด/ทดสอบ</div>
                                <div style="width:60%; float: left;">
                                    <form:select path="processId" class="form-control" required="required" >
                                        <form:options items="${processes}" itemLabel="processCode" itemValue="processId" />
                                    </form:select>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div style="width:20%; float: left;">เรื่อง</div><div style="width:80%; float: left;"><input type="text" name="processSubject" style="width:80%;" value="สมาคมส่งเสริมเทคโนโลยี (ไทย-ญี่ปุ่น)" disabled></div>
                            </div>
                            <div class="col-sm-12">
                                <div style="width:10%; float: left;">โดย</div><div style="width:90%; float: left;"><input type="text" name="processBy" style="width:80%;" value="วัดเปรียบเทียบกับค่ามาตรฐาน (ไม้บรรทัดมาตรฐาน) เป็นช่วงๆ ช่วงละเท่าๆ กัน" disabled></div>
                            </div>

                            <div class="col-sm-12">
                                <hr>
                            </div>
                            <div class="col-sm-6">
                                <div style="width:40%; float: left;">หน่วยวัด</div>
                                <div style="width:60%; float: left;">
                                    <form:select path="unitId" class="form-control" required="required" >
                                        <form:options items="${units}" itemLabel="unitNameTh" itemValue="unitId" />
                                    </form:select>
                                </div>
                            </div>
                            <div class="col-sm-12">
                                <hr>
                            </div>
                            <div class="col-sm-12">
                                <div style="width:40%; float: left;">ผู้สอบเทียบ:/ผู้ตรวจสอบ</div><div style="width:60%; float: left;"><input type="text" style="width:100%;" value="กมล" disabled></div>
                            </div>
                            <div class="col-sm-12">
                                <div style="width:40%; float: left;">ผู้อนุมัติ</div>
                                <div style="width:60%; float: left;">
                                    <select class="selectpicker" id="colIds" data-title="รายชื่อผู้อนุมัติ" data-width="100%">
                                        <option value="1">name   surname</option>
                                        <option value="2">name   surname</option>
                                    </select>
                                </div>
                            </div>
                        </div><!-- /.pad -->
                    </div>
                </div><!-- /.row - inside box -->

            </div><!-- /.box-body -->
            <div class="box-footer">
                <div class="row">
                    <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                        <div class="col-sm-6">
                            <input class="btn btn-primary btn-lg" type="submit" id="cancelSubmit">บันทึกรายการสอบเทียบ</button>
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

        $("#modelId").on("change", function () {
            var id = $(this).find('option:selected').val();
            $.ajax({
                url: "../ajaxHelper/findModel.htm",
                data: {"modelId": id}, // serializes the form's elements.
                success: function (result)
                {
                    var obj = JSON.parse(result);
                    $("input[name='locationBy']").val(obj.locationBy);
                    $("input[name='locationReturn']").val(obj.locationReturn);
                    $("input[name='cerNo']").each(function (i, cerNoInput) {
                        $(cerNoInput).val(obj.cerNo);
                    });
                    $.ajax({
                        url: "../ajaxHelper/findMeasure.htm",
                        data: {"measureId": obj.measureId}, // serializes the form's elements.
                        success: function (result)
                        {
                            var obj = JSON.parse(result);
                            $("input[name='measureName']").val(obj.fullName);

                        }
                    });
                }
            });
        });

        $("#processId").on("change", function () {
            var id = $(this).find('option:selected').val();
            $.ajax({
                url: "../ajaxHelper/findProcess.htm",
                data: {"processId": id}, // serializes the form's elements.
                success: function (result)
                {
                    var obj = JSON.parse(result);
                    $("input[name='processCode']").val(obj.processCode);
                    $("input[name='processSubject']").val(obj.processSubject);
                    $("input[name='processBy']").val(obj.processBy);

                }
            });
        });

        $("#modelId").trigger("change");
        $("#processId").trigger("change");
    });

</script>
<jsp:include page="include/include_footer.jsp" flush="true"></jsp:include>

