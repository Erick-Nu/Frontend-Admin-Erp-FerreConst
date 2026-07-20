<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar empresa - Ferreconst</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
<jsp:include page="../components/navbar.jsp"/>
<main class="main-content">
    <div class="page-container companies-container">
        <a class="company-register-back-link" href="${pageContext.request.contextPath}/companies" aria-label="Volver a empresas" title="Volver a empresas">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" aria-hidden="true"><path d="M19 12H5M11 18l-6-6 6-6"/></svg>
        </a>
        <header class="company-register-header">
            <div>
                <span class="company-register-eyebrow">Administración</span>
                <h1 id="company-form-title">Registrar empresa</h1>
                <p>Completa la información requerida para crear y administrar una nueva empresa.</p>
            </div>
        </header>
        <section class="company-register-card" aria-labelledby="company-register-card-title">
            <header class="company-register-card-header">
                <span class="company-register-eyebrow">Nueva empresa</span>
                <h2 id="company-register-card-title">Información de la empresa</h2>
                <p>Ingresa los datos necesarios para crear el registro.</p>
            </header>
            <form class="company-form company-register-form" action="${pageContext.request.contextPath}/companies" method="post" aria-labelledby="company-register-card-title">
                <div class="company-register-form-body">
                    <c:if test="${not empty error}">
                        <div class="alert alert-error company-register-alert" role="alert" tabindex="-1"><c:out value="${error}"/></div>
                    </c:if>
                    <div class="company-form-grid">
                        <div class="input-group company-register-full-field<c:if test="${not empty businessNameError}"> has-error</c:if>">
                            <label for="businessName">Razón social <span class="field-required" aria-hidden="true">*</span></label>
                            <input type="text" id="businessName" name="businessName" value="<c:out value='${businessName}'/>" autocomplete="organization" required autofocus aria-invalid="${not empty businessNameError}"<c:if test="${not empty businessNameError}"> aria-describedby="business-name-error"</c:if>>
                            <c:if test="${not empty businessNameError}"><p class="field-error" id="business-name-error"><c:out value="${businessNameError}"/></p></c:if>
                        </div>
                        <div class="input-group<c:if test="${not empty rucError}"> has-error</c:if>">
                            <label for="ruc">RUC <span class="field-required" aria-hidden="true">*</span></label>
                            <input type="text" id="ruc" name="ruc" value="<c:out value='${ruc}'/>" inputmode="numeric" autocomplete="off" minlength="13" maxlength="13" pattern="[0-9]{13}" required aria-invalid="${not empty rucError}" aria-describedby="ruc-hint<c:if test="${not empty rucError}"> ruc-error</c:if>">
                            <p class="field-hint" id="ruc-hint">Ingresa 13 dígitos, sin espacios ni caracteres especiales. Ejemplo: 1754303699001.</p>
                            <c:if test="${not empty rucError}"><p class="field-error" id="ruc-error"><c:out value="${rucError}"/></p></c:if>
                        </div>
                        <div class="input-group<c:if test="${not empty codeError}"> has-error</c:if>">
                            <label for="code">Código interno <span class="field-required" aria-hidden="true">*</span></label>
                            <input type="text" id="code" name="code" value="<c:out value='${code}'/>" autocomplete="off" minlength="4" maxlength="4" pattern="[A-Z]{2}[0-9]{2}" required aria-invalid="${not empty codeError}" aria-describedby="code-hint<c:if test="${not empty codeError}"> code-error</c:if>">
                            <p class="field-hint" id="code-hint">Dos letras mayúsculas seguidas de dos números. Ejemplo: AB12.</p>
                            <c:if test="${not empty codeError}"><p class="field-error" id="code-error"><c:out value="${codeError}"/></p></c:if>
                        </div>
                        <div class="input-group company-register-full-field<c:if test="${not empty emailError}"> has-error</c:if>">
                            <label for="email">Correo corporativo <span class="field-required" aria-hidden="true">*</span></label>
                            <input type="email" id="email" name="email" value="<c:out value='${email}'/>" autocomplete="email" required aria-invalid="${not empty emailError}"<c:if test="${not empty emailError}"> aria-describedby="email-error"</c:if>>
                            <c:if test="${not empty emailError}"><p class="field-error" id="email-error"><c:out value="${emailError}"/></p></c:if>
                        </div>
                    </div>
                </div>
                <div class="company-form-actions">
                    <a class="company-cancel-button" href="${pageContext.request.contextPath}/companies">Cancelar</a>
                    <button class="companies-add-button" type="submit">Registrar empresa</button>
                </div>
            </form>
        </section>
    </div>
</main>
<script src="${pageContext.request.contextPath}/resources/js/companies/register.js"></script>
</body>
</html>
