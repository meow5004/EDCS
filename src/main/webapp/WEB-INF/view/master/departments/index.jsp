<%-- 
    Document   : index
    Created on : Sep 4, 2017, 3:57:53 PM
    Author     : admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
    .bg-primary > th,
    .bg-primary > td{
        background-color: #337ab7
    }

    .bg-info > th,
    .bg-info > td{
        background-color: #d9edf7
    }

    tr:hover {
        background-color: #ddd !important;
    }

    .closed-line{
        border-bottom: #337ab7 dashed thick;
    }
</style>
<jsp:include page="../../include/include_header.jsp" flush="true"></jsp:include>
    <div class="col-md-12">
        <div class="row">
            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title"><i class="fa fa-cog"></i><spring:message code="department.title" text="message not found"/></h3>
            </div>
            <div class="box-body">

                <div id="ajaxCRUDfield">
                </div>
                <table id="availableDepartmentTable" class="datatable hover cell-border ">
                    <thead>
                        <tr>
                            <th colspan="10" style="text-align: center"><spring:message code="department.table.avaliable" text="message not found"/></th>
                        </tr>
                        <tr class="alt" >
                            <th><spring:message code="branch" text="message not found" /></th>
                            <th><spring:message code="department.depId" text="message not found"/></th>
                            <th><spring:message code="department.depNameTh" text="message not found"/></th>
                            <th><spring:message code="department.depNameEn"  text="message not found" /></th>
                            <th><spring:message code="department.createBy" text="message not found" /></th>
                            <th><spring:message code="department.createOn" text="message not found"/></th>
                            <th><spring:message code="department.changeBy" text="message not found"/></th>
                            <th><spring:message code="department.changeOn" text="message not found"/></th>
                            <th></th>
                            <th><button class="deleteMultiple btn btn-danger"><spring:message code="department.delete" text="message not found"/><i class="fa fa-trash-o" aria-hidden="true"></i></th>
                        </tr> 
                    </thead>
                </table>
                <br/>
                <table id="unavailableDepartmentTable" class="datatable hover cell-border">
                    <thead>
                        <tr>
                            <th colspan="10" style="text-align: center"><spring:message code="department.table.unavaliable" text="message not found"/></th>
                        </tr>
                        <tr class="alt" >
                            <th><spring:message code="branch" text="message not found" /></th>
                            <th><spring:message code="department.depId" text="message not found"/></th>
                            <th><spring:message code="department.depNameTh" text="message not found"/></th>
                            <th><spring:message code="department.depNameEn"  text="message not found" /></th>
                            <th><spring:message code="department.createBy" text="message not found" /></th>
                            <th><spring:message code="department.createOn" text="message not found"/></th>
                            <th><spring:message code="department.changeBy" text="message not found"/></th>
                            <th><spring:message code="department.changeOn" text="message not found"/></th>
                            <th><button class="reuseMultiple btn btn-success"><spring:message code="department.reuse" text="message not found"/><i class="fa fa-recycle" aria-hidden="true"></i></button></th>
                            <th><button class="realDeleteMultiple btn btn-danger"><spring:message code="department.realDelete" text="message not found"/><i class="fa fa-remove" aria-hidden="true"></i></button></th>
                        </tr> 
                    </thead>
                </table>
            </div>
        </div>

    </div>
</div>

