<%-- 
    Document   : index
    Created on : Jul 4, 2016, 1:44:52 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- แท็กหลัก -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- แท็กจัดรูปแบบ -->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- ฟังก์ชัน -->
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
    </head>
    <body>
        <h1>Test MVC</h1>
        <table>
            <thead>
            <th>BoxCode</th>
            <th>BoxName</th>
        </thead>
        <tbody>
            <c:forEach items="${chkMasBoxcode}" var="box">
                <tr>
                    <td>${box.boxCode}</td>
                    <td>${box.boxCodeName}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
