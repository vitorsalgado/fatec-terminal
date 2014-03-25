<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <title>FATEC PG - Terminal de Consultas</title>
        <link rel="icon" type="image/x-icon" href="resources/images/favicon.ico" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,700,300italic' rel='stylesheet' type='text/css' />
        <link rel="stylesheet" href="resources/css/core.css" />
        <link rel="stylesheet" href="resources/css/site.css" />
        <link rel="stylesheet" href="resources/css/misc.css" />
    </head>

    <body id="body">
        <%@include file="shared/header.jspf" %>

        <div class="page">
            <div id="loading-container">
                <img src="resources/images/loading.gif" alt="carregando..." />
            </div>
            <div id="content" class="wrap">
                <div id="page-content"></div>
            </div>
            <div id="index-content" class="wrap">
                <%@include file="shared/menu.jspf" %>
            </div>
        </div>

        <%@include file="shared/login.jspf" %>
        <%@include file="shared/footer.jspf" %>
    </body>

    <script src="resources/js/jquery-2.1.0.min.js"></script>
    <script src="resources/js/core.js"></script>
</html>
