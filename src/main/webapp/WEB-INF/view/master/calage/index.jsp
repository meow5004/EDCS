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
                    <h3 class="box-title"><i class="fa fa-cog"></i> <spring:message code="calage.title" text="message not found"/></h3>
            </div>
            <div class="box-body">

                <div id="ajaxCRUDfield">
                </div>
                <table id="availableCalageTable" class="dataTable hover cell-border nowrap">
                    <thead>
                        <tr>
                            <th colspan="6" style="text-align: center"><spring:message code="calage.table.avaliable" text="message not found"/></th>
                        </tr>
                        <tr class="alt" >
                            <th><spring:message code="calage.calageId" text="message not found"/></th>
                            <th><spring:message code="calage.calage" text="message not found"/></th>
                            <th><spring:message code="calage.startDate" text="message not found"/></th>
                            <th><spring:message code="calage.endDate" text="message not found"/></th>
                            <th></th>
                            <th><button class="deleteMultiple btn btn-danger"><spring:message code="calage.delete" text="message not found"/><i class="fa fa-trash-o" aria-hidden="true"></i></button></th>
                        </tr> 
                    </thead>
                </table>
                <br/>
                <table id="unavailableCalage" class="dataTable hover cell-border ">
                    <thead>
                        <tr>
                            <th colspan="6" style="text-align: center"><spring:message code="calage.table.unavaliable" text="message not found"/></th>
                        </tr>
                        <tr class="alt" >
                            <th><spring:message code="calage.calageId" text="message not found"/></th>
                            <th><spring:message code="calage.calage" text="message not found"/></th>
                            <th><spring:message code="calage.startDate" text="message not found"/></th>
                            <th><spring:message code="calage.endDate" text="message not found"/></th>
                            <th><button class="reuseMultiple btn btn-success"><spring:message code="calage.reuse" text="message not found"/><i class="fa fa-recycle" aria-hidden="true"></i></button></th>
                            <th><button class="realDeleteMultiple btn btn-danger"><spring:message code="calage.realDelete" text="message not found"/><i class="fa fa-remove" aria-hidden="true"></i></button></th>
                        </tr> 
                    </thead>
                </table>
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
        availableTable = $('#availableCalageTable').DataTable({
            responsive: {
                details: {
                    type: 'inline'
                }
            },
            "columns": [
                {"data": "calageId", "target": 0},
                {"data": "calage", "target": 1},
                {"data": "startDate", "target": 2
                    , render: function (d) {
                        return moment(d).format("DD/MM/YYYY");
                    }
                },
                {"data": "endDate", "target": 3
                    , render: function (d) {
                        return moment(d).format("DD/MM/YYYY");
                    }
                },
                {"data": "actionLink", "target": 4, "searchable": false, "orderable": false},
                {"data": "deleteCheck", "target": 5, "className": "dt-center", "searchable": false, "orderable": false}
            ],
            "ajax": "./getAvailableCalage.htm",
            "dom": '<lftip>',
            "order": [[0, 'asc']],
            "displayLength": 10});

        unAvailableTable = $('#unavailableCalage').DataTable({
            responsive: {
                details: {
                    type: 'inline'
                }
            },
            "columns": [
                {"data": "calageId", "target": 0},
                {"data": "calage", "target": 1},
                {"data": "startDate", "target": 2
                    , render: function (d) {
                        return moment(d).format("DD/MM/YYYY");
                    }
                },
                {"data": "endDate", "target": 3
                    , render: function (d) {
                        return moment(d).format("DD/MM/YYYY");
                    }
                },
                {"data": "reuseCheck", "target": 4, "className": "dt-center", "searchable": false, "orderable": false},
                {"data": "realDeleteCheck", "target": 5, "className": "dt-center", "searchable": false, "orderable": false}
            ],
            "ajax": "./getUnavailableCalage.htm",
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
        });
        $(window).scrollTop(0);$("input").first().focus();
        return false;
    }

    function showFrom(link) {
        $("#ajaxCRUDfield").load(link, function () {
        });
        $(window).scrollTop(0);$("input").first().focus();
        return false;
    }

    function deleteMultiple() {
        var idArray = [];
        var size;
        $("input[type='checkbox'][name='deletedCalageId']:checked").each(function (i) {
            idArray[i] = parseInt($(this).val());
        });
        //console.log(idArray);
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
        $("input[type='checkbox'][name='reuseCalageId']:checked").each(function (i) {
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
        $("input[type='checkbox'][name='realDeletedCalageId']:checked").each(function (i) {
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

        var idfield = $("input[type=hidden][id=calAgeId]");
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
                    highlightDatum(id, 0, availableTable);
                }
            });
        }
        return false;
    }

    function validateInput() {
        valid = 1;

        var calage = new Object();
        var id = $("#calAgeId").val();
        if (typeof id !== "undefined" && id !== null) {
            //undefined id on create
            id = id.trim();
        }
        calage.calAgeId = id;

        calage.calAge = $("#calAge").val().trim();
        calage.startDate = $("#startDate").val().trim();
        calage.endDate = $("#endDate").val().trim();

        $.ajax({
            type: "POST",
            url: "checkIfExisted.htm",
            async: false,
            data: {
                calAgeId: calage.calAgeId,
                calAge: calage.calAge,
                startDate: calage.startDate,
                endDate: calage.endDate
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

