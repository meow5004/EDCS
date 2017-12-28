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
            <form:form id="editForm"  class="form-inline" action="edit.htm" method="post" modelAttribute="calpoint">
                <form:hidden path="calpointId"></form:hidden>
                    <div class="col-md-2">
                        <div class="form-group">
                        <spring:message code="branch.branchId" text="message not found"/>:
                        ${calpoint.calpointId}
                    </div>
                </div>
                <div class="form-group">
                    <spring:message code="calpoint.calpointMin" text="message not found"/>:

                    <form:input path="calpointMin" type="number" min="0" class="form-control positiveNum" required="required"/>
                </div>
                <div class="form-group">
                    <spring:message code="calpoint.calpointMax" text="message not found"/>:

                    <form:input path="calpointMax"  type="number" min="0" class="form-control positiveNum" required="required" />
                </div>

                <button class="btn btn-primary editSubmitButton" type="submit" ><spring:message code="calpoint.fulledit" text="message not found"/></button>

                <!--                        cancel edit goto add mode-->
             <button class="addData" value="./add.htm"><spring:message code="cancel" text="message not found"/><i class="fa fa-ban" aria-hidden="true"></i></button>


            </form:form>
        </div>
        
        <script>

        </script>
    </body>
</html>

