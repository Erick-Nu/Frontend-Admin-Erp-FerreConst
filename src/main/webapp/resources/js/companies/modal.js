function sanitizeRuc(value) {
    return value.replace(/\D/g, "").slice(0, 13);
}

function sanitizeCompanyCode(value) {
    var normalized = value.toUpperCase();
    var letters = normalized.replace(/[^A-Z]/g, "").slice(0, 2);
    var digits = normalized.replace(/\D/g, "").slice(0, 2);
    return (letters + digits).slice(0, 4);
}

function openRegisterCompanyModal(modal, businessNameInput, errorBanner, form) {
    modal.hidden = false;
    document.body.style.overflow = "hidden";
    form.reset();
    errorBanner.hidden = true;
    if (businessNameInput) businessNameInput.focus();
}

function closeRegisterCompanyModal(modal) {
    modal.hidden = true;
    document.body.style.overflow = "";
}

function isFormDirty(rucInput, codeInput, businessNameInput, emailInput) {
    return rucInput.value.trim() !== ""
        || codeInput.value.trim() !== ""
        || businessNameInput.value.trim() !== ""
        || emailInput.value.trim() !== "";
}

function handleModalClose(modal, rucInput, codeInput, businessNameInput, emailInput) {
    if (isFormDirty(rucInput, codeInput, businessNameInput, emailInput)) {
        var discard = window.confirm("¿Descartar los cambios? Los datos ingresados se perderán.");
        if (!discard) return;
    }
    closeRegisterCompanyModal(modal);
}

function showModalError(errorBanner, message) {
    errorBanner.textContent = message;
    errorBanner.hidden = false;
}

function validateRegisterCompanyForm(event, rucInput, codeInput, businessNameInput, emailInput, errorBanner) {
    var ruc = rucInput.value.trim();
    var code = codeInput.value.trim().toUpperCase();
    var businessName = businessNameInput.value.trim();
    var email = emailInput.value.trim();

    codeInput.value = code;
    errorBanner.hidden = true;

    if (!/^\d{13}$/.test(ruc)) {
        event.preventDefault();
        showModalError(errorBanner, "El RUC debe contener exactamente 13 dígitos.");
        return;
    }

    if (!/^[A-Z]{2}\d{2}$/.test(code)) {
        event.preventDefault();
        showModalError(errorBanner, "El código debe tener dos letras mayúsculas y dos números.");
        return;
    }

    if (!businessName) {
        event.preventDefault();
        showModalError(errorBanner, "Ingresa la razón social de la empresa.");
        return;
    }

    if (!email) {
        event.preventDefault();
        showModalError(errorBanner, "Ingresa el correo corporativo de la empresa.");
        return;
    }
}

function initializeModalRegisterForm() {
    var modal = document.getElementById("register-modal");
    var openButton = document.getElementById("open-register-modal");
    var closeButton = document.getElementById("modal-close");
    var cancelButton = document.getElementById("modal-cancel");
    var form = document.getElementById("modal-register-form");
    var errorBanner = document.getElementById("modal-error");
    var rucInput = document.getElementById("modal-ruc");
    var codeInput = document.getElementById("modal-code");
    var businessNameInput = document.getElementById("modal-businessName");
    var emailInput = document.getElementById("modal-email");

    if (!modal || !openButton || !closeButton || !cancelButton || !form) return;

    openButton.addEventListener("click", function () {
        openRegisterCompanyModal(modal, businessNameInput, errorBanner, form);
    });

    closeButton.addEventListener("click", function () {
        handleModalClose(modal, rucInput, codeInput, businessNameInput, emailInput);
    });

    cancelButton.addEventListener("click", function () {
        handleModalClose(modal, rucInput, codeInput, businessNameInput, emailInput);
    });

    modal.addEventListener("click", function (event) {
        if (event.target.id === "register-modal") {
            handleModalClose(modal, rucInput, codeInput, businessNameInput, emailInput);
        }
    });

    document.addEventListener("keydown", function (event) {
        if (event.key === "Escape" && !modal.hidden) {
            handleModalClose(modal, rucInput, codeInput, businessNameInput, emailInput);
        }
    });

    if (rucInput) {
        rucInput.addEventListener("input", function () {
            rucInput.value = sanitizeRuc(rucInput.value);
        });
    }

    if (codeInput) {
        codeInput.addEventListener("input", function () {
            codeInput.value = sanitizeCompanyCode(codeInput.value);
        });
    }

    form.addEventListener("submit", function (event) {
        validateRegisterCompanyForm(event, rucInput, codeInput, businessNameInput, emailInput, errorBanner);
    });

    if (!modal.hidden) {
        document.body.style.overflow = "hidden";
    }
}

initializeModalRegisterForm();
