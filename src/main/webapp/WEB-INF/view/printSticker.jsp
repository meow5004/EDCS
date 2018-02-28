<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>

    <div class="row">

        <div class="col-lg-12" style="text-align: left;">
            <div class="box box-primary" id="loading-example">
                <div class="box-header">
                    <!-- tools box -->
                    <i class="fa fa-cloud"></i>

                    <h3 class="box-title">พิมพ์สติํกเกอร์</h3>
                    <div style="float: right;"><div style="float: left; margin-top: 10px; margin-right: 5px;"><b>เลือกทั้งหมด</b></div><input type="checkbox" style="width: 35px; height: 35px;"></div>
                </div><!-- /.box-header -->
                <div class="box-body container">
                    <div class="row">
                        <div class="col-sm-12">
                            <table id="requestStickerTable" style="width: 90%;" class="dataTable hover cell-border nowrap">
                                <thead>
                                    <tr>
                                        <th  colspan="8">
                                            รายงานสอบเทียบที่ยังไม่มีการพิมพ์สติกเกอร์
                                        </th>
                                    </tr>
                                    <tr>
                                        <th style="text-align: center;">เลขที่</th>
                                        <th style="text-align: center;">หน่วยงาน</th>
                                        <th style="text-align: center;">รหัสเครื่องวัด</th>
                                        <th style="text-align: center;">ชื่อเครื่องวัด</th>
                                        <th style="text-align: center;">อนุมัติผลวันที่</th>
                                        <th style="text-align: center;">อนุมัติผลโดย</th>
                                        <th style="text-align: center;">หมดอายุวันที่</th>
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
                                <button class="btn btn-primary btn-lg" id="printSticker" style="width: 100%;">พิมพ์สติํกเกอร์</button>
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
        availableTable = $('#requestStickerTable').DataTable({
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
                {"data": "associateMeasure.fullName", "t arget": 3},
                {"data": "approveStatusOn", "target": 4, render: function (data, type, row, meta) {
                        return formatDateFromJavaDateJSONEncoded(data);
                    }},
                {"data": "associateApproveStatusByUser", "target": 5, render: function (data, type, row, meta) {
                        if (data != null) {
                            return data.userName;
                        }
                    }
                },
                {"data": "dueDate", "target": 6, render: function (data, type, row, meta) {
                        //format date form json
                        return formatDateFromJavaDateJSONEncoded(data);
//                            return formatDateFromJavaDateJSONEncoded(data);
                    }},
                {"data": "calId", "target": 7, render: function (data, type, row, meta) {
                        var choice = "<input type='checkbox' name='calId[]' value='" + data + "'>";
                        return choice
                    }, "searchable": false}
            ],
            "createdRow": function (row, data, index) {
            },
            "ajax": {
                "url": "../ajaxHelper/getLabApporvedDevice.htm"
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

        $("#printSticker").on("click", function () {
            var checkedValues = [];
            availableTable.$("input[type='checkbox'][name='calId[]']:checked", {"filter": "applied"}).each(function (i) {
                checkedValues[i] = $(this).val();
            });
            $.ajax({
                type: "POST",
                async: false,
                url: "../calibration/printStickerFromCalibrationId.htm",
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
               availableTable.ajax.reload();
                }
            });
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
    });
</script>

