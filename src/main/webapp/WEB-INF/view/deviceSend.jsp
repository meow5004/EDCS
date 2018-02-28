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

                <h3 class="box-title">รับอุปกรณ์สอบเทียบกลับคืน</h3>
                <div style="float: right;"><div style="float: left; margin-top: 10px; margin-right: 5px;"><b>เลือกทั้งหมด</b></div><input type="checkbox"  name="selectAllCalId" style="width: 35px; height: 35px;"></div>
            </div><!-- /.box-header -->
            <div class="box-body container">
                <div class="row">
                    <div class="col-sm-12">

                        <table id="returnedDeviceTable" class="dataTable hover cell-border nowrap compact">
                            <thead>
                                <tr>
                                    <th style="text-align: center;">เลขที่</th>
                                    <th style="text-align: center;">หน่วยงาน</th>
                                    <th style="text-align: center;">รหัสเครื่องวัด</th>
                                    <th style="text-align: center;">ชื่อเครื่องวัด</th>
                                    <th style="text-align: center;">อนุมัติคำขอ</th>
                                    <th style="text-align: center;">สอบเทียบ</th>
                                    <th style="text-align: center;">ผล</th>
                                    <th style="text-align: center;">หมดอายุ</th>
                                    <th style="text-align: center;">สถานะเครื่อง</th>
                                    <th style="text-align: center;">เลือก</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>

                    </div>
                </div><!-- /.row - inside box -->
            </div><!-- /.box-body -->
            <div class="box-footer">
                <div class="row">
                    <div class="col-xs-12 text-center" style="border-right: 1px solid #f4f4f4">
                        <div class="col-sm-12">
                            <button class="btn btn-primary btn-lg" id="deviceReceiveBack" style="width: 100%;">รับอุปกรณ์กลับคืน</button>
                        </div>
                    </div>
                </div><!-- /.row -->
            </div><!-- /.box-footer -->
        </div><!-- /.box -->        

    </div>

