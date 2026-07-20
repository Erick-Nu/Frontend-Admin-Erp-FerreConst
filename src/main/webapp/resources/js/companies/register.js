const RUC_MAX_LENGTH = 13;
const COMPANY_CODE_MAX_LENGTH = 4;

function sanitizeRuc(value) {
    return value.replace(/\D/g, "").slice(0, RUC_MAX_LENGTH);
}

function sanitizeCompanyCode(value) {
    const normalizedValue = value.toUpperCase();
    const letters = normalizedValue.replace(/[^A-Z]/g, "").slice(0, 2);
    const digits = normalizedValue.replace(/\D/g, "").slice(0, 2);

    return (letters + digits).slice(0, COMPANY_CODE_MAX_LENGTH);
}

function normalizeRucInput(rucInput) {
    rucInput.value = sanitizeRuc(rucInput.value);
}

function normalizeCompanyCodeInput(companyCodeInput) {
    companyCodeInput.value = sanitizeCompanyCode(companyCodeInput.value);
}

function initializeCompanyRegisterForm() {
    const rucInput = document.getElementById("ruc");
    const companyCodeInput = document.getElementById("code");

    if (!rucInput || !companyCodeInput) return;

    rucInput.addEventListener("input", function () {
        normalizeRucInput(rucInput);
    });

    companyCodeInput.addEventListener("input", function () {
        normalizeCompanyCodeInput(companyCodeInput);
    });
}

initializeCompanyRegisterForm();
