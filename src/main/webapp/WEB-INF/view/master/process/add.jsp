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
                    <div class="row">
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="process.processNameTh" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="processNameTh"  class="form-control" required="required" />
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="process.processNameEn" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="processNameEn"  class="form-control" required="required" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="process.processSubjectTh" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="processSubjectTh"  class="form-control" required="required" />
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="process.processSubjectEn" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="processSubjectEn"  class="form-control" required="required" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <button type="submit" class="addSubmitButton btn btn-success" ><spring:message code="process.fulladd" text="message not found"/> </button>
        </form:form>
    </div>


</body>
</html>

