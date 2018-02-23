<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>




<style>
    #body_login{
        width: 480px;
        margin: auto;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $('#userId').focus();
    });
</script>

<div id="body_login">
ไม่มีสิทธิ์เข้าถึงหน้านี้
</div>
<div style="margin: auto; color: red; width: 480px; text-align: center;">
    <c:if test="${not empty warnning}">
        <h3>${warnning}</h3>
    </c:if>
</div>

