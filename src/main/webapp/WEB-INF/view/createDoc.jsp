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
                <div class="box-body container">
                    <jsp:useBean id="now" class="java.util.Date" />
                    <fmt:formatDate value="${now}"  
                                    type="date" 
                                    pattern="yyyy-MM-dd"
                                    var="theFormattedDate" />

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="pad">
                                <div class="row">
                                    <form:hidden path="calId"  class="form-control" required="required" />
                                    <form:hidden path="calibrationStatusBy"  class="form-control" required="required" />
                                    <div class="col-sm-2">
                                        <b>เลขที่</b>
                                    </div>
                                    <div class="col-sm-4">
                                        <form:input path="calCode"  class="form-control" required="required"  readonly="true"/>
                                    </div>
<!--                                   <form:input path="calibrationStatusOn" value="${theFormattedDate}" class="form-control" required="required"  readonly="true"/></div>-->
                                    <div class="col-sm-2">
                                        <b>วันที่รายงาน</b>
                                    </div>
                                    <div class="col-sm-4">
                                        ${theFormattedDate}
                                    </div>
                                </div>

                                <div class="row">
                                    <hr>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">
                                        <b>หน่วยงาน/บริษัท</b>
                                    </div>
                                    <div class="col-sm-4">
                                        <form:input path="associateDep.fullName" readonly="true" class="form-control"/>
                                        <form:hidden path="depId" readonly="true" class="form-control" required="required"/>
                                    </div>
                                </div>

                                <div class="row">
                                    <hr>
                                </div>
                                <div class="row">
                                    <form:input path="measureId"  class="form-control" required="required"  type="hidden"/>
                                    <div class="col-sm-2">
                                        <b>ชื่อเครื่องวัด/ทดสอบ</b>
                                    </div>
                                    <div class="col-sm-4">
                                        <input type="text" name="measureFullName" style="width:100%;" value="${calibration.associateMeasure.fullName}" disabled>
                                    </div>

                                    <div class="col-sm-2"><b>รหัสเครื่องวัดและทดสอบ</b>
                                    </div>
                                    <div class="col-sm-4">
                                        <input type="text" name="measureCode" style="width:100%;" value=" ${calibration.associateMeasure.measureCode}" disabled>
                                    </div>
                                </div>
                                <div class="row">
                                    <hr>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">
                                        <b>
                                            สภาพภายนอก : 
                                        </b>
                                    </div>

                                    <div class="col-sm-4"> 
                                        <form:select  path="equipConId" items="${equipConditions}" itemLabel="fullName" itemValue="equipConId" required="required" style="width:100%" />
                                    </div>
                                    <div class="col-sm-2"> 
                                        เนื่องจาก
                                    </div>
                                    <div class="col-sm-4 "> 
                                        <form:textarea  path="requestComment" style="width:100%" />
                                    </div>
                                </div>
                                <div class="row">
                                    <hr>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">
                                        <b>
                                            สถานที่สอบเทียบ : 
                                        </b>
                                    </div>
                                    <div class="col-sm-4"> 
                                        <form:select path="calibrationLocation" required="required" style="width:100%">
                                            <form:option  value="inside" label="สถานที่ภายใน" />           
                                            <form:option  value="outside" label="สถานที่ภายนอก" /> 
                                        </form:select>
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                    <hr>
                                </div>
                                <div class="row">
                                    <div class="col-sm-4"><b>มาตรฐานเปรียบเทียบอ้างอิง</b></div>
                                </div>
                                <br/>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="col-md-4">รายงานเลขที่</div>
                                        <div class="col-md-6"><input type="text" name="cerNo" style="width:100%;" disabled></div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="col-md-4">โดย</div>
                                        <div class="col-md-6"><input type="text" name="locationBy" style="width:100%;" value="สมาคมส่งเสริมเทคโนโลยี (ไทย-ญี่ปุ่น)" disabled></div>
                                    </div>
                                </div>
                                <br/>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="col-md-4">แม่แบบ รหัส</div>
                                        <div class="col-md-6"> 
                                            <form:select path="modelId" class="form-control" required="required" >
                                                <form:option value="" label="" />
                                                <form:options items="${models}" itemLabel="modelCode" itemValue="modelId" />
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="col-md-4">ชื่อ</div>
                                        <div class="col-md-6"><input type="text" name="measureName" style="width:100%;" value="STEEL RULE 7" disabled></div>
                                    </div>
                                </div>
                                <br/>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="col-md-4">การสอบกลับ</div>
                                        <div class="col-md-6"><input type="text" name="locationReturn" style="width:100%;" value="สมาคมส่งเสริมเทคโนโลยี (ไทย-ญี่ปุ่น)" disabled></div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="col-md-4">CER.NO</div>
                                        <div class="col-md-6"><input type="text" name="cerNo" disabled style="width:100%;"></div>
                                    </div>
                                </div>



                                <div class="row">
                                    <hr>
                                </div>
                                <div class="row">
                                    <div class="col-sm-4">
                                        <b>วิธีการวัด</b>
                                    </div>
                                </div>
                                <br/>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="col-sm-4"> รหัส</div>
                                        <div class="col-sm-6">
                                            <form:select path="processId" class="form-control" required="required" >
                                                <form:option value="" label="" />
                                                <form:options items="${processes}" itemLabel="processCode" itemValue="processId" />
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="col-sm-4">เรื่อง</div> 
                                        <div class="col-sm-6"><input type="text" name="processSubject" style="width:80%;" value="สมาคมส่งเสริมเทคโนโลยี (ไทย-ญี่ปุ่น)" disabled></div>
                                    </div>
                                </div>
                                <br/>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="col-md-4">
                                            โดย
                                        </div> 
                                        <div class="col-md-6">
                                            <textarea name="processBy" cols="40" rows="4"  disabled>
                                            
                                            </textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <hr>
                                </div>
                                <div class="row">
                                    <div  class="col-md-2"><b>หน่วยวัด</b></div>
                                    <div  class="col-md-4">
                                        <form:select path="unitId" class="form-control" required="required" >
                                            <form:option value="" label="" />
                                            <form:options items="${units}" itemLabel="unitNameTh" itemValue="unitId" />
                                        </form:select>
                                        <%--${unitNameEn}--%>
                                    </div>
                                </div>
                                <div class="row">
                                    <hr>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">
                                        <b>
                                            ค่าความผิดพลาดที่ยอมรับได้หรือเท่ากับ( + / - )
                                        </b>
                                    </div>
                                    <div class="col-sm-1">
                                        <form:input required="required" path="calError" type="number" step="any" onchange="this.value=Math.abs(this.value);" /></div>
                                    <div class="unitLabel">
                                    </div>
                                </div>
                                <div class="row">
                                    <hr>
                                </div>
                                <div class="row">
                                    <div class="col-md-2">
                                        <b>
                                            ผู้สอบเทียบ:/ผู้ตรวจสอบ
                                        </b>
                                    </div>
                                    <div class="col-md-4">
                                        <input type="text" value="${calibrator.userName}" style="width: 100%" disabled>
                                    </div>
                                    <form:hidden path="calibratorBy"/>
                                </div>
                                <br/>
                                <div class="row">
                                    <div class="col-md-2">
                                        <b>
                                            ผู้อนุมัติ
                                        </b>
                                    </div>
                                    <div class="col-md-4">
                                        <form:select path="approveStatusBy" required="required"  data-width="100%"  style="width: 100%">
                                            <form:option value="" label="" />
                                            <form:options items="${approvers}" itemLabel="userName" itemValue="empId"></form:options>
                                        </form:select>
                                    </div>
                                </div>
                            </div><!-- /.pad -->
                        </div>
                    </div><!-- /.row - inside box -->

                </div><!-- /.box-body -->
                <div class="box-footer">
                    <div class="row">
                        <div class="col-sm-4">
                            <input class="btn btn-primary" type="submit" id="cancelSubmit" value="บันทึกรายการสอบเทียบ">
                        </div>
                        <div class="col-sm-6">
                            <button class="btn btn-default" onclick="window.history.back()">กลับไปหน้าที่แล้ว</button>

                        </div>

                    </div><!-- /.row -->
                </div><!-- /.box-footer -->
            </div><!-- /.box -->        
        </form:form>
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
                        $("textarea[name='processBy']").val(obj.processBy);

                    }
                });
            });


            $('#unitId').on('change', function () {
                $(".unitLabel").text($(this).children(':selected').text());
            });

            $("#unitId").trigger("change");
            $("#modelId").trigger("change");
            $("#processId").trigger("change");
        });

    </script>
    <jsp:include page="include/include_footer.jsp" flush="true"></jsp:include>

