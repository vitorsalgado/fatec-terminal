<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
    <c:when test="${model.success}">
        <h1 id="page-title">Matrículas no Semestre</h1>
        <table class="grid">
            <thead>
                <tr>
                    <th>Ciclo</th>
                    <th>Disciplina</th>
                    <th>Créditos</th>
                    <th>Carga Horária</th>
                    <th>Média</th>
                    <th>Conceito</th>
                    <th>Semestre</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="entry" items="${model.history.entries}">
                    <tr>
                        <td><c:out value="${entry.discipline.ciclo}"/>°</td>
                        <td><c:out value="${entry.discipline.name}"/></td>
                        <td><c:out value="${entry.discipline.credits}"/></td>
                        <td><c:out value="${entry.discipline.totalWorkload}"/></td>
                        <td><c:out value="${entry.average}"/></td>
                        <td><c:out value="${entry.concept}"/></td>
                        <td><c:out value="${entry.semester}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <ul class="grid-detail">
            <li><a href="javascript:void(0);">Carga Horária Total <b>${model.history.totalWorkLoad}</b></a></li>
            <li><a href="javascript:void(0)">Total de Créditos <b>${model.history.totalCredits}</b></a></li>
            <li><a href="javascript:void(0);">Percentual de Rendimento <b>${model.history.efficiencyPercent} %</b></a></li>
        </ul>
    </c:when>
    <c:otherwise>
        <div id="error-container">
            <div>
                <p>${model.message}</p>
                <p>Tecle <b>ESC</b> ou <b>0</b> para voltar...</p>
            </div>
        </div>
    </c:otherwise>
</c:choose>
