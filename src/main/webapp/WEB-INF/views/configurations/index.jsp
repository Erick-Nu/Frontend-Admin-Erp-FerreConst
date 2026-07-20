<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Configuraciones - Ferreconst</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
<jsp:include page="../components/navbar.jsp"/>
<main class="main-content">
    <div class="page-container configurations-container">
        <a class="configurations-back-link" href="${pageContext.request.contextPath}/companies" aria-label="Volver a empresas" title="Volver a empresas">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" aria-hidden="true"><path d="M19 12H5M11 18l-6-6 6-6"/></svg>
        </a>
        <header class="configurations-header">
            <div>
                <span class="configurations-eyebrow">Administración</span>
                <h1>Configuraciones</h1>
                <p>Define los valores operativos para la empresa seleccionada.</p>
            </div>
        </header>

        <c:if test="${not empty error}">
            <div class="alert alert-error configurations-alert" role="alert"><c:out value="${error}"/></div>
        </c:if>

        <div class="configurations-layout">
            <section class="configurations-form-card" aria-labelledby="configuration-form-title">
                <header>
                    <span class="configurations-eyebrow">Nueva configuración</span>
                    <h2 id="configuration-form-title">Agregar valor</h2>
                    <p>Guarda un valor que será usado por esta empresa.</p>
                </header>
                <form class="configurations-form" action="${pageContext.request.contextPath}/companies/${companyId}/configurations" method="post">
                    <div class="input-group">
                        <label for="configuration-key">Clave</label>
                        <input type="text" id="configuration-key" name="key" value="<c:out value='${configurationKey}'/>" placeholder="Ej. iva_porcentaje" required autofocus>
                    </div>
                    <div class="input-group">
                        <label for="configuration-value">Valor</label>
                        <input type="text" id="configuration-value" name="value" value="<c:out value='${configurationValue}'/>" placeholder="Ej. 15" required>
                    </div>
                    <button class="configurations-submit-button" type="submit">Guardar configuración</button>
                </form>
            </section>

            <section class="configurations-table-section" aria-labelledby="configurations-table-title">
                <header class="configurations-table-header">
                    <div>
                        <span class="configurations-eyebrow">Valores registrados</span>
                        <h2 id="configurations-table-title">Configuraciones de empresa</h2>
                    </div>
                    <c:if test="${not empty configurations}">
                        <p><c:out value="${fn:length(configurations)}"/> configuraciones</p>
                    </c:if>
                </header>

                <c:choose>
                    <c:when test="${not empty error}">
                        <div class="configurations-empty-state">
                            <h2>No se pudieron cargar las configuraciones</h2>
                            <p>Revisa el mensaje de error e inténtalo nuevamente.</p>
                        </div>
                    </c:when>
                    <c:when test="${not empty configurations}">
                        <div class="configurations-table-wrapper">
                            <table class="configurations-table">
                                <thead>
                                    <tr>
                                        <th scope="col">Clave</th>
                                        <th scope="col">Valor</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="configuration" items="${configurations}">
                                        <tr>
                                            <td class="configurations-table-key"><c:out value="${configuration.key}"/></td>
                                            <td><c:out value="${configuration.value}"/></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="configurations-empty-state">
                            <h2>Aún no hay configuraciones</h2>
                            <p>Agrega la primera clave y valor para esta empresa.</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </section>
        </div>
    </div>
</main>
</body>
</html>
