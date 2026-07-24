var toastTimer = null;

function hideToast() {
    var toast = document.getElementById("companies-toast");
    if (!toast) return;
    toast.hidden = true;
    if (toastTimer) {
        clearTimeout(toastTimer);
        toastTimer = null;
    }
}

function showToast(type, message) {
    var toast = document.getElementById("companies-toast");
    var icon = document.getElementById("companies-toast-icon");
    var msgEl = document.getElementById("companies-toast-message");

    if (!toast || !icon || !msgEl) return;

    hideToast();

    toast.className = "companies-toast is-" + type;
    icon.textContent = type === "success" ? "\u2713" : "\u2717";
    msgEl.textContent = message;
    toast.hidden = false;

    if (type === "success") {
        toastTimer = setTimeout(function () {
            hideToast();
        }, 5000);
    }
}

function initializeToast() {
    var closeBtn = document.getElementById("companies-toast-close");
    if (closeBtn) {
        closeBtn.addEventListener("click", hideToast);
    }
}

function initializeStatusForms() {
    var forms = document.querySelectorAll(".status-form");

    forms.forEach(function (form) {
        var select = form.querySelector(".status-select");
        if (!select) return;

        form.addEventListener("submit", function (event) {
            if (select.value === "eliminado") {
                var confirmed = window.confirm("\u00bfEst\u00e1s seguro de eliminar esta empresa? Esta acci\u00f3n no se puede deshacer.");
                if (!confirmed) {
                    event.preventDefault();
                }
            }
        });
    });
}

initializeToast();
initializeStatusForms();