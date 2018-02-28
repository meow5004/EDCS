<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>

    <div class="row">

        <div class="col-lg-12" style="text-align: left;">
            <div class="box box-primary" id="loading-example">
                <div class="box-header">
                    <!-- tools box -->
                    <i class="fa fa-cloud"></i>

                    <h3 class="box-title">ติดตามรายงานผลการสอบเทียบ</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <div class="form-group col-lg-4 col-sm-12">
                        <label  class="col-sm-2 control-label" style="font-weight: bold;">วันที่</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="startDateFilter" placeholder="Start...">
                        </div>
                    </div>
                    <div class="form-group col-lg-4 col-sm-12">
                        <label for="inputEmail3" class="col-sm-2 control-label" style="font-weight: bold;">ถึง</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="endDateFilter" placeholder="End...">
                        </div>
                    </div>
                    <div class="form-group col-lg-4 col-sm-12">
                        <label class="col-sm-2 control-label">สถานะ</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="statusFilter">
                                <option value="all">ทั้งหมด</option>
                                <option value="approvingRequest">อนุมัติใบคำขอ</option>
                                <option value="received">รับอุปกรณ์</option>
                                <option value="calibrate">สอบเทียบ</option>
                                <option value="approvingCalibration">อนุมัติ</option>
                                <option value="returned">รับคืน</option>
                            </select>
                        </div>
                    </div>
                
                </div>
                <div class="box-body container">
                    <div class="row">

                        <table style="min-width: 100%!important;" id="trackingTable" class="dataTable hover cell-border nowrap compact">
                            <thead>
                                <tr>
                                    <th style="text-align: center;">เลขที่</th>
                                    <th style="text-align: center;">หน่วยงาน</th>
                                    <th style="text-align: center;">เครื่องวัด</th>
                                    <th style="text-align: center;">ส่งคำขอ</th>
                                    <th style="text-align: center;">อนุมัติคำขอ</th>
                                    <th style="text-align: center;">รับอุปกรณ์</th>
                                    <th style="text-align: center;">สอบเทียบ </th>
                                    <th style="text-align: center;">อนุมัติ</th>
                                    <th style="text-align: center;">รับคืน</th>
                                    <th style="text-align: center;">สอบเทียบ</th>
                                    <th style="text-align: center;">หมดอายุ</th>
                                    <th style="text-align: center;">เอกสาร</th>
                                    <th style="text-align: center;">หมายเหตุ</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div><!-- /.row - inside box -->
                </div><!-- /.box-body -->
            </div><!-- /.box -->        

        </div>

    </div>
