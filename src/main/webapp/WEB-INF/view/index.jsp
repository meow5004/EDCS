<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_header.jsp" flush="true"></jsp:include>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="col-md-12">
    <div class="row">
        <div class="box box-primary">
            <div class="box-header">
                <h3 class="box-title"><i class="fa fa-th-large"></i> เมนูระบบงาน E-Doc Calibration Service</h3>
            </div>
            <div class="box-body">
                <c:if test="${fn:contains( user.userAuthTypeIds,1)}">
                    <a href="checkDoc.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                        <i class="fa fa-edit" style="font-size: 90px;"></i><b style="font-size: 14px;">ส่งอุปกรณ์สอบเทียบ</b>
                    </a>
                </c:if>
                <c:if test="${fn:contains( user.userAuthTypeIds,2)}">
                    <a href="approveDoc.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                        <i class="fa fa-edit" style="font-size: 90px;"></i><b style="font-size: 14px;">อนุมัติส่งอุปกรณ์สอบเทียบ</b>
                    </a>
                </c:if>

                    <a href="trackingDoc.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                        <i class="fa fa-search" style="font-size: 90px;"></i><b style="font-size: 14px;">ติดตามรายงานสอบเทียบ</b>
                    </a>

                   <c:if test="${fn:contains( user.userAuthTypeIds,3)}">
                    <a href="deviceReceivce.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                        <i class="fa fa-sign-in" style="font-size: 90px;"></i><b style="font-size: 14px;">รับอุปกรณ์สอบเทียบ</b>
                    </a>
                </c:if>
                    <c:if test="${fn:contains( user.userAuthTypeIds,4)}">
                    <a href="createSearch.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                        <i class="fa fa-calendar" style="font-size: 90px;"></i><b style="font-size: 14px;">จัดทำรายงานสอบเทียบ</b>
                    </a>
                </c:if>
                <!--                    <a href="createDoc.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                                        <i class="fa fa-file-text-o" style="font-size: 90px;"></i><b style="font-size: 14px;">รายงานผล<br>การสอบเทียบ</b>
                                    </a>
                                    <a href="createDetail.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                                        <i class="fa fa-list-alt" style="font-size: 90px;"></i><b style="font-size: 14px;">ผลการสอบเทียบ</b>
                                    </a>-->
                    <c:if test="${fn:contains( user.userAuthTypeIds,5)}">
                    <a href="labApprove.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                        <i class="fa fa-check-square-o" style="font-size: 90px;"></i><b style="font-size: 14px;">อนุมัติรายการสอบเทียบ</b>
                    </a>
                </c:if>
                    <c:if test="${fn:contains( user.userAuthTypeIds,6)}">
                    <a href="printSticker.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                        <i class="fa fa-print" style="font-size: 90px;"></i><b style="font-size: 14px;">พิมพ์สติ๊กเกอร์</b>
                    </a>
                </c:if>
                   <c:if test="${fn:contains( user.userAuthTypeIds,7)}">
                    <a href="deviceSend.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                        <i class="fa fa-sign-out" style="font-size: 90px;"></i><b style="font-size: 14px;">คืนอุปกรณ์สอบเทียบ</b>
                    </a>
                </c:if>
                   <c:if test="${fn:contains( user.userAuthTypeIds,8)}">
                    <a href="#" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                        <i class="fa fa-table" style="font-size: 90px;"></i><b style="font-size: 14px;">แผนประจำปี</b>
                    </a>
                </c:if>
            </div>
        </div>

    </div>
    <div class="row">
        <div class="box box-primary">
            <div class="box-header">
                <h3 class="box-title"><i class="fa fa-cog"></i> จัดการข้อมูลหลัก</h3>
            </div>
            <div class="box-body">
                <a href="../branchs/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-building fa-2x" style="font-size: 90px;"></i><b style="font-size: 14px;">จัดการ<br>สาขา</b>
                </a>
                <a href="../departments/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-building-o" style="font-size: 90px;"></i><b style="font-size: 14px;">จัดการ<br>แผนก</b>
                </a>
                <a href="../measure/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-book" style="font-size: 90px;"></i><b style="font-size: 14px;">สร้างรหัส<br>เครื่องวัด/ทดสอบ</b>
                </a>
                <a href="../equipcons/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-th-list" style="font-size: 90px;"></i><b style="font-size: 14px;">สถานะ<br>เครื่องวัด/ทดสอบ</b>
                </a>
                <a href="../measureGroup/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-columns" style="font-size: 90px;"></i><b style="font-size: 14px;">กลุ่ม<br>เครื่องวัด/ทดสอบ</b>
                </a>
                <a href="../calpoints/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-crosshairs" style="font-size: 90px;"></i><b style="font-size: 14px;">จุดสอบเทียบ</b>
                </a>
                <a href="#" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-subscript" style="font-size: 90px;"></i><b style="font-size: 14px;">สูตรคำนวณ</b>
                </a>
                <a href="../measureUnits/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-balance-scale" style="font-size: 90px;"></i><b style="font-size: 14px;">หน่วยวัด</b>
                </a>
                <a href="../statusCaldocs/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-file" style="font-size: 90px;"></i><b style="font-size: 14px;">สถานะเอกสาร</b>
                </a>
                <a href="#" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-user-plus" style="font-size: 90px;"></i><b style="font-size: 14px;">จัดการข้อมูล<br>ผู้ใช้งานภายนอก</b>
                </a>
                  <c:if test="${fn:contains( user.userAuthTypeIds,9)}">
                <a href="../userRole/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-unlock-alt" style="font-size: 90px;"></i><b style="font-size: 14px;">กำหนดสิทธิ์การใช้งาน</b>
                </a>
                  </c:if>
                <a href="../user/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-user-plus" style="font-size: 90px;"></i><b style="font-size: 14px;">จัดการข้อมูล<br>ผู้ใช้งาน</b>
                </a>
                <a href="../userAuthType/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-user-plus" style="font-size: 90px;"></i><b style="font-size: 14px;">จัดการข้อมูล<br>สิทธิ์การใช้งาน</b>
                </a>
                <a href="../userType/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-user-plus" style="font-size: 90px;"></i><b style="font-size: 14px;">จัดการข้อมูล<br>ประเภทการใช้งาน</b>
                </a>
                <a href="../process/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-book" style="font-size: 90px;"></i><b style="font-size: 14px;">วิธีทดสอบ</b>
                </a>
                <a href="../model/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-key" style="font-size: 90px;"></i><b style="font-size: 14px;">จัดการแม่แบบ</b>
                </a>
                <a href="../calage/index.htm" class="btn btn-app btn-primary" style="width: 160px; height: 150px;">
                    <i class="fa fa-clock-o" style="font-size: 90px;"></i><b style="font-size: 14px;">จัดการช่วงเวลาสอบเทียบ</b>
                </a>
            </div>
        </div>

    </div>
</div>
<jsp:include page="include/include_footer.jsp" flush="true"></jsp:include>

