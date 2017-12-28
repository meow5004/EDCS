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

    .sticky-wrap{
        overflow-x: hidden !important;
    }
</style>
<jsp:include page="../../include/include_header.jsp" flush="true"></jsp:include>
    <div class="col-md-12">

        <div class="box box-primary">
            <div class="box-header">
                <h3 class="box-title"><i class="fa fa-cog"></i> <spring:message code="measure.title" text="message not found"/></h3>
        </div>
        <div class="box-body">

            <div id="ajaxCRUDfield">
            </div>
            <table id="availableMeasureTable" class="datatable hover cell-border">
                <thead>
                    <tr>
                        <th colspan="12" style="text-align: center"><spring:message code="measure.table.avaliable" text="message not found"/></th>
                    </tr>
                    <tr class="alt" >
                        <th><spring:message code="measure.measure" text="message not found"/></th>
                        <th><spring:message code="measure.measureCode" text="message not found"/></th>
                        <th><spring:message code="measure.measureName" text="message not found"/></th>
                        <th><spring:message code="measure.measureGroup" text="message not found"/></th>
                        <th><spring:message code="measure.calpointId" text="message not found"/></th>
                        <th><spring:message code="measure.range" text="message not found"/></th>
                        <th><spring:message code="measure.useRange" text="message not found"/></th>
                        <th><spring:message code="measure.description" text="message not found"/></th>
                        <th><spring:message code="measure.measureTimes" text="message not found"/></th>
                        <th><spring:message code="measure.abtype" text="message not found"/></th>

                        <th></th>
                        <th><button class="deleteMultiple btn btn-danger"><spring:message code="measure.delete" text="message not found"/><i class="fa fa-trash-o" aria-hidden="true"></i></button></th>
                    </tr> 
                </thead>
            </table>
            <br/>
            <table id="unavailableMeasure" class="datatable hover cell-border ">
                <thead>
                    <tr>
                        <th colspan="12" style="text-align: center"><spring:message code="measure.table.unavaliable" text="message not found"/></th>
                    </tr>
                    <tr class="alt" >
                        <th><spring:message code="measure.measure" text="message not found"/></th>
                        <th><spring:message code="measure.measureCode" text="message not found"/></th>
                        <th><spring:message code="measure.measureName" text="message not found"/></th>
                        <th><spring:message code="measure.measureGroup" text="message not found"/></th>
                        <th><spring:message code="measure.calpointId" text="message not found"/></th>
                        <th><spring:message code="measure.range" text="message not found"/></th>
                        <th><spring:message code="measure.useRange" text="message not found"/></th>
                        <th><spring:message code="measure.description" text="message not found"/></th>
                        <th><spring:message code="measure.measureTimes" text="message not found"/></th>
                        <th><spring:message code="measure.abtype" text="message not found"/></th>
                        <th><button class="reuseMultiple btn btn-success"><spring:message code="measure.reuse" text="message not found"/><i class="fa fa-recycle" aria-hidden="true"></i></button></th>
                        <th><button class="realDeleteMultiple btn btn-danger"><spring:message code="measure.realDelete" text="message not found"/><i class="fa fa-remove" aria-hidden="true"></i></button></th>
                    </tr> 
                </thead>
            </table>
        </div>
    </div>


</div>
<div id="resultDialog" title="Result" style="display:none;">
</div>


<jsp:include page="../../include/include_footer.jsp" flush="true"></jsp:include>


