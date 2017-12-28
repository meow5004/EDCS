<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>

    <div class="row">

        <div class="col-lg-12" style="text-align: left;">
            <div class="box box-primary" id="loading-example">
                <div class="box-header">
                    <!-- tools box -->
                    <i class="fa fa-cloud"></i>

                    <h3 class="box-title">ส่งรายงานสอบเทียบ</h3>
                    <div style="float: right;"><div style="float: left; margin-top: 10px; margin-right: 5px;"><b>เลือกทั้งหมด</b></div><input type="checkbox" style="width: 35px; height: 35px;"></div>
                </div><!-- /.box-header -->
                <div class="box-body no-padding">
                    <div class="row">
                        <div class="col-sm-12">

                            <table style="width: 90%;">
                                <thead>
                                    <tr>
                                        <th style="text-align: center;">เลขที่</th>
                                        <th style="text-align: center;">หน่วยงาน</th>
                                        <th style="text-align: center;">รหัสเครื่องวัด</th>
                                        <th style="text-align: center;">ชื่อเครื่องวัด</th>
                                        <th style="text-align: center;">สอบเทียบล่าสุด</th>
                                        <th style="text-align: center;">เหลืออายุสอบเทียบ</th>
                                        <th style="text-align: center;">ผู้แจ้ง</th>
                                        <th style="text-align: center;">เลือก</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr style="color: red;">
                                        <th>7200007</th>
                                        <td>ชิ้นส่วน</td>
                                        <td>RI0001</td>
                                        <td>สายวัดสีดำ</td>
                                        <td>12/08/2016</td>
                                        <td style="text-align: center;">19 วัน</td>
                                        <td style="text-align: center;">nameTH</td>
                                        <td style="text-align: center;"><input type="checkbox" style="width: 35px; height: 35px;"></td>
                                    </tr>
                                    <tr>
                                        <th>7200008</th>
                                        <td>ชิ้นส่วน</td>
                                        <td>RI0002</td>
                                        <td>สายวัดสีดำ</td>
                                        <td>12/09/2016</td>
                                        <td style="text-align: center;">49 วัน</td>
                                        <td style="text-align: center;">nameTH</td>
                                        <td style="text-align: center;"><input type="checkbox" style="width: 35px; height: 35px;"></td>
                                    </tr>  
                                    <tr>
                                        <th>7200009</th>
                                        <td>ชิ้นส่วน</td>
                                        <td>RI0003</td>
                                        <td>สายวัดสีดำ</td>
                                        <td>12/09/2016</td>
                                        <td style="text-align: center;">49 วัน</td>
                                        <td style="text-align: center;">nameTH</td>
                                        <td style="text-align: center;"><input type="checkbox" style="width: 35px; height: 35px;"></td>
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
                                <button class="btn btn-primary btn-lg" id="cancelSubmit" style="width: 100%;">ส่งอุปกรณ์</button>
                            </div>
                        </div>
                    </div><!-- /.row -->
                </div><!-- /.box-footer -->
            </div><!-- /.box -->        

        </div>

    </div>
<jsp:include page="include/include_footer.jsp" flush="true"></jsp:include>

