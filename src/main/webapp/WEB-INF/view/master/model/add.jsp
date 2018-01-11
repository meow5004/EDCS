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
            <form:form id="addForm"  class="form-inline" action="add.htm" method="post" modelAttribute="entityModel" onsubmit="return false;">
                <div class="col-md-8">
                    <div class="row">
                            <div class="col-md-6">
                                <div class="col-md-6">
                                <spring:message code="model.modelCode" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="modelCode"  class="form-control" required="required" />
                            </div>
                        </div>      
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="model.measureId" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:select path="measureId" class="form-control" required="required" >
                                    <form:options items="${measures}" itemLabel="measureId" itemValue="measureId" />
                                </form:select>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="model.cerOn" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="cerOn"  class="form-control" required="required" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="model.locationBy" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="locationBy"  class="form-control" required="required" />
                            </div>

                        </div>

                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="model.locationReturn" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="locationReturn"  class="form-control" required="required" />
                            </div>
                        </div>
                    </div>
                </div>

                <button type="submit" class="addSubmitButton btn btn-success" ><spring:message code="model.fulladd" text="message not found"/> </button>
            </form:form>
        </div>


    </body>
</html>

