<%-- 
    Document   : index
    Created on : Jun 14, 2019, 9:32:52 AM
    Author     : Wattana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%
        if (session.getAttribute("cono") == null) {
            response.sendRedirect("./login.jsp");
        }
    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="assets/jQuery-3.3.1/jquery-ui.min.css">
        <link rel="stylesheet" type="text/css" href="assets/Bootstrap-3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="assets/jsgrid-1.5.3/dist/jsgrid.min.css" />
        <link rel="stylesheet" type="text/css" href="assets/jsgrid-1.5.3/dist/jsgrid-theme.min.css">
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
        <link rel="stylesheet" type="text/css" href="assets/animate/animate.css">
        <link rel="stylesheet" type="text/css" href="assets/css-hamburgers/hamburgers.min.css">
        <link rel="stylesheet" type="text/css" href="assets/animsition/css/animsition.min.css">
        <link rel="stylesheet" type="text/css" href="assets/select2/select2.min.css">
        <link rel="stylesheet" type="text/css" href="assets/util.css">
        <link rel="stylesheet" type="text/css" href="assets/main.css">
        <script type="text/javascript" src="assets/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="assets/Bootstrap-3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="assets/jQuery-3.3.1/jquery-ui.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
        <script type="text/javascript" src="assets/jsgrid-1.5.3/dist/jsgrid.min.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.core.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.validation.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.load-indicator.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.load-strategies.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.sort-strategies.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.field.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.text.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.number.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.select.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.checkbox.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.control.js"></script>
        <link rel="shortcut icon" href="images/icons/favicon.ico"/>
        <title>Production Report : <%out.print(session.getAttribute("comp"));%></title> 
    </head>

    <style>

        .btnsignout {
            display: inline-block;
            padding: 0px 12px;
            margin-bottom: 0;
            font-size: 14px;
            font-weight: 400;
            line-height: 1.42857143;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
            -ms-touch-action: manipulation;
            touch-action: manipulation;
            cursor: pointer;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            background-image: none;
            border: 1px solid transparent;
            border-radius: 4px;
        }
        .li {
            display: none;
        }

    </style>

    <body style="background-color: #bababa;">

        <div>
            <nav id="vNavbar" class="navbar navbar-default navigation-clean" >
                <div class="container">
                    <div class="navbar-header"><a class="navbar-brand" href="./">Production Report</a><button data-toggle="collapse" class="navbar-toggle collapsed" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button></div>
                    <div class="collapse navbar-collapse" id="navcol-1">
                        <ul class="nav navbar-nav navbar-right">
                            <li role="presentation"></li>
                            <li class="dropdown"><a class="dropdown-toggle" aria-expanded="false" href="./">Home</a></li>

                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./">MPM Report&nbsp;<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li role="presentation"><a class='dropdown-item' name="mo_production" href="?report=mo_production">MPM001</a></li>
                                    <li role="presentation"><a class='dropdown-item' name="MPM012" href="?report=MPM012">MPM012</a></li>
                                    <li role="presentation"><a class='dropdown-item' name="MPM024" href="?report=MPM024">MPM024</a></li>
                                    <li role="presentation"><a class='dropdown-item' name="MPM029" href="?report=MPM029">MPM029</a></li>
                                    <li role="presentation"><a class='dropdown-item' name="sum_mo_production" href="?report=sum_mo_production">Summary MO Production</a></li>
                                    <li role="presentation"><a class='dropdown-item' name="sum_modetail_production" href="?report=sum_modetail_production">Summary MO Detail Production</a></li>
                                </ul>
                            </li>
                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./">WAREHOUSE Report&nbsp;<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li role="presentation"><a class='dropdown-item' name="WHS021" href="?report=WHS021">WHS021</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <form action="Logout" method="POST">
                                        <input class="btnsignout btn-danger mb-2" type="submit" value="Sign Out">
                                    </form>
                                </a>
                            </li>
                        </ul>
                    </div><link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>
                </div>
            </nav>
        </div>

        <section id="pages">
            <div class='centerDiv' id="vSection"></div>
        </section>

    </body>
</html>

<script type="text/javascript">
    
    <% if (request.getParameter("page") != null) {%>
    $("#vSection").load("./pages/<%=request.getParameter("page").toString()%>.jsp");
    <% }%>

    <% if (request.getParameter("report") != null) {%>
    $("#vSection").load("./reports/<%=request.getParameter("report").toString()%>.jsp");
    <% }%>

    $('.dropdown-item').on('click', function () {
//        console.log(this.name);
        $("#vSection").load("./pages/" + this.name + ".jsp");
    });

//    $.ajax({
//
//        url: './Menuitem',
//        type: 'GET',
//        dataType: 'json',
//        async: false,
//        cache: false,
//        timeout: 30000,
//        data: {page: 'EP01E'},
//        success: function (response, textStatus, xhr) {
//            for (var i = 0; i < response.length; i++) {
//                $('#menuitem').append(" <a class='dropdown-item' id='link-aa' name='" + response[i].REPORT + "' href='?page=" + response[i].REPORT + "'>" + response[i].REPORT + "</a><div class='dropdown-divider''></div>");
//            }
//            $('.dropdown-item').on('click', function () {
//                console.log(this.name);
//                $("#show1").load("./pages/" + this.name + ".jsp");
//            });
//        },
//        error: function (xhr, textStatus, errorThrown) {
//            console.log('Error in Operation');
//        }
//    });

</script>