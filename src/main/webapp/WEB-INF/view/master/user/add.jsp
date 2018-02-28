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
            <form:form id="addForm"  class="form-inline" action="add.htm" method="post" modelAttribute="user" onsubmit="return false;">
                <div class="col-md-8">
                    <div class="row centralize-flex">
                        <div class="col-md-6">
                            <div class="col-md-6">
                                <spring:message code="user.user" text="message not found"/>:
                            </div>
                            <div class="col-md-6">
                                <form:input path="empId"   type = "number" class="form-control" required="required" />
                            </div>
                        </div>
                    </div>

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
                                <form:select path="depId" class="form-control" required="required" >
                                    <form:option value=""/>
                                    <form:options items="${departments}" itemLabel="fullName" itemValue="depId"/>
                                </form:select>
                            </div>
                        </div>
                    </div>

                </div>

                <button type="submit" class="addSubmitButton btn btn-success" ><spring:message code="user.fulladd" text="message not found"/> </button>
            </form:form>
        </div>
        <script  type="text/javascript">
            $(document).ready(function () {
                // autocomplete user
                $("#empId").autocomplete({
                    delay: 0,
                    minLength: 4,
                    source: function (request, response) {
                        jQuery.get("../user/getUsersFromLdapByWildCardEmpId.htm", {
                            wildcardEmpId: request.term
                        }, function (data) {
                            var jsonData = JSON.parse(data);
                            response($.map(jsonData, function (item) {
                                return {
                                    label: item.label,
                                    value: item.value
                                };
                            }));
                        });
                    },
                    focus: function (event, ui) {
                        $("#insertEmpByIdFromLdap").val(ui.item.value);
                        return false;
                    },
                    select: function (event, ui) {
                        $("#userName").val(ui.item.label);
                        $("#empId").val(ui.item.value);


                        return false;
                    }
                })
                        .autocomplete("instance")._renderItem = function (ul, item) {
                    console.log(item);
                    return $("<li>")
                            .append("<div>" + item.label + " " + item.value + "</div></li>")
                            .appendTo(ul);
                };
                //end autocomplete user
            });
        </script>

    </body>
</html>

