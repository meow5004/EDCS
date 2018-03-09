<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_script.jsp" flush="true"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
    <script>
        $(document).ready(function () {

            window.print();
            $.ajax({
                type: "POST",
                url: "../sticker/printProcessedStickers.htm",
                success: function (result)
                {
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
                -webkit-print-color-adjust: exact !important; /*   Chrome, Safari */
                color-adjust: exact !important;                /* Firefox*/
            }

        }
        .divTable {
            display: table;
            width: 100%;
            height: 100%;
            border-collapse:separate;
            border-spacing:5px 10px;

        }
        .insideDivTable{
            display: table;
            width: 100%;
            border-collapse:collapse;
        }

        .divTableRow {
            display: table-row;
        }
        .insideDivTableRow{
            display: table-row;
            width: 100%;
            height: 100%;
        }
        .insideDivTableCell
        {
            display: table-cell;
            width:100%;
        }
        .half{
            width:50%;
        }
        .divTableHeading {
            background-color: #EEE;
            display: table-header-group;
        }
        .divTableCell,
        .divTableHead {
            border:1px solid black;
            display: table-cell;
            width:63px;
            height: 140px;
            font-size: 9px;
        }
        .divHiddenTableCell{
            display: table-cell;
            width:63px;
            height: 140px;
        }
        .divTableHeading {
            background-color: #EEE;
            display: table-header-group;
            font-weight: bold;
        }
        .divTableFoot {
            background-color: #EEE;
            display: table-footer-group;
            font-weight: bold;
        }
        .divTableBody {
            display: table-row-group;
        }

        /*paper header*/
        #paperHead{
            height:30px;
            text-align: center;
        }
        .row:after,.row:before{
            display:none!important;
        }
        .container-fluid:after,.container-fluid:before{
            display:none!important;
        }
        .container-fluid{
            font-size: 10px!important;
        }
        .legend .insideDivTableRow:nth-of-type(odd) div {
            background-color:#F0F0F0!important;;
        }
        .legend .insideDivTableRow:nth-of-type(even) div {
            background: #FFFFFF!important;;
        }
    </style>
</head>
<body class="A4">
    <c:set var="maxPage" value="${(stickers.size()/77)+1}" scope="page" />
    <c:set var="count" value="0" scope="page" />

    <c:forEach var = "pageCount" begin = "1" end = "${maxPage}">
        <section class="sheet" style="padding:5mm">
            <div class="divTable">
                <div class="divTableBody">
                    <c:forEach var = "row" begin = "1" end = "7">
                        <div class="divTableRow">
                            <c:forEach var = "s" begin = "1" end = "11">
                                <c:choose>
                                    <c:when test="${stickers[count]!= null}">
                                        <div class="divTableCell">
                                            <div class="insideDivTable legend">
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell ">
                                                        เลขที่
                                                    </div>
                                                </div>
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell ">
                                                        ${stickers[count].associateCalibration.calCode}
                                                    </div>
                                                </div>
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell ">
                                                        รหัสเครื่อง
                                                    </div>
                                                </div>
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell ">
                                                        ${stickers[count].associateCalibration.associateMeasure.measureCode}
                                                    </div>
                                                </div>
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell ">
                                                        วันที่สอบเทียบ
                                                    </div>
                                                </div>
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell ">
                                                        <fmt:formatDate value="${stickers[count].associateCalibration.calibrationAttachStatusOn}"  
                                                                        type="date" 
                                                                        pattern="dd/MM/yyyy"
                                                                        />
                                                    </div>
                                                </div>
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell ">
                                                        วันหมดอายุ
                                                    </div>
                                                </div>
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell ">
                                                        <fmt:formatDate value="${stickers[count].associateCalibration.dueDate}"  
                                                                        type="date" 
                                                                        pattern="dd/MM/yyyy"
                                                                        />
                                                    </div>
                                                </div>
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell">
                                                        <div class="insideDivTable table-striped"  style="text-align: center">
                                                            <div class="insideDivTableRow">
                                                                <div class="insideDivTableCell half" style="border-right: 1px solid black">
                                                                    ผ่าน
                                                                </div> 
                                                                <div class="insideDivTableCell half">
                                                                    ไม่ผ่าน
                                                                </div>
                                                            </div>
                                                            <div class="insideDivTableRow">
                                                                <div class="insideDivTableCell half" style="border-right: 1px solid black">
                                                                    <c:if test="${stickers[count].associateCalibration.statusCaldocId == '1'}">
                                                                        1
                                                                    </c:if>
                                                                </div> 
                                                                <div class="insideDivTableCell half">
                                                                    <c:if test="${stickers[count].associateCalibration.statusCaldocId == '2'}">
                                                                        1
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="insideDivTableRow" style="display:none">
                                                    <!--                                        blank row to compensate above table to adjust odd even row count-->
                                                </div>
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell ">
                                                        ผู้สอบเทียบ
                                                    </div>
                                                </div>
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell ">
                                                        ${stickers[count].associateCalibration.associateCalibratorByUser.sysName}
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>    
                                    <c:otherwise>
                                        <div class="divHiddenTableCell"></div>
                                    </c:otherwise>
                                </c:choose>

                                <c:set var="count"  scope="page" value="${count+1}"/>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
    </c:forEach>
</body>

