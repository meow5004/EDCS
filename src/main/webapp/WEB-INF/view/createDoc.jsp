<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>

    <div class="row">
        <div class="col-lg-12" style="text-align: left;">
            <div class="box box-primary" id="loading-example">
                <div class="box-header">
                    <!-- tools box -->
                    <i class="fa fa-cloud"></i>

                    <h3 class="box-title">รายงานผลการสอบเทียบ</h3>
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
                                <div class="col-sm-6">
                                    <div style="width:40%; float: left;"><b>หน่วยงาน/บริษัท</b></div><div style="width:60%; float: left;"><input type="text" style="width:100%;"></div>
                                </div>
                                <div class="col-sm-6">
                                    <div style="width:40%; float: left;"><b>อ้างอิงใบรับงานสอบเทียบ</b></div><div style="width:60%; float: left;"><input type="text" style="width:100%;"></div>
                                </div>


                                <div class="col-sm-12">
                                    <hr>
                                </div>
                                <div class="col-sm-6">
                                    <div style="width:100%; float: left;"><b>ชื่อเครื่องวัด/ทดสอบ</b><br><input type="text" style="width:100%;"></div>
                                </div>
                                <div class="col-sm-6">
                                    <div style="width:100%; float: left;"><b>รหัสเครื่องวัดและทดสอบ</b><br><input type="text" style="width:100%;"></div>
                                </div>


                                <div class="col-sm-12">
                                    <hr>
                                    <div><b>มาตรฐานเปรียบเทียบอ้างอิง</b></div>
                                </div>
                                <div class="col-sm-6">
                                    <div style="width:40%; float: left;">รายงานเลขที่</div>
                                    <div style="width:60%; float: left;"><input type="text" style="width:100%;"></div>
                                    <div style="width:40%; float: left;">แม่แบบ รหัส</div>
                                    <div style="width:60%; float: left;"><input type="text" style="width:100%;" value="CRM 0207" disabled></div>
                                    <div style="width:40%; float: left;">การสอบกลับ</div>
                                    <div style="width:60%; float: left;"><input type="text" style="width:100%;" value="สมาคมส่งเสริมเทคโนโลยี (ไทย-ญี่ปุ่น)" disabled></div>
                                </div>
                                <div class="col-sm-6">
                                    <div style="width:40%; float: left;">โดย</div>
                                    <div style="width:60%; float: left;"><input type="text" style="width:100%;" value="สมาคมส่งเสริมเทคโนโลยี (ไทย-ญี่ปุ่น)" disabled></div>
                                    <div style="width:40%; float: left;">ชื่อ</div>
                                    <div style="width:60%; float: left;"><input type="text" style="width:100%;" value="STEEL RULE 7" disabled></div>
                                    <div style="width:40%; float: left;">CER.NO</div>
                                    <div style="width:60%; float: left;"><input type="text" style="width:100%;"></div>
                                </div>


                                <div class="col-sm-12">
                                    <hr>
                                </div>
                                <div class="col-sm-4">
                                    <div style="width:40%; float: left;">วันที่สอบเทียบ</div><div style="width:60%; float: left;"><input type="text" style="width:100%;"></div>
                                </div>
                                <div class="col-sm-4">
                                    <div style="width:40%; float: left;">อุณหภูมิ</div><div style="width:60%; float: left;"><input type="text" style="width:80%;"> °C</div>
                                </div>
                                <div class="col-sm-4">
                                    <div style="width:40%; float: left;">ความชื้นสัมพันธ์</div><div style="width:60%; float: left;"><input type="text" style="width:80%;"> % RH</div>
                                </div>


                                <div class="col-sm-12">
                                    <hr>
                                </div>
                                <div class="col-sm-6">
                                    <div style="width:40%; float: left;">วิธีการวัด/ทดสอบ</div><div style="width:60%; float: left;"><input type="text" style="width:100%;" value="ตาม WI-RD-902" disabled></div>
                                </div>
                                <div class="col-sm-6">
                                    <div style="width:20%; float: left;">เรื่อง</div><div style="width:80%; float: left;"><input type="text" style="width:80%;" value="สมาคมส่งเสริมเทคโนโลยี (ไทย-ญี่ปุ่น)" disabled></div>
                                </div>
                                <div class="col-sm-12">
                                    <div style="width:10%; float: left;">โดย</div><div style="width:90%; float: left;"><input type="text" style="width:80%;" value="วัดเปรียบเทียบกับค่ามาตรฐาน (ไม้บรรทัดมาตรฐาน) เป็นช่วงๆ ช่วงละเท่าๆ กัน" disabled></div>
                                </div>


                                <div class="col-sm-12">
                                    <hr>
                                </div>
                                <div class="col-sm-12">
                                    <div style="width:40%; float: left;">ผู้สอบเทียบ:/ผู้ตรวจสอบ</div><div style="width:60%; float: left;"><input type="text" style="width:100%;" value="กมล" disabled></div>
                                </div>
                                <div class="col-sm-12">
                                    <div style="width:40%; float: left;">ผู้อนุมัติ</div>
                                    <div style="width:60%; float: left;">
                                        <select class="selectpicker" id="colIds" data-title="รายชื่อผู้อนุมัติ" data-width="100%">
                                            <option value="1">name   surname</option>
                                            <option value="2">name   surname</option>
                                        </select>
                                    </div>
                                </div>
                            </div><!-- /.pad -->
                        </div>
                    </div><!-- /.row - inside box -->
                </div><!-- /.box-body -->
                <div class="box-footer">
                    <div class="row">
                        <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                            <div class="col-sm-6">
                                <button class="btn btn-primary btn-lg" id="cancelSubmit">บันทึกรายการสอบเทียบ</button>
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

