<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E-Doc Calibration Service</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <link href="../plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
        <link href="../plugins/dataTable/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="../plugins/dataTable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <jsp:include page="../include/include_script.jsp" flush="true"></jsp:include>

            <script>
                function logout() {
                    window.location = "Logout";
                    window.open('', '_self', '');
                    window.close();
                    self.close();
                }
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

                //jump page api
                jQuery.fn.dataTable.Api.register('page.jumpToData()', function (data, column) {
                    var pos = this.column(column, {order: 'index'}).data().indexOf(data);
                    if (pos >= 0) {
                        var page = Math.floor(pos / this.page.info().length);
                        this.page(page).draw(false);
                        $(this.row(pos).nodes()).addClass("lastModifiedRow");
                    }
                    return this;
                });
            </script>
        </head>
        <body class="skin-blue fixed sidebar-collapse"> 
            <div class="wrapper" id="mainProfit"> 
                <div id="head-wrapper">
                    <header class="main-header">
                        <a href="<c:url value = "/index/home.htm"/>" class="logo"><b style="font-size: 18px;">EDCS</b></a>
                    <!-- Header Navbar: style can be found in header.less -->
                    <nav class="navbar navbar-static-top" role="navigation">
                        <!-- Sidebar toggle button-->                            
                        <a href="<c:url value = "#"/>" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>
                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse pull-left" id="navbar-collapse">
                            <ul class="nav navbar-nav">
                                <li><a href="<c:url value = "/index/home.htm"/>">E-Doc Calibration Service</a></li>
                                <!--
                                <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                                <li><a href="#">Link</a></li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="#">Action</a></li>
                                        <li><a href="#">Another action</a></li>
                                        <li><a href="#">Something else here</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#">Separated link</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#">One more separated link</a></li>
                                    </ul>
                                </li>
                                -->
                            </ul>
                            <!--
                            <form class="navbar-form navbar-left" role="search">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="navbar-search-input" placeholder="Search">
                                </div>
                            </form>
                            -->
                        </div>
                        <!-- /.navbar-collapse -->

                        <div class="navbar-custom-menu">
                            <ul class="nav navbar-nav">          
                                <!-- User Account: style can be found in dropdown.less -->
                                <li class="dropdown user user-menu">
                                    <a href="<c:url value = "#"/>" class="dropdown-toggle" data-toggle="dropdown">
                                        <i class="glyphicon glyphicon-user"></i>
                                        <span>{name} {surname}<i class="caret"></i></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <!-- User image -->
                                        <li class="user-header">
                                            <%--<img src="http://prev.wacoalsampan.com/employee_picture/${empId}.jpg" class="img-circle" alt="User Image" />--%>
                                            <p>
                                                ${profile.empNameTh} ${profile.empSurnameTh} (${profile.uid})
                                                <small>${profile.empDeptTh}</small>
                                            </p>
                                        </li>                 
                                        <!-- Menu Footer-->
                                        <li class="user-footer">                   
                                            <div class="pull-right">
                                                <c:choose>
                                                    <c:when test="${origin==null}">
                                                        <a href="Login" class="btn btn-default btn-flat">Sign out</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="<c:url value = "#"/>" id="logout" class="btn btn-default btn-flat">Sign out</a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </li>
                                    </ul>
                                </li>
                                <c:if test="${not empty trAdmin}">
                                    <li class="admin-menu">
                                        <a href="<c:url value = "/index/AdminIndex?s=c"/>">
                                            <span class="admin">Admin</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </nav>
                </header>
                <aside class="main-sidebar sidebar-collapse">
                    <!-- sidebar: style can be found in sidebar.less -->
                    <section class="sidebar">
                        <!-- Sidebar user panel -->
                        <div class="user-panel">
                            <div class="pull-left image">
                                [emp_fname] [emp_sname]
                                <%--<img src="http://prev.wacoalsampan.com/employee_picture/${empId}.jpg" class="img-circle" alt="User Image" />--%>
                            </div>
                            <div class="pull-left info">
                                <%--<p>${profile.empNameTh}</p>--%>
                                <%--<p>${profile.empSurnameTh}</p>--%>
                            </div>
                        </div>
                        <!-- sidebar menu: : style can be found in sidebar.less -->
                        <ul class="sidebar-menu">
                            <li class="header">MAIN NAVIGATION</li>     
                            <li>
                                <a href="<c:url value = "/index/AdminIndex?s=c"/>checkDoc.htm">
                                    <i class="fa fa-edit"></i> <span>ส่งรายงานสอบเทียบ</span>
                                    <i class="fa fa-angle-right pull-right"></i>
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value = "/index/trackingDoc.htm"/>">
                                    <i class="fa fa-search"></i> <span>ติดตามรายงานสอบเทียบ</span>
                                    <i class="fa fa-angle-right pull-right"></i>
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value = "/index/deviceReceivce.htm"/>">
                                    <i class="fa fa-sign-in"></i> <span>รับอุปกรณ์สอบเทียบ</span>
                                    <i class="fa fa-angle-right pull-right"></i>
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value = "/index/createSearch.htm"/>">
                                    <i class="fa fa-calendar"></i> <span>จัดทำรายงานสอบเทียบ</span>
                                    <i class="fa fa-angle-right pull-right"></i>
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value = "/index/createDoc.htm"/>">
                                    <i class="fa fa-file-text-o"></i> <span>รายงานผลการสอบเทียบ</span>
                                    <i class="fa fa-angle-right pull-right"></i>
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value = "/index/createDetail.htm"/>">
                                    <i class="fa fa-list-alt"></i> <span>ผลการสอบเทียบ</span>
                                    <i class="fa fa-angle-right pull-right"></i>
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value = "/index/labApprove.htm"/>">
                                    <i class="fa fa-check-square-o"></i> <span>อนุมัติรายการสอบเทียบ</span>
                                    <i class="fa fa-angle-right pull-right"></i>
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value = "/index/printSticker.htm"/>">
                                    <i class="fa fa-print"></i> <span>พิมพ์สติ๊กเกอร์</span>
                                    <i class="fa fa-angle-right pull-right"></i>
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value = "/index/deviceSend.htm"/>">
                                    <i class="fa fa-sign-out"></i> <span>คืนอุปกรณ์สอบเทียบ</span>
                                    <i class="fa fa-angle-right pull-right"></i>
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value = "#"/>">
                                    <i class="fa fa-table"></i> <span>แผนประจำปี</span>
                                    <i class="fa fa-angle-right pull-right"></i>
                                </a>
                            </li>
                            <li class="treeview">
                                <a href="<c:url value = "#"/>">
                                    <i class="fa fa-cog"></i> <span>จัดการข้อมูลหลัก</span>
                                    <i class="fa fa-th pull-right"></i>
                                </a>
                                <ul class="treeview-menu">
                                    <li><a href="<c:url value = "/branchs/index.htm"/>" ><i class="fa fa-building" ></i><span>จัดการสาขา</span></a><li>
                                    <li><a href="<c:url value = "/departments/index.htm"/>" ><i class="fa fa-building-o" ></i><span>จัดการแผนก</span></a><li>
                                    <li><a href="<c:url value = "/measure/index.htm"/>" ><i class="fa fa-book" ></i><span>สร้างรหัสเครื่องวัด/ทดสอบ</span></a><li>
                                    <li><a href="<c:url value = "/equipcons/index.htm"/>" ><i class="fa fa-th-list" ></i><span>ประเภทเครื่องวัด/ทดสอบ</span></a><li>
                                    <li><a href="<c:url value = "/measureGroup/index.htm"/>" ><i class="fa fa-columns" ></i><span>กลุ่มเครื่องวัด/ทดสอบ</span></a><li>
                                    <li><a href="<c:url value = "/calpoints/index.htm"/>" ><i class="fa fa-crosshairs" ></i><span>จุดสอบเทียบ</span> </a><li>
                                    <li><a href="<c:url value = "#"/>" ><i class="fa fa-subscript" ></i><span>สูตรคำนวณ</span></a><li>
                                    <li><a href="<c:url value = "/measureUnits/index.htm"/>" ><i class="fa fa-balance-scale" ></i><span>หน่วยวัด</span></a><li>
                                    <li><a href="<c:url value = "/statusCaldocs/index.htm"/>" ><i class="fa fa-file" ></i><span>สถานะเอกสาร</span></a><li>
                                    <li><a href="<c:url value = "#"/>" ><i class="fa fa-user-plus" ></i><span>จัดการข้อมูลผู้ใช้งานภายนอก</span></a><li>
                                    <li><a href="<c:url value = "#"/>" ><i class="fa fa-unlock-alt" ></i><span>กำหนดสิทธิ์การใช้งาน</span></a><li>
                                    <li><a href="<c:url value = "/process/index.htm"/>" ><i class="fa fa-book" ></i><span>วิธีทดสอบ</span></a><li>
                                    <li><a href="<c:url value = "/model/index.htm"/>" ><i class="fa fa-key" ></i><span>จัดการแม่แบบ</span></a><li>
                                    <li><a href="<c:url value = "/calage/index.htm"/>" ><i class="fa fa-clock-o" ></i><span>จัดการช่วงเาลาสอบเทียบ</span></a><li>
                                </ul>
                            </li> 
                        </ul>
                    </section>
                    <!-- /.sidebar -->
                </aside>
            </div>
            <div class="content-wrapper" id="con-wrap">
                <!-- Main content -->
                <section class="content">
