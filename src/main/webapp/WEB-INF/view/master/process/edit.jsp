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
            <form:form id="editForm"  class="form-inline" action="edit.htm" method="post" modelAttribute="process">
                <form:hidden path="processId"></form:hidden>
                    <div class="col-md-2">
                        <div class="form-group">
                        <spring:message code="process.processId" text="message not found"/>:
                        ${process.processId}
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="form-group">
                        <spring:message code="process.processNameTh" text="message not found"/>:
                        <form:input path="processNameTh"  class="form-control" required="required" />
                    </div>
                    <div class="form-group">
                        <spring:message code="process.processNameEn" text="message not found"/>:
                        <form:input path="processNameEn"  class="form-control" required="required" />
                    </div>
                    <div class="form-group">
                        <spring:message code="process.processSubjectTh" text="message not found"/>:
                        <form:input path="processSubjectTh" class="form-control"  required="required" />
                    </div>
                    <div class="form-group">
                        <spring:message code="process.processSubjectEn" text="message not found"/>:
                        <form:input path="processSubjectEn"  class="form-control" required="required" />
                    </div>
                </div>
                <button class="btn btn-primary editSubmitButton" type="submit" ><spring:message code="process.fulledit" text="message not found"/></button>

                <!--                        cancel edit goto add mode-->
             <button class="addData" value="./add.htm"><spring:message code="cancel" text="message not found"/><i class="fa fa-ban" aria-hidden="true"></i></button>

            </form:form>
        </div>
        
        <script>

        </script>
    </body>
</html>

