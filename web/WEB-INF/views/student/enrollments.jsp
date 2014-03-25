<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
    <c:when test="${model.success}">
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
