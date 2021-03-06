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

                <h3 class="box-title">รับอุปกรณ์สอบเทียบ</h3>
                <div style="float: right;"><div style="float: left; margin-top: 10px; margin-right: 5px;"><b>เลือกทั้งหมด</b></div><input type="checkbox" name="selectAllCalId" style="width: 35px; height: 35px;"></div>
            </div><!-- /.box-header -->
            <div class="box-body container">
                <div class="row">
                    <div class="col-sm-12">

                        <table id="receiveDeviceTable" class="dataTable hover cell-border nowrap" style="width: 90%;">
                            <thead>
                                <tr>
                                    <th style="text-align: center;">เลขที่</th>
                                    <th style="text-align: center;">หน่วยงาน</th>
                                    <th style="text-align: center;">รหัสเครื่องวัด</th>
                                    <th style="text-align: center;">ชื่อเครื่องวัด</th>
                                    <th style="text-align: center;">อนุมัติแจ้งวันที่</th>
                                    <th style="text-align: center;">ผู้อนุมัติแจ้ง</th>
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
                            <button class="btn btn-primary btn-lg" id="receivceDevice" style="width: 100%;">รับอุปกรณ์</button>
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
            availableTable = $('#receiveDeviceTable').DataTable({
                responsive: {
                    details: {
                        type: 'inline'
                    }
                }, columnDefs: [
                    {responsivePriority: 1, targets: 0},
                    {responsivePriority: 2, targets: -1}
                ],
                pageLength: 1,
                "columns": [
                    {"data": "calCode", "target": 0},
                    {"data": "associateDep.fullName", "target": 1},
                    {"data": "associateMeasure.measureCode", "target": 2, responsivePriority: 1},
                    {"data": "associateMeasure.fullName", "target": 3},
                    {"data": "requestApproverOn", "target": 4, render: function (data, type, row, meta) {
                            if (data != null) {
                                return formatDateFromJavaDateJSONEncoded(data);
                            }
                        }},
                    {"data": "associateRequestApproverByUser", "target": 5, render: function (data, type, row, meta) {
                            var message = row.requestComment;
                            var sender = "<div class='row'>" +
                                    data.userName
                                    + "</div>";
                            var bootboxFunction = "bootbox.alert({size:\"small\",title:\"ความคิดเห็นคำขอสอบเทียบ\",message:$(this).data(\"reqcomment\")})";
                            var requestDataComment = "<div class='row'>"
                                    + "<button class=\"btn btn-success\" data-reqcomment='" + message + "' onclick='" + bootboxFunction + "'>"
                                    + '<i class=\"fa fa-comment\"></i>'
                                    + "</button>"
                                    + "</div>";
                            var showData = "";
                            if (data != null) {
                                showData += sender;
                                if (message != null && message.length > 0) {
                                    showData += requestDataComment;
                                }
                            }
                            return showData;
                        }, "searchable": false
                    },
                    {target:6,"data": function (data, type, row, meta) {
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
                        }, "searchable": false
                    }, {
                        data: function (data, type, row, meta) {
                            return  "<input type='checkbox' id='receivceRow-" + meta.row + "' name='receivceCalId[]' data-row='" + meta.row + "'data-equipconcomment='" + data.conditionComment + "' data-equipconId='" + data.equipConId + "' value='" + data.calId + "'>";
                        }
                    }
                ],
                "createdRow": function (row, data, index) {
                },
                "ajax": {
                    "url": "../ajaxHelper/getApprovedDevice.htm"
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
            $("#receivceDevice").on("click", function () {
                var calibrationDeviceCheckModels = [];
                availableTable.$("input[type='checkbox'][name='receivceCalId[]']:checked").each(function (i) {
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
                    url: "../calibration/receivedDevice.htm",
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
                    availableTable.$("input[type='checkbox'][name='receivceCalId[]']", {"filter": "applied"}).each(function () {
                        this.checked = true;
                    });
                } else {
                    availableTable.$("input[type='checkbox'][name='receivceCalId[]']", {"filter": "applied"}).each(function () {
                        this.checked = false;
                    });
                }
            });

            $(document).on("change", "select[id^=condition-]", function () {
                var row = $(this).data("row");
                var condId = $(this).val();
                $("input[type='checkbox'][id*='receivceRow'][data-row='" + row + "']").data("equipconid", condId);
                $("input[type='checkbox'][id*='receivceRow'][data-row='" + row + "']").attr("data-equipconid", condId);
            });
            $(document).on("click", "button[id^=equipconcomment-]", function () {

                var button = $(this);
                var row = $(this).data("row");
                var associateCheckbox = "input[type='checkbox'][id*='receivceRow'][data-row='" + row + "']";
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

