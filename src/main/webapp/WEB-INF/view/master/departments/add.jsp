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
            <form:form id="addForm"  class="form-inline" action="add.htm" method="post" modelAttribute="department">

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
                        <form:select path="branchId" class="form-control" required="required" >
                            <form:options items="${branchs}" itemLabel="fullName" itemValue="branchId" />
                        </form:select>
                    </div>

                        <button type="submit" class="addSubmitButton btn btn-success" ><spring:message code="department.fulladd" text="message not found"/></button>



            </form:form>
        </div>  
        
        <script>

        </script>
    </body>
</html>

