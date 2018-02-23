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
                    <br><br><br><button type="button" class="btn btn-block btn-primary">ค้นหา</button>
                </div>
                <div class="box-body container">
                    <div class="row">

                        <table style="min-width: 100%!important;" id="trackingTable" class="dataTable hover cell-border nowrap compact">
                            <thead>
                                <tr>
                                    <th style="text-align: center;">เลขที่</th>
                                    <th style="text-align: center;">หน่วยงาน</th>
                                    <th style="text-align: center;">เครื่องวัด</th>
                                    <th style="text-align: center;">วันที่แจ้ง</th>
                                    <th style="text-align: center;">อนุมัติใบคำขอ</th>
                                    <th style="text-align: center;">รับอุปกรณ์</th>
                                    <th style="text-align: center;">สอบเทียบ <br> (คลิกรูปเพื่อแสดงรายละเอียด)</th>
                                    <th style="text-align: center;">อนุมัติ</th>
                                    <th style="text-align: center;">รับคืน</th>
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
        var availableTable = $('#trackingTable').DataTable({
            responsive: {
                details: {
                    type: 'inline'
                }
            },
            "columns": [
                {"data": "calCode", "target": 0},
                {"data": "associateDep.fullName", "target": 1},
                {"data": function (data, type, row, meta) {
                        return data.associateMeasure.measureCode + "-" + data.associateMeasure.fullName;
                    }, "target": 2, "className": "dt-body-left"},
                {"data": "requestOn", "target": 3, render: function (data, type, row, meta) {
                        return formatDateFromJavaDateJSONEncoded(data);
                    }},
                {"data": "requestApproverStatus", "target": 4, render: function (data, type, row, meta) {
                        if (data == 1) {
                            var date = moment.utc(row.requestApproverOn).add(1900, 'y').format("DD/MM/YYYY");
                            var title = " อนุมัติการขอสอบเทียบเมื่อวันที่ " + date;
                            return "<img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;' title='" + title + "'/>";
                        } else {
                            return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                        }
                    }, searchable: false},
                {"data": "receiveStatus", "target": 5, render: function (data, type, row, meta) {
                        if (data == 1) {
                            var date = moment.utc(row.receiveStatusOn).add(1900, 'y').format("DD/MM/YYYY");
                            var title = " รับอุปกรณ์เข้าสอบเทียบวันที่ " + date;
                            return "<img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;' title='" + title + "'/>";
                        } else {
                            return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                        }
                    }, searchable: false},
                {"data": function (data, type, row, meta) {
                        if ((data.calibrationAttachStatus == 1) && (data.calibrationStatus == 1)) {
                            var dueDate = moment.utc(data.dueDate).add(1900, 'y').format("DD/MM/YYYY");
                            var calibrateDate = moment.utc(data.calibrationAttachStatusOn).add(1900, 'y').format("DD/MM/YYYY");
                            var calibrateOn = "สอบเทียบ " + calibrateDate;
                            var dueDateOn = "หมดอายุ " + dueDate;
                            //file-alt
                            var image = "<i class='fa fa-file fa-3x' title='" + calibrateOn + "&#13;"+dueDateOn+"'></i>";
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
                            return template;
                        } else {
                            return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                        }
                    }, "target": 6, searchable: false,
                    "createdCell": function (td, cellData, rowData, row, col) {
                        $(td).addClass("container-fluid");
                    }
                },
                {"data": "approveStatus", "target": 7, render: function (data, type, row, meta) {
                        if (data == 1) {
                            var date = moment.utc(row.approveStatusOn).add(1900, 'y').format("DD/MM/YYYY");
                            var title = "อนุมัติผลสอบเทียบวันที่ " + date;
                            return "<img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;' title='" + title + "'/>";
                        } else {
                            return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                        }
                    }, searchable: false}, {"data": "returnStatus", "target": 8, render: function (data, type, row, meta) {
                        if (data == 1) {
                            var date = moment.utc(row.returnStatusOn).add(1900, 'y').format("DD/MM/YYYY");
                            var title = " ส่งอุปกรณ์คืนวันที่ " + date;
                            return "<img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;' title='" + title + "'/>";
                        } else {
                            return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                        }
                    }, searchable: false},
                {"data": "requestApproverStatus", "target": 9, "visible": false, render: function (data, type, row, meta) {
                        return data == 1 ? 1 : 0;
                    }},
                {"data": "receiveStatus", "target": 10, "visible": false, render: function (data, type, row, meta) {
                        return data == 1 ? 1 : 0;
                    }},
                {"data": function (data, type, row, meta) {
                        return (data.calibrationAttachStatus == 1 ? 1 : 0) && (data.calibrationStatus == 1 ? 1 : 0);
                    }, "target": 12, "visible": false
                },
                {"data": "approveStatus", "target": 12, "visible": false, render: function (data, type, row, meta) {
                        return data == 1 ? 1 : 0;
                    }},
                {"data": "returnStatus", "target": 13, "visible": false, render: function (data, type, row, meta) {
                        return data == 1 ? 1 : 0;
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
            function (settings, data, dataIndex) {
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
                            statFilter = (data[9] == 1) && (data[10] != 1) && (data[11] != 1) && (data[12] != 1) && (data[13] != 1);
                            break;
                        case "received":
                            statFilter = (data[10] == 1 && (data[11] != 1) && (data[12] != 1) && (data[13] != 1));
                            break;
                        case "calibrate":
                            statFilter = (data[11] == 1 && (data[12] != 1) && (data[13] != 1));
                            break;
                        case "approvingCalibration":
                            statFilter = (data[12] == 1 && (data[13] != 1));
                            break;
                        case "returned":
                            statFilter = (data[13] == 1);
                            break;
                        default:
                    }
                }

                var date = moment(data[4]) || 0; // use data for the age column


                if (min != null && min.length > 0) {
                    passMinDate = moment(min, "MM/DD/YYYY").isSameOrBefore(date, 'day');
                }
                if (max != null && max.length > 0) {
                    passMaxDate = moment(max, "MM/DD/YYYY").isSameOrAfter(date, 'day');
                }



                return passMinDate && passMaxDate && statFilter;

            }
    );
</script>

