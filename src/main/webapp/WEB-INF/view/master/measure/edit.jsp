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
            <form:form id="editForm"  class="form-inline" action="edit.htm" method="post" modelAttribute="measure">
                <form:hidden path="measureId"></form:hidden>
                    <div class="col-md-10">
                        <div class="row" style="margin-bottom:20px">
                            <div class="form-group" style="border: 1px solid black;padding: 2px">
                            <spring:message code="measure.measure" text="message not found"/>:
                            <span  style="font-weight: bolder"> ${measure.measureId} </span >
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <spring:message code="measure.measureCode" text="message not found"/>:
                        </div>
                        <div class="col-md-9">
                            <form:input path="measureCode"  class="form-control" required="required" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">

                            <spring:message code="measure.measureGroup" text="message not found"/>:
                        </div>
                        <div class="col-md-9">
                            <form:select path="measureGroupId" class="form-control" required="required" >
                                <form:options items="${measureGroups}" itemLabel="fullName" itemValue="measureGroupId" />
                            </form:select>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <spring:message code="measure.measureNameTh" text="message not found"/>:
                        </div>
                        <div class="col-md-3">
                            <form:input path="measureNameTh"  class="form-control" required="required" />
                        </div>
                        <div class="col-md-3">
                            <spring:message code="measure.measureNameEn" text="message not found"/>:
                        </div>
                        <div class="col-md-3">
                            <form:input path="measureNameEn"  class="form-control" required="required" />
                        </div>
                    </div>
                    <div class= "row">

                        <div class="col-md-3"> 
                            <spring:message code="measure.calpointId" text="message not found"/>:
                        </div>
                        <div class="col-md-9"> 
                            <form:select path="calpointId" class="form-control" required="required" >
                                <form:options items="${calpoints}" itemLabel="calpoint" itemValue="calpointId" />
                            </form:select>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <spring:message code="measure.range" text="message not found"/>:
                        </div>
                        <div class="col-md-9">
                            <form:input path="rangeMin" type="number" min="0" class="form-control positiveNum" required="required" />
                            -
                            <form:input path="rangeMax"  type="number" min="0" class="form-control positiveNum" required="required" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3 ">
                            <spring:message code="measure.useRange" text="message not found"/>:
                        </div>
                        <div class="col-md-9">
                            <form:input path="useRangeMin"  type="number" min="0" class="form-control positiveNum" required="required" />
                            -
                            <form:input path="useRangeMax"  type="number" min="0" class="form-control positiveNum" required="required" />

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <spring:message code="measure.description" text="message not found"/>:
                        </div>
                        <div class="col-md-9">
                            <form:input path="description"  style="width:100%" class="form-control" required="required" />
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <spring:message code="measure.abtype" text="message not found"/>:
                        </div>
                        <div class="col-md-9">

                            <form:select path="abType" class="form-control" required="required" >
                                <form:option value="A" label="A"/>
                                <form:option  value="AB" label="AB" />
                            </form:select>
                        </div>
                    </div>
                </div>

                <button class="btn btn-primary editSubmitButton" type="submit" ><spring:message code="measure.fulledit" text="message not found"/></button>

                <!--                        cancel edit goto add mode-->
                <button class="addData" value="./add.htm"><spring:message code="cancel" text="message not found"/><i class="fa fa-ban" aria-hidden="true"></i></button>

            </form:form>
        </div>

        <script>

        </script>
    </body>
</html>

