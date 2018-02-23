<%-- 
    Document   : index
    Created on : Sep 4, 2017, 3:57:53 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
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
        <div class="box-body container-fluid">

            <div id="ajaxCRUDfield" class="row">
            </div>
            <hr style="background-color: black;color:black;height: 5px"/>
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <h3>เลือกแผนก/กลุ่มเครื่องวัดเพื่อแสดงเครื่องวัด</h4>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-md-1">
                    เลือกแผนก :
                </div>
                <div class="col-md-3">
                    <select id="departmentFilter" class="datatableFilterSelection">
                        <option value="" selected disabled="disabled"></option>
                        <option value="">ทุกแผนก</option>
                        <c:forEach items="${departments}" var="dep">
                            <option value="${dep.depId}">${dep.fullName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-2">
                    เลือกกลุ่มเครื่องวัด :
                </div>
                <div class="col-md-3">
                    <select id="measureGroupFilter" class="datatableFilterSelection">
                        <option value="" selected disabled="disabled"></option>
                        <option value="">ทุกกลุ่ม</option>
                        <c:forEach items="${measureGroups}" var="group">
                            <option value="${group.measureGroupId}">${group.fullName}</option>
                        </c:forEach>
                    </select>
                </div>

            </div>
            <br/>
            <div class="table-responsive row">
                <table id="availableMeasureTable" class="dataTable hover cell-border nowrap">
                    <thead>
                        <tr>
                            <th colspan="13" style="text-align: center"><spring:message code="measure.table.avaliable" text="message not found"/></th>
                        </tr>
                        <tr class="alt" >
                            <th><spring:message code="measure.measure" text="message not found"/></th>
                            <th><spring:message code="measure.measureGroup" text="message not found"/></th>
                            <th><spring:message code="measure.depName" text="message not found"/></th>
                            <th><spring:message code="measure.measureCode" text="message not found"/></th>
                            <th><spring:message code="measure.measureName" text="message not found"/></th>
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
                    <tbody>
                        <tr>
                            <td  colspan="13">
                                <h1>เลือกแผนก และ/หรือกลุ่มเครื่องวัดเพื่อแสดงข้อมูล</h1>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <br/>
            <div class="table-responsive row">
                <table id="unavailableMeasureTable" class="dataTable hover cell-border no-wrap">
                    <thead>
                        <tr>
                            <th colspan="13" style="text-align: center"><spring:message code="measure.table.unavaliable" text="message not found"/></th>
                        </tr>
                        <tr class="alt" >
                            <th><spring:message code="measure.measure" text="message not found"/></th>
                            <th><spring:message code="measure.measureGroup" text="message not found"/></th>
                            <th><spring:message code="measure.depName" text="message not found"/></th>
                            <th><spring:message code="measure.measureCode" text="message not found"/></th>
                            <th><spring:message code="measure.measureName" text="message not found"/></th>
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
                    <tbody>
                        <tr>
                            <td  colspan="13">
                                <h1>เลือกแผนก และ/หรือกลุ่มเครื่องวัดเพื่อแสดงข้อมูล</h1>
                            </td>
                        </tr>
                    </tbody>
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
        var availableTable;
        var unAvailableTable;
        $(".datatableFilterSelection").on("change", function () {
            var groupId = $("#measureGroupFilter").val();
            var depId = $("#departmentFilter").val();
            if (availableTable == null && unAvailableTable == null) {
                //if not init then start initilize
                availableTable = $('#availableMeasureTable').DataTable({
                    "columns": [
                        {"data": "measureId", "target": 0, "visible": false},
                        {"data": "measureGroupName", "target": 1},
                        {"data": "depName", "target": 2},
                        {"data": "measureCode", "target": 3},
                        {"data": "measureName", "target": 4},
                        {"data": "calpointId", "target": 5},
                        {"data": "range", "target": 6},
                        {"data": "useRange", "target": 7},
                        {"data": "description", "target": 8},
                        {"data": "measureTime", "target": 9},
                        {"data": "abtype", "target": 10, "render": function (data, type, row, meta) {
                                if (data === "A") {
                                    return "วัดหน้าเดียว";
                                } else if (data === "AB") {
                                    return "วัดสองหน้า";
                                } else {
                                    return 'ไม่ได้ระบุ';
                                }
                            }},
                        {"data": "actionLink", "target": 11, "searchable": false, "orderable": false},
                        {"data": "deleteCheck", "target": 12, "className": "dt-center", "searchable": false, "orderable": false}
                    ],
                    'ajax': {
                        'contentType': 'application/json',
                        'url': "./getAvailableMeasure.htm?depId=" + depId + "&measureGroupId=" + groupId,
                        'type': 'POST',
                        'data': function (d) {
                            return JSON.stringify(d);
                        }
                    },
                    "dom": '<lftip>',
                    "order": [[0, 'asc']],
                    "displayLength": 10});
                unAvailableTable = $('#unavailableMeasureTable').DataTable({
                    "columns": [
                        {"data": "measureId", "target": 0, "visible": false},
                         {"data": "measureGroupName", "target": 1},
                             {"data": "depName", "target": 2},
                        {"data": "measureCode", "target": 3},
                        {"data": "measureName", "target": 4},
                        {"data": "calpointId", "target": 5},
                        {"data": "range", "target": 6},
                        {"data": "useRange", "target": 7},
                        {"data": "description", "target": 8},
                        {"data": "measureTime", "target": 9},
                        {"data": "abtype", "target": 10, render: function (data, type, row, meta) {
                                if (data === "A") {
                                    return "วัดหน้าเดียว";
                                } else if (data === "AB") {
                                    return "วัดสองหน้า";
                                } else {
                                    return 'ไม่ได้ระบุ';
                                }
                            }
                        },
                        {"data": "reuseCheck", "target": 11, "className": "dt-center", "searchable": false, "orderable": false},
                        {"data": "realDeleteCheck", "target": 12, "className": "dt-center", "searchable": false, "orderable": false}
                    ],
                    "ajax": "./getUnavailableMeasure.htm?depId=" + depId + "&measureGroupId=" + groupId,
                    "dom": '<lftip>',
                    "order": [[0, 'asc']],
                    "displayLength": 10});
            } else {
                //if already initlize then reload data
                availableTable.ajax.url("./getAvailableMeasure.htm?depId=" + depId + "&measureGroupId=" + groupId).load();
                unAvailableTable.ajax.url("./getUnavailableMeasure.htm?depId=" + depId + "&measureGroupId=" + groupId).load();
            }
        });

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
            var point = $(this).find("option:selected").text();
            var min = point.split("-")[0].trim();
            var max = point.split("-")[1].trim();
            $("#rangeMin,#useRangeMin").filter(function () {
                var defaultVal = parseInt($(this).prop("defaultValue"));
                var pass = defaultVal <= 0 || isNaN(defaultVal);
                return pass;
            }).val(min);

            $("#useRangeMax,#rangeMax").filter(function () {
                var defaultVal = parseInt($(this).prop("defaultValue"));
                var pass = defaultVal <= 0 || isNaN(defaultVal);
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



</script>

