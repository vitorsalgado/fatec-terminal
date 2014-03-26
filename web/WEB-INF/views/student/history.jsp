<%@ page buffer="128kb" autoFlush="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="en_BR"/>
<c:choose>
    <c:when test="${model.success}">
        <h1 id="page-title">Histórico Completo</h1>
        <table class="grid">
            <thead>
                <tr>
                    <th>Ciclo</th>
                    <th class="text-left">Disciplina</th>
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
                        <td><c:out value="${entry.discipline.ciclo}" />°</td>
                        <td class="text-left"><c:out value="${entry.discipline.name}" /></td>
                        <td><fmt:formatNumber value="${entry.discipline.credits}" type="number" maxFractionDigits="0" /></td>
                        <td><fmt:formatNumber value="${entry.discipline.totalWorkload}" type="number" maxFractionDigits="0" /></td>
                        <td><fmt:formatNumber value="${entry.average}" type="number" maxFractionDigits="1" /></td>
                        <td><c:out value="${entry.concept}" /></td>
                        <td><c:out value="${entry.semester}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p class="grid-detail-label"><b>Legenda</b></br> <b>A</b>- Aprovado, <b>D</b>- Dispensado (Aproveitamento de Estudos)</p>
        <ul class="grid-detail large-grid-detail">
            <li><a href="javascript:void(0);">Carga Horária Total <b><fmt:formatNumber value="${model.history.totalWorkload}" type="number" maxFractionDigits="0" /></b></a></li>
            <li><a href="javascript:void(0);">Total de Créditos <b><fmt:formatNumber value="${model.history.totalCredits}" type="number" maxFractionDigits="0" /></b></a></li>
            <li><a href="javascript:void(0);">Percentual de Rendimento <b><fmt:formatNumber value="${model.history.efficiencyPercent}" type="number" maxFractionDigits="2" />%</b></a></li>
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
