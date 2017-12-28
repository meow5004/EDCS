<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>

    <div class="row">
        <div class="col-lg-12" style="text-align: left;">
            <div class="box box-primary" id="loading-example">
                <div class="box-header">
                    <!-- tools box -->
                    <i class="fa fa-cloud"></i>

                    <h3 class="box-title">รายละเอียดผลการสอบเทียบ</h3>
                </div><!-- /.box-header -->
                <div class="box-body no-padding">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="pad">
                                <div class="col-sm-6">
                                    <div style="width:40%; float: left;"><b>เลขที่</b></div><div style="width:60%; float: left;"><input type="text" style="width:100%;" value="Running Number" disabled></div>
                                </div>
                                <div class="col-sm-6">
                                    <div style="width:40%; float: left;"><b>วันที่รายงาน</b></div><div style="width:60%; float: left;"><input type="text" style="width:100%;" value="01/06/2017" disabled></div>
                                </div>
                                <div class="col-sm-12">
                                    <hr>
                                </div>

                                <div class="col-sm-12">
                                    <div style="width:100%; float: left;">ผลการสอบเทียบ (หน่วยวัด :<input type="text" value="mm." style="width:50px; text-align: center;">)</div>
                                </div>
                                <br><br>
                                <div class="col-sm-12">
                                    <div style="width:15%; float: left; margin-top: 8px;">สภาพภายนอก</div>
                                    <div style="width:15%; float: left;"><input type="checkbox" style="height: 20px; width: 20px;"> ปกติ  </div>
                                    <div style="width:15%; float: left;"><input type="checkbox" style="height: 20px; width: 20px;"> ผิดปกติ </div> 
                                    <div style="width:15%; float: left;"><input type="checkbox" style="height: 20px; width: 20px;"> ลบเลือน</div>
                                    <div style="width:15%; float: left;"><input type="checkbox" style="height: 20px; width: 20px;"> ชำรุดเสียหาย</div>
                                    <div style="width:25%; float: left;"><input type="checkbox" style="height: 20px; width: 20px;"> อื่นๆ <input type="text" value=""></div>
                                </div>
                                <div class="col-sm-12">
                                    <hr>
                                </div>

                                <table>
                                    <thead>
                                        <tr>
                                            <th style="text-align: center;">ค่าระบุ</th>
                                            <th style="text-align: center;">
                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                ค่าวัดได้
                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                            </th>
                                            <th style="text-align: center;">
                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                ค่าความผิดพลาด(ค่าแก้)
                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                            </th>
                                            <th style="text-align: center;">ค่าความไม่แน่นอน(+/-)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td style="text-align: center;"><input type="text" style="width:50%"></td>
                                            <td>
                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                            </td>
                                            <td>
                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                            </td>
                                            <td style="text-align: center;"><input type="text" style="width:50%"></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: center;"><input type="text" style="width:50%"></td>
                                            <td>
                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                            </td>
                                            <td>
                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                            </td>
                                            <td style="text-align: center;"><input type="text" style="width:50%"></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: center;"><input type="text" style="width:50%"></td>
                                            <td>
                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                            </td>
                                            <td>
                                                <div style="width:25%; float: left;"><input type="text" style="width:100%"></div>
                                                <div style="width:25%; float: right;"><input type="text" style="width:100%"></div>
                                            </td>
                                            <td style="text-align: center;"><input type="text" style="width:50%"></td>
                                        </tr>
                                    </tbody>
                                </table>

                                <div class="col-sm-12">
                                    <hr>
                                </div>
                                <div class="col-sm-12">
                                    <div style="width:100%; float: left;">ค่าความผิดพลาดที่ยอมรับได้ น้อยกว่า หรือ เท่ากับ : (+ / -) <input type="text" value="" style="width:30%;"></div>
                                </div>
                                <br><br><br><br>
                                <div class="col-sm-6">
                                    <div style="width:20%; float: left; margin-top: 8px;">สรุป</div>
                                    <div style="width:30%; float: left;"><input type="checkbox" style="height: 20px; width: 20px;"> ผ่าน</div>
                                    <div style="width:50%; float: left;"><input type="checkbox" style="height: 20px; width: 20px;"> ไม่ผ่าน <input type="text" value=""></div>
                                </div>
                                <div class="col-sm-6">
                                    <div style="width:100%; float: left;">วันที่หมดอายุ (DUE Date) DATE <input type="text" value="" style="width:50%;"></div>
                                </div>
                            </div>
                        </div>
                    </div><!-- /.row - inside box -->
                </div><!-- /.box-body -->
                <div class="box-footer">
                    <div class="row">
                        <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                            <div class="col-sm-6">
                                <button class="btn btn-primary btn-lg" id="cancelSubmit">บันทึกผลการสอบเทียบ</button>
                            </div>
                        </div>
                    </div><!-- /.row -->
                </div><!-- /.box-footer -->
            </div><!-- /.box -->        

        </div>
    </div>
    <script>
        $(document).ready(function () {
            $('.selectpicker').selectpicker();
            $('.selectpicker').selectpicker('refresh');
        });
    </script>
<jsp:include page="include/include_footer.jsp" flush="true"></jsp:include>

