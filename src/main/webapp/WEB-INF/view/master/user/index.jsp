<%-- 
    Document   : index
    Created on : Sep 4, 2017, 3:57:53 PM
    Author     : admin
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
                    <h3 class="box-title"><i class="fa fa-cog"></i> <spring:message code="user.title" text="message not found"/></h3>
            </div>
            <div class="box-body">

                <div id="ajaxCRUDfield">
                </div>
                <table id="availableUserTable" class="dataTable hover cell-border nowrap">
                    <thead>
                        <tr>
                            <th colspan="7" style="text-align: center"><spring:message code="user.table.avaliable" text="message not found"/></th>
                        </tr>
                        <tr class="alt" >
                            <th><spring:message code="user.empId" text="message not found"/></th>
                            <th><spring:message code="user.depId" text="message not found"/></th>
                            <th><spring:message code="user.userName" text="message not found"/></th>
                            <th><spring:message code="user.sysName" text="message not found"/></th>
                            <th><spring:message code="user.phone" text="message not found"/></th>
                            <th></th>
                            <th><button class="deleteMultiple btn btn-danger"><spring:message code="user.delete" text="message not found"/><i class="fa fa-trash-o" aria-hidden="true"></i></button></th>
                        </tr> 
                    </thead>
                </table>
                <br/>
            </div>
        </div>

    </div>
</div>
<div id="resultDialog" title="Result" style="display:none;">
</div>


<jsp:include page="../../include/include_footer.jsp" flush="true"></jsp:include>


<script>

    $(document).ready(function () {
        moment.locale("en");
        availableTable = $('#availableUserTable').DataTable({
            responsive: {
                details: {
                    type: 'inline'
                }
            },
            "columns": [
                {"data": "empId", "target": 0},
                {"data": "depName", "target": 1},
                {"data": "userName", "target": 2},
                {"data": "sysName", "target": 3},
                {"data": "phone", "target": 4},
                {"data": "actionLink", "target": 5, "searchable": false, "orderable": false},
                {"data": "deleteCheck", "target": 6, "className": "dt-center", "searchable": false, "orderable": false}
            ],
            "ajax": "./getAvailableUser.htm",
            "dom": '<lftip>',
            "order": [[0, 'asc']],
            "displayLength": 10});


        $(document).on("click", ".addData,.editData", showFormByClick);
        $(document).on("submit", "#addForm,#editForm", sendDataPOSTByAction);
        $(document).on("click", ".deleteMultiple", deleteMultiple);

        //start by show add form
        showFrom("add.htm");
    });

    function showFormByClick() {
        $("#ajaxCRUDfield").load($(this).attr("value"), function () {
        });
        $(window).scrollTop(0);
        return false;
    }

    function showFrom(link) {
        $("#ajaxCRUDfield").load(link, function () {
        });
        $(window).scrollTop(0);
        return false;
    }

    function deleteMultiple() {
        var idArray = [];
        var size;
        $("input[type='checkbox'][name='deletedUserId']:checked").each(function (i) {
            idArray[i] = parseInt($(this).val());
        });
        console.log(idArray);
        size = idArray.length;
        if (size > 0) {
            bootbox.confirm({
                message: "ต้องการลบรายการทั้งหมด " + size + " รายการหรือไม่?",
                className: "dangerFont",
                callback: function (confirmResult) { /* result is a boolean; true = OK, false = Cancel*/
                    if (confirmResult) {
                        $.ajax({
                            type: "POST",
                            async: false,
                            url: "mutipleDelete.htm",
                            data: {"idArray": idArray.toString()}, // serializes the form's elements.
                            success: function (result)
                            {
                                bootbox.alert({
                                    backdrop: true,
                                    className: "dangerFont",
                                    message: result,
                                    callback: function () { /* your callback code */
                                    }
                                });
                                refreshDataAndJumpTo();
                            }
                        });
                    }
                }
            });
        }
    }




    function sendDataPOSTByAction() {
        event.preventDefault();
        var valid;
        // if add or edit form check valid
        //delete edit  form dont need validation+
        console.log($("#addForm").length > 0);
        if ($("#addForm").length > 0) {
            //check only new user
            valid = validateInput();
        } else {
            valid = 1;
        }

        var idfield = $("input[type=hidden][id=empId]");
        var id = "lastest";
        if (typeof idfield !== 'undefined') {
            if (typeof $(idfield).val() !== 'undefined') {
                id = $(idfield).val();
            }
        }
        if (valid === 1) {
            console.log(valid);
            var form = $(this); //wrap this in jQuery
            var url = form.prop('action'); // the script where you handle the form input.
            $.ajax({
                type: "POST",
                url: url,
                data: $(this).serialize(), // serializes the form's elements.
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
                    refreshDataAndJumpTo(id, 0);
                    highlightDatum(id, 0, availableTable);
                }
            });
        }
        return false;
    }

    function validateInput() {
        valid = 1;

        var user = new Object();
        var id = $("#empId").val();
        if (typeof id !== "undefined" && id !== null) {
            //undefined id on create
            id = id.trim();
        }

        $.ajax({
            type: "POST",
            url: "checkIfExisted.htm",
            async: false,
            data: {
                empId: id
            },
            success: function (result)
            {
                if (result.trim().length > 0) {
                    bootbox.alert({
                        backdrop: true,
                        className: "dangerFont",
                        message: result,
                        callback: function () { /* your callback code */
                        }
                    });
                    valid = 0;
                }
            }
        });

        return valid;
    }



</script>

