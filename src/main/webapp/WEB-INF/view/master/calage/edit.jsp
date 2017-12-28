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
            <form:form id="editForm"  class="form-inline" action="edit.htm" method="post" modelAttribute="calage" onsubmit="return false;">
                <div class="col-md-8">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <form:input path="calAgeId"   type = "hidden" class="form-control" required="required" />
                                <spring:message code="calage.calage" text="message not found"/>:
                                <form:input path="calAge"   type = "number" class="form-control" required="required" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <spring:message code="calage.startDate" text="message not found"/>:
                                <form:input path="startDate" class="form-control" required="required" />
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group"> 
                                <spring:message code="calage.endDate" text="message not found"/>:
                                <form:input path="endDate" class="form-control" required="required" />
                            </div>
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary editSubmitButton" type="submit" ><spring:message code="calage.fulledit" text="message not found"/></button>


                <button class="addData" value="./add.htm"><spring:message code="cancel" text="message not found"/><i class="fa fa-ban" aria-hidden="true"></i></button>
                </form:form>
        </div>
        <script  type="text/javascript">
            $(function () {
                from = $("#startDate").datepicker({
                    dateFormat: 'dd/mm/yy',
                    changeYear: true,
                    changeMonth: true}).on("change", function () {
                    to.datepicker("option", "minDate", getDate(this));
                }), to = $("#endDate").datepicker({
                    dateFormat: 'dd/mm/yy',
                    changeYear: true,
                    changeMonth: true
                })
                        .on("change", function () {
                            from.datepicker("option", "maxDate", getDate(this));
                        });

            });
        </script>

    </body>
</html>

