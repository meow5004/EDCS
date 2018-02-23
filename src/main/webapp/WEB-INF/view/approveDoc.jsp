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

                <h3 class="box-title">อนุมัติส่งอุปกรณ์สอบเทียบ</h3>
                <div style="float: right;"><div style="float: left; margin-top: 10px; margin-right: 5px;"><b>เลือกทั้งหมด</b></div><input type="checkbox" name="selectAllCalId" style="width: 35px; height: 35px;"></div>
            </div><!-- /.box-header -->
            <div class="box-body container">
                <div class="row">
                    <div class="col-sm-12">

                        <table id="approveDocTable" class="dataTable hover cell-border nowrap" style="width: 90%;">
                            <thead>
                                <tr>
                                    <th style="text-align: center;">เลขที่</th>
                                    <th style="text-align: center;">หน่วยงาน</th>
                                    <th style="text-align: center;">รหัสเครื่องวัด</th>
                                    <th style="text-align: center;">ชื่อเครื่องวัด</th>
                                    <th style="text-align: center;">วันที่แจ้ง</th>
                                    <th style="text-align: center;">ผู้แจ้ง</th>
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
                            <button class="btn btn-primary btn-lg" id="sendRequestedCalibToApprove" style="width: 100%;">ส่งอุปกรณ์</button>
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
            availableTable = $('#approveDocTable').DataTable({
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
                    {"data": "requestOn", "target": 4, render: function (data, type, row, meta) {
                            if (data != null) {
                                return formatDateFromJavaDateJSONEncoded(data);
                            }
                        }
                    },
                    {"data": "associateRequestByUser", "target": 5, render: function (data, type, row, meta) {
                            if (data != null) {
                                return data.empId + " " + data.userName;
                            }
                        }
                    },
                    {"data": function (data, type, row, meta) {
                            var choice = "<div class='container-fluid'>" +
                                    "<div class='row'>"
                                    + "<div class='col-sm-2 no-padding'>"
                                    + "<input type='checkbox' id='requestRow-" + meta.row + "' name='requestCalId[]' data-row='" + meta.row + "'data-comment='" + data.requestComment + "' value='" + data.calId + "'>"
                                    + "</div>"
                                    + "<div class='col-sm-2'>"
                                    + "<button class='btn' id='comment-" + meta.row + "' data-row='" + meta.row + "'>"
                                    + "note"
                                    + "</button>"
                                    + "</div>"
                                    + "</div>"
                                    + "</div>";
                            return choice;
                        }, "createdCell": function (td, cellData, rowData, row, col) {
                            $(td).css("white-space", "normal");
                            var comment = rowData.requestComment;
                            if (comment.length > 0) {
                                $(td).find("button[id*='comment']").addClass("btn-success");
                            }
                        }, "searchable": false
                    }
                ]
                , "ajax": {
                    "url": "../ajaxHelper/getRequestedApprover.htm"
                },
                "dom": '<"row"f><"scrollBox row"ti>',
                "order": [[0, 'asc']],
                "paging": false
            });
            availableTable.on('search.dt', function () {
                var allCheckboxes = availableTable.$("input[type='checkbox'][name='requestCalId[]']", {"filter": "applied"});
                var checked = availableTable.$("input[type='checkbox'][name='requestCalId[]']:checked", {"filter": "applied"});
                if (allCheckboxes.length > checked.length) {
                    $("input[id='selectAllCalId'][type='checkbox']").prop('checked', false);
                } else {
                    $("input[id='selectAllCalId'][type='checkbox']").prop('checked', true);
                }
            });

            $("#sendRequestedCalibToApprove").on("click", function () {

                var calibrationRequestModels = [];
                availableTable.$("input[type='checkbox'][name='requestCalId[]']:checked").each(function (i) {
                    var calibrationRequestModel = {};
                    calibrationRequestModel["calId"] = $(this).val();
                    calibrationRequestModel["requestComment"] = $(this).data("comment");
//approver is this user

                    calibrationRequestModels.push(calibrationRequestModel);
                });
                reqModels = JSON.stringify({
                    'calibrationRequestModels': calibrationRequestModels
                });

                $.ajax({
                    type: "POST",
                    async: false,
                    url: "../calibration/approveRequestedApprover.htm",
                    data: reqModels,
                    dataType: "html",
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    success: function (result)
                    {
//                        bootbox.alert({
//                            backdrop: true,
//                            className: "successFont",
//                            message: result,
//                            callback: function () { /* your callback code */
//                            }
//                        });
                        location.reload();
                    }
                });
            });
            $("input[name='selectAllCalId'][type='checkbox']").on("click", function (event) {
                if (this.checked) {
                    // Iterate each checkbox
                    availableTable.$("input[type='checkbox'][name='requestCalId[]']", {"filter": "appiled"}).each(function () {
                        this.checked = true;
                    });
                } else {
                    availableTable.$("input[type='checkbox'][name='requestCalId[]']", {"filter": "appiled"}).each(function () {
                        this.checked = false;
                    });
                }
            });

            

            $(document).on("click", "button[id^=comment-]", function () {

                var button = $(this);
                var row = $(this).data("row");
                var associateCheckbox = "input[type='checkbox'][id*='requestRow'][data-row='" + row + "']";
                var oldComment = $(associateCheckbox).data("comment");
                bootbox.prompt({
                    size: "small",
                    inputType: "textarea",
                    title: "ใส่คอมเมนท์",
                    value: oldComment,
                    callback: function (result) { /* result = String containing user input if OK clicked or null if Cancel clicked */
                        var comment = result;
                        if (comment != null) {
                            console.log(comment);
                            console.log(associateCheckbox);
                            $(associateCheckbox).data("comment", comment);
                            $(associateCheckbox).attr("data-comment", comment);
                            if (comment.length > 0) {
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

