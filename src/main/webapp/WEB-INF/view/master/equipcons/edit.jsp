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
            <form:form id="editForm"  class="form-inline" action="edit.htm" method="post" modelAttribute="equipcon">
                <form:hidden path="equipConId"></form:hidden>
                    <div class="col-md-2">
                        <div class="form-group">
                        <spring:message code="equipcon.equipConId" text="message not found"/>:
                        ${equipcon.equipConId}
                    </div>
                </div>
                <div class="form-group">
                    <spring:message code="equipcon.equipConNameTh" text="message not found"/>:

                    <form:input path="equipConNameTh"  class="form-control" required="required"/>
                </div>
                <div class="form-group">
                    <spring:message code="equipcon.equipConNameEn" text="message not found"/>:

                    <form:input path="equipConNameEn" class="form-control" required="required" />
                </div>
                <button class="btn btn-primary editSubmitButton" type="submit" ><spring:message code="equipcon.fulledit" text="message not found"/></button>

                <!--                        cancel edit goto add mode-->
             <button class="addData" value="./add.htm"><spring:message code="cancel" text="message not found"/><i class="fa fa-ban" aria-hidden="true"></i></button>

            </form:form>
        </div>
        
        <script>

        </script>
    </body>
</html>

