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
            <form:form id="addForm"  class="form-inline" action="add.htm" method="post"  modelAttribute="branch" onsubmit="return false;">
                <div class="form-group">
                    <label for="branchNameTh"><spring:message code="branch.branchNameTh" text="message not found"/>:</label>
                    <form:input path="branchNameTh"  class="form-control" required="required" />
                </div>
                <div class="form-group">
                    <label for="branchNameEn">  <spring:message code="branch.branchNameEn" text="message not found"/>:</label>
                    <form:input path="branchNameEn"  class="form-control" required="required" />
                </div>
                    <button type="submit" class="addSubmitButton btn btn-success" ><spring:message code="branch.fulladd" text="message not found"/></button>
            </form:form>
        </div>
        

    </body>
</html>