<jsp:include page="../../include/include_footer.jsp" flush="true"></jsp:include>
<script>
    $(document).ready(function () {
        availableTable = $('#availableDepartmentTable').DataTable({
            "columns": [
                {"data": "associateBranchName", "target": 0, "visible": false},
                {"data": "depId", "target": 1},
                {"data": "depNameTh", "target": 2},
                {"data": "depNameEn", "target": 3},
                {"data": "createBy", "target": 4},
                {"data": "createOn", "target": 5},
                {"data": "changeBy", "target": 6},
                {"data": "changeOn", "target": 7},
                {"data": "actionLink", "target": 7, "searchable": false, "orderable": false},
                {"data": "deleteCheck", "target": 8, "className": "dt-center", "searchable": false, "orderable": false}
            ], rowGroup: {
                dataSrc: 'associateBranchName',
                startClassName: "bg-primary text-white"
            },
            "ajax": "./getAvailableDepartment.htm",
            "dom": '<lf<t>ip>',
            "order": [[0, 'asc']],
            "displayLength": 10
        });



        unAvailableTable = $('#unavailableDepartmentTable').DataTable({
            "columns": [
                {"data": "associateBranchName", "target": 0, "visible": false},
                {"data": "depId", "target": 1},
                {"data": "depNameTh", "target": 2},
                {"data": "depNameEn", "target": 3},
                {"data": "createBy", "target": 4},
                {"data": "createOn", "target": 5},
                {"data": "changeBy", "target": 6},
                {"data": "changeOn", "target": 7},
//                {"data": "actionLink", "target": 7, "searchable": false, "orderable": false},
                {"data": "reuseCheck", "target": 8, "className": "dt-center", "searchable": false, "orderable": false},
                {"data": "realDeleteCheck", "target": 9, "className": "dt-center", "searchable": false, "orderable": false}
            ], rowGroup: {
                dataSrc: 'associateBranchName',
                startClassName: "bg-info",
                endClassName: "closed-line"
            },
            "ajax": "./getUnavailableDepartment.htm",
            "dom": '<lf<t>ip>',
            "order": [[0, 'asc']],
            "displayLength": 10
        });



        $(document).on("click", ".addData,.editData,.deleteData,.reuseData", showFormByClick);
        $(document).on("submit", "#addForm,#editForm,#deleteForm,#reuseForm", sendDataPOSTByAction);

        $(document).on("click", ".deleteMultiple", deleteMultiple);
        $(document).on("click", ".reuseMultiple", reuseMultiple);
        $(document).on("click", ".realDeleteMultiple", realDeleteMultiple);
        //start by show add form
        showFrom("add.htm");
    });



    function showFormByClick() {
        $("#ajaxCRUDfield").load($(this).attr("value"), function () {
            var $inputs = $('input[type=text][name=depNameEn],input[type=text][name=depNameTh]');
            $inputs.on('input', function () {
                // Set the required property of the other input to false if this input is not empty.
                $inputs.not(this).prop('required', !$(this).val().length);
            });

            $inputs.trigger("input");
        });
        $(window).scrollTop(0);
        return false;
    }

    function showFrom(link) {
        $("#ajaxCRUDfield").load(link, function () {
            var $inputs = $('input[type=text][name=depNameEn],input[type=text][name=depNameTh]');
            $inputs.on('input', function () {
                // Set the required property of the other input to false if this input is not empty.
                $inputs.not(this).prop('required', !$(this).val().length);
            });
        });
        $(window).scrollTop(0);
        return false;
    }

    function deleteMultiple() {
        var idArray = [];
        var size;
        $("input[type='checkbox'][name='deletedDep']:checked").each(function (i) {
            idArray[i] = $(this).val();
        });
        size = idArray.length;
        if (size > 0) {
            bootbox.confirm({
                message: "ต้องการลบรายการทั้งหมด " + size + " รายการหรือไม่?",
                className: "dangerFont",
                callback: function (confirmResult) { /* result is a boolean; true = OK, false = Cancel*/

                    if (confirmResult) {
                        $.ajax({
                            type: "POST",
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
                                refreshDataAndJumpTo(idArray[0], 1);
                                highlightData(idArray, 1, unAvailableTable);
                            }
                        });
                    }
                }
            });
        }
    }

    function reuseMultiple() {
        var idArray = [];
        var size;
        $("input[type='checkbox'][name='reuseDep']:checked").each(function (i) {
            idArray[i] = $(this).val();
        });
        size = idArray.length;
        if (size > 0) {
            bootbox.confirm({
                message: "ต้องการกู้คืนรายการทั้งหมด " + size + " รายการหรือไม่?",
                className: "successFont",
                callback: function (confirmResult) { /* result is a boolean; true = OK, false = Cancel*/

                    if (confirmResult) {
                        $.ajax({
                            type: "POST",
                            url: "mutipleReuse.htm",
                            data: {"idArray": idArray.toString()}, // serializes the form's elements.
                            success: function (result)
                            {
                                bootbox.alert({
                                    backdrop: true,
                                    className: "successFont",
                                    message: result,
                                    callback: function () { /* your callback code */
                                    }
                                });
                                refreshDataAndJumpTo(idArray[0], 1);
                                highlightData(idArray, 1, availableTable);
                            }
                        });
                    }
                }
            });
        }
    }

    function realDeleteMultiple() {
        var idArray = [];
        var size;
        $("input[type='checkbox'][name='realDeletedDep']:checked").each(function (i) {
            idArray[i] = $(this).val();
        });
        size = idArray.length;
        if (size > 0) {
            bootbox.confirm({
                message: "ต้องการลบรายการรหัส (" + idArray.join(",") + ") ทั้งหมด " + size + " รายการโดยถาวรหรือไม่?",
                className: "dangerFont",
                callback: function (confirmResult) { /* result is a boolean; true = OK, false = Cancel*/

                    if (confirmResult) {
                        $.ajax({
                            type: "POST",
                            url: "mutipleRealDelete.htm",
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
        //delete and reuse form dont need validation
        if ($("#addForm").length > 0 || $("#editForm").length > 0) {
            valid = validateInput();
        } else {
            valid = 1;
        }

        var idfield = $("#depId");
        var id = "lastest";
        if (typeof idfield !== 'undefined') {
            if (typeof $(idfield).val() !== 'undefined') {
                id = $(idfield).val();
            }
        }

        if (valid === 1) {
            var form = $(this); //wrap this in jQuery
            var url = form.prop('action'); // the script where you handle the form input.
            $.ajax({
                type: "POST",
                url: url,
                data: $(this).serialize(), // serializes the form's elements.
                success: function (result)
                {
                    bootbox.alert({
                        backdrop: true,
                        className: "successFont",
                        message: result,
                        callback: function () { /* your callback code */
                        }
                    });
                    refreshDataAndJumpTo(id, 1);
                    highlightDatum(id, 1, availableTable);
                }
            });
        }
        return false;
    }

    function validateInput() {
        var id = $("#depId").val();

        //edited case
        var oldValueId = $("#oldDepId").val();

        valid = 1;
        if (id.trim().length <= 0) {
            bootbox.alert({
                backdrop: true,
                className: "dangerFont",
                message: "please Enter department ID",
                callback: function () { /* your callback code */
                }
            });
            valid = 0;
        }
        if ($("#depNameTh").val().trim().length <= 0 && $("#depNameEn").val().trim().length <= 0) {
            valid = 0;
            bootbox.alert({
                backdrop: true,
                className: "dangerFont",
                message: "please Enter department Name",
                callback: function () { /* your callback code */
                }
            });
        }
        if ($("#branchId").val().trim().length <= 0) {
            valid = 0;
            bootbox.alert({
                backdrop: true,
                className: "dangerFont",
                message: "please choose at least one branch",
                callback: function () { /* your callback code */
                }
            });
        }
        if (oldValueId !== null && typeof oldValueId !== "undefined") {
            $.ajax({
                type: "POST",
                url: "checkRepeatId.htm",
                async: false,
                data: {id: id.trim(), oldDepId: oldValueId.trim()}, // serializes the form's elements.
                success: function (result)
                {
                    if (result.length > 0) {
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
        }
        return valid;
    }
</script>

