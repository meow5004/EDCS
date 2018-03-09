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
                    <div class="row centralize-flex">
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="model.modelCode" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="modelCode"  class="form-control" required="required" />
                            </div>
                        </div>      
                    </div>
                    <div class="row centralize-flex">
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="model.measureCode" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:select path="measureId" class="form-control" required="required" >
                                    <form:options items="${measures}" itemLabel="measureCode" itemValue="measureId" />
                                </form:select>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="model.cerNo" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="cerNo"  class="form-control" required="required" />
                            </div>
                        </div>
                    </div>
                    <div class="row centralize-flex">
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="model.resolution" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input type="number" path="resolution" step="any" required="required"  class="form-control"/>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="model.uncertainty" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input type="number" path="uncertainty" step="any" required="required"  class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="row centralize-flex">
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="model.dueDate" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input type="text" path="dueDate" required="required"  class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="row centralize-flex">
                        <div class="col-md-12">
                            <div class="col-md-3">
                                <spring:message code="model.locationBy" text="message not found"/>:
                            </div>
                            <div class="col-md-9">
                                <form:input path="locationBy"  class="form-control" required="required" />
                            </div>
                        </div>
                    </div>
                    <div class="row centralize-flex">
                        <div class="col-md-12">
                            <div class="col-md-3">
                                <spring:message code="model.locationReturn" text="message not found"/>:
                            </div>
                            <div class="col-md-9">
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

