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
            <form:form id="editForm"  class="form-inline" action="edit.htm" method="post" modelAttribute="user" onsubmit="return false;">
                <div class="col-md-8">

                    <form:hidden path="empId" class="form-control" required="required" />


                    <div class="row centralize-flex">
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="user.userName" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="userName"   type = "text" class="form-control" required="required" />
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="user.sysName" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="sysName"   type = "text" class="form-control"  />
                            </div>
                        </div>
                    </div>
                    <div class="row centralize-flex">
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="user.phone" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="phone"   type = "tel" class="form-control"  />
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="user.depId" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:select path="depId"  items="${departments}" itemLabel="fullName" itemValue="depId"  required="required" />
                            </div>
                        </div>
                    </div>

                </div>
                <button class="btn btn-primary editSubmitButton" type="submit" ><spring:message code="user.fulledit" text="message not found"/></button>


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

