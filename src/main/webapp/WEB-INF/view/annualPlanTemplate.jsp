<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_script.jsp" flush="true"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
    <script>
        $(document).ready(function () {
            console.log(getMonthNames('th-th', 'MMM'));

        });
        function getMonthNames(lang, frmt) {
            var userLang = moment.lang();            // Save user language
            moment.lang(lang);                       // Switch to specified language
            var months = [];                         // Months array
            var m = moment("2011");                  // Create a moment in 2011
            for (var i = 0; i < 12; i++)             // Loop from 0 to 12 (exclusive)
                months.push(m.months(i).format(frmt)); // Append the formatted month
            moment.lang(userLang);                   // Restore user language
            return months;                           // Return the array of months
        }
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
    <c:set var="measureGroup" value="${measureGroups}" scope="page" />
    <c:set var="departments" value="${departments}" scope="page" />
    <c:set var="months" value="${measureGroups}" scope="page" />
    <c:set var="count" value="0" scope="page" />


</body>

