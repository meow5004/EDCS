<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="include/include_script.jsp" flush="true"></jsp:include>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/paper-css/0.3.0/paper.css">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
    @page {
        size: A4;
        margin: 0;
    }
    .divTable {
        display: table;
        width: 100%;
        height: 100%;
    }
    .divTableRow {
        display: table-row;
    }
    .divTableHeading {
        background-color: #EEE;
        display: table-header-group;
    }
    .divTableCell,
    .divTableHead {
        border-top: 3px double black;
        border-bottom: 3px double black;
        border-left: 2px double black;
        border-right: 2px double black;
        display: table-cell;
        padding: 3px 10px;
        width:100px;
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
</style>
<body class="A4">
    <section class="sheet padding-10mm">
        <div class="divTable">
            <div class="divTableBody">
                <c:set var="count" value="0" scope="page" />
                <c:forEach var = "row" begin = "1" end = "7">
                    <div class="divTableRow">
                        <c:forEach var = "s" begin = "1" end = "11">
                            <div class="divTableCell">
                                TEST TABLE
                            </div>
                            <c:set var="count"  scope="page" value="${count+1}"/>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</body>
