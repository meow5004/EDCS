<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>

    <div class="row">

        <div class="col-lg-12" style="text-align: left;">
            <div class="box box-primary" id="loading-example">
                <div class="box-header">
                    <!-- tools box -->
                    <i class="fa fa-cloud"></i>

                    <h3 class="box-title">พิมพ์สติํกเกอร์</h3>
                    <div style="float: right;"><div style="float: left; margin-top: 10px; margin-right: 5px;"><b>เลือกทั้งหมด</b></div><input type="checkbox" id="reserveAllCurrentCal" style="width: 35px; height: 35px;"></div>
                </div><!-- /.box-header -->
                <div class="box-body container">
                    <div class="row">
                        <div class="col-sm-12">
                            <table id="requestStickerTable" style="width: 90%;" class="dataTable hover cell-border nowrap">
                                <thead>
                                    <tr>
                                        <th colspan="9">
                                            สถานะรายงานสอบเทียบ 
                                            <select id="stickerStatus" style="color: black">
                                                <option value="1" selected>ยังไม่มีการพิมพ์</option>
                                                <option value="2">พิมพ์แล้ว</option>
                                            </select>
                                        </th>
                                    </tr>
                                    <tr id="timeFilter" style="display:none">
                                        <th colspan="9">
                                            <div class="row">
                                                <div class="form-group col-lg-4 col-sm-12">
                                                    <label  class="col-sm-4 control-label" style="font-weight: bold;">วันที่สอบเทียบ</label>
                                                    <div class="col-sm-8">
                                                        <input type="text" class="form-control" id="startDateFilter" placeholder="Start...">
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-4 col-sm-12 ">
                                                    <label for="endDateFilter" class="col-sm-4 control-label no-padding" style="font-weight: bold;">ถึง</label>
                                                    <div class="col-sm-8">
                                                        <input type="text" class="form-control" id="endDateFilter" placeholder="End...">
                                                    </div>
                                                </div>
                                            </div>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th style="text-align: center;">เลขที่</th>
                                        <th style="text-align: center;">หน่วยงาน</th>
                                        <th style="text-align: center;">รหัสเครื่องวัด</th>
                                        <th style="text-align: center;">ชื่อเครื่องวัด</th>
                                        <th style="text-align: center;">วันสอบเทียบ</th>
                                        <th style="text-align: center;">วันหมดอายุ</th>
                                        <th style="text-align: center;">ผลสอบเทียบ</th>
                                        <th style="text-align: center;">ผู้สอบเทียบ</th>
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
                                <button class="btn btn-primary btn-lg" id="reserveSticker" style="width: 100%;">เพิ่มสติกเกอร์เข้ารอพิมพ์</button>
                            </div>
                        </div>
                    </div><!-- /.row -->
                </div><!-- /.box-footer -->
            </div><!-- /.box -->        

        </div>

    </div>


    <div class="row">

        <div class="col-lg-12" style="text-align: left;">
            <div class="box box-primary" id="loading-example">
                <div class="box-header">
                    <!-- tools box -->
                    <i class="fa fa-cloud"></i>

                    <h3 class="box-title">พิมพ์สติํกเกอร์</h3>
                    <div style="float: right;">
                        <div style="float: left; margin-top: 10px; margin-right: 5px;">
                            <button class="btn btn-danger" id="clearReservedStickers">ลบสติ๊กเกอร์รอพิมพ์ทั้งหมด</button>
                        </div>
                    </div>

                </div><!-- /.box-header -->
                <div class="box-body container">
                    <div class="row">
                        <div class="col-sm-12">
                            <table id="reserveStickerTable" style="width: 90%;" class="dataTable hover cell-border nowrap">
                                <thead>
                                    <tr>
                                        <th  colspan="9">
                                            รายละเอียดสติกเกอร์รอพิมพ์
                                        </th>
                                    </tr>
                                    <tr>
                                        <th style="text-align: center;"></th>
                                        <th style="text-align: center;">เลขที่</th>
                                        <th style="text-align: center;">หน่วยงาน</th>
                                        <th style="text-align: center;">รหัสเครื่องวัด</th>
                                        <th style="text-align: center;">ชื่อเครื่องวัด</th>
                                        <th style="text-align: center;">วันสอบเทียบ</th>
                                        <th style="text-align: center;">วันหมดอายุ</th>
                                        <th style="text-align: center;">ผลสอบเทียบ</th>
                                        <th style="text-align: center;">ผู้สอบเทียบ</th>
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
                            <div class="col-sm-6">
                                <button class="btn btn-primary " id="printStickerType1" style="width: 50%;">พิมพ์สติกเกอร์ ประเภทหนึ่ง</button>
                            </div>
                            <div class="col-sm-6">
                                <button class="btn btn-primary " id="printStickerType2" style="width: 50%;">พิมพ์สติกเกอร์ ประเภทสอง</button>
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
                {"data": "calibrationAttachStatusOn", "target": 4, render: function (data, type, row, meta) {
                        return formatDateFromJavaDateJSONEncoded(data);
                    }},
                {"data": "dueDate", "target": 5, render: function (data, type, row, meta) {
                        //format date form json
                        return formatDateFromJavaDateJSONEncoded(data);
                    }},
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
                {"data": "associateCalibrationAttachStatusByUser", "target": 7, render: function (data, type, row, meta) {
                        if (data != null) {
                            return data.userName;
                        }
                    }
                },
                {"data": "calId", "target": 8, render: function (data, type, row, meta) {
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
        $("input[id='reserveAllCurrentCal'][type='checkbox']").on("click", function (event) {
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

        availableTable.on('search.dt', function () {
            var allCheckboxes = availableTable.$("input[type='checkbox'][name='calId[]']", {"filter": "applied"});
            var checked = availableTable.$("input[type='checkbox'][name='calId[]']:checked", {"filter": "applied"});
            if (allCheckboxes.length > checked.length) {
                availableTable.$("input[id='selectAllCalId'][type='checkbox']").prop('checked', false);
            } else {
                availableTable.$("input[id='selectAllCalId'][type='checkbox']").prop('checked', true);
            }
        });
        $("#stickerStatus").on("change", function () {
            var status = $(this).val();
            if (status == "1") {
                var url = "../ajaxHelper/getLabApporvedDevice.htm";
                $("#timeFilter").hide();
                availableTable.ajax.url(url).load();
            } else if (status == "2") {
                availableTable.clear().draw();//clear table waiting for date input for printed sticker
                $("#timeFilter").show();
            }

        });
//date filter old calib
        $('input[id^="startDateFilter"],[id$="endDateFilter"]').datepicker({
            dateFormat: 'dd/mm/yy',
            changeYear: true,
            changeMonth: true});
        // Event listener to the two range filtering inputs to redraw on input
        $('#startDateFilter, #endDateFilter').change(function () {
            var url = "../ajaxHelper/listOldCalibStickerPrintedBetween.htm?start=" + $("#startDateFilter").val() + "&end=" + $("#endDateFilter").val()
            availableTable.ajax.url(url).load();
        });
        //reserve table
        reserveTable = $('#reserveStickerTable').DataTable({
            "pageLength": 11,
            responsive: {
                details: {
                    type: 'inline'
                }
            }, columnDefs: [
                {responsivePriority: 1, targets: 0},
                {responsivePriority: 2, targets: -1}
            ],
            "columns": [
                {"data": "stickerId", "target": 0, visible: false},
                {"data": "associateCalibration", "target": 1, render: function (data, type, row, meta) {
                        if (data != null) {
                            return data.calCode;
                        }
                    }
                },
                {"data": "associateCalibration", "target": 2, render: function (data, type, row, meta) {
                        if (data != null) {
                            return data.associateDep.fullName;
                        }
                    }},
                {"data": "associateCalibration", "target": 3, responsivePriority: 1, render: function (data, type, row, meta) {
                        if (data != null) {
                            return data.associateMeasure.measureCode;
                        }
                    }},
                {"data": "associateCalibration", "target": 4, render: function (data, type, row, meta) {
                        if (data != null) {
                            return data.associateMeasure.fullName;
                        }
                    }
                },
                {"data": "associateCalibration", "target": 5, render: function (data, type, row, meta) {
                        if (data != null) {
                            return formatDateFromJavaDateJSONEncoded(data.calibrationAttachStatusOn);
                        }
                    }},
                {"data": "associateCalibration", "target": 6, render: function (data, type, row, meta) {
                        if (data != null) {
                            var due = data.dueDate;
                            //format date form json
                            return formatDateFromJavaDateJSONEncoded(due);
                        }
                    }},
                {"data": "associateCalibration", "target": 7, render: function (data, type, row, meta) {
                        if (data != null) {
                            var mainData = data.associateStatusCaldoc.statusCaldocName;
                            var message = data.comment;
                            var colorizationResult = "";
                            if (mainData == "ผ่าน") {
                                colorizationResult = "<span style='color:green;font-weight:bold;font-size:1.5em'>" + mainData + "</span>";
                            } else if (mainData == "ไม่ผ่าน") {
                                colorizationResult = "<span style='color:red;font-weight:bold;font-size:1.5em'>" + mainData + "</span>";
                            } else {
                                colorizationResult = "<span style=font-weight:bold;font-size:1.5em'>" + mainData + "</span>";
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
                        }
                    }, "createdCell": function (td, cellData, rowData, row, col) {
                        if (rowData.associateCalibration != null) {
                            var date = formatDateFromJavaDateJSONEncoded(rowData.associateCalibration.calibrationAttachStatusOn);
                            var title = " สอบเทียบวันที่ " + date;
                            $(td).prop("title", title);
                        }
                    }
                },
                {"data": "associateCalibration", "target": 8, render: function (data, type, row, meta) {
                        if (data != null) {
                            return data.associateCalibrationAttachStatusByUser.userName;
                        }
                    }
                }
            ],
            "createdRow": function (row, data, index) {
            },
            "ajax": {
                "url": "../sticker/listReservedSticker.htm"
            },
            "dom": '<"row"f><"row"ti>',
            "order": [[0, 'asc']],
            fnDrawCallback: function () {
                if ($(this).find('.dataTables_empty').length > 0) {
                    $(this).closest(".box-primary").hide();
                } else {
                    $(this).closest(".box-primary").show();
                }
            }
        });
        reserveTable.on('search.dt', function () {
            var allCheckboxes = reserveTable.$("input[type='checkbox'][name='calId[]']", {"filter": "applied"});
            var checked = reserveTable.$("input[type='checkbox'][name='calId[]']:checked", {"filter": "applied"});
            if (allCheckboxes.length > checked.length) {
                reserveTable.$("input[id='selectAllCalId'][type='checkbox']").prop('checked', false);
            } else {
                reserveTable.$("input[id='selectAllCalId'][type='checkbox']").prop('checked', true);
            }
        });

        $("#clearReservedStickers").on("click", function () {
            $.ajax({
                type: "POST",
                async: false,
                url: "../sticker/clearStickersReserve.htm",
                success: function (result)
                {
                    reserveTable.ajax.reload();
                    availableTable.ajax.reload();
                    $("#stickerStatus").val(1);
                }
            });
            return false;
        });
        $("#printStickerType1").on("click", function () {
            setTimeout(function () {
                reserveTable.ajax.reload();
            }, 10000);
            window.open('../index/stickerTableTemplate.htm?type=1');
            return false;
        });
        $("#printStickerType2").on("click", function () {
            setTimeout(function () {
                reserveTable.ajax.reload();
            }, 10000);
            window.open('../index/stickerTableTemplate.htm?type=2');
            return false;
        });
        $("#reserveSticker").on("click", function () {
            var checkedValues = [];
            availableTable.$("input[type='checkbox'][name='calId[]']:checked", {"filter": "applied"}).each(function (i) {
                checkedValues[i] = $(this).val();
            });
            $.ajax({
                type: "POST",
                async: false,
                url: "../sticker/addStickersByCalIds.htm",
                data: {"checkedValues": checkedValues.toString()},
                success: function (result)
                {
                    reserveTable.ajax.reload();
                    availableTable.ajax.reload();
                }
            });
        });

        $("#reserveOldSticker").on("click", function () {
            var checkedValues = [];
            oldStickerTable.$("input[type='checkbox'][name='calId[]']:checked", {"filter": "applied"}).each(function (i) {
                checkedValues[i] = $(this).val();
            });
            $.ajax({
                type: "POST",
                async: false,
                url: "../sticker/addStickersByCalIds.htm",
                data: {"checkedValues": checkedValues.toString()},
                success: function (result)
                {
//                    bootbox.alert({
//                        backdrop: true,
//                        className: "dangerFont",
//                        message: result,
//                        callback: function () { /* your callback code */
//                        }
//                    });
                    reserveTable.ajax.reload();
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

