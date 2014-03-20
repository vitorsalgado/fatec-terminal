<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FATEC PG - Terminal de Consultas</title>
        <link rel="stylesheet" href="resources/css/pepper-grinder/jquery-ui-1.10.4.custom.min.css" />
        <link rel="stylesheet" href="resources/css/core.css" />
    </head>

    <body>
    <header>

    </header>

    <div class="page">
        <div class="page-container">

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
                    <input id="txtEnrollment" type="text" class="red-text" />
                </div>
                <br />
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
