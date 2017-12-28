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
            <form:form id="editForm"  class="form-inline" action="edit.htm" method="post" modelAttribute="entityModel">
                <div class="col-md-8">
                    <div class="row">
                        <form:hidden path="modelId"></form:hidden>
                            <div class="col-md-2">
                                <div class="form-group">
                                <spring:message code="model.modelId" text="message not found"/>:
                                ${entityModel.modelId}
                            </div>
                        </div>      
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="col-md-8">
                                    <spring:message code="model.measureId" text="message not found"/>:
                                </div>
                                <div class="col-md-4">
                                    <form:select path="measureId" class="form-control" required="required" >
                                        <form:options items="${measures}" itemLabel="measureId" itemValue="measureId" />
                                    </form:select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="col-md-6">
                                    <spring:message code="model.cerOn" text="message not found"/>:
                                </div>
                                <div class="col-md-6">
                                    <form:input path="cerOn"  class="form-control" required="required" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="col-md-6">
                                    <spring:message code="model.locationBy" text="message not found"/>:
                                </div>
                                <div class="col-md-6">
                                    <form:input path="locationBy"  class="form-control" required="required" />
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group"> 
                                <div class="col-md-6">
                                    <spring:message code="model.locationReturn" text="message not found"/>:
                                </div>
                                <div class="col-md-6">
                                    <form:input path="locationReturn"  class="form-control" required="required" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary editSubmitButton" type="submit" ><spring:message code="model.fulledit" text="message not found"/></button>

                <!--                        cancel edit goto add mode-->
                <button class="addData" value="./add.htm"><spring:message code="cancel" text="message not found"/><i class="fa fa-ban" aria-hidden="true"></i></button>

            </form:form>
        </div>
        
        <script>

        </script>
    </body>
</html>
