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
                                    <th style="text-align: center;">สอบเทียบวันที่่</th>
                                    <th style="text-align: center;">รายงานโดย</th>
                                    <th style="text-align: center;">ผลสอบเทียบ</th>
                                    <th style="text-align: center;">แสดงรายงานสอบเทียบ</th>
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
                {"data": "calibrationAttachStatusOn", "target": 4, render: function (data, type, row, meta) {
                        return formatDateFromJavaDateJSONEncoded(data);
                    }},
                {"data": "associateCalibrationAttachStatusByUser", "target": 5, render: function (data, type, row, meta) {
                        if (data != null) {
                            return data.empId + " " + data.userName;
                        }
                    }
                },
                {"data": "associateStatusCaldoc.statusCaldocName", "target": 6, render: function (data, type, row, meta) {
                        if (row.comment !== null && row.comment.length > 0) {
                            return data + "( " + row.comment + " ) ";
                        } else {
                            return data;
                        }

                    }},
                {"data": function (data, type, row, meta) {
                        return '<a href="../ajaxHelper/previewReportFromInputDataModel.htm?calId=' + data.calId + '" target="_blank" class="btn btn-default">รายละเอียด</a>';
                    }, "target": 7},
                {"data": "calId", "target": 8, render: function (data, type, row, meta) {
                        var choice = "<input type='checkbox' name='calId[]' value='" + data + "'>";
                        return choice;
                    }, "searchable": false}
            ],
            "createdRow": function (row, data, index) {
            },
            "ajax": {
                "url": "../ajaxHelper/listFinishCalibrationWaitForApproval.htm"
            },
            "dom": '<"row"f><"scrollBox row"ti>',
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
                    bootbox.alert({
                        backdrop: true,
                        className: "successFont",
                        message: result,
                        callback: function () { /* your callback code */
                        }
                    });
                    location.reload();
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
