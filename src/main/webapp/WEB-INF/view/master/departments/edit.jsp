<%-- 
    Document   : add
    Created on : Sep 5, 2017, 3:22:47 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div align="center container">
            <form:form id="editForm"  class="form-inline" action="edit.htm" method="post" modelAttribute="department">
                <input id="oldDepId" name="oldDepId" type="hidden" value="${department.depId}">

                    <div class="form-group">
                        <spring:message code="department.depId" text="message not found"/>:
                        <form:input path="depId" class="form-control" required="required" />
                    </div>
                    <div class="form-group">
                        <spring:message code="department.depNameTh" text="message not found"/>:
                        <form:input path="depNameTh"  class="form-control" required="required"/>
                    </div>
                    <div class="form-group">
                        <spring:message code="department.depNameEn" text="message not found"/>:
                        <form:input path="depNameEn" class="form-control" required="required" />
                    </div>
                    <div class="form-group">
                        <spring:message code="department.branchId" text="message not found"/>:
                        <form:select path="branchId" required="required" class="form-control" >
                            <form:options items="${branchs}" itemLabel="fullName" itemValue="branchId" />
                        </form:select>
                    </div>

                         <button class="btn btn-primary editSubmitButton" type="submit" ><spring:message code="department.fulledit" text="message not found"/></button>
                        <!--                            cancel edit go to add mode-->
                     <button class="addData" value="./add.htm"><spring:message code="cancel" text="message not found"/><i class="fa fa-ban" aria-hidden="true"></i></button>



            </form:form>
        </div>
        
        <script>

        </script>
    </body>
</html>

