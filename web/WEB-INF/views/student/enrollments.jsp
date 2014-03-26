<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${model.success}">
        <h1 id="page-title">Matrículas no Semestre</h1>
        <table class="grid">
            <thead>
                <tr>
                    <th class="text-left">Disciplina</th>
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
                        <td class="text-left"><c:out value="${enrolledDiscipline.discipline}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${enrolledDiscipline.grade1} < 6"><span class="red-text"><fmt:formatNumber value="${enrolledDiscipline.grade1}" type="number" maxFractionDigits="1" /></span></c:when>
                                <c:otherwise><fmt:formatNumber value="${enrolledDiscipline.grade1}" type="number" maxFractionDigits="1" /></c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${enrolledDiscipline.abscenses1}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${enrolledDiscipline.grade2} < 6"><span class="red-text"><fmt:formatNumber value="${enrolledDiscipline.grade2}" type="number" maxFractionDigits="1" /></span></c:when>
                                <c:otherwise><fmt:formatNumber value="${enrolledDiscipline.grade2}" type="number" maxFractionDigits="1" /></c:otherwise>
                            </c:choose>                                    
                        </td>
                        <td><c:out value="${enrolledDiscipline.abscenses2}"/></td>
                        <td><fmt:formatNumber value="${enrolledDiscipline.workGrade}" type="number" maxFractionDigits="1" /></td>
                        <td><c:out value="${enrolledDiscipline.concept}"/></td>
                        <td><fmt:formatNumber value="${enrolledDiscipline.grade}" type="number" maxFractionDigits="1" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <ul class="grid-detail">
            <li><a href="javascript:void(0);">Total de disciplinas matriculadas no semestre <b>${model.count}</b></a></li>
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
