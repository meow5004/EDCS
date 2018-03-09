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
                    <div class="col-sm-4 col-sm-offset-3" style="text-align: left;font-size: 1.5em;margin:-20px;">


                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <table id="checkDocTable"  class="dataTable hover cell-border nowrap compact" style="width: 100%">
                            <thead>
                                <tr>
                                    <th colspan="8">
                                        แผนก 
                                        <select id="calibDepsList" style="color: black">
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
                                    </th>
                                </tr>
                                <tr>
                                    <th>หน่วยงาน</th>
                                    <th>รหัสเครื่องวัด</th>
                                    <th>ชื่อเครื่องวัด</th>
                                    <th>สอบเทียบล่าสุด</th>
                                    <th>วันหมดอายุสอบเทียบ</th>
                                    <th>ผู้แจ้ง</th>
                                    <th>หมายเหตุ</th>
                                    <th>เลือก</th>
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
                            เลือกผู้อนุมัติ
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
<!-- Font Awesome Icons -->
<script src="../font-awesome/js/fontawesome-all.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        availableTable = $('#checkDocTable').DataTable({
            columnDefs: [
                {responsivePriority: 1, targets: 0},
                {responsivePriority: 2, targets: -1}
            ],
            "dom": '<"row"f><"row"ti>',
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
                            var date = formatDateFromJavaDateJSONEncoded(data);
                            var end = moment(date, "DD/MM/YYYY");
                            return date + "<br/> (" + end.fromNow() + ")";
                        } else {
                            return "-";
                        }
                    }},
                {"data": "requestBy", "target": 5, render: function (data, type, row, meta) {
                        if (data != null && data.length > 0) {
                            return data;
                        } else {
                            return "<span style='font-weight:bolder;color:red'>&#x2014;</span>";
                        }
                    }
                },
                {"target": 6, "data": function (data, type, row, meta) {
                        var choice = "<button class='btn' id='reqcomment-" + meta.row + "' data-row='" + meta.row + "'>"
                                + '<i class="fas fa-pencil-alt" data-fa-transform="shrink-10 up-.5" data-fa-mask="fas fa-comment fa-2x" style="font-size:2em"></i>'
                                + "</button>";
                        return choice;
                    }, "searchable": false},
                {"target": 7, responsivePriority: 1, "data": function (data, type, row, meta) {
                        var choice = "<input style='margin:0px' type='checkbox' id='requestRow-" + meta.row + "' name='requestMeasureId[]' data-row='" + meta.row + "'data-reqcomment='' value='" + data.measureId + "'>"
                        return choice;
                    }, "searchable": false}
            ],
            "createdRow": function (row, data, index) {
                var dataDate = data.dueDate;
                if (data.calId < 1) {
                    $(row).addClass('newData');
                }
                if (dataDate != null) {
                    var date = formatDateFromJavaDateJSONEncoded(data.dueDate);
                    var time = moment(date, "DD/MM/YYYY");
                    var dueDay = time.diff(moment(), 'days');
                    if (dueDay < 30 && dueDay > 0) {
                        $(row).addClass('warning');
                    } else if (dueDay <= 0) {
                        $(row).addClass('danger-alert');
                    }
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
                        calibrationRequestModel["requestComment"] = $(this).data("reqcomment");
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
//                            bootbox.alert({
//                                backdrop: true,
//                                className: "dangerFont",
//                                message: result,
//                                callback: function () { /* your callback code */
//                                }
//                            });
                            $("#calibDepsList").trigger("change");
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
    //approval cond reqcomment section
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
    $(document).on("click", "button[id^=reqcomment-]", function () {

        var button = $(this);
        var row = $(this).data("row");
        var associateCheckbox = "input[type='checkbox'][id*='requestRow'][data-row='" + row + "']";
        var oldComment = $(associateCheckbox).data("reqcomment");
        bootbox.prompt({
            size: "small",
            title: "ใส่คอมเมนท์",
            value: oldComment,
            inputType: "textarea",
            callback: function (result) { /* result = String containing user input if OK clicked or null if Cancel clicked */
                var reqcomment = result;
                if (reqcomment != null) {
                    $(associateCheckbox).data("reqcomment", reqcomment);
                    $(associateCheckbox).attr("data-reqcomment", reqcomment);
                    if (reqcomment.length > 0) {
                        $(button).addClass("btn-success");
                    } else {
                        $(button).removeClass("btn-success");
                    }
                }
            }
        });
    });
</script>

