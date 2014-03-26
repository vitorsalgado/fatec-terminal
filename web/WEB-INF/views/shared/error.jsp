<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
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
        <%@include file="header.jspf" %>
        <div id="error-container">
            <div>
                <p>Ops, desculpe mas ocorreu um erro no sistema... :-(</p>
                <p>${model.message}</p>
                <p>${model.logId}</p>
                <p>${model.errorDate}</p>
                <p>Tecle <b>ESC</b> ou <b>0</b> para voltar...</p>
            </div>
        </div>
        <%@include file="footer.jspf" %>
    </body>
</html>
