<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="companiesActive" value="${fn:contains(pageContext.request.requestURI, '/companies')}"/>
<c:set var="dashboardActive" value="${not companiesActive}"/>

<button class="sidebar-toggle" id="sidebar-toggle" type="button" aria-label="Abrir navegación" aria-controls="main-navigation" aria-expanded="false">
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" aria-hidden="true"><path d="M4 7h16M4 12h16M4 17h16"/></svg>
</button>

<aside class="sidebar" id="main-navigation">
    <div class="sidebar-header">
        <a class="sidebar-brand" href="${pageContext.request.contextPath}/dashboard">Ferreconst</a>
        <button class="sidebar-close" id="sidebar-close" type="button" aria-label="Cerrar navegación">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" aria-hidden="true"><path d="M6 6l12 12M18 6L6 18"/></svg>
        </button>
    </div>

    <nav class="sidebar-navigation" aria-label="Navegación principal">
        <span class="sidebar-section-label">Navegación</span>
        <ul class="sidebar-menu">
            <li>
                <a class="sidebar-link ${dashboardActive ? 'is-active' : ''}" href="${pageContext.request.contextPath}/dashboard" aria-current="${dashboardActive ? 'page' : 'false'}">
                    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" aria-hidden="true"><rect x="4" y="4" width="6" height="6" rx="1"/><rect x="14" y="4" width="6" height="6" rx="1"/><rect x="4" y="14" width="6" height="6" rx="1"/><rect x="14" y="14" width="6" height="6" rx="1"/></svg>
                    <span>Dashboard</span>
                </a>
            </li>
            <li>
                <a class="sidebar-link ${companiesActive ? 'is-active' : ''}" href="${pageContext.request.contextPath}/companies" aria-current="${companiesActive ? 'page' : 'false'}">
                    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" aria-hidden="true"><path d="M4 20V5a1 1 0 0 1 1-1h8a1 1 0 0 1 1 1v15M2 20h20M7 8h1M7 12h1M7 16h1M11 8h1M11 12h1M11 16h1M16 10h2M16 14h2M16 18h2"/></svg>
                    <span>Empresas</span>
                </a>
            </li>
        </ul>
    </nav>

    <a href="${pageContext.request.contextPath}/logout" class="btn-logout">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" aria-hidden="true"><path d="M10 5H5v14h5M14 8l4 4-4 4M18 12H9"/></svg>
        <span>Cerrar sesión</span>
    </a>
    <span class="sidebar-version">Versión ${appVersion}</span>
</aside>

<div class="sidebar-backdrop" id="sidebar-backdrop" hidden></div>
<script type="module" src="${pageContext.request.contextPath}/resources/js/navbar.js"></script>
