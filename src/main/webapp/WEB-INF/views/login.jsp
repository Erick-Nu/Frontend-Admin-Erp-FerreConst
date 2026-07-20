<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión - Ferreconst</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body class="login-page">
<div class="login-wrapper">
    <aside class="login-panel" aria-hidden="true"></aside>

    <main class="login-form-area">
        <div class="login-card">
            <header class="login-header">
                <h1>Ferreconst</h1>
                <p>Acceso a la gestión de empresas</p>
                <span>Ingresa tus credenciales para continuar.</span>
            </header>

            <c:if test="${not empty error}">
                <div class="alert alert-error" id="login-error" role="alert" aria-live="assertive">
                    <span><c:out value="${error}"/></span>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/login" method="post" id="login-form">
                <div class="input-group">
                    <label for="rut">RUC</label>
                    <div class="input-control">
                        <span class="field-icon" aria-hidden="true">
                            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8"><path d="M4 20V5a1 1 0 0 1 1-1h8a1 1 0 0 1 1 1v15M2 20h20M7 8h1M7 12h1M7 16h1M11 8h1M11 12h1M11 16h1M16 10h2M16 14h2M16 18h2"/></svg>
                        </span>
                        <input type="text" id="rut" name="rut" value="<c:out value='${auth.rut}'/>" placeholder="Ingresa tu RUC" inputmode="numeric" autocomplete="organization" required autofocus>
                    </div>
                </div>

                <div class="input-group">
                    <label for="username">Usuario</label>
                    <div class="input-control">
                        <span class="field-icon" aria-hidden="true">
                            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8"><circle cx="12" cy="8" r="3.5"/><path d="M4.5 20c.8-3.4 3.5-5.2 7.5-5.2s6.7 1.8 7.5 5.2"/></svg>
                        </span>
                        <input type="text" id="username" name="user" value="<c:out value='${auth.user}'/>" placeholder="Ingresa tu usuario" autocomplete="username" required>
                    </div>
                </div>

                <div class="input-group">
                    <label for="password">Contraseña</label>
                    <div class="input-control">
                        <span class="field-icon" aria-hidden="true">
                            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8"><rect x="4.5" y="10" width="15" height="10" rx="2"/><path d="M8 10V7a4 4 0 0 1 8 0v3M12 14v2"/></svg>
                        </span>
                        <input type="password" id="password" name="password" placeholder="Ingresa tu contraseña" autocomplete="current-password" required>
                        <button class="password-toggle" type="button" aria-label="Mostrar contraseña" aria-pressed="false">Mostrar</button>
                    </div>
                </div>

                <button type="submit" class="login-btn" id="login-submit">
                    <span class="button-label">Ingresar</span>
                    <span class="button-loader" aria-hidden="true"></span>
                </button>
            </form>
        </div>
    </main>
</div>
<script type="module" src="${pageContext.request.contextPath}/resources/js/login.js"></script>
</body>
</html>
