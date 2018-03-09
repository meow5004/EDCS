<%-- 
    Document   : add
    Created on : Sep 5, 2017, 3:22:47 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<style>
    #editRoleForm input[type=checkbox]{
        width:20px;
        height: 20px;
    }

    .bordered-box{
        border:1px solid black;
    }

    .editRoleFormHeader{
        font-weight: bolder;
        text-decoration: underline;
        padding-bottom: 10px;
    }
</style>
<!DOCTYPE html>
<form:form id="editRoleForm"  class="form-inline" action="edit.htm" method="post" modelAttribute="user" onsubmit="return false;">
    <div class="col-md-6">
        <div class="row">
            <div class="row">
                <div class="col-md-offset-6">
                    <button class="btn btn-primary editRoleButton btn-group-lg" ><spring:message code="userRole.editRole" text="message not found"/></button>
                </div>
            </div>
            <form:hidden path="empId" class="form-control" required="required" />
            <div class="col-md-5 bordered-box">
                <div class="editRoleFormHeader">
                    <spring:message code="userRole.user.viewableDep" text="message not found"/>:
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <spring:message code="userRole.selectAll" text="message not found"/>:
                    </div>
                    <div class="col-md-6">
                        <input type="checkbox" id="selectAll_viewableDepartmentIds">
                    </div>
                </div>
                <c:forEach items="${deps}" var="dep" varStatus="status">
                    <div class="row">
                        <div class="col-md-6">
                            ${dep.depNameTh}:
                        </div>
                        <div class="col-md-6">
                            <form:checkbox  path="viewableDepartmentIds" value="${dep.depId}"/>
                        </div>
                    </div>
                </c:forEach>

            </div>
            <div class="col-md-5 bordered-box">
                <div class="editRoleFormHeader">
                    <spring:message code="userRole.user.auth" text="message not found"/>:
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <spring:message code="userRole.selectAll" text="message not found"/>:
                    </div>
                    <div class="col-md-6">
                        <input type="checkbox" id="selectAll_userAuthTypeIds">
                    </div>
                </div>
                <c:forEach items="${auths}" var="auth" varStatus="status">
                    <div class="row">
                        <div class="col-md-6">
                            ${auth.authTypeNameTh}:
                        </div>
                        <div class="col-md-6">
                            <form:checkbox  path="userAuthTypeIds" value="${auth.authTypeId}"/>
                        </div>
                    </div>
                </c:forEach>

            </div>
            <div class="col-md-2 bordered-box">
                <div class="editRoleFormHeader">
                    <spring:message code="userRole.user.userType" text="message not found"/>:
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <spring:message code="userRole.selectAll" text="message not found"/>:
                    </div>
                    <div class="col-md-6">
                        <input type="checkbox" id="selectAll_userTypeIds">
                    </div>
                </div>
                <c:forEach items="${types}" var="type" varStatus="status">
                    <div class="row">
                        <div class="col-md-6">
                            ${type.userTypeNameTh}:
                        </div>
                        <div class="col-md-6">
                            <form:checkbox  path="userTypeIds" value="${type.userTypeId}" />
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>

    </div>

</form:form>




