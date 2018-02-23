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
            <form:form id="addForm"  class="form-inline" action="add.htm" method="post" modelAttribute="process" onsubmit="return false;">
                <div class="col-md-8">
                    <div class="row centralize-flex">
                        <div class="col-md-3">
                            <spring:message code="process.processCode" text="message not found"/>:
                        </div>
                        <div class="col-md-3">
                            <form:input path="processCode"  class="form-control" required="required" />
                        </div>
                    </div>
                    <div class="row centralize-flex">
                        <div class="col-md-3">
                            <spring:message code="process.processSubject" text="message not found"/>:
                        </div>
                        <div class="col-md-6">
                            <form:input path="processSubject"  class="form-control" required="required" />
                        </div>
                    </div>
                    <div class="row centralize-flex">
                        <div class="col-md-3">
                            <spring:message code="process.processBy" text="message not found"/>:
                        </div>
                        <div class="col-md-9">
                            <form:input path="processBy"  class="form-control" required="required" />
                        </div>
                    </div>
                </div>

                <button type="submit" class="addSubmitButton btn btn-success" ><spring:message code="process.fulladd" text="message not found"/> </button>
            </form:form>
        </div>


    </body>
</html>

