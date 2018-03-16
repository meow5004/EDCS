<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_script.jsp" flush="true"></jsp:include>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
    <script>
        $(document).ready(function () {
            var error = parseFloat($("#errorTolerance").val());
            $.each($(".errorCell"), function (key, value) {
                if (Math.abs(parseFloat($(this).text())) > error) {
                    $(this).css("background-color", "red");
                    $(this).css("color", "white");
                }
            });
        });
    </script>
    <style>
        @page {
            size: A4;
            margin: 0;
            -webkit-print-color-adjust: exact;
        }
        @media print{
            body {
                .errorCell{
                    background-color: white;
                    color: black;
                }
                -webkit-print-color-adjust: exact !important; /*   Chrome, Safari */
                color-adjust: exact !important;                /* Firefox*/
            }
        }
        .summaryTable{
            margin:0px;
            padding:0px;
        }
        .size13font{
            font-size: 13px;
        }
        .stripeless tr{
            background-color: white;
        }
        .stripeless td{
            background-color: white;
        }
        .stripeless{
            background-color: white;
        }

        .outerTable>tbody>tr>td{
            width: 50%;
        }
        .white-bordered{
            border:2px white solid;
        }
    </style>
</head>




<body class="A4">
    <section class="sheet padding-10mm">

        <c:set var="measureTimes" value="${calibration.associateMeasure.measureTimes}"/>
        <c:set var="useRangeMax" value="${calibration.associateMeasure.useRangeMax}"/>
        <c:set var="useRangeMin" value="${calibration.associateMeasure.useRangeMin}"/>
        <c:set var="diffRange" value="${useRangeMax-useRangeMin}"/>
        <c:set var="tableAmount" value="${calibration.edcsCalibrationAttachHeadList.size()}" scope="page" />
        <c:set var="error" value="${calibration.calError}" ></c:set>
        <input type="hidden" value="${error}" id="errorTolerance"/>
        <table class="table-condensed stripeless outerTable" style="border: 1px solid black">
            <tr class="white-bordered">
                <td  style="font-size: 15px;font-weight: bold;" colspan="2">
                    บริษัท ไทยวาโก้ จำกัด (มหาชน)
                </td>
            </tr>
            <tr  class="white-bordered">
                <td  style="font-size:10px" colspan="2">
                    132 ซอยเจริญราษฎร์ 7 แขวงบางโคล่ เขตบางคอแหลม กรุงเทพฯ 10120 TEL 289-3100
                </td>
            </tr>
            <tr  class="white-bordered">
                <td  class="size13font" colspan="2">
                    <div style="width: 70%;display: inline-block">
                    </div>
                    <div style="width: 10%;display: inline-block;text-align: right" >
                        เลขที่ 
                    </div>
                    <div style="width: 19%;display: inline-block;text-align: right" >
                        <span style="font-weight: bold;">${calibration.calCode}</span> 
                    </div>
                </td>
            </tr>
            <tr class="white-bordered">
                <td  class="size13font" colspan="2" style="font-size: 15px;text-align: center;font-weight: bold">   
                    รายงานผลการสอบเทียบ
                </td>
            </tr>
            <tr style="border-left: 2px white solid;border-right: 2px white solid;">
                <td  class="size13font" colspan="2">
                    <div style="width: 70%;display: inline-block">

                    </div>
                    <div style="width: 10%;display: inline-block;text-align: right" >
                        วันที่รายงาน 
                    </div>
                    <div style="width: 19%;display: inline-block;text-align: right" >
                        <span style="font-weight: bold;"><fmt:formatDate value="${calibration.calibrationAttachStatusOn}" pattern="dd/MM/yyyy" /></span> 
                    </div>
                </td>
            </tr>

            <tr style="border-bottom: 1px solid black;border-top: 1px black solid">
                <td  class="size13font" colspan="2">
                    หน่วยงาน/บริษัท <span style="font-weight: bold;">${calibration.associateDep.fullName}</span> 
                </td>
            </tr>
            <tr>
                <td  class="size13font">
                    <span style="font-weight: bold;">
                        ชื่อเครื่องวัด/ทดสอบ
                    </span> 
                </td>
                <td  class="size13font">
                    <span style="font-weight: bold;">
                        รหัสเครื่องวัดและทดสอบ
                    </span>
                </td>
            </tr>
            <tr style="border-bottom: 1px solid black">
                <td  class="size13font">
                    <span style="">
                        ${calibration.associateMeasure.fullName}
                    </span> 
                </td>
                <td  class="size13font">
                    <span style="">
                        ${calibration.associateMeasure.measureCode}
                    </span>
                </td>
            </tr>
            <tr>
                <td  class="size13font" colspan="2">
                    <span style="font-weight: bold;">มาตรฐานเปรียบเทียบอ้างอิง</span> 
                </td>
            </tr>
            <tr>
                <td  class="size13font" colspan="2">
                    <div style="width: 16%;display: inline-block">
                        รายงานเลขที่ :
                    </div>
                    <div style="width: 32%;display: inline-block">
                        <span style="font-weight: bold;">${calibration.associateModel.cerNo}</span> 
                    </div>
                    <div style="width: 16%;display: inline-block">
                        โดย :
                    </div>
                    <div style="width: 32%;display: inline-block;word-break: break-all;">
                        <span style="font-weight: bold;">${calibration.associateModel.locationBy}</span> 
                    </div>

                </td>
            </tr>
            <tr>
                <td  class="size13font" colspan="2">
                    <div style="width: 16%;display: inline-block">
                        แม่แบบ  รหัส :
                    </div>
                    <div style="width: 32%;display: inline-block">
                        <span style="font-weight: bold;">${calibration.associateModel.modelCode}</span> 
                    </div>
                    <div style="width: 16%;display: inline-block">
                        ชื่อ :
                    </div>
                    <div style="width: 32%;display: inline-block;word-break: break-all;">
                        <span style="font-weight: bold;">${calibration.associateModelMeasure.fullName}
                    </div>
                </td>
            </tr>
            <tr style="border-bottom: 1px solid black">
                <td  class="size13font" colspan="2">
                    <div style="width: 16%;display: inline-block">
                        การสอบกลับ :
                    </div>
                    <div style="width: 32%;display: inline-block;word-break: break-all;">
                        <span style="font-weight: bold;">${calibration.associateModel.locationReturn}</span> 
                    </div>
                    <div style="width: 16%;display: inline-block">
                        CER.NO :
                    </div>
                    <div style="width: 32%;display: inline-block">
                        <span style="font-weight: bold;">${calibration.associateModel.cerNo}</span> 
                    </div>
                </td>
            </tr>

            <tr style="border-bottom: 1px solid black">
                <td  class="size13font" colspan="2">
                    <div style="width: 16%;display: inline-block">
                        วันที่สอบเทียบ :
                    </div>
                    <div style="width: 14%;display: inline-block">
                        <span style="font-weight: bold;"><fmt:formatDate value="${calibration.calibratorOn}" pattern="dd/MM/yyyy" /></span> 
                    </div>
                    <div style="width: 7%;display: inline-block">
                        อุณหภูมิ :
                    </div>
                    <div style="width: 10%;display: inline-block">
                        <span style="font-weight: bold;">${calibration.edcsCalibrationAttachHeadList[0].temperature}</span> &#8451;
                    </div>
                    <div style="width: 14%;display: inline-block;">
                        ความชื้นสัมพัทธ์ :
                    </div>
                    <div style="width: 16%;display: inline-block">
                        <span style="font-weight: bold;">${calibration.edcsCalibrationAttachHeadList[0].humidity}</span>% RH
                    </div>
                </td>
            </tr>
            <tr >
                <td  class="size13font" colspan="2">
                    <div style="width: 16%;display: inline-block;">
                        วิธีการวัด/ทดสอบ :
                    </div>
                    <div style="width: 32%;display: inline-block;text-align: left">
                        <span style="font-weight: bold"> ${calibration.associateProcess.processCode}</span>
                    </div>
                    <div style="width: 16%;display: inline-block;">
                        เรื่อง :
                    </div>
                    <div style="width: 32%;display: inline-block;word-break: break-all;">
                        <span style="font-weight: bold"> ${calibration.associateProcess.processSubject}</span>
                    </div>
                </td>
            </tr>
            <tr style="border-bottom: 1px solid black">
                <td  class="size13font" colspan="2">
                    <div style="width: 16%;display: inline-block;">
                        โดย :
                    </div>
                    <div style="width: 82%;display: inline-block;word-break: break-all;">
                        <span style="font-weight: bold"> ${calibration.associateProcess.processBy}</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td  class="size13font" colspan="2">
                    <div style="width: 16%;display: inline-block;">
                        ผลการสอบเทียบ
                    </div>
                    <div style="width: 32%;display: inline-block;word-break: break-all;">
                        (หน่วยวัด : <span style="font-weight: bold"> ${calibration.associateUnit.unitNameTh})</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td  class="size13font" colspan="2">
                    <div style="width: 16%;display: inline-block;">
                        สภาพภายนอก
                    </div>
                    <div style="width: 32%;display: inline-block;word-break: break-all;">
                        <span style="font-weight: bold">${calibration.associateEquipCon.fullName} ${calibration.conditionComment}</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="padding:0px;border: 0px">
                    <table class="summaryTable table-condensed size13font" >
                        <thead>
                            <tr>
                                <th rowspan="${tableAmount}">ค่าระบุ</th>
                                <th  colspan="${tableAmount}" >ค่าวัดได้</th>
                                <th colspan="${tableAmount}">ค่าความผิดพลาด( ค่าแก้ )</th>                                     
                                <th colspan="${tableAmount}" >ค่าความไม่แน่นอน</th>
                            </tr>
                            <c:if test="${tableAmount > 1}">
                                <tr>
                                    <th>A</th>
                                    <th>B</th>
                                    <th>A</th>
                                    <th>B</th>
                                    <th>A</th>
                                    <th>B</th>
                                </tr>
                            </c:if>
                        </thead>
                        <tbody>
                            <c:forEach var = "s" begin = "1" end = "10">
                                <tr>
                                    <td style="text-align: left">
                                        <fmt:formatNumber value="${useRangeMin}"
                                                          maxFractionDigits="0" />
                                        -
                                        <fmt:formatNumber value="${useRangeMin+(s*(diffRange/10))}"
                                                          maxFractionDigits="0" />
                                    </td>
                                    <c:set var="lineValue" value="${useRangeMin+(s*(diffRange/10))}" ></c:set>
                                        <!--                                                                tableId start at zero-->
                                    <c:set var="lineAValue" value="${sideALine[s-1]}" ></c:set>
                                    <c:set var="lineBValue" value="${sideBLine[s-1]}" ></c:set>


                                        <td class="joinedColumnLeft " style="text-align: right">
                                        ${lineAValue.mean}
                                    </td>
                                    <c:if test="${tableAmount == 2}">
                                        <td class="joinedColumnRight" style="border-left: 1px solid black;text-align: right">
                                            ${lineBValue.mean}
                                        </td>
                                    </c:if>  
                                    <td class="joinedColumnLeft errorCell" style="text-align: right">
                                        <fmt:formatNumber value=" ${lineValue - lineAValue.mean}"
                                                          maxFractionDigits="2" />
                                    </td>
                                    <c:if test="${tableAmount == 2}">
                                        <td class="joinedColumnRight errorCell" style="border-left: 1px solid black;text-align: right">
                                            <fmt:formatNumber value=" ${lineValue - lineBValue.mean}"
                                                              maxFractionDigits="2" />

                                        </td>
                                    </c:if>  

                                    <td class="joinedColumnLeft" style="text-align: right">
                                        <fmt:formatNumber value="${lineAValue.uncertaintyCombined}"
                                                          maxFractionDigits="2" />
                                    </td>
                                    <c:if test="${tableAmount == 2}">
                                        <td class="joinedColumnRight" style="border-left: 1px solid black;text-align: right">
                                            <fmt:formatNumber value="${lineBValue.uncertaintyCombined}"
                                                              maxFractionDigits="2" />
                                        </td>
                                    </c:if>  
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </td>
            </tr>
            <tr style="border-top: 2px solid black">
                <td class="size13font" colspan="2" style="padding-top: 10px">
                    <div style="width: 50%;display: inline-block;">
                        ค่าความผิดพลาดที่ยอมรับได้ น้อยกว่า หรือ เท่ากับ : (+ / -) 
                    </div>
                    <div style="width: 10%;display: inline-block;word-break: break-all;">
                        <span style=";font-weight: bold">${calibration.calError}</span> 
                    </div>
                    <div style="width: 20%;display: inline-block;word-break: break-all;">
                        ${calibration.associateUnit.unitNameTh}
                    </div>

                </td>
            </tr>
            <tr>
                <td class="size13font" style="border-right: 0px;padding-bottom: 10px">
                    <c:choose>
                        <c:when test="${calibration.statusCaldocId == 1}">
                            <div style="width: 16%;display: inline-block;">
                                ผลสรุป
                            </div>
                            <div style="width: 32%;display: inline-block;word-break: break-all;">
                                <span style="font-weight: bolder;font-size: 15px">ผ่าน  ${calibration.comment}</span>
                            </div>

                        </c:when>
                        <c:otherwise>
                            <div style="width: 16%;display: inline-block;">
                                ผลสรุป
                            </div>
                            <div style="width: 32%;display: inline-block;word-break: break-all;">
                                <span style="font-weight: bolder;font-size: 15px">ไม่ผ่าน  ${calibration.comment}</span>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="size13font" style="border-left: 0px;padding-bottom: 10px">
                    <div style="width: 50%;display: inline-block;">
                        วันที่หมดอายุ (DUE Date)Date
                    </div>
                    <div style="width: 40%;display: inline-block;word-break: break-all;">
                        <span style="font-weight: bolder;font-size: 15px"><fmt:formatDate value="${calibration.dueDate}" pattern="dd/MM/yyyy" /></span>
                    </div>
                </td>
            </tr>
            <tr style="border-top: 2px solid black">
                <td class="size13font" style="font-weight: bolder">
                    ผู้สอบเทียบ / ผู้ตรวจสอบ
                </td>
                <td class="size13font" style="font-weight: bolder">
                    ผู้อนุมัติ
                </td>
            </tr>
            <tr>
                <td class="size13font" style="text-align: center">
                    ${calibration.associateCalibratorByUser.userName}
                </td>
                <td class="size13font" style="text-align: center">
                    ${calibration.associateApproveStatusByUser.userName}
                </td>
            </tr>
            <tr>
                <td class="size13font" style="text-align: center;text-decoration: overline;">
                    พนักงานควบคุมเครื่องวัด
                </td>
                <td class="size13font" style="text-align: center;text-decoration: overline">
                    ผู้บังคับบัญชาแผนก/สูงกว่า
                </td>
            </tr>

        </table>
    </section>
</body>











