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
                    <h3 class="box-title"><i class="fa fa-cog"></i> <spring:message code="model.title" text="message not found"/></h3>
            </div>
            <div class="box-body">
                <div class="row">
                    <div id="ajaxCRUDfield">
                    </div>
                </div>
                <br/>
                <div class="table-responsive row">
                    <table id="availableModelTable" class="dataTable hover cell-border nowrap compact">
                        <thead>
                            <tr>
                                <th colspan="14" style="text-align: center"><spring:message code="model.table.avaliable" text="message not found"/></th>
                            </tr>
                            <tr class="alt" >
                                <th><spring:message code="model.modelId" text="message not found"/></th>
                                <th><spring:message code="model.modelCode" text="message not found"/></th>
                                <th><spring:message code="model.measureId" text="message not found"/></th>
                                <th><spring:message code="model.measureCode" text="message not found"/></th>
                                <th><spring:message code="model.measureName" text="message not found"/></th>
                                <th><spring:message code="model.resolution" text="message not found"/></th>
                                <th><spring:message code="model.uncertainty" text="message not found"/></th>
                                <th><spring:message code="model.cerNo" text="message not found"/></th>
                                <th><spring:message code="model.locationBy" text="message not found"/></th>
                                <th><spring:message code="model.locationReturn" text="message not found"/></th>
                                <th><spring:message code="model.duedate" text="message not found"/></th>
                                <th><spring:message code="model.activate" text="message not found"/></th>
                                <th></th>
                                <th><button class="deleteMultiple btn btn-danger"><spring:message code="model.delete" text="message not found"/><i class="fa fa-trash-o" aria-hidden="true"></i></button></th>
                            </tr> 
                        </thead>
                    </table>
                </div>
                <br/>
                <div class="table-responsive row">
                    <table id="unavailableModelGroup" class="dataTable hover cell-border compact">
                        <thead>
                            <tr>
                                <th colspan="13" style="text-align: center"><spring:message code="model.table.unavaliable" text="message not found"/></th>
                            </tr>
                            <tr class="alt" >
                                <th><spring:message code="model.modelId" text="message not found"/></th>
                                <th><spring:message code="model.modelCode" text="message not found"/></th>
                                <th><spring:message code="model.measureId" text="message not found"/></th>
                                <th><spring:message code="model.measureCode" text="message not found"/></th>
                                <th><spring:message code="model.measureName" text="message not found"/></th>
                                <th><spring:message code="model.resolution" text="message not found"/></th>
                                <th><spring:message code="model.uncertainty" text="message not found"/></th>
                                <th><spring:message code="model.cerNo" text="message not found"/></th>
                                <th><spring:message code="model.locationBy" text="message not found"/></th>
                                <th><spring:message code="model.locationReturn" text="message not found"/></th>
                                <th><spring:message code="model.duedate" text="message not found"/></th>

                                <th><button class="reuseMultiple btn btn-success"><spring:message code="model.reuse" text="message not found"/><i class="fa fa-recycle" aria-hidden="true"></i></button></th>
                                <th><button class="realDeleteMultiple btn btn-danger"><spring:message code="model.realDelete" text="message not found"/><i class="fa fa-remove" aria-hidden="true"></i></button></th>
                            </tr> 
                        </thead>
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>
<div id="resultDialog" title="Result" style="display:none;">
</div>


<jsp:include page="../../include/include_footer.jsp" flush="true"></jsp:include>


<script>

    $(document).ready(function () {


        availableTable = $('#availableModelTable').DataTable({

            "columns": [
                {"data": "modelId", "target": 0, "visible": false},
                {"data": "modelCode", "target": 1},
                {"data": "measureId", "target": 2, "visible": false},
                {"data": "measureCode", "target": 3},
                {"data": "measureName", "target": 4},
                {"data": "resolution", "target": 5},
                {"data": "uncertainty", "target": 6},
                {"data": "cerNo", "target": 7},
                {"data": "locationBy", "target": 8},
                {"data": "locationReturn", "target": 9},
                {"data": "duedate", "target": 10},
                {"data": "activeCheck", "target": 11, "searchable": false, "orderable": false},
                {"data": "actionLink", "target": 12, "searchable": false, "orderable": false},
                {"data": "deleteCheck", "target": 13, "className": "dt-center", "searchable": false, "orderable": false}
            ],
            "ajax": "./getAvailableModel.htm",
            "dom": '<lftip>',
            "order": [[0, 'asc']],
            "displayLength": 10,
            fnDrawCallback: function () {
                $("input[name='activate']").on("change", function () {
                    if (this.checked) {
                        $.ajax({
                            type: "POST",
                            url: "activate.htm",
                            data: {"modelId": $(this).val()} // serializes the form's elements.
                        });
                    } else {
                        $.ajax({
                            type: "POST",
                            url: "disactivate.htm",
                            data: {"modelId": $(this).val()} // serializes the form's elements.
                        });
                    }
                });
            }
        });
        unAvailableTable = $('#unavailableModelGroup').DataTable({

            "columns": [
                {"data": "modelId", "target": 0, "visible": false},
                {"data": "modelCode", "target": 1},
                {"data": "measureId", "target": 2, "visible": false},
                {"data": "measureCode", "target": 3},
                {"data": "measureName", "target": 4},
                {"data": "resolution", "target": 5},
                {"data": "uncertainty", "target": 6},
                {"data": "cerNo", "target": 7},
                {"data": "locationBy", "target": 8},
                {"data": "locationReturn", "target": 9},
                {"data": "duedate", "target": 10},
                {"data": "reuseCheck", "target": 11, "className": "dt-center", "searchable": false, "orderable": false},
                {"data": "realDeleteCheck", "target": 12, "className": "dt-center", "searchable": false, "orderable": false}
            ],
            "ajax": "./getUnavailableModel.htm",
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
            $("#dueDate").datepicker({
                dateFormat: 'dd/mm/yy',
                changeYear: true,
                changeMonth: true});
        });
        $(window).scrollTop(0);
        $("input").first().focus();
        return false;
    }

    function showFrom(link) {
        $("#ajaxCRUDfield").load(link, function () {
            $("#dueDate").datepicker({
                dateFormat: 'dd/mm/yy',
                changeYear: true,
                changeMonth: true});
        });
        $(window).scrollTop(0);
        $("input").first().focus();
        return false;
    }

    function deleteMultiple() {
        var idArray = [];
        var size;
        $("input[type='checkbox'][name='deletedModel']:checked").each(function (i) {
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
        $("input[type='checkbox'][name='reuseModel']:checked").each(function (i) {
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
        $("input[type='checkbox'][name='realDeletedModel']:checked").each(function (i) {
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

        var idfield = $("input[type=hidden][id=model]");
        var id = "lastest";
        if (typeof idfield !== 'undefined') {
            if (typeof $(idfield).val() !== 'undefined') {
                id = $(idfield).val();
            }
        }
        if (valid === 1) {
            //console.log(valid);
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
                }
            });
        }
        return false;
    }

    function validateInput() {
        return 1;
    }



</script>

