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
            <form:form id="editForm"  class="form-inline" action="edit.htm" method="post" modelAttribute="measureUnit">
                <form:hidden path="unitId"></form:hidden>
                    <div class="row col-md-10">
                        <div class="col-md-2">
                            <div class="form-group">
                            <spring:message code="measureUnit.unitId" text="message not found"/>:
                            ${measureUnit.unitId}
                        </div>
                    </div> 
                    <div class="col-md-2">
                        <spring:message code="measureUnit.unitNameTh" text="message not found"/>:
                    </div>
                    <div class="col-md-2">
                        <form:input path="unitNameTh"  class="form-control" required="required" />
                    </div>
                    <div class="col-md-2">
                        <spring:message code="measureUnit.unitShortTh" text="message not found"/>:
                    </div>
                    <div class="col-md-2">
                        <form:input path="unitShortTh"  class="form-control" required="required" />
                    </div>
                </div>
                <div class="row col-md-10">
                    <div class="col-md-2 col-md-offset-2">
                        <spring:message code="measureUnit.unitNameEn" text="message not found"/>:
                    </div>
                    <div class="col-md-2">
                        <form:input path="unitNameEn" class="form-control"  required="required" />
                    </div>
                    <div class="col-md-2">
                        <spring:message code="measureUnit.unitShortEn" text="message not found"/>:
                    </div>
                    <div class="col-md-2">
                        <form:input path="unitShortEn"  class="form-control" required="required" />
                    </div>
                </div>
                <button class="btn btn-primary editSubmitButton" type="submit" ><spring:message code="measureUnit.fulledit" text="message not found"/></button>

                <!--                        cancel edit goto add mode-->
                <button class="addData" value="./add.htm"><spring:message code="cancel" text="message not found"/><i class="fa fa-ban" aria-hidden="true"></i></button>

            </form:form>
        </div>

        <script>

        </script>
    </body>
</html>

