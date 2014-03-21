<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>FATEC PG - Terminal de Consultas</title>
        <link rel="icon" type="image/x-icon" href="resources/images/favicon.ico" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,700,300italic' rel='stylesheet' type='text/css' />
        <link rel="stylesheet" href="resources/css/pepper-grinder/jquery-ui-1.10.4.custom.min.css" />
        <link rel="stylesheet" href="resources/css/core.css" />
    </head>

    <body>
    <header>

    </header>

    <div class="page">
        <div class="wrap">
            <div class="title-bar">
                <h1>Digite no teclado a opção desejada</h1>
            </div>
        </div>
    </div>

    <div id="login-overlay">
        <div class="login-container">
            <div class="login-content">
                <div class="login-header">
                    <h2>Terminal de Consultas</h2>
                    <p>Informe seu número de matrícula</p>
                    <p class="red-text">**Somente números</p>
                </div>
                <div class="login-wrapper">
                    <input id="txtEnrollment" type="text" class="red-text" placeholder="seu número de matrícula" />
                </div>
                <br />
                <p id="login-errors" class="red-text"></p>
                <p>Tecle 'ENTER' para ENTRAR</p>
                <p>Tecle 'ESC' para SAIR</p>
            </div>
        </div>
    </div>
</body>

<script src="resources/js/jquery-2.1.0.min.js"></script>
<script src="resources/js/jquery-ui-1.10.4.custom.min.js"></script>
<script src="resources/js/core.js"></script>
</html>
