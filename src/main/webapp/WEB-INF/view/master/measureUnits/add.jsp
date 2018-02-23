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
            <form:form id="addForm"  class="form-inline" action="add.htm" method="post" modelAttribute="measureUnit" onsubmit="return false;">
                <div class="col-md-8">
                    <div class="row centralize-flex">
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="measureUnit.unitNameTh" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="unitNameTh"  class="form-control" required="required" />
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="measureUnit.unitShortTh" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="unitShortTh"  class="form-control" required="required" />
                            </div>
                        </div>
                    </div>
                    <div class="row centralize-flex">
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="measureUnit.unitNameEn" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="unitNameEn"  class="form-control" required="required" />
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="measureUnit.unitShortEn" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="unitShortEn"  class="form-control" required="required" />
                            </div>
                        </div>

                    </div>
                </div>

                <button type="submit" class="addSubmitButton btn btn-success" ><spring:message code="measureUnit.fulladd" text="message not found"/> </button>
            </form:form>
        </div>


    </body>
</html>