</div>
<jsp:include page="include/include_footer.jsp" flush="true"></jsp:include>
    <!-- Font Awesome Icons -->
    <script src="../font-awesome/js/fontawesome-all.js" type="text/javascript"></script>
    <script>
        $(document).ready(function () {
            availableTable = $('#returnedDeviceTable').DataTable({
                responsive: {
                    details: {
                        type: 'inline'
                    }
                }, columnDefs: [
                    {responsivePriority: 1, targets: 0},
                    {responsivePriority: 2, targets: -1}
                ],
                "columns": [
                    {"data": "calCode", "target": 0},
                    {"data": "associateDep.fullName", "target": 1},
                    {"data": "associateMeasure.measureCode", "target": 2, responsivePriority: 1},
                    {"data": "associateMeasure.fullName", "target": 3},
                    {"data": "requestApproverStatus", "target": 4, render: function (data, type, row, meta) {
                            var userApprover = row.associateRequestApproverByUser;
                            var date = "<div class='row' style='padding:5px'>" +
                                    "<div class='col-sm-10 '>" +
                                    formatDateFromJavaDateJSONEncoded(row.requestApproverOn)
                                    + "</div>"
                                    + "</div>";

                            var message = row.requestComment;
                            var sender = "<div class='row' style='padding:5px'>" +
                                    "<div class='col-sm-10 '>" +
                                    userApprover.userName
                                    + "</div>"
                                    + "</div>";
                            var bootboxFunction = "bootbox.alert({size:\"small\",title:\"ความคิดเห็นคำขอสอบเทียบ\",message:$(this).data(\"reqcomment\")})";
                            var requestDataComment = "<div class='row'>"
                                    + "<button class=\"btn btn-success\" data-reqcomment='" + message + "' onclick='" + bootboxFunction + "'>"
                                    + "<i class=\"fa fa-comment\">"
                                    + "</button>"
                                    + "</div>";
                            var showData = date;
                            if (userApprover != null) {
                                showData += sender;
                                if (message != null && message.length > 0) {
                                    showData += requestDataComment;
                                }
                            }
                            return showData;
                        }, "createdCell": function (td, cellData, rowData, row, col) {
                            if (rowData.requestApproverOn != null) {
                                var date = formatDateFromJavaDateJSONEncoded(rowData.requestApproverOn);
                                var title = " อนุมัติการขอสอบเทียบเมื่อวันที่ " + date;
                                $(td).prop("title", title);
                            }
                        }
                        , searchable: false},
                    {
                        "data": "associateCalibrationAttachStatusByUser", target: "5", render: function (data, type, row, meta) {
                            var date = formatDateFromJavaDateJSONEncoded(row.calibrationAttachStatusOn);
                            console.log(date);
                            return date + " <br/>" + data.userName;
                        }, "createdCell": function (td, cellData, rowData, row, col) {
                            if (rowData.calibrationAttachStatusOn != null) {
                                var date = formatDateFromJavaDateJSONEncoded(rowData.calibrationAttachStatusOn);
                                var title = " สอบเทียบวันที่ " + date;
                                $(td).prop("title", title);
                            }
                        }
                    },
                    {"data": "associateStatusCaldoc.statusCaldocName", "target": 6, render: function (data, type, row, meta) {
                            var message = row.comment;
                            var colorizationResult = "";
                            if (data == "ผ่าน") {
                                colorizationResult = "<span style='color:green;font-weight:bold;font-size:1.5em'>" + data + "</span>";
                            } else if (data == "ไม่ผ่าน") {
                                colorizationResult = "<span style='color:red;font-weight:bold;font-size:1.5em'>" + data + "</span>";
                            } else {
                                colorizationResult = "<span style=font-weight:bold;font-size:1.5em'>" + data + "</span>";
                            }
                            var calibrateResult = "<div class='row' style='padding:5px'>" +
                                    "<div class='col-sm-10 '>" +
                                    colorizationResult
                                    + "</div>"
                                    + "</div>";

                            var bootboxFunction = "bootbox.alert({size:\"small\",title:\"หมายเหตุ\",message:$(this).data(\"comment\")})";
                            var requestDataComment = "<div class='row'>"
                                    + "<button class=\"btn btn-success \" data-comment='" + message + "' onclick='" + bootboxFunction + "'>"
                                    + "<i class=\"fa fa-comment\"></i>"
                                    + "</button>"
                                    + "</div>";
                            var showData = "";
                            if (calibrateResult != null && calibrateResult.length > 0) {
                                showData += calibrateResult;
                            }
                            if (message != null && message.length > 0) {
                                showData += requestDataComment;
                            }
                            if (type == "filter") {
                                return data;
                            }
                            return showData;

                        }, "createdCell": function (td, cellData, rowData, row, col) {
                            if (rowData.calibrationAttachStatusOn != null) {
                                var date = formatDateFromJavaDateJSONEncoded(rowData.calibrationAttachStatusOn);
                                var title = " สอบเทียบวันที่ " + date;
                                $(td).prop("title", title);
                            }
                        }

                    },
                    {"data": "dueDate", "target": 7, render: function (data, type, row, meta) {
                            if (data != null) {
                                var date = formatDateFromJavaDateJSONEncoded(data);
                                var end = moment(date, "DD/MM/YY");
                                return date + "<br/> (" + end.fromNow() + ")";
                            } else {
                                return "-";
                            }
                        },
                        "createdCell": function (td, cellData, rowData, row, col) {
                            if (cellData != null) {
                                var date = formatDateFromJavaDateJSONEncoded(cellData);
                                var end = moment(date, "DD/MM/YY");
                                if (end.isBefore(moment().add('days', 30))) {
                                    $(td).css("color", "red");
                                }
                                if (end.isBefore()) {
                                    $(td).css("font-weight", "bolder");
                                }
                            }
                        }},
                    {target: 8, "data": function (data, type, row, meta) {
                            var choice = "<div class='container-fluid'>"
                                    + "<div class='row'>"
                                    + "<select id='condition-" + meta.row + "' data-row='" + meta.row + "'>"
                                    + "<c:forEach items='${conditions}' var = 'condition'>"
                                    + "<option value='${condition.equipConId}'>${condition.equipConNameTh}</option>"
                                    + "</c:forEach>"
                                    + "</select>"
                                    + "</div>"
                                    + "<div class='row'>"
                                    + "<button class='btn' id='equipconcomment-" + meta.row + "' data-row='" + meta.row + "'>"
                                    + '<i class="fas fa-pencil-alt" data-fa-transform="shrink-10 up-.5" data-fa-mask="fas fa-comment fa-2x" style="font-size:2em"></i>'
                                    + "</button>"
                                    + "</div>"
                                    + "</div>";
                            return choice;
                        }, "createdCell": function (td, cellData, rowData, row, col) {
                            $(td).find("select[id*='condition']").val(rowData.equipConId)
                            $(td).find("select").trigger("change");
                            $(td).css("white-space", "normal");
                            var comment = rowData.conditionComment;
                            console.log(comment);
                            if (comment.length > 0) {
                                $(td).find("button[id*='comment']").addClass("btn-success");
                            }
                        }, "searchable": false
                    }, {
                        data: function (data, type, row, meta) {
                            return  "<input type='checkbox' id='sendRow-" + meta.row + "' name='receivceCalId[]' data-row='" + meta.row + "'data-equipconcomment='" + data.conditionComment + "' data-equipconId='" + data.equipConId + "' value='" + data.calId + "'>";
                        }
                    }
                ],
                "createdRow": function (row, data, index) {
                },
                "ajax": {
                    "url": "../ajaxHelper/getStickerPrintedDevice.htm"
                },
                "dom": '<"row"f><"row"ti>',
                "order": [[0, 'asc']],
                "paging": false
            });
            availableTable.on('search.dt', function () {
                var allCheckboxes = availableTable.$("input[type='checkbox'][name='calId[]']", {"filter": "applied"});
                var checked = availableTable.$("input[type='checkbox'][name='calId[]']:checked", {"filter": "applied"});
                if (allCheckboxes.length > checked.length) {
                    $("input[id='selectAllCalId'][type='checkbox']").prop('checked', false);
                } else {
                    $("input[id='selectAllCalId'][type='checkbox']").prop('checked', true);
                }
            });

            $("#deviceReceiveBack").on("click", function () {
                var calibrationDeviceCheckModels = [];
                availableTable.$("input[type='checkbox'][name='sendCalId[]']:checked").each(function (i) {
                    var calibrationDeviceCheckModel = {};
                    calibrationDeviceCheckModel["calId"] = $(this).val();
                    calibrationDeviceCheckModel["conditionComment"] = $(this).data("equipconcomment");
                    calibrationDeviceCheckModel["equipConId"] = $(this).data("equipconid");
//approver is this user

                    calibrationDeviceCheckModels.push(calibrationDeviceCheckModel);
                });
                recModels = JSON.stringify({
                    'calibrationDeviceCheckModels': calibrationDeviceCheckModels
                });
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "../calibration/receiveCalibratedDeviceBack.htm",
                    data: recModels,
                    dataType: "html",
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    success: function (result)
                    {
                        availableTable.ajax.reload();
                    }
                });
            });

            $("input[name='selectAllCalId'][type='checkbox']").on("click", function (event) {
                if (this.checked) {
                    // Iterate each checkbox
                    availableTable.$("input[type='checkbox'][name='sendCalId[]']", {"filter": "applied"}).each(function () {
                        this.checked = true;
                    });
                } else {
                    availableTable.$("input[type='checkbox'][name='sendCalId[]']", {"filter": "applied"}).each(function () {
                        this.checked = false;
                    });
                }
            });
            $(document).on("click", "button[id^=equipconcomment-]", function () {

                var button = $(this);
                var row = $(this).data("row");
                var associateCheckbox = "input[type='checkbox'][id*='sendRow'][data-row='" + row + "']";
                var oldComment = $(associateCheckbox).data("equipconcomment");
                bootbox.prompt({
                    size: "small",
                    inputType: "textarea",
                    title: "ใส่คอมเมนท์",
                    value: oldComment,
                    callback: function (result) { /* result = String containing user input if OK clicked or null if Cancel clicked */
                        var equipconcomment = result;
                        if (equipconcomment != null) {
                            $(associateCheckbox).data("equipconcomment", equipconcomment);
                            $(associateCheckbox).attr("data-equipconcomment", equipconcomment);
                            if (equipconcomment.length > 0) {
                                $(button).addClass("btn-success");
                            } else {
                                $(button).removeClass("btn-success");
                            }
                        }
                    }
                });
            });
            $("select[id^=condition-]").trigger("change");
        });
</script>