<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Bootstrap 3.3.2 -->
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- Font Awesome Icons -->
<link href="../font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link href="../ionicons/css/ionicons.min.css" rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="../dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
<!-- AdminLTE Skins. Choose a skin from the css/skins 
     folder instead of downloading all of them to reduce the load. -->
<link href="../dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />   
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="../css/jquery-ui.theme.min.css" rel="stylesheet" type="text/css"/>
<link href="../css/jquery-ui.structure.min.css" rel="stylesheet" type="text/css"/>
<link href="../css/normalize.css" rel="stylesheet" type="text/css"/>
<link href="../css/component.css" rel="stylesheet" type="text/css"/>
<link href="../css/demo.css" rel="stylesheet" type="text/css"/>
<!--datatable-->
<link href="../plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
<link href="../plugins/dataTable/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
<link href="../plugins/dataTable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css"/>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
<![endif]-->
<script src="../js/jquery.js" type="text/javascript"></script>
<script src="../js/jquery-ui.min.js" type="text/javascript"></script>
<!-- Bootstrap 3.3.2 JS -->
<script src="../bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!-- SlimScroll -->
<script src="../plugins/slimScroll/jquery.slimscroll.js" type="text/javascript"></script>
<!-- FastClick -->
<script src='../plugins/fastclick/fastclick.min.js'></script>
<!-- AdminLTE App -->
<script src="../dist/js/app.min.js" type="text/javascript"></script>
<script src="../js/jquery.ba-throttle-debounce.min.js" type="text/javascript"></script>
<!--<script src="../js/jquery.stickyheader.js" type="text/javascript"></script>-->

<script src="../plugins/dataTable/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="../plugins/dataTable/dataTables.responsive.min.js" type="text/javascript"></script>
<script src="../plugins/dataTable/dataTables.rowGroup.min.js" type="text/javascript"></script>
<script src="../js/bootbox.min.js" type="text/javascript"></script>
<script src="../js/moment.js" type="text/javascript"></script>
<script src="../plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>


<script>
    $(function () {
        //regex psudo selector
        jQuery.expr[':'].regex = function (elem, index, match) {
            var regex = new RegExp(match[3]),
                    $elem = $(elem);
            return regex.test($elem.attr('class')) || regex.test($elem.attr('id'));
        };
        //jump page api
        jQuery.fn.dataTable.Api.register('page.jumpToData()', function (data, column) {
            var pos = this.column(column, {order: 'index'}).data().indexOf(data);
            if (pos >= 0) {
                var page = Math.floor(pos / this.page.info().length);
                this.page(page).draw(false);
                //$(this.row(pos).nodes()).addClass("lastModifiedRow");
            }
            return this;
        });

        $('.sidebar').slimScroll({
            height: '375px',
            alwaysVisible: true
        });

        //prevent negative number on input type number
        $(document).on("keydown", ".positiveNum", function (e) {
            if (!((e.keyCode > 95 && e.keyCode < 106)
                    || (e.keyCode > 47 && e.keyCode < 58)
                    || e.keyCode == 8)) {
                return false;
            }
        });
        $(document).ready(function () {
            $("#logout").click(function (event) {
                event.preventDefault();
                logout();
            });
        });
        //close modal on overlay click
        $(document).on('click', ".ui-widget-overlay", function () {
            $(".ui-dialog-titlebar-close").trigger('click');
        });
    });
    function getDate(element) {
        var date;
        var dateFormat = 'dd/mm/yy';
        try {
            date = $.datepicker.parseDate(dateFormat, element.value);
        } catch (error) {
            date = null;
        }

        return date;
    }
    function pad_with_zeroes(number, length) {

        var my_string = '' + number;
        while (my_string.length < length) {
            my_string = '0' + my_string;
        }

        return my_string;

    }

    function formatDateFromJavaDateJSONEncoded(JSONData) {
        var day = pad_with_zeroes(JSONData.date, 2);
        var month = pad_with_zeroes(parseInt(JSONData.month) + 1, 2);
        var year = parseInt(JSONData.year) + 1900;
        var date = day + "/" + month + "/" + year;
        return date;
    }
    function logout() {
        window.location = "Logout";
        window.open('', '_self', '');
        window.close();
        self.close();
    }

    function refreshDataAndJumpTo(jumpTo, searchColumn) {
        if (typeof availableTable !== 'undefined') {
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
        }
        if (typeof unAvailableTable !== 'undefined') {
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
        }
        //start by show add form
        showFrom("add.htm");
    }

    function highlightData(/*array of object*/ ids, columnToSearch, table) {
        table.ajax.reload(function () {
            console.log("length=" + ids.length);
            for (var i = 0; i < ids.length; i++) {
                var pos = table.column(columnToSearch, {order: 'index'}).data().indexOf(ids[0]);
                if (pos >= 0) {
                    $(table.row(pos).nodes()).addClass("lastModifiedRow");
                } else {
                    var posInt = table.column(columnToSearch, {order: 'index'}).data().indexOf(parseInt(ids[0]));
                    if (posInt >= 0) {
                        $(table.row(posInt).nodes()).addClass("lastModifiedRow");
                    }
                }
            }
        }, false);
        return this;
    }

    function highlightDatum(id, columnToSearch, table) {
        table.ajax.reload(function () {
            var pos = table.column(columnToSearch, {order: 'index'}).data().indexOf(id);
            if (pos >= 0) {
                $(table.row(pos).nodes()).addClass("lastModifiedRow");
            } else {
                var posInt = table.column(columnToSearch, {order: 'index'}).data().indexOf(parseInt(id));
                if (posInt >= 0) {
                    $(table.row(posInt).nodes()).addClass("lastModifiedRow");
                }
            }

        }, false);
        return this;
    }

    //enforce that only a float can be inputed
    function enforceFloat() {
        var valid = /^\-?\d+\.\d*$|^\-?[\d]*$/;
        var number = /\-\d+\.\d*|\-[\d]*|[\d]+\.[\d]*|[\d]+/;
        if (!valid.test(this.value)) {
            var n = this.value.match(number);
            this.value = n ? n[0] : '';
        }
    }
