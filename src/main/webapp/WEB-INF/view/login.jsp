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
    <form:form action="login.htm" method="post" modelAttribute="loginModel">
        <div class="row">
            <div style="margin-left:60px;">ลอคอิน</div>
        </div>
        <div class="row">
            <div class="col-md-3">user</div>
            <div class="col-md-3"><form:input path="userId" value="" placeholder="Username" /></div>
        </div>
        <br/>
        <div class="row">
            <div class="col-md-3">password</div>
            <div class="col-md-3"><form:password path="password"  value="" placeholder="Password"/></div>
        </div>

        <button type="submit" data-theme="b">Login</button>

    </form:form>
</div>
<div style="margin: auto; color: red; width: 480px; text-align: center;">
    <c:if test="${not empty warnning}">
        <h3>${warnning}</h3>
    </c:if>
</div>
