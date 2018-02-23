<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<div class="row">

    <div class="col-lg-12" style="text-align: left;">
        <div class="box box-primary" id="loading-example">
            <div class="box-header">
                <!-- tools box -->
                <i class="fa fa-cloud"></i>

                <h3 class="box-title">ส่งอุปกรณ์สอบเทียบ</h3>
                <div style="float: right;"><div style="float: left; margin-top: 10px; margin-right: 5px;"><b>เลือกทั้งหมด</b></div><input type="checkbox" name="selectAllMeasureId" style="width: 35px; height: 35px;"></div>
            </div><!-- /.box-header -->
            <div class="box-body container">
                <div class="row text-center" style="border-right: 1px solid #f4f4f4">
                    <div class="col-sm-4 col-sm-offset-3" style="text-align: left;font-size: 1.5em;border: 1px solid black">
                        เลือกแผนก 
                        <select id="calibDepsList">
                            <c:forEach items="${deps}" var = "department">
                                <c:choose>
                                    <c:when test="${department.depId == user.depId}">
                                        <option value="${department.depId}" selected>${department.fullName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${department.depId}">${department.fullName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>

                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <table id="checkDocTable"  class="dataTable hover cell-border nowrap compact" style="width: 100%">
                            <thead>
                                <tr>
                                    <th>หน่วยงาน</th>
                                    <th>รหัสเครื่องวัด</th>
                                    <th>ชื่อเครื่องวัด</th>
                                    <th>สอบเทียบล่าสุด</th>
                                    <th>วันหมดอายุสอบเทียบ</th>
                                    <th>ผู้แจ้ง</th>
                                    <th>เลือกสภาพอุปกรณ์</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div><!-- /.row - inside box -->
                </div>
            </div><!-- /.box-body -->
            <div class="box-footer">
                <div class="row">
                    <div class="col-xs-12 text-center" style="border-right: 1px solid #f4f4f4">
                        <div class="col-sm-6" style="font-size:1.5em;text-align: right">
                            ผู้อนุมัติ
                            <select id="approverList">
                                <option value="" selected></option>
                                <c:forEach items="${approver}" var = "approverUser">
                                    <option value="${approverUser.empId}">${approverUser.userName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-6">
                            <button class="btn btn-primary btn-lg" id="sendMeasureToApprove" style="width: 100%;">ส่งอุปกรณ์</button>
                        </div>
                    </div>
                </div>
            </div><!-- /.box-footer -->
        </div><!-- /.box -->        

    </div>

</div>
<jsp:include page="include/include_footer.jsp" flush="true"></jsp:include>
<script>
    $(document).ready(function () {
        availableTable = $('#checkDocTable').DataTable({
            columnDefs: [
                {responsivePriority: 1, targets: 0},
                {responsivePriority: 2, targets: -1}
            ],
            "dom": '<"row"f><"scrollBox row"ti>',
            "columns": [
                {"data": "associateDep.fullName", "target": 0},
                {"data": "associateMeasure.measureCode", "target": 1, responsivePriority: 1},
                {"data": "associateMeasure.fullName", "target": 2},
                {"data": "approveStatusOn", "target": 3, render: function (data, type, row, meta) {

                        if (data != null) {
                            return formatDateFromJavaDateJSONEncoded(data);
                        } else {
                            return "<span style='font-weight:bolder;color:red'>ไม่เคยมีการสอบเทียบ</span>";
                        }
                    }},
                {"data": "dueDate", "target": 4, render: function (data, type, row, meta) {

                        if (data != null) {
                            var time = moment.utc(formatDateFromJavaDateJSONEncoded(data), "DD/MM/YYYY");
                            var dueDay = time.get("date") - moment().get("date");
                            var formatted = moment.utc(data).add(1900, 'y').format("DD/MM/YYYY");
                            if (dueDay == 0) {
                                return formatted + "<span style='color:red'>( หมดอายุวันนี้ )</span>";
                            } else if (dueDay < 0) {
                                return formatted + "<span style='color:red;font-weight:bolder'>( หมดอายุไปแล้ว " + Math.abs(dueDay) + " วัน )</span>";
                            } else {
                                return formatted + "( อีก" + Math.abs(dueDay) + " วัน หมดอายุ )";
                            }

                        } else {
                            return "<span style='font-weight:bolder;color:red'>ไม่เคยมีการสอบเทียบ</span>";
                        }
                    }},
                {"data": "requestBy", "target": 5},
                {"target": 6, responsivePriority: 1, "data": function (data, type, row, meta) {
                        var choice = "<div class='container-fluid'>" +
                                "<div class='row'>"
                                + "<div class='col-sm-4 no-padding'>"
                                + "<input style='margin:0px' type='checkbox' id='requestRow-" + meta.row + "' name='requestMeasureId[]' data-row='" + meta.row + "'data-comment='' value='" + data.measureId + "'>"
                                + "</div>"
                                + "<div class='col-sm-8'>"
                                + "<button class='btn' id='comment-" + meta.row + "' data-row='" + meta.row + "'>"
                                + "note"
                                + "</button>"
                                + "</div>";
                        +"</div>";
                        +"</div>";
                        return choice;
                    }, "createdCell": function (td, cellData, rowData, row, col) {
                        $(td).find("select").trigger("change");
                        $(td).css("white-space", "normal");
                    }, "searchable": false}
            ],
            "createdRow": function (row, data, index) {

                var time = moment.utc(data.dueDate).add(1900, 'y');
                var dueDay = time.get("date") - moment().get("date");
                if (data.calId < 1) {
                    $(row).addClass('newData');
                } else if (dueDay < 0) {
                    $(row).addClass('danger-alert');
                }
            },
            "ajax": {
                "url": "../ajaxHelper/getNearDueAndNewCalibration.htm",
                "data": {
                    "dayDue": 30
                }
            },
            responsive: {
                details: {
                    type: 'inline'
                }
            },
            "order": [[0, 'asc']],
            "paging": false,
            "drawCallback": function (settings) {
                var api = this.api();
                api.columns.adjust().responsive.recalc();
            }
        });
        availableTable.on('search.dt', function () {
            var allCheckboxes = availableTable.$("input[type='checkbox'][name='requestMeasureId[]']", {"filter": "applied"});
            var checked = availableTable.$("input[type='checkbox'][name='requestMeasureId[]']:checked", {"filter": "applied"});
            if (allCheckboxes.length > checked.length) {
                $("input[id='selectAllMeasureId'][type='checkbox']").prop('checked', false);
            } else {
                $("input[id='selectAllMeasureId'][type='checkbox']").prop('checked', true);
            }
        });
        $("#sendMeasureToApprove").on("click", function () {
            var approver = $("#approverList").val();
            if (availableTable.$("input[type='checkbox'][name='requestMeasureId[]']:checked", {"filter": "applied"}).length > 0) {
                if (approver !== null && approver.length > 0) {
                    var calibrationRequestModels = [];
                    availableTable.$("input[type='checkbox'][name='requestMeasureId[]']:checked").each(function (i) {
                        var index = i;
                        var calibrationRequestModel = {};
                        calibrationRequestModel["measureId"] = $(this).val();
                        calibrationRequestModel["requestComment"] = $(this).data("comment");
                        calibrationRequestModel["approverId"] = approver;

                        calibrationRequestModels.push(calibrationRequestModel);
                    });
                    reqModels = JSON.stringify({
                        'calibrationRequestModels': calibrationRequestModels
                    });
                    $.ajax({
                        type: "POST",
                        async: false,
                        url: "../calibration/createCalibration.htm",
                        data: reqModels,
                        dataType: "html",
                        contentType: 'application/json',
                        mimeType: 'application/json',
                        success: function (result)
                        {
                            bootbox.alert({
                                backdrop: true,
                                className: "dangerFont",
                                message: result,
                                callback: function () { /* your callback code */
                                }
                            });
                            location.reload();
                        }
                    });
                } else {
                    bootbox.alert("<h3 style='color:red'>-กรุณาเลือกผู้อนุมัติก่อนส่งอุปกรณ์</h3>");
                }
            } else {
                bootbox.alert("<h3 style='color:red'>-กรุณาเลือกอุปกรณ์ที่ต้องการส่งสอบเทียบ</h3>");
            }
        });


    });
    $("input[name='selectAllMeasureId'][type='checkbox']").on("click", function (event) {
        if (this.checked) {
            // Iterate each checkbox
            availableTable.$("input[type='checkbox'][name='requestMeasureId[]']", {"filter": "applied"}).each(function () {
                this.checked = true;
            });
        } else {
            availableTable.$("input[type='checkbox'][name='requestMeasureId[]']", {"filter": "applied"}).each(function () {
                this.checked = false;
            });
        }
    });

    //approval cond comment section
    $("#calibDepsList").on("change", function () {
        var url = "../ajaxHelper/getNearDueAndNewCalibration.htm?dayDue=30&&depId=" + $(this).val();
        availableTable.ajax.url(url).load();
        $.ajax({
            type: "POST",
            url: "../ajaxHelper/getRequestApproverByDepId.htm",
            data: {
                depId: $(this).val()
            },
            success: function (result)
            {
                var arr = JSON.parse(result);
                var options = "<option value=''></option>";
                arr.forEach(function (item) {
                    options += "<option value='" + item.empId + "'>" + item.userName + "</option>";
                });
                $("#approverList").html(options);
            }
        });


    });


    $(document).on("click", "button[id^=comment-]", function () {

        var button = $(this);
        var row = $(this).data("row");
        var associateCheckbox = "input[type='checkbox'][id*='requestRow'][data-row='" + row + "']";
        var oldComment = $(associateCheckbox).data("comment");
        bootbox.prompt({
            size: "small",
            title: "ใส่คอมเมนท์",
            value: oldComment,
            inputType: "textarea",
            callback: function (result) { /* result = String containing user input if OK clicked or null if Cancel clicked */
                var comment = result;
                if (comment != null) {
                    $(associateCheckbox).data("comment", comment);
                    $(associateCheckbox).attr("data-comment", comment);
                    if (comment.length > 0) {
                        $(button).addClass("btn-success");
                    } else {
                        $(button).removeClass("btn-success");
                    }
                }
            }
        });
    });
</script>