</script>

<style>
    .scrollBox{
        height: 300px;
        overflow-y: scroll;
    }

    .cell-border td{
        border-color: black!important;
    }
    tr.newData{
        background-color: #c7e9b4!important
    }

    td:first-child{border-left: 1px solid black;border-right: 1px solid black}
    td{border-right: 1px solid black}

    input[type=text][class*=inputCell]:focus {
      background-color: #00c0ef;
    }

    .inputTable {border-collapse: collapse;border: 1px solid black;}
    .inputTable   td {margin: 0 !important; padding: 0 !important;text-align: center;height: 30px!important;}
    .inputTable   th {text-align: center}
    .inputTable   input {display: block !important; padding: 0 !important; margin: 0 !important; width: 100% !important; border-radius: 0 !important; line-height: 1 !important;min-height:30px!important}

    .summaryTable{border-collapse: collapse;border: 1px solid black;}
    .summaryTable th, .summaryTable td{text-align: center;border: 1px solid black;}
    .summaryTable .joinedColumnLeft{border-right: none}
    .summaryTable .joinedColumnRight{border-left:none}
    /*for future use when input table > 2*/
    .summaryTable .joinedMiddle{border:none}

    .important-data-level-1{
        font-weight: bolder;
        font-size: 1em;
        text-decoration: underline
    }

    .important-data-level-2{
        font-weight: bolder;
        font-size: 1.5em;
        text-decoration: underline
    }

    .warning{
        background-color:#ffdb99!important;
    }
    .danger-alert{
        background-color:red;
    }
    .alert{
        background-color:#e4b9c0;
    }

    /*add red color if link = #*/
    a[href="#"]{
        color:red;
    }
    .ui-datepicker{ z-index: 9999 !important;}

    input{box-sizing:border-box} 

    .dangerFont{
        font-size: 20px;
        color:#d9534f;
    }

    .successFont{
        font-size: 20px;
        color:#5cb85c;
    }

    .primaryFont{
        font-size: 20px;
        color:#0275d8;
    }
    table[id^="unavailable"] th{
        background-color: lightcoral;
    }
    table[id^="disapprovedTable"] th{
        background-color: lightcoral;
    }
    div[id^="unavailable"] th{
        background-color: lightcoral;
    }


    table[id^="available"] th{
        background-color:#006600;
    }
    /*for scrollX enabled table*/
    div[id^="unavailable"] th{
        background-color: lightcoral;
    }

    /*fix overflow input issue*/
    div[class*="col"] > input{
        box-sizing:border-box!important;
        width: 100%!important;
    } 


    button[type=submit],button[class=addData]{
        overflow: visible;padding: 2px 5px 2px 5px;
        height: 30px;
    }


    input[type=checkbox]{
        width:30px;
        height: 30px;
    }

    .lastModifiedRow{
        background-color: lightgreen!important;
        /*outline: thin solid red!important;*/
    }

    /*fix overflow issue*/
    .dataTable{
        width: 100% !important;
    }
    .dataTable td,
    .dataTable th{
        text-align: center;
        width: auto !important;
        white-space: normal;
        text-overflow: ellipsis;
        overflow: hidden;
    }

    /*fix top-align bootstrap grid problem*/
    @media (min-width: 768px) {
        .row .centralize-flex{
            display: flex;
            align-items: center;
        }
    }

</style>

