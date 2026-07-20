function openNavigation(sidebar, toggle, backdrop) {
    sidebar.classList.add("is-open");
    toggle.setAttribute("aria-expanded", "true");
    backdrop.hidden = false;
    document.body.classList.add("navigation-open");
}

function closeNavigation(sidebar, toggle, backdrop) {
    sidebar.classList.remove("is-open");
    toggle.setAttribute("aria-expanded", "false");
    backdrop.hidden = true;
    document.body.classList.remove("navigation-open");
}

function toggleNavigation(sidebar, toggle, backdrop) {
    if (sidebar.classList.contains("is-open")) {
        closeNavigation(sidebar, toggle, backdrop);
        return;
    }

    openNavigation(sidebar, toggle, backdrop);
}

function initializeNavbar() {
    const sidebar = document.getElementById("main-navigation");
    const toggle = document.getElementById("sidebar-toggle");
    const close = document.getElementById("sidebar-close");
    const backdrop = document.getElementById("sidebar-backdrop");

    if (!sidebar || !toggle || !close || !backdrop) return;

    toggle.addEventListener("click", function () {
        toggleNavigation(sidebar, toggle, backdrop);
    });

    close.addEventListener("click", function () {
        closeNavigation(sidebar, toggle, backdrop);
    });

    backdrop.addEventListener("click", function () {
        closeNavigation(sidebar, toggle, backdrop);
    });

    document.addEventListener("keydown", function (event) {
        if (event.key === "Escape") {
            closeNavigation(sidebar, toggle, backdrop);
        }
    });
}

initializeNavbar();