<script>

    $(document).ready(function () {
        availableTable = $('#availableMeasureTable').DataTable({
            "columns": [
                {"data": "measure", "target": 0},
                {"data": "measureCode", "target": 1},
                {"data": "measureName", "target": 2},
                {"data": "measureGroupName", "target": 3},
                {"data": "calpointId", "target": 4},
                {"data": "range", "target": 5},
                {"data": "useRange", "target": 6},
                {"data": "description", "target": 7},
                {"data": "measureTime", "target": 8},
                {"data": "abtype", "target": 9},
                {"data": "actionLink", "target": 10, "searchable": false, "orderable": false},
                {"data": "deleteCheck", "target": 11, "className": "dt-center", "searchable": false, "orderable": false}
            ],
            "ajax": "./getAvailableMeasure.htm",
            "dom": '<lf<t>ip>',
            "order": [[0, 'asc']],
            "scrollX": true,
            "displayLength": 10});
        unAvailableTable = $('#unavailableMeasure').DataTable({
            "columns": [
                {"data": "measure", "target": 0},
                {"data": "measureCode", "target": 1},
                {"data": "measureName", "target": 2},
                {"data": "measureGroupName", "target": 3},
                {"data": "calpointId", "target": 4},
                {"data": "range", "target": 5},
                {"data": "useRange", "target": 6},
                {"data": "description", "target": 7},
                {"data": "measureTime", "target": 8},
                {"data": "abtype", "target": 9},
                {"data": "reuseCheck", "target": 10, "className": "dt-center", "searchable": false, "orderable": false},
                {"data": "realDeleteCheck", "target": 11, "className": "dt-center", "searchable": false, "orderable": false}
            ],
            "ajax": "./getUnavailableMeasure.htm",
            "dom": '<lf<t>ip>',
            "order": [[0, 'asc']],
            "scrollX": true,
            "displayLength": 10});
        $(document).on("click", ".addData,.editData", showFormByClick);
        $(document).on("submit", "#addForm,#editForm", sendDataPOSTByAction);
        $(document).on("click", ".deleteMultiple", deleteMultiple);
        $(document).on("click", ".reuseMultiple", reuseMultiple);
        $(document).on("click", ".realDeleteMultiple", realDeleteMultiple);
        //start by show add form
        showFrom("add.htm");
        // rangeMin rangeMax   useRangeMin useRangeMax
        //
        $(document).on("change", "#calpointId", function () {
            console.log($(this).val());
            var point = $(this).find("option:selected").text();
            console.log(point);
            var min = point.split("-")[0].trim();
            var max = point.split("-")[1].trim();
            $("#rangeMin,#useRangeMin").filter(function () {
                var val = parseInt($(this).val());
                var pass = (val <= 0 || isNaN(val));
                return pass;
            }).val(min);

            $("#useRangeMax,#rangeMax").filter(function () {
                var val = parseInt($(this).val());
                var pass = (val <= 0 || isNaN(val));
                return pass;
            }).val(max);

        });
        $(document).on("change", '#rangeMin,#useRangeMax,#rangeMax,#useRangeMin', function () {
            var max = parseInt($("#rangeMax").val());
            var min = parseInt($("#rangeMin").val());
            var useMin = parseInt($("#useRangeMin").val());
            var useMax = parseInt($("#useRangeMax").val());
            if (min > 0 && max > 0 && min > max) {
                $(this).val("");
                bootbox.alert({
                    backdrop: true,
                    className: "dangerFont",
                    message: "ค่าต่ำสุดต้องมีค่าน้อยกว่าค่าสูงสุด",
                    callback: function () { /* your callback code */
                    }
                });
            }
            if (useMin > 0 && useMax > 0 && useMin > useMax) {
                $(this).val("");
                bootbox.alert({
                    backdrop: true,
                    className: "dangerFont",
                    message: "ค่าต่ำสุดที่ใช้งานจริงต้องมีค่าน้อยกว่าค่าสูงสุดที่ใช้งานจริง",
                    callback: function () { /* your callback code */
                    }
                });
            }
            if (min.length > 0 && useMin.length > 0 && min > useMin) {
                $(this).val("");
                bootbox.alert({
                    backdrop: true,
                    className: "dangerFont",
                    message: "ค่าต่ำสุดต้องมีค่าน้อยกว่าค่าต่ำสุดที่ใช้งานจริง",
                    callback: function () { /* your callback code */
                    }
                });
            }
            if (max > 0 && useMax > 0 && max < useMax) {
                $(this).val("");
                bootbox.alert({
                    backdrop: true,
                    className: "dangerFont",
                    message: "ค่าสูงสุดต้องมีค่ามากกว่าค่าสูงสุดที่ใช้งานจริง",
                    callback: function () { /* your callback code */
                    }
                });
            }
        });
    });
    function showFormByClick() {
        $("#ajaxCRUDfield").load($(this).attr("value"), function () {
            var $inputs = $('input[type=text][name=measureNameTh],input[type=text][name=measureNameEn]');
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
            var $inputs = $('input[type=text][name=unitNameTh],input[type=text][name=unitNameEn]');
            $inputs.on('input', function () {
                // Set the required property of the other input to false if this input is not empty.
                $inputs.not(this).prop('required', !$(this).val().length);
            });
            $inputs.trigger("input");
        });
        $(window).scrollTop(0);
        return false;
    }

    function deleteMultiple() {
        var idArray = [];
        var size;
        $("input[type='checkbox'][name='deletedmeasureId']:checked").each(function (i) {
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
        $("input[type='checkbox'][name='reusemeasureId']:checked").each(function (i) {
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
        $("input[type='checkbox'][name='realDeletedmeasureId']:checked").each(function (i) {
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

        var idfield = $("input[type=hidden][id=measure]");
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
                }
            });
        }
        return false;
    }

    function validateInput() {
        valid = 1;
        if ($("#measureNameTh").val().trim().length <= 0 && $("#measureNameEn").val().trim().length <= 0) {
            valid = 0;
            bootbox.alert({
                backdrop: true,
                className: "dangerFont",
                message: "please Enter measure Name",
                callback: function () { /* your callback code */
                }
            });
        }

        var id = $("measure").val();
        $.ajax({
            type: "POST",
            url: "checkIfExisted.htm",
            async: false,
            data: {measureNameTh: $("#measureNameTh").val().trim(), measureNameEn: $("#measureNameEn").val().trim(), id: id}, // serializes the form's elements.
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

    function refreshDataAndJumpTo(jumpTo, searchColumn) {
        availableTable.ajax.reload(function () {
            //relaod before jump
            if (jumpTo !== null) {
                if (jumpTo === "lastest") {
                    var maxId = availableTable
                            .column(searchColumn)
                            .data()
                            .sort(function (a, b) {
                                return a - b;
                            }).reverse()[0];
                    availableTable.page.jumpToData(maxId, searchColumn);
                } else {
                    var toInt = parseInt(jumpTo);
                    availableTable.page.jumpToData(toInt, searchColumn);
                }
            }
        }, false);
        unAvailableTable.ajax.reload(function () {
            //relaod before jump
            if (jumpTo !== null) {
                if (jumpTo === "lastest") {
                    var maxId = unAvailableTable
                            .column(searchColumn)
                            .data()
                            .sort(function (a, b) {
                                return a - b;
                            }).reverse()[0];
                    unAvailableTable.page.jumpToData(maxId, searchColumn);
                } else {
                    var toInt = parseInt(jumpTo);
                    unAvailableTable.page.jumpToData(toInt, searchColumn);
                }
            }
        }, false);
        //start by show add form
        showFrom("add.htm");
    }

    function highlightData(/*array of object*/ ids, columnToSearch, table) {
        table.ajax.reload(function () {
            for (var i = 0; i < ids.length; i++) {
                var pos = table.column(columnToSearch, {order: 'index'}).data().indexOf(ids[i]);
                if (pos >= 0) {
                    $(table.row(pos).nodes()).addClass("lastModifiedRow");
                }
            }
        }, false);
        return this;
    }

</script>