<jsp:include page="include/include_footer.jsp" flush="true"></jsp:include>
<script>
    $(document).ready(function () {
        moment.locale('en');
        var availableTable = $('#trackingTable').DataTable({
            responsive: {
                details: {
                    type: 'inline'
                }
            },
            "columns": [
                {"data": "calCode", "target": 0},
                {"data": "associateDep", render: function (data, type, row, meta) {
                        return data.depNameTh + "<br/>" + data.depNameEn;
                    }, "target": 1},
                {"data": "associateMeasure", render: function (data, type, row, meta) {
                        return data.measureCode + "<br/>" + data.measureNameTh + "<br/>" + data.measureNameEn;
                    }, "target": 2, "className": "dt-body-center"},
                {"data": "requestApproverOn", render: function (data, type, row, meta) {
                        if (data != null) {
                            var date = formatDateFromJavaDateJSONEncoded(data);
                            return date;
                        } else {
                            return "-";
                        }
                    }, "target": 3, "className": "dt-body-center"},
                {"data": "requestApproverStatus", "target": 4, render:
                            function (data, type, row, meta) {

                                if (type == "filter") {
                                    return data;
                                }
                                if (data == 1) {
                                    return  "<img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;'/>";
                                } else {
                                    return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                                }
                            }
                    ,
                    "createdCell": function (td, cellData, rowData, row, col) {
                        var userApprover = rowData.associateRequestApproverByUser;
                        if (rowData.requestApproverOn != null) {
                            var date = formatDateFromJavaDateJSONEncoded(rowData.requestApproverOn);
                            var title = " อนุมัติการขอสอบเทียบเมื่อวันที่ " + date + " \nโดย " + userApprover.userName;
                            $(td).prop("title", title);
                        }
                    }},
                {"data": "receiveStatus", "target": 5, render: function (data, type, row, meta) {
                        if (type == "filter") {
                            return data;
                        }
                        if (data == 1) {
                            var userApprover = row.associateReceiveStatusByUser;
                            var message = row.conditionComment;
                            var sender = "<div class='row' style='padding:5px'>" +
                                    "<div class='col-sm-10 '>" +
                                    userApprover.empId + " " + userApprover.userName
                                    + "</div>"
                                    + "</div>";
                            var bootboxFunction = "bootbox.alert({size:\"small\",title:\"ความคิดเห็นสถานะเครื่อง\",message:$(this).data(\"comment\")})";
                            var requestDataComment = "<div class='row'>"
                                    + "<button class=\"btn btn-success\" data-comment='" + message + "' onclick='" + bootboxFunction + "'>"
                                    + "หมายเหตุ"
                                    + "</button>"
                                    + "</div>";
                            var showData = "";
                            if (userApprover != null) {
                                showData += sender;
                                if (message != null && message.length > 0) {
                                    showData += requestDataComment;
                                }
                            }
                            return "<img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;'/>";
                        } else {
                            return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                        }
                    }, "createdCell": function (td, cellData, rowData, row, col) {
                        var userApprover = rowData.associateReceiveStatusByUser;
                        if (rowData.receiveStatusOn != null) {
                            var date = formatDateFromJavaDateJSONEncoded(rowData.receiveStatusOn);
                            var title = " รับอุปกรณ์เข้าสอบเทียบวันที่ " + date + "\n โดย " + userApprover.userName;
                            $(td).prop("title", title);
                        }
                    }},
                {"target": 6, "data": function (data, type, row, meta) {
                        if (type == "filter") {
                            if ((data.calibrationAttachStatus == 1) && (data.calibrationStatus == 1)) {
                                return "1";
                            } else {
                                return "0";
                            }
                        }
                        if ((data.calibrationAttachStatus == 1) && (data.calibrationStatus == 1)) {
                            var dueDate = moment.utc(data.dueDate).add(1900, 'y').format("DD/MM/YYYY");
                            var calibrateDate = moment.utc(data.calibrationAttachStatusOn).add(1900, 'y').format("DD/MM/YYYY");
                            var calibrateOn = "สอบเทียบ " + calibrateDate;
                            var dueDateOn = "หมดอายุ " + dueDate;
                            //file-alt
                            var image = "<i class='fa fa-file fa-3x' title='" + calibrateOn + "&#13;" + dueDateOn + "'></i>";
                            var link = '<a href="../ajaxHelper/previewReportFromInputDataModel.htm?calId=' + data.calId + '" target="_blank">' + image + '</a>';
                            var template =
                                    "<div class='row'>" +
                                    "<div class='col-md-4 no-padding'>" +
                                    link +
                                    "</div>" +
                                    "<div class='col-md-8 no-padding'>" +
                                    "<div class='row'>" +
                                    calibrateOn +
                                    "</div>" +
                                    "<div class='row'>" +
                                    dueDateOn +
                                    "</div>" +
                                    "</div>" +
                                    "</div>";
                            return "<img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;'/>";
                        } else {
                            return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                        }
                    },
                    "createdCell": function (td, cellData, rowData, row, col) {
                        var userApprover = rowData.associateCalibrationAttachStatusByUser;
                        if (rowData.calibrationAttachStatusOn != null) {
                            var date = formatDateFromJavaDateJSONEncoded(rowData.calibrationAttachStatusOn);
                            var title = " สอบเทียบวันที่ " + date + "\n โดย " + userApprover.userName;
                            $(td).prop("title", title);
                        }
                    }
                },
                {"data": "approveStatus", "target": 7, render: function (data, type, row, meta) {
                        if (type == "filter") {
                            return data;
                        }
                        if (data == 1) {
                            return "<img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;'/>";
                        } else {
                            return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                        }
                    },
                    "createdCell": function (td, cellData, rowData, row, col) {
                        var userApprover = rowData.associateApproveStatusByUser;
                        if (rowData.approveStatusOn != null) {
                            var date = formatDateFromJavaDateJSONEncoded(rowData.approveStatusOn);
                            var title = " อนุมัติผลสอบเทียบวันที่ " + date + "\n โดย " + userApprover.userName;
                            $(td).prop("title", title);
                        }
                    }
                },
                {"data": "returnStatus", "target": 8, render: function (data, type, row, meta) {
                        if (type == "filter") {
                            return data;
                        }
                        if (data == 1) {
                            var date = formatDateFromJavaDateJSONEncoded(rowData.returnStatusOn);
                            var title = " ส่งอุปกรณ์คืนวันที่ " + date;
                            return "<img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;' />";
                        } else {
                            return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                        }
                    },
                    "createdCell": function (td, cellData, rowData, row, col) {
                        var userApprover = rowData.associateReturnStatusByUser;
                        if (rowData.returnStatusOn != null) {
                            var date = formatDateFromJavaDateJSONEncoded(rowData.returnStatusOn);
                            var title = " ส่งอุปกรณ์คืนวันที่ " + date + "\n โดย " + userApprover.userName;
                            $(td).prop("title", title);
                        }
                    }}, {"data": "calibrationAttachStatusOn", "target": 9, render: function (data, type, row, meta) {
                        if (data != null) {
                            var date = formatDateFromJavaDateJSONEncoded(data);
                            return date;
                        } else {
                            return "-";
                        }
                    }},
                {"data": "dueDate", "target": 10, render: function (data, type, row, meta) {
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
                    }}, {"target": 11, data: function (data, type, row, meta) {
                        var image = "<i class='fa fa-file fa-3x'></i>";
                        var link = '<a href="../ajaxHelper/previewReportFromInputDataModel.htm?calId=' + data.calId + '" target="_blank">' + image + '</a>';
                        return link;
                    }}, {"target": 12, data: function (data, type, row, meta) {
                        var reqComment = data.requestComment;
                        var condComment = "";
                        if (data.associateEquipCon != null && data.associateEquipCon.equipConId > 0) {
                            condComment = data.associateEquipCon.fullName;
                            if (data.conditionComment != null && data.conditionComment.length > 0) {
                                condComment = condComment + " เนื่องจาก " + data.conditionComment;
                            }
                        }
                        var statCalDoc = "";
                        if (data.associateStatusCaldoc != null && data.associateStatusCaldoc.statusCaldocId != 0) {
                          
                            statCalDoc = data.associateStatusCaldoc.statusCaldocName;
                            if (data.comment != null && data.comment.length > 0) {
                                statCalDoc = statCalDoc + " เนื่องจาก " + data.comment;
                            }
                        }
                        var message = "";
                        if (reqComment != null && reqComment.length > 0) {
                            message += "หมายเหตุ : " + reqComment;
                        }
                        if (condComment != null && condComment.length > 0) {
                            if (message.length > 0) {
                                message += "<br/>";
                            }
                            message += "สถานะเครื่อง : " + condComment;
                        }
                        if (statCalDoc != null && statCalDoc.length > 0) {
                            if (message.length > 0) {
                                message += "<br/>";
                            }
                            message += "ผลสอบ : " + statCalDoc;
                        }
                        var bootboxFunction = "bootbox.alert({size:\"small\",title:\"หมายเหตุ\",message:$(this).data(\"comment\")})";
                        var requestDataComment = 
                                 "<button class=\"btn btn-success\" data-comment='" + message + "' onclick='" + bootboxFunction + "'>"
                                + "<i class=\"fa fa-comment\">"
                                + "</button>";

                        var showData="";
                        if (message != null && message.length > 0) {
                            showData += requestDataComment;
                        }

                        return showData;
                    }}
            ],
            "createdRow": function (row, data, index) {
                if (data.calId < 1) {
                    $(row).addClass('warning');
                }
            },
            "ajax": {
                "url": "../ajaxHelper/getCalibrationListInSystem.htm",
            },
            "dom": '<lftip>',
            "order": [[0, 'asc']],
            "displayLength": 10
        }
        );
        $('input[id^="startDateFilter"],[id$="endDateFilter"]').datepicker({
            dateFormat: 'dd/mm/yy',
            changeYear: true,
            changeMonth: true});
        // Event listener to the two range filtering inputs to redraw on input
        $('#startDateFilter, #endDateFilte , #statusFilter').change(function () {
            availableTable.draw();
        });
    });
    /* Custom filtering function which will search data in column four between two values */
    $.fn.dataTable.ext.search.push(
            function (settings, searchData, index, rowData, counter) {
                var passMinDate = true;
                var passMaxDate = true;
                var statFilter = true;
                var min = $("#startDateFilter").val();
                var max = $("#endDateFilter").val();
                var status = $("#statusFilter").val();
//                       check stat coulmn
                if (status != null && status.trim().length > 0) {
                    switch (String(status)) {
                        case "all":
                            statFilter = true;
                            break;
                        case "approvingRequest":
                            statFilter = (searchData[4] == 1) && (searchData[6] != 1) && (searchData[7] != 1) && (searchData[8] != 1) && (searchData[7] != 1);
                            break;
                        case "received":
                            statFilter = (searchData[5] == 1 && (searchData[6] != 1) && (searchData[7] != 1) && (searchData[8] != 1));
                            break;
                        case "calibrate":
                            statFilter = (searchData[6] == 1 && (searchData[7] != 1) && (searchData[8] != 1));
                            break;
                        case "approvingCalibration":
                            statFilter = (searchData[7] == 1 && (searchData[8] != 1));
                            break;
                        case "returned":
                            statFilter = (searchData[8] == 1);
                            break;
                        default:
                    }
                }

                var date = moment(searchData[3], "DD/MM/YYYY") || 0; // use searchData for the age column

                if (min != null && min.length > 0) {

                    passMinDate = moment(min, "DD/MM/YYYY").isSameOrBefore(date);
                }
                if (max != null && max.length > 0) {
                    passMaxDate = moment(max, "DD/MM/YYYY").isSameOrAfter(date);
                }



                return passMinDate && passMaxDate && statFilter;
            }
    );
</script>

