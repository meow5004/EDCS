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
            <form:form id="editForm"  class="form-inline" action="edit.htm" method="post" modelAttribute="userAuthType" onsubmit="return false;">
                <div class="col-md-8">
                    <div class="row centralize-flex">
                        <div class="form-group">
                            <form:hidden path="authTypeId"  class="form-control" required="required"/>
                            <spring:message code="userAuthType.authTypeNameTh" text="message not found"/>:
                            <form:input path="authTypeNameTh"  class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <spring:message code="userAuthType.authTypeNameEn" text="message not found"/>:
                            <form:input path="authTypeNameEn" class="form-control" required="required" />
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary editSubmitButton" type="submit" ><spring:message code="userAuthType.fulledit" text="message not found"/></button>


                <button class="addData" value="./add.htm"><spring:message code="cancel" text="message not found"/><i class="fa fa-ban" aria-hidden="true"></i></button>
                </form:form>
        </div>


    </body>
</html>

