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
            height: 100%;
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
            width:150px;
            height: 50px;
            font-size: 7px;
        }
        .headSpan{
            font-size: 11px;
        }
        .divHiddenTableCell{
            display: table-cell;
            width:150px;
            height: 100px;
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
        .passedLabel{
            background-color: green!important;
            font-weight: bolder!important;
            color:black!important;
            padding-bottom: 5px!important;
            padding-top: 5px!important;
            text-align: center!important;
            font-size: 12px!important;
        }
        .rejectLabel{
            background-color: red!important;
            font-weight: bolder!important;
            color:black!important;
            padding-bottom: 5px!important;
            padding-top: 5px!important;
            text-align: center!important;
            font-size: 12px!important;
        }
    </style>
</head>
<body class="A4">
    <c:set var="maxPage" value="${(stickers.size()/48)+1}" scope="page" />
    <c:set var="count" value="0" scope="page" />

    <c:forEach var = "pageCount" begin = "1" end = "${maxPage}">
        <section class="sheet" >
            <div class="divTable">
                <div class="divTableBody">
                    <c:forEach var = "row" begin = "1" end = "9">
                        <div class="divTableRow">
                            <c:forEach var = "s" begin = "1" end = "6">
                                <c:choose>
                                    <c:when test="${stickers[count]!= null}">
                                        <div class="divTableCell">
                                            <div class="insideDivTable legend">
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell headSpan" style="text-align: center;padding-top: 3px;">
                                                        twc instrument calibration
                                                    </div>
                                                </div>
                                                <div class="insideDivTableRow">                                    
                                                    <c:choose>
                                                        <c:when test ="${stickers[count].associateCalibration.statusCaldocId == '1'}">
                                                            <div class="insideDivTableCell passedLabel">
                                                                PASSED
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise >
                                                            <div class="insideDivTableCell rejectLabel">
                                                                REJECT
                                                            </div>
                                                        </c:otherwise>                          
                                                    </c:choose>
                                                </div>
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell ">
                                                        <span class="headSpan">CERT. NO </span>  <span style="text-decoration: underline"> ${stickers[count].associateCalibration.calCode}</span>
                                                    </div>
                                                </div>
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell ">
                                                        <span class="headSpan">I.D. NO </span>  <span style="text-decoration: underline">${stickers[count].associateCalibration.associateMeasure.measureCode}</span>
                                                    </div>
                                                </div>
                                                <br/>
                                                <div class="insideDivTableRow">
                                                    <div class="insideDivTableCell">
                                                        <div class="insideDivTable"  style="text-align: center">
                                                            <div class="insideDivTableRow">
                                                                <div class="insideDivTableCell " style="border-right: 1px solid black;width: 60%">
                                                                    <span class="headSpan">DATE </span><span style="text-decoration: underline"><fmt:formatDate value="${stickers[count].associateCalibration.calibrationAttachStatusOn}"  
                                                                                    type="date" 
                                                                                    pattern="dd/MM/yyyy"
                                                                                    />
                                                                    </span>
                                                                </div> 
                                                                <div class="insideDivTableCell  headSpan" style="text-align: left;padding-left: 3px;border-top:1px solid black">
                                                                    BY  
                                                                </div>
                                                            </div>
                                                            <div class="insideDivTableRow">
                                                                <div class="insideDivTableCell " style="border-right: 1px solid black;width: 60%;padding-bottom: 5px">
                                                                    <span class="headSpan">DUE </span> <span style="text-decoration: underline"><fmt:formatDate value="${stickers[count].associateCalibration.dueDate}"  
                                                                                    type="date" 
                                                                                    pattern="dd/MM/yyyy"
                                                                                    />
                                                                    </span>
                                                                </div> 
                                                                <div class="insideDivTableCell  headSpan" style="padding-bottom: 5px">
                                                                    ${stickers[count].associateCalibration.associateCalibratorByUser.sysName}
                                                                </div>
                                                            </div>
                                                        </div>
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

