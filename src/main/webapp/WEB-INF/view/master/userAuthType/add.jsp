<%-- 
    Document   : add
    Created on : Sep 5, 2017, 3:22:47 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div align="center container">
            <form:form id="addForm"  class="form-inline" action="add.htm" method="post" modelAttribute="userAuthType" onsubmit="return false;">
                <div class="col-md-8">
                    <div class="row centralize-flex">
                        <div class="form-group">
                            <spring:message code="userAuthType.authTypeNameTh" text="message not found"/>:
                            <form:input path="authTypeNameTh"  class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <spring:message code="userAuthType.authTypeNameEn" text="message not found"/>:
                            <form:input path="authTypeNameEn" class="form-control" required="required" />
                        </div>
                    </div>
                </div>

                <button type="submit" class="addSubmitButton btn btn-success" ><spring:message code="userAuthType.fulladd" text="message not found"/> </button>
            </form:form>
        </div>
        <script  type="text/javascript">

        </script>

    </body>
</html>

