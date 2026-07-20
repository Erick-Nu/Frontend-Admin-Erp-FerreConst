<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Ferreconst</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<main class="main-content">
    <div class="page-container dashboard-container">
        <header class="dashboard-header">
            <div>
                <span class="dashboard-eyebrow">Resumen de cuenta</span>
                <h1>Bienvenido, <c:out value="${user.name}"/></h1>
                <p>Perfil de la cuenta</p>
            </div>
            <a class="dashboard-action" href="${pageContext.request.contextPath}/companies"><span>Registrar empresa</span><span aria-hidden="true">→</span></a>
        </header>

        <section class="dashboard-card dashboard-summary-card" aria-label="Información de cuenta">
            <section class="dashboard-info-section" aria-labelledby="company-title">
                <div class="dashboard-card-heading">
                    <span class="dashboard-card-kicker">Empresa</span>
                    <c:choose><c:when test="${company.status eq 'activo'}"><span class="status-badge status-active">Activo</span></c:when><c:otherwise><span class="status-badge"><c:out value="${company.status}"/></span></c:otherwise></c:choose>
                </div>
                <div class="dashboard-identity">
                    <h2 id="company-title"><c:out value="${company.rznsocial}"/></h2>
                    <p>RUC <c:out value="${company.ruc}"/></p>
                </div>
                <dl class="dashboard-data-list">
                    <div><dt>Tipo de empresa</dt><dd><c:choose><c:when test="${company.isParent}">Empresa matriz</c:when><c:otherwise>Sucursal</c:otherwise></c:choose></dd></div>
                    <c:if test="${not empty company.email}"><div><dt>Correo</dt><dd><c:out value="${company.email}"/></dd></div></c:if>
                    <c:if test="${not empty company.code}"><div><dt>Código</dt><dd><c:out value="${company.code}"/></dd></div></c:if>
                    <c:if test="${not empty company.formattedDateRegister}"><div><dt>Registro</dt><dd><c:out value="${company.formattedDateRegister}"/></dd></div></c:if>
                </dl>
            </section>

            <section class="dashboard-info-section" aria-labelledby="profile-title">
                <div class="dashboard-card-heading">
                    <span class="dashboard-card-kicker">Perfil</span>
                    <c:choose><c:when test="${user.status eq 'activo'}"><span class="status-badge status-active">Activo</span></c:when><c:otherwise><span class="status-badge"><c:out value="${user.status}"/></span></c:otherwise></c:choose>
                </div>
                <div class="dashboard-identity">
                    <h2 id="profile-title"><c:out value="${user.name}"/></h2>
                    <p>@<c:out value="${user.nickname}"/></p>
                </div>
                <dl class="dashboard-data-list">
                    <div><dt>Correo</dt><dd><c:out value="${user.email}"/></dd></div>
                    <div><dt>Rol</dt><dd><c:out value="${user.role}"/></dd></div>
                    <c:if test="${not empty user.formattedDateRegister}"><div><dt>Registro</dt><dd><c:out value="${user.formattedDateRegister}"/></dd></div></c:if>
                </dl>
            </section>
        </section>
    </div>
</main>

</body>
</html>
