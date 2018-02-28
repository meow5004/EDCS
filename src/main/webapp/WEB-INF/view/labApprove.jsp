<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>

    <div class="row">

        <div class="col-lg-12" style="text-align: left;">
            <div class="box box-primary" id="loading-example">
                <div class="box-header">
                    <!-- tools box -->
                    <i class="fa fa-cloud"></i>

                    <h3 class="box-title">อนุมัติรายงานผลการสอบเทียบ</h3>
                    <div style="float: right;"><div style="float: left; margin-top: 10px; margin-right: 5px;"><b>เลือกทั้งหมด</b></div><input type="checkbox" name="selectAllCalId" style="width: 35px; height: 35px;"></div>
                </div><!-- /.box-header -->
                <div class="box-body container no-padding">
                    <div class="row">
                        <table id="checkLabTable" style="width: 90%;" class="dataTable hover cell-border nowrap compact">
                            <thead>
                                <tr>
                                    <th style="text-align: center;">รายงานเลขที่</th>
                                    <th style="text-align: center;">หน่วยงาน</th>
                                    <th style="text-align: center;">รหัสเครื่องวัด</th>
                                    <th style="text-align: center;">ชื่อเครื่องวัด</th>
                                    <th style="text-align: center;">อนุมัติคำขอ</th>
                                    <th style="text-align: center;">รับเครื่อง</th>
                                    <th style="text-align: center;">สอบเทียบ</th>
                                    <th style="text-align: center;">ผล</th>
                                    <th style="text-align: center;">รายละเอียด</th>
                                    <th style="text-align: center;">เลือก</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div><!-- /.row - inside box -->
                </div><!-- /.box-body -->
                <div class="box-footer">
                    <div class="row">
                        <div class="col-xs-12 text-center" style="border-right: 1px solid #f4f4f4">
                            <div class="col-sm-3">
                                <button class="btn btn-primary btn-lg" id="approveLapResult" style="width: 100%;">อนุมัติ</button>
                            </div>
                            <div class="col-sm-3">
                                <button class="btn btn-warning btn-lg" id="disapproveLapResult" style="width: 100%;">ไม่อนุมัติ</button>
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
        availableTable = $('#checkLabTable').DataTable({
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
                        var message = row.requestComment;
                        var sender = "<div class='row'>" +
                                userApprover.userName
                                + "</div>";
                        var bootboxFunction = "bootbox.alert({size:\"small\",title:\"ความคิดเห็นคำขอสอบเทียบ\",message:$(this).data(\"reqcomment\")})";
                        var requestDataComment = "<div class='row'>"
                                + "<button class=\"btn btn-success\" data-reqcomment='" + message + "' onclick='" + bootboxFunction + "'>"
                                + "<i class=\"fa fa-comment\"></i>"
                                + "</button>"
                                + "</div>";
                        var showData = "";
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
                {"data": "receiveStatus", "target": 5, render: function (data, type, row, meta) {
                        var userApprover = row.associateReceiveStatusByUser;
                        var condition = row.associateEquipCon.equipConNameTh;
                        var message = row.conditionComment;
                        var conditionMessage = condition + " " + message;
                        var sender = "<div class='row' style='padding:5px'>" +
                                "<div class='col-sm-12 '>" +
                                userApprover.userName
                                + "</div>"
                                + "</div>";
                        var bootboxFunction = "bootbox.alert({size:\"small\",title:\"สถานะเครื่อง\",message:$(this).data(\"conditionmessage\")})";
                        var requestDataComment = "<div class='row'>"
                                + "<button class=\"btn btn-success\" data-equipconcomment='" + message + "' data-contition='" + condition + "' data-conditionmessage='" + conditionMessage + "' onclick='" + bootboxFunction + "'>"
                                + '<i class=\"fa fa-comment\"></i>'
                                + "</button>"
                                + "</div>";
                        var showData = "";
                        if (userApprover != null) {
                            showData += sender;
                            if (message != null && message.length > 0) {
                                showData += requestDataComment;
                            }
                        }
                        return showData;
                    }, "createdCell": function (td, cellData, rowData, row, col) {
                        if (rowData.receiveStatusOn != null) {
                            var date = formatDateFromJavaDateJSONEncoded(rowData.receiveStatusOn);
                            var title = " รับอุปกรณ์เข้าสอบเทียบวันที่ " + date;
                            $(td).prop("title", title);
                        }
                    }, searchable: false
                },
                {
                    "data": "associateCalibrationAttachStatusByUser", target: "6", render: function (data, type, row, meta) {
                        return data.userName;
                    }, "createdCell": function (td, cellData, rowData, row, col) {
                        if (rowData.calibrationAttachStatusOn != null) {
                            var date = formatDateFromJavaDateJSONEncoded(rowData.calibrationAttachStatusOn);
                            var title = " สอบเทียบวันที่ " + date;
                            $(td).prop("title", title);
                        }
                    }
                },
                {"data": "associateStatusCaldoc.statusCaldocName", "target": 7, render: function (data, type, row, meta) {
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
                {"data": "calId", "target": 8, render: function (data, type, row, meta) {
                        var previewData = "<div class='row' style='padding:5px'>" +
                                '<a href="../ajaxHelper/previewReportFromInputDataModel.htm?calId=' + data + '" target="_blank" class="btn btn-default"><i class=\"fa fa-table fa-3x\"></i></a>'
                                + "</div>";
                        return previewData;
                    }, "searchable": false},
                {"data": "calId", "target": 9, render: function (data, type, row, meta) {
                        var choice = "<input type='checkbox' name='calId[]' value='" + data + "'>";
                        return choice;
                    }, "searchable": false}
            ],
            "createdRow": function (row, data, index) {
            },
            "ajax": {
                "url": "../ajaxHelper/listFinishCalibrationWaitForApproval.htm"
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
        $("input[name='selectAllCalId'][type='checkbox']").on("click", function (event) {
            if (this.checked) {
                // Iterate each checkbox
                availableTable.$("input[type='checkbox'][name='calId[]']", {"filter": "applied"}).each(function () {
                    this.checked = true;
                });
            } else {
                availableTable.$("input[type='checkbox'][name='calId[]']", {"filter": "applied"}).each(function () {
                    this.checked = false;
                });
            }
        });

        $("#approveLapResult").on("click", function () {
            var checkedValues = [];
            availableTable.$("input[type='checkbox'][name='calId[]']:checked", {"filter": "applied"}).each(function (i) {
                checkedValues[i] = $(this).val();
            });

            $.ajax({
                type: "POST",
                async: false,
                url: "../calibration/approveLapResult.htm",
                data: {"checkedValues": checkedValues.toString()},
                success: function (result)
                {
//                    bootbox.alert({
//                        backdrop: true,
//                        className: "successFont",
//                        message: result,
//                        callback: function () { /* your callback code */
//                        }
//                    });
                    availableTable.ajax.reload();
                }
            });
        });

        $("#disapproveLapResult").on("click", function () {
            var checkedValues = [];
            availableTable.$("input[type='checkbox'][name='calId[]']:checked").each(function (i) {
                checkedValues[i] = $(this).val();
            });

            $.ajax({
                type: "POST",
                async: false,
                url: "../calibration/disapproveLapResult.htm",
                data: {"checkedValues": checkedValues.toString()},
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
        });
    });
</script>
