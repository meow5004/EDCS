<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_script.jsp" flush="true"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
    <style>
        @page {
            size: A4;
            margin: 0;
        }
        @media print{
            #paperHead{
                display:none;
            }
            -webkit-print-color-adjust: exact !important;   /* Chrome, Safari */
            color-adjust: exact !important;                 /*Firefox*/
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
            width:100px;
        }
        .divTableHeading {
            background-color: #EEE;
            display: table-header-group;
        }
        .divTableCell,
        .divTableHead {
            border:1px solid black;
            display: table-cell;
            width:100px;
            font-size: 9px;
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
            background-color:antiquewhite;
        }
        .legend .insideDivTableRow:nth-of-type(even) div {
            background: #FFFFFF;
        }
    </style>
</head>
<body class="A4">
    <section class="sheet" id="paperHead">
        <button class="btn btn-primary" onclick="window.print();">พิมพ์สติกเกอร์</button>
    </section>
    <section class="sheet" style="padding:5mm">
        <div class="divTable">
            <div class="divTableBody">
               
                <c:set var="count" value="0" scope="page" />

                <c:forEach var = "row" begin = "1" end = "7">
                    <div class="divTableRow">
                        <c:forEach var = "s" begin = "1" end = "11">
                            <div class="divTableCell">
                                <div class="insideDivTable legend">
                                    <div class="insideDivTableRow">
                                        <div class="insideDivTableCell ">
                                            เลขที่
                                        </div>
                                    </div>
                                    <div class="insideDivTableRow">
                                        <div class="insideDivTableCell ">
                                            test
                                        </div>
                                    </div>
                                    <div class="insideDivTableRow">
                                        <div class="insideDivTableCell ">
                                            รหัสเครื่อง
                                        </div>
                                    </div>
                                    <div class="insideDivTableRow">
                                        <div class="insideDivTableCell ">
                                            test
                                        </div>
                                    </div>
                                    <div class="insideDivTableRow">
                                        <div class="insideDivTableCell ">
                                            วันที่สอบเทียบ
                                        </div>
                                    </div>
                                    <div class="insideDivTableRow">
                                        <div class="insideDivTableCell ">
                                            test
                                        </div>
                                    </div>
                                    <div class="insideDivTableRow">
                                        <div class="insideDivTableCell ">
                                            วันหมดอายุ
                                        </div>
                                    </div>
                                    <div class="insideDivTableRow">
                                        <div class="insideDivTableCell ">
                                            test
                                        </div>
                                    </div>
                                    <div class="insideDivTableRow">
                                        <div class="insideDivTableCell">
                                            <div class="insideDivTable table-striped"  style="text-align: center">
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell" style="border-right: 1px solid black">
                                                        ผ่าน
                                                    </div> 
                                                    <div class="insideDivTableCell">
                                                        ไม่ผ่าน
                                                    </div>
                                                </div>
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell" style="border-right: 1px solid black">
                                                        1
                                                    </div> 
                                                    <div class="insideDivTableCell">

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
                                            test
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:set var="count"  scope="page" value="${count+1}"/>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>

</body>
