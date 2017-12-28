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
<script src="../js/jquery.stickyheader.js" type="text/javascript"></script>

<script src="../plugins/dataTable/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="../plugins/dataTable/dataTables.responsive.min.js" type="text/javascript"></script>
<script src="../plugins/dataTable/dataTables.rowGroup.min.js" type="text/javascript"></script>
<script src="../js/bootbox.min.js" type="text/javascript"></script>
<script src="../js/moment.js" type="text/javascript"></script>
<script src="../plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
<script>
    $(function () {
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
</script>

<style>

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
    table[id^="unavailable"]>thead>tr> th{
        background-color: lightcoral;
    }
    table[id^="available"]>thead>tr> th{
        background-color:#006600;
    }
    input{
        box-sizing:border-box;
        width: 100%;
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
        width: auto !important;
        white-space: normal;
        text-overflow: ellipsis;
        overflow: hidden;
    }



</style>

