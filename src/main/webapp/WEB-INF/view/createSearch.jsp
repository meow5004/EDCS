<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>

    <div class="row">

        <div class="col-lg-12" style="text-align: left;">
            <div class="box box-primary" id="loading-example">
                <div class="box-header">
                    <!-- tools box -->
                    <i class="fa fa-cloud"></i>

                    <h3 class="box-title">รับอุปกรณ์สอบเทียบ</h3>
                    <div style="float: right;"><div style="float: left; margin-top: 10px; margin-right: 5px;"><b>เลือกทั้งหมด</b></div><input type="checkbox" style="width: 35px; height: 35px;"></div>
                </div><!-- /.box-header -->
                <div class="box-body no-padding">
                    <div class="row">
                        <div class="col-sm-12">

                            <table>
                                <thead>
                                    <tr>
                                        <th style="text-align: center;">เลขที่</th>
                                        <th style="text-align: center;">หน่วยงาน</th>
                                        <th style="text-align: center;">รหัสเครื่องวัด</th>
                                        <th style="text-align: center;">ชื่อเครื่องวัด</th>
                                        <th style="text-align: center;">วันที่จัดส่ง</th>
                                        <th style="text-align: center;">รายงาน</th>
                                        <th style="text-align: center;">ผลสอบเทียบ</th>
                                        <th style="text-align: center;">ส่งอนุมัติ</th>
                                        <th style="text-align: center;">เลือก</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th>7200007</th>
                                        <td>ชิ้นส่วน</td>
                                        <td>RI0001</td>
                                        <td>สายวัดสีดำ</td>
                                        <td style="text-align: center;">12/06/2017</td>
                                        <td style="text-align: center;"><a href="createDoc.htm"><img src="../images/icons/BallIconCancel.png" alt="" style="width: 40px; height: 40px;"/></a></td>
                                        <td style="text-align: center;"><a href="createDoc.htm"><img src="../images/icons/BallIconCancel.png" alt="" style="width: 40px; height: 40px;"/></a></td>
                                        <td style="text-align: center;"><img src="../images/icons/BallIconCancel.png" alt="" style="width: 40px; height: 40px;"/></td>
                                        <td style="text-align: center;"><input type="checkbox" style="width: 35px; height: 35px;" disabled></td>
                                    </tr>

                                </tbody>
                            </table>

                        </div>
                    </div><!-- /.row - inside box -->
                </div><!-- /.box-body -->
                <div class="box-footer">
                    <div class="row">
                        <div class="col-xs-12 text-center" style="border-right: 1px solid #f4f4f4">
                            <div class="col-sm-12">
                                <button class="btn btn-primary btn-lg" id="cancelSubmit" style="width: 100%;">ส่งอนุมัติ</button>
                            </div>
                        </div>
                    </div><!-- /.row -->
                </div><!-- /.box-footer -->
            </div><!-- /.box -->        

        </div>

    </div>
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
                    "url": "../ajaxHelper/listNonFinishCalibration.htm",
                },
                "dom": '<lf<t>ip>',
                "order": [[0, 'asc']],
                "displayLength": 10});
        });
    </script>
<jsp:include page="include/include_footer.jsp" flush="true"></jsp:include>

