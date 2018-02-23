<%-- 
    Document   : index
    Created on : Sep 4, 2017, 3:57:53 PM
    or     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
    tr.group,
    tr.group:hover {
        background-color: #ddd !important;
    }
</style>
<jsp:include page="../../include/include_header.jsp" flush="true"></jsp:include>
    <div class="col-md-12">
        <div class="row">
            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title"><i class="fa fa-cog"></i> <spring:message code="userRole.title" text="message not found"/></h3>
            </div>
            <div class="box-body container-fluid">
                <div class="row">
                    <div class="col-md-6">
                        <table id="availableUserRoleTable" class="dataTable hover cell-border nowrap">
                            <thead>
                                <tr>
                                    <th colspan="7" style="text-align: center"><spring:message code="userRole.table.avaliable" text="message not found"/></th>
                                </tr>
                                <tr class="alt" >
                                    <th><spring:message code="userRole.user.empId" text="message not found"/></th>
                                    <th><spring:message code="userRole.user.userName" text="message not found"/></th>
                                    <th><spring:message code="userRole.user.sysName" text="message not found"/></th>
                                    <th><spring:message code="userRole.user.phone" text="message not found"/></th>
                                    <th><spring:message code="userRole.user.showRole" text="message not found"/></th>
                                </tr> 
                            </thead>
                        </table>

                    </div>
                    <div class="col-md-6" id="roleTable">


                    </div>
                    <div id="resultDialog" title="Result" >
                    </div>
                </div>

                <br/>
            </div>
        </div>

    </div>
</div>



<jsp:include page="../../include/include_footer.jsp" flush="true"></jsp:include>


<script>

    $(document).ready(function () {


        moment.locale("en");
        availableTable = $('#availableUserRoleTable').DataTable({
            responsive: {
                details: {
                    type: 'inline'
                }
            },
            "columns": [
                {"data": "empId", "target": 0},
                {"data": "userName", "target": 1},
                {"data": "sysName", "target": 2},
                {"data": "phone", "target": 3},
                {"data": "rowChangeActionColumn", "target": 4, "searchable": false, "orderable": false},
            ],
            "ajax": "getUsersForRoleChange.htm",
            "dom": '<lftip>',
            "order": [[0, 'asc']],
            "displayLength": 10});

        $(document).on('click', ".showRow", function () {
            var empId = $(this).attr("data-value");
            $.ajax({
                type: "POST",
                async: false,
                url: "showUserAuthorization.htm",
                data: {"empId": empId}, // serializes the form's elements.
                success: function (result)
                {
                    $("#resultDialog").html(result);
                }
            });
        });


        $(document).on("click", 'input[id^="selectAll"]', function (event) {
            var choice = $(this).attr('id');
            var choicePointer = choice.split("_")[1];
            $('input[type="checkbox"][name$="' + choicePointer + '"]').prop('checked', this.checked);
        });
        $(document).on("click", ".editRoleButton", function () {
            var str = $("#editRoleForm").serialize();
            $.ajax({
                type: "POST",
                url: "editUserAuthorization.htm",
                data: str, // serializes the form's elements.
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                success: function (result)
                {
                    bootbox.alert({
                        backdrop: true,
                        className: "successFont",
                        message: result,
                        callback: function () { /* your callback code */
                        }
                    });
                }
            });
        });
    });





</script>

