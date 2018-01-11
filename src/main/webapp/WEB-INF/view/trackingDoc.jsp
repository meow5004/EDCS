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
                        <label for="inputEmail3" class="col-sm-2 control-label" style="font-weight: bold;">วันที่</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="inputEmail3" placeholder="Start...">
                        </div>
                    </div>
                    <div class="form-group col-lg-4 col-sm-12">
                        <label for="inputEmail3" class="col-sm-2 control-label" style="font-weight: bold;">ถึง</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="inputEmail3" placeholder="End...">
                        </div>
                    </div>
                    <div class="form-group col-lg-4 col-sm-12">
                        <label class="col-sm-2 control-label">สถานะ</label>
                        <div class="col-sm-10">
                            <select class="form-control">
                                <option>ทั้งหมด</option>
                                <option>ส่งใบรับงาน</option>
                                <option>รับอุปกรณ์</option>
                                <option>สอบเทียบ</option>
                                <option>รับคืน</option>
                            </select>
                        </div>
                    </div>
                    <br><br><br><button type="button" class="btn btn-block btn-primary">ค้นหา</button>
                </div>
                <div class="box-body no-padding">
                    <div class="row">
                        <div class="col-sm-12">

                            <table style="width: 90%;" id="trackingTable">
                                <thead>
                                    <tr>
                                        <th style="text-align: center;">เลขที่</th>
                                        <th style="text-align: center;">หน่วยงาน</th>
                                        <th style="text-align: center;">รหัสเครื่องวัด</th>
                                        <th style="text-align: center;">ชื่อเครื่องวัด</th>
                                        <th style="text-align: center;">วันที่</th>
                                        <th style="text-align: center;">ระยะเวลา</th>
                                        <th style="text-align: center;">ส่งใบรับงาน</th>
                                        <th style="text-align: center;">รับอุปกรณ์</th>
                                        <th style="text-align: center;">สอบเทียบ</th>
                                        <th style="text-align: center;">อนุมัติ</th>
                                        <th style="text-align: center;">รับคืน</th>
                                    </tr>
                                </thead>
                                <tbody>
<!--                                    <tr>
                                        <th>7200007</th>
                                        <td>ชิ้นส่วน</td>
                                        <td>RI0001</td>
                                        <td>สายวัดสีดำ</td>
                                        <td>12/06/2017</td>
                                        <td style="text-align: center;">60 วัน</td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCheck.png" alt="" style="width: 40px; height: 40px;"/></td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCancel.png" alt="" style="width: 40px; height: 40px;"/></td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCancel.png" alt="" style="width: 40px; height: 40px;"/></td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCancel.png" alt="" style="width: 40px; height: 40px;"/></td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCancel.png" alt="" style="width: 40px; height: 40px;"/></td>
                                    </tr>
                                    <tr style="background-color: lightpink; color: #4c4c4c;">
                                        <th>7200008</th>
                                        <td>ชิ้นส่วน</td>
                                        <td>RI0002</td>
                                        <td>สายวัดสีดำ</td>
                                        <td>12/06/2017</td>
                                        <td style="text-align: center;">60 วัน</td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCheck.png" alt="" style="width: 40px; height: 40px;"/></td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCheck.png" alt="" style="width: 40px; height: 40px;"/></td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCheck.png" alt="" style="width: 40px; height: 40px;"/></td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCancel.png" alt="" style="width: 40px; height: 40px;"/></td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCancel.png" alt="" style="width: 40px; height: 40px;"/></td>
                                    </tr>  
                                    <tr style="background-color: #d0e9c6; color: #4c4c4c;">
                                        <th>7200009</th>
                                        <td>ชิ้นส่วน</td>
                                        <td>RI0003</td>
                                        <td>สายวัดสีดำ</td>
                                        <td>12/06/2017</td>
                                        <td style="text-align: center;">60 วัน</td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCheck.png" alt="" style="width: 40px; height: 40px;"/></td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCheck.png" alt="" style="width: 40px; height: 40px;"/></td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCheck.png" alt="" style="width: 40px; height: 40px;"/></td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCheck.png" alt="" style="width: 40px; height: 40px;"/></td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCheck.png" alt="" style="width: 40px; height: 40px;"/></td>
                                    </tr>  -->
                                </tbody>
                            </table>

                        </div>
                    </div><!-- /.row - inside box -->
                </div><!-- /.box-body -->
            </div><!-- /.box -->        

        </div>

    </div>
<jsp:include page="include/include_footer.jsp" flush="true"></jsp:include>
<script>
    $(document).ready(function () {
        availableTable = $('#trackingTable').DataTable({
            "columns": [
                {"data": "calCode", "target": 0},
                {"data": "requestCommnet", "target": 1},
                {"data": "measureId", "target": 2},
                {"data": "associateMeasure.fullName", "target": 3},
                {"data": "approveStatusOn", "target": 4},
                {"data": "calAgeId", "target": 5},
                {"data": "requestApproverStatus", "target": 6, render: function (data, type, row, meta) {
                        if (data == 1) {
                            return "<img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;'/>";
                        } else {
                            return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                        }
                    }},
                {"data": "receiveStatus", "target": 7, render: function (data, type, row, meta) {
                        if (data == 1) {
                            return "<img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;'/>";
                        } else {
                            return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                        }
                    }},
                {"data": "calibrationStatus", "target": 8, render: function (data, type, row, meta) {
                        if (data == 1) {
                            return "<img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;'/>";
                        } else {
                            return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                        }
                    }},
                {"data": "returnStatus", "target": 9, render: function (data, type, row, meta) {
                        var choice = "<input type='checkbox' name='measureId[]' value='" + data + "'>";
                        return choice
                    }}, {"data": "ApproveStatus", "target": 9, render: function (data, type, row, meta) {
                        if (data == 1) {
                            return "<img src='../images/icons/BallIconCheck.png' alt='' style='width: 40px; height: 40px;'/>";
                        } else {
                            return "<img src='../images/icons/BallIconCancel.png' alt='' style='width: 40px; height: 40px;'/>";
                        }
                    }}
            ],
            "createdRow": function (row, data, index) {
                if (data.calId < 1) {
                    $(row).addClass('warning');
                }
            },
            "ajax": {
                "url": "../ajaxHelper/getApprovedAndReceiverdDevice.htm",
            },
            "dom": '<lf<t>ip>',
            "order": [[0, 'asc']],
            "displayLength": 10});

        $("#sendMeasureToApprove").on("click", function () {
            var checkedValues = [];
            $("input[type='checkbox'][name='measureId[]']:checked").each(function (i) {
                checkedValues[i] = $(this).val();
            });
            console.log(checkedValues);
            $.ajax({
                type: "POST",
                async: false,
                url: "../calibration/createCalibration.htm",
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

    $("input[name='selectAllMeasureId'][type='checkbox']").on("click", function (event) {
        if (this.checked) {
            // Iterate each checkbox
            $("input[type='checkbox'][name='measureId[]']").each(function () {
                this.checked = true;
            });
        } else {
            $("input[type='checkbox'][name='measureId[]']").each(function () {
                this.checked = false;
            });
        }
    });
</script>

