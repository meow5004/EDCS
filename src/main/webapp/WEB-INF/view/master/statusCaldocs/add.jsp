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
            <form:form id="addForm"  class="form-inline" action="add.htm" method="post" modelAttribute="statusCaldoc" onsubmit="return false;">

                    <div class="form-group">
                        <spring:message code="statusCaldoc.statusCaldocName" text="message not found"/>:
                        <form:input path="statusCaldocName"  class="form-control" required="required" />
                    </div>

                        <button type="submit" class="addSubmitButton btn btn-success" ><spring:message code="statusCaldoc.fulladd" text="message not found"/>" </button>


            </form:form>
        </div>
        

    </body>
</html>

