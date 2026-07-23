<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Empresas - Ferreconst</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
<jsp:include page="../components/navbar.jsp"/>
<main class="main-content">
    <div class="page-container companies-container">
        <header class="companies-header">
            <div>
                <span class="companies-eyebrow">Administración</span>
                <h1>Empresas</h1>
                <p>Registra y gestiona las empresas vinculadas al servicio.</p>
            </div>
            <button type="button" class="companies-add-button" id="open-register-modal"><span aria-hidden="true">+</span>Agregar empresa</button>
        </header>
        <form class="companies-search-form" action="${pageContext.request.contextPath}/companies" method="get">
            <input type="search" id="company-search" name="query" value="<c:out value='${query}'/>" placeholder="RUC, código o razón social" aria-label="Buscar empresa">
            <button class="companies-search-button" type="submit">Buscar</button>
        </form>

        <c:if test="${not empty error}">
            <div class="alert alert-error companies-alert" role="alert"><c:out value="${error}"/></div>
        </c:if>

        <c:if test="${empty error}">
            <section class="companies-table-section" aria-labelledby="companies-table-title">
                <header class="companies-table-header">
                    <div>
                        <span class="companies-eyebrow">Resultados</span>
                        <h2 id="companies-table-title">Empresas registradas</h2>
                    </div>
                    <c:if test="${not empty companyPage}">
                        <p><c:out value="${companyPage.totalItems}"/> empresas encontradas</p>
                    </c:if>
                </header>

                <c:choose>
                    <c:when test="${not empty companyPage and not empty companyPage.items}">
                        <div class="companies-table-wrapper">
                            <table class="companies-table">
                                <thead>
                                    <tr>
                                        <th scope="col">Empresa</th>
                                        <th scope="col">Correo</th>
                                        <th scope="col">Código</th>
                                        <th scope="col">Estado</th>
                                        <th scope="col">Registro</th>
                                        <th scope="col" class="companies-table-actions-header">Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="company" items="${companyPage.items}">
                                        <tr>
                                            <td>
                                                <div class="companies-table-company">
                                                    <div>
                                                        <span class="companies-table-name"><c:out value="${company.rznsocial}"/></span>
                                                        <span class="companies-table-ruc">RUC <c:out value="${company.ruc}"/></span>
                                                    </div>
                                                </div>
                                            </td>
                                            <td><c:out value="${company.email}"/></td>
                                            <td><c:out value="${company.code}"/></td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${company.status eq 'activo'}"><span class="status-badge status-active"><c:out value="${company.status}"/></span></c:when>
                                                    <c:otherwise><span class="status-badge"><c:out value="${company.status}"/></span></c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><c:out value="${company.formattedDateRegister}"/></td>
                                            <td class="companies-table-actions">
                                                <a class="companies-settings-button" href="${pageContext.request.contextPath}/companies/${company.id}/configurations">Configuración</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <c:if test="${companyPage.totalPages gt 1}">
                            <nav class="companies-pagination" aria-label="Paginación de empresas">
                                <c:choose>
                                    <c:when test="${companyPage.page gt 1}">
                                        <c:url var="previousPageUrl" value="/companies">
                                            <c:param name="page" value="${companyPage.page - 1}"/>
                                            <c:if test="${not empty query}"><c:param name="query" value="${query}"/></c:if>
                                        </c:url>
                                        <a class="companies-pagination-button" href="${previousPageUrl}">Anterior</a>
                                    </c:when>
                                    <c:otherwise><span class="companies-pagination-button is-disabled" aria-disabled="true">Anterior</span></c:otherwise>
                                </c:choose>
                                <p>Página <strong><c:out value="${companyPage.page}"/></strong> de <c:out value="${companyPage.totalPages}"/></p>
                                <c:choose>
                                    <c:when test="${companyPage.page lt companyPage.totalPages}">
                                        <c:url var="nextPageUrl" value="/companies">
                                            <c:param name="page" value="${companyPage.page + 1}"/>
                                            <c:if test="${not empty query}"><c:param name="query" value="${query}"/></c:if>
                                        </c:url>
                                        <a class="companies-pagination-button" href="${nextPageUrl}">Siguiente</a>
                                    </c:when>
                                    <c:otherwise><span class="companies-pagination-button is-disabled" aria-disabled="true">Siguiente</span></c:otherwise>
                                </c:choose>
                            </nav>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <p class="companies-empty-state">No se encontraron empresas con los criterios indicados.</p>
                    </c:otherwise>
                </c:choose>
            </section>
        </c:if>
    </div>

    <!-- Modal de registro -->
    <div class="modal-backdrop" id="register-modal" hidden>
        <div class="modal" role="dialog" aria-labelledby="modal-title" aria-modal="true">
            <div class="modal-header">
                <h2 id="modal-title">Registrar empresa</h2>
                <button type="button" class="modal-close" id="modal-close" aria-label="Cerrar">&times;</button>
            </div>
            <div class="modal-body">
                <form id="modal-register-form" action="${pageContext.request.contextPath}/companies" method="post">
                    <c:choose>
                        <c:when test="${not empty modalError and showRegisterModal}">
                            <div id="modal-error" class="alert alert-error" role="alert"><c:out value="${modalError}"/></div>
                        </c:when>
                        <c:otherwise>
                            <div id="modal-error" role="alert" hidden></div>
                        </c:otherwise>
                    </c:choose>
                    <div class="company-form-grid">
                        <div class="input-group company-register-full-field">
                            <label for="modal-businessName">Razón social <span class="field-required" aria-hidden="true">*</span></label>
                            <input type="text" id="modal-businessName" name="businessName" value="<c:out value='${businessName}'/>" autocomplete="organization" required>
                        </div>
                        <div class="input-group">
                            <label for="modal-ruc">RUC <span class="field-required" aria-hidden="true">*</span></label>
                            <input type="text" id="modal-ruc" name="ruc" value="<c:out value='${ruc}'/>" inputmode="numeric" autocomplete="off" minlength="13" maxlength="13" pattern="[0-9]{13}" required aria-describedby="modal-ruc-hint">
                            <p class="field-hint" id="modal-ruc-hint">13 dígitos, sin espacios. Ejemplo: 1754303699001.</p>
                        </div>
                        <div class="input-group">
                            <label for="modal-code">Código interno <span class="field-required" aria-hidden="true">*</span></label>
                            <input type="text" id="modal-code" name="code" value="<c:out value='${code}'/>" autocomplete="off" minlength="4" maxlength="4" pattern="[A-Z]{2}[0-9]{2}" required aria-describedby="modal-code-hint">
                            <p class="field-hint" id="modal-code-hint">Ejemplo: AB12.</p>
                        </div>
                        <div class="input-group company-register-full-field">
                            <label for="modal-email">Correo corporativo <span class="field-required" aria-hidden="true">*</span></label>
                            <input type="email" id="modal-email" name="email" value="<c:out value='${email}'/>" autocomplete="email" required>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="company-cancel-button" id="modal-cancel">Cancelar</button>
                <button type="submit" class="companies-add-button" id="modal-submit" form="modal-register-form">Registrar empresa</button>
            </div>
        </div>
    </div>
</main>
<script src="${pageContext.request.contextPath}/resources/js/companies/modal.js"></script>
<c:if test="${showRegisterModal}">
    <script>document.getElementById("register-modal").hidden = false;</script>
</c:if>
</body>
</html>
