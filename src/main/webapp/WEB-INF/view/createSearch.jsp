<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>

    <div class="row">

        <div class="col-lg-12" style="text-align: left;">
            <div class="box box-primary" id="loading-example">
                <div class="box-header">
                    <!-- tools box -->
                    <i class="fa fa-cloud"></i>

                    <h3 class="box-title">จัดทำรายการสอบเทียบอุปกรณ์</h3>
                </div><!-- /.box-header -->

                <div class="box-body container">

                    <div class="row">
                        <div class="col-sm-12">
                            <div style="float: right;"><div style="float: left; margin-top: 10px; margin-right: 5px;"><b>เลือกเอกสารสอบเทียบรออนุมัติทั้งหมด</b></div><input type="checkbox" id="waitApproveCalSelectAll" style="width: 35px; height: 35px;"></div>
                            <table id="createTable" class="dataTable hover cell-border nowrap">
                                <thead>
                                    <tr>
                                        <th colspan="9" style="text-align: center">เอกสารสอบเทียบรอส่งอนุมัติ</th>
                                    </tr>
                                    <tr>
                                        <th style="text-align: center;">เลขที่</th>
                                        <th style="text-align: center;">หน่วยงาน</th>
                                        <th style="text-align: center;">รหัสเครื่องวัด</th>
                                        <th style="text-align: center;">ชื่อเครื่องวัด</th>
                                        <th style="text-align: center;">วันที่รับเครื่อง</th>
                                        <th style="text-align: center;">ผู้รับเครื่อง</th>
                                        <th style="text-align: center;">รายงาน</th>
                                        <th style="text-align: center;">ผลสอบเทียบ</th>
                                        <th style="text-align: center;">เลือก</th>
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                            <div style="float: right;"><div style="float: left; margin-top: 10px; margin-right: 5px;"><b>เลือกเอกสารสอบเทียบไม่ผ่านการอนุมัติทั้งหมด</b></div><input id="disApproveCalSelectAll"  type="checkbox" style="width: 35px; height: 35px;"></div>
                            <table id="disapprovedTable" class="dataTable hover cell-border nowrap">
                                <thead>
                                    <tr>
                                        <th colspan="9" style="text-align: center">เอกสารสอบเทียบไม่ผ่านการอนุมัติ</th>
                                    </tr>
                                    <tr>
                                        <th style="text-align: center;">เลขที่</th>
                                        <th style="text-align: center;">หน่วยงาน</th>
                                        <th style="text-align: center;">รหัสเครื่องวัด</th>
                                        <th style="text-align: center;">ชื่อเครื่องวัด</th>
                                        <th style="text-align: center;">วันที่รับเครื่อง</th>
                                        <th style="text-align: center;">ผู้รับเครื่อง</th>
                                        <th style="text-align: center;">รายงาน</th>
                                        <th style="text-align: center;">ผลสอบเทียบ</th>
                                        <th style="text-align: center;">เลือก</th>
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /.row - inside box -->
                </div><!-- /.box-body -->
                <div class="box-footer">
                    <div class="row">
                        <div class="col-xs-12 text-center" style="border-right: 1px solid #f4f4f4">
                            <div class="col-sm-12">
                                <button class="btn btn-primary btn-lg" id="sendToApprove" style="width: 100%;">ส่งอนุมัติ</button>
                            </div>
                        </div>
                    </div><!-- /.row -->
                </div><!-- /.box-footer -->
            </div><!-- /.box -->        

        </div>

    </div>
    <script>
        $(document).ready(function () {
            availableTable = $('#createTable').DataTable({
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
                    {"data": "receiveStatusOn", "target": 4, render: function (data, type, row, meta) {
                            if (data != null) {
                                return formatDateFromJavaDateJSONEncoded(data);
                            }
                        }},
                    {"data": "associateReceiveStatusByUser", "target": 5, render: function (data, type, row, meta) {
                            if (data != null) {
                                return data.empId + " " + data.userName;
                            }
                        }
                    },
                    {"data": "calibrationStatus", "target": 6, render: function (data, type, row, meta) {
                            if (data == 1) {
                                return "<a href='createDoc.htm?calId=" + row.calId + "'> <img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;'/>";
                            } else {
                                return "<a href='createDoc.htm?calId=" + row.calId + "'> <img src='../images/icons/BallIconCancel.png'  alt='' style='width: 40px; height: 40px;'/>";
                            }
                        }},
                    {"data": "calibrationAttachStatus", "target": 7, render: function (data, type, row, meta) {
                            if (data == 1) {
                                return "<a href='createDetail.htm?calId=" + row.calId + "'> <img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;'/>";
                            } else {
                                if (row.calibrationStatus == 1) {
                                    return "<a href='createDetail.htm?calId=" + row.calId + "'> <img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                                } else {
//                                    return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                                    return "";
                                }

                            }
                        }, "createdCell": function (td, cellData, rowData, row, col) {
                            if (rowData.calibrationStatus != 1) {
                                $(td).css("backgroundColor", "gray");
                            }
                        }
                    }, {"data": "calId", "target": 7, render: function (data, type, row, meta) {
                            var choice = "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                            if (row.calibrationAttachStatus == 1 && row.calibrationStatus == 1) {
                                choice = "<input type='checkbox' name='calId[]' value='" + data + "'>";
                            } else {
                                choice = "";
                            }
                            return choice
                        }, "createdCell": function (td, cellData, rowData, row, col) {
                            if (rowData.calibrationAttachStatus != 1) {
                                $(td).css("backgroundColor", "gray");
                            }
                        }
                    }
                ],
                "createdRow": function (row, data, index) {
                    if (data.calId < 1) {
                        $(row).addClass('warning');
                    }
                },
                "ajax": {
                    "url": "../ajaxHelper/listNonFinishCalibration.htm",
                },
                "dom": '<"row"f><"scrollBox row"ti>',
                "order": [[0, 'asc']],
                "paging": false
            });
            availableTable.on('search.dt', function () {
                var allCheckboxes = availableTable.$("input[type='checkbox'][name='calId[]']", {"filter": "applied"});
                var checked = availableTable.$("input[type='checkbox'][name='calId[]']:checked", {"filter": "applied"});
                if (allCheckboxes.length > checked.length||allCheckboxes.length <= 0) {
                    $("input[id='waitApproveCalSelectAll'][type='checkbox']").prop('checked', false);
                } else {
                    $("input[id='waitApproveCalSelectAll'][type='checkbox']").prop('checked', true);
                }
            });

            disapprovedTable = $('#disapprovedTable').DataTable({
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
                    {"data": "depId", "target": 1},
                    {"data": "associateMeasure.measureCode", "target": 2, responsivePriority: 1},
                    {"data": "associateMeasure.fullName", "target": 3},
                    {"data": "receiveStatusOn", "target": 4, render: function (data, type, row, meta) {
                            if (data != null) {
                                return formatDateFromJavaDateJSONEncoded(data);
                            }
                        }},
                    {"data": "associateReceiveStatusByUser", "target": 5, render: function (data, type, row, meta) {
                            if (data != null) {
                                return data.empId + " " + data.userName;
                            }
                        }
                    },
                    {"data": "calibrationStatus", "target": 6, render: function (data, type, row, meta) {
                            if (data == 1) {
                                return "<a href='createDoc.htm?calId=" + row.calId + "'> <img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;'/>";
                            } else {
                                return "<a href='createDoc.htm?calId=" + row.calId + "'> <img src='../images/icons/BallIconCancel.png'  alt='' style='width: 40px; height: 40px;'/>";
                            }
                        }, "searchable": false},
                    {"data": "calibrationAttachStatus", "target": 7, render: function (data, type, row, meta) {
                            if (data == 1) {
                                return "<a href='createDetail.htm?calId=" + row.calId + "'> <img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;'/>";
                            } else {
                                return "<a href='createDetail.htm?calId=" + row.calId + "'> <img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                            }
                        }, "searchable": false},
                    {"data": "calId", "target": 8, render: function (data, type, row, meta) {
                            var choice = "<input type='checkbox' name='calId[]' value='" + data + "'>";
                            return choice
                        }, "searchable": false}
                ],
                "createdRow": function (row, data, index) {
                    if (data.calId < 1) {
                        $(row).addClass('warning');
                    }
                },
                "ajax": {
                    "url": "../ajaxHelper/listDisapprovedCalibration.htm"
                },
                "dom": '<"row"f><"scrollBox row"ti>',
                "order": [[0, 'asc']],
                "paging": false,
                fnInitComplete: function () {
                    if ($(this).find('.dataTables_empty').length > 0) {
                        $(this).parent().parent().hide();
                          $("input[id='disApproveCalSelectAll'][type='checkbox']").parent().hide();
                    }
                }
            });
            disapprovedTable.on('search.dt', function () {
                var allCheckboxes = disapprovedTable.$("input[type='checkbox'][name='calId[]']", {"filter": "applied"});
                var checked = disapprovedTable.$("input[type='checkbox'][name='calId[]']:checked", {"filter": "applied"});
                if (allCheckboxes.length > checked.length) {
                    $("input[id='disApproveCalSelectAll'][type='checkbox']").prop('checked', false);
                } else {
                    $("input[id='disApproveCalSelectAll'][type='checkbox']").prop('checked', true);
                }
            });

            $("#sendToApprove").on("click", function () {
                var checkedValues = [];
                availableTable.$("input[type='checkbox'][name='calId[]']:checked", {"filter": "applied"}).each(function (i) {
                    checkedValues[i] = $(this).val();
                });
                disapprovedTable.$("input[type='checkbox'][name='calId[]']:checked", {"filter": "applied"}).each(function (i) {
                    checkedValues[i] = $(this).val();
                });
                console.log(checkedValues);
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "../calibration/sendCalibrationToApprove.htm",
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

            $("input[id='waitApproveCalSelectAll'][type='checkbox']").on("click", function (event) {
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

            $("input[id='disApproveCalSelectAll'][type='checkbox']").on("click", function (event) {
                if (this.checked) {
                    // Iterate each checkbox
                    disapprovedTable.$("input[type='checkbox'][name='calId[]']", {"filter": "applied"}).each(function () {
                        this.checked = true;
                    });
                } else {
                    disapprovedTable.$("input[type='checkbox'][name='calId[]']", {"filter": "applied"}).each(function () {
                        this.checked = false;
                    });
                }
            });
        });
    </script>
<jsp:include page="include/include_footer.jsp" flush="true"></jsp:include>

