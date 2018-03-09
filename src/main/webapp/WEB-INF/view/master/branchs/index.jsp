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
                    <h3 class="box-title"><i class="fa fa-cog"></i> <spring:message code="branch.title" text="message not found"/></h3>
            </div>
            <div class="box-body">

                <div id="ajaxCRUDfield">
                </div>
                <table id="availableBranchTable" class="dataTable hover cell-border compact">
                    <thead>
                        <tr>
                            <th colspan="5" style="text-align: center"><spring:message code="branch.table.avaliable" text="message not found"/></th>
                        </tr>
                        <tr class="alt" >
                            <th><spring:message code="branch.branchId" text="message not found"/></th>
                            <th><spring:message code="branch.branchNameTh" text="message not found"/></th>
                            <th><spring:message code="branch.branchNameEn"  text="message not found" /></th>
                            <th></th>
                            <th><button class="deleteMultiple btn btn-danger"><spring:message code="branch.delete" text="message not found"/><i class="fa fa-trash-o" aria-hidden="true"></i></button></th>
                        </tr> 
                    </thead>
                </table>
                <br/>
                <table id="unavailableBranchTable" class="dataTable hover cell-border nowrap">
                    <thead>
                        <tr>
                            <th colspan="5" style="text-align: center"><spring:message code="branch.table.unavaliable" text="message not found"/></th>
                        </tr>
                        <tr class="alt" >
                            <th><spring:message code="branch.branchId" text="message not found"/></th>
                            <th><spring:message code="branch.branchNameTh" text="message not found"/></th>
                            <th><spring:message code="branch.branchNameEn"  text="message not found" /></th>
                            <th><button class="reuseMultiple btn btn-success"><spring:message code="branch.reuse" text="message not found"/><i class="fa fa-recycle" aria-hidden="true"></i></button></th>
                            <th><button class="realDeleteMultiple btn btn-danger"><spring:message code="branch.realDelete" text="message not found"/><i class="fa fa-remove" aria-hidden="true"></i></button></th>
                        </tr> 
                    </thead>
                </table>
            </div>
        </div>

    </div>
</div>
<div id="resultDialog" title="Result" style="display:none;">
</div>


<jsp:include page="../../include/include_footer.jsp" flush="true"></jsp:include>    <!-- has close modal on overlay click-->

<script>

    $(document).ready(function () {
        availableTable = $('#availableBranchTable').DataTable({
            responsive: {
                details: {
                    type: 'inline'
                }
            },
            "columns": [
                {"data": "branchId", "target": 0},
                {"data": "branchNameTh", "target": 1},
                {"data": "branchNameEn", "target": 2},
                {"data": "actionLink", "target": 3, "searchable": false, "orderable": false},
                {"data": "deleteCheck", "target": 4, "className": "dt-center", "searchable": false, "orderable": false}
            ],
            "ajax": "./getAvailableBranch.htm",
            "dom": '<lftip>',
            "order": [[0, 'asc']],
            "displayLength": 10
        });
        unAvailableTable = $('#unavailableBranchTable').DataTable({
            responsive: {
                details: {
                    type: 'inline'
                }
            },
            "columns": [
                {"data": "branchId", "target": 0},
                {"data": "branchNameTh", "target": 1},
                {"data": "branchNameEn", "target": 2},
//                {"data": "actionLink", "target": 7}
                {"data": "reuseCheck", "target": 3, "className": "dt-center", "searchable": false, "orderable": false},
                {"data": "realDeleteCheck", "target": 4, "className": "dt-center", "searchable": false, "orderable": false}
            ],
            "ajax": "./getUnavailableBranch.htm",
            "dom": '<lftip>',
            "order": [[0, 'asc']],
            "displayLength": 10,
            fnDrawCallback: function () {
                if ($(this).find('.dataTables_empty').length > 0) {
                    $(this).closest(".dataTables_wrapper").hide();
                } else {
                    $(this).closest(".dataTables_wrapper").show();
                }
            }
        });
        $(document).on("click", ".addData,.editData", showFormByClick);
        $(document).on("submit", "#addForm,#editForm", sendDataPOSTByAction);
        $(document).on("click", ".deleteMultiple", deleteMultiple);
        $(document).on("click", ".reuseMultiple", reuseMultiple);
        $(document).on("click", ".realDeleteMultiple", realDeleteMultiple);
        //start by show add form
        showFrom("add.htm");
    });
    function showFormByClick() {
        $("#ajaxCRUDfield").load($(this).attr("value"), function () {
            var $inputs = $('input[type=text][name=branchNameTh],input[type=text][name=branchNameEn]');
            $inputs.on('input', function () {
                // Set the required property of the other input to false if this input is not empty.
                $inputs.not(this).prop('required', !$(this).val().length);
            });
            $inputs.trigger("input");
        });
        $(window).scrollTop(0);$("input").first().focus();
        return false;
    }

    function showFrom(link) {
        $("#ajaxCRUDfield").load(link, function () {
            var $inputs = $('input[type=text][name=branchNameTh],input[type=text][name=branchNameEn]');
            $inputs.on('input', function () {
                // Set the required property of the other input to false if this input is not empty.
                $inputs.not(this).prop('required', !$(this).val().length);
            });
            $inputs.trigger("input");
        });
        $(window).scrollTop(0);$("input").first().focus();
        return false;
    }

    function deleteMultiple() {
        var idArray = [];
        var size;
        $("input[type='checkbox'][name='deletedBranch']:checked").each(function (i) {
            idArray[i] = parseInt($(this).val());
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
                                refreshDataAndJumpTo(idArray[0], 0);
                                highlightData(idArray, 0, unAvailableTable);
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
        $("input[type='checkbox'][name='reuseBranch']:checked").each(function (i) {
            idArray[i] = parseInt($(this).val());
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
                            async: false,
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
                                refreshDataAndJumpTo(idArray[0], 0);
                                highlightData(idArray, 0, availableTable);
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
        $("input[type='checkbox'][name='realDeletedBranch']:checked").each(function (i) {
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

        var idfield = $("input[type=hidden][id=branchId]");
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
        if ($("#branchNameTh").val().trim().length <= 0 && $("#branchNameEn").val().trim().length <= 0) {
            valid = 0;
            bootbox.alert({
                backdrop: true,
                className: "dangerFont",
                message: "enter Branch Name before proceed",
                callback: function () { /* your callback code */
                }
            });
        }
        var id = $("#branchId").val();
        $.ajax({
            type: "POST",
            url: "checkIfExisted.htm",
            async: false,
            data: {nameTh: $("#branchNameTh").val().trim(), nameEn: $("#branchNameEn").val().trim(), id: id}, // serializes the form's elements.
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

