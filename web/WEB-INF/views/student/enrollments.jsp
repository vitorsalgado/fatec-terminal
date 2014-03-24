<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <title>Matrículas - Terminal de Consultas FATEC PG</title>
        <link rel="icon" type="image/x-icon" href="resources/images/favicon.ico" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,700,300italic' rel='stylesheet' type='text/css' />
        <link rel="stylesheet" href="../resources/css/pepper-grinder/jquery-ui-1.10.4.custom.min.css" />
        <link rel="stylesheet" href="../resources/css/core.css" />
        <link rel="stylesheet" href="../resources/css/site.css" />
        <link rel="stylesheet" href="../resources/css/misc.css" />
    </head>
    <body>
        <%@include file="../shared/header.jspf" %>
        <div class="page">
            <div id="content" class="wrap">
                <h1 id="page-title">Matrículas no Semestre</h1>
                <table class="grid">
                    <thead>
                        <tr>
                            <th>Disciplina</th>
                            <th>Nota P1</th>
                            <th>Faltas 1° B.</th>
                            <th>Nota P2</th>
                            <th>Faltas 2° B.</th>
                            <th>NP</th>
                            <th>Conceito</th>
                            <th>Média</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="enrolledDiscipline" items="${model.enrolledDisciplines}" varStatus="counter">
                            <tr>
                                <td><c:out value="${enrolledDiscipline.discipline}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${enrolledDiscipline.grade1} < 6"><span class="red-text"><c:out value="${enrolledDiscipline.grade1}"/></span></c:when>
                                         <c:otherwise><c:out value="${enrolledDiscipline.grade1}"/></c:otherwise>
                                    </c:choose>
                                </td>
                                <td><c:out value="${enrolledDiscipline.abscenses1}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${enrolledDiscipline.grade2} < 6"><span class="red-text"><c:out value="${enrolledDiscipline.grade2}"/></span></c:when>
                                         <c:otherwise><c:out value="${enrolledDiscipline.grade2}"/></c:otherwise>
                                    </c:choose>                                    
                                </td>
                                <td><c:out value="${enrolledDiscipline.abscenses2}"/></td>
                                <td><c:out value="${enrolledDiscipline.workGrade}"/></td>
                                <td><c:out value="${enrolledDiscipline.concept}"/></td>
                                <td><c:out value="${enrolledDiscipline.grade}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <ul class="grid-detail">
                    <li><a href="javascript:void(0);">Total de disciplinas matrículas <b>${model.count}</b></a></li>
                </ul>
            </div>
        </div>
        <%@include file="../shared/footer.jspf" %>
    </body>

    <script src="resources/js/jquery-2.1.0.min.js"></script>
    <script src="resources/js/jquery-ui-1.10.4.custom.min.js"></script>
    <script src="resources/js/core.js"></script>    
</html>
