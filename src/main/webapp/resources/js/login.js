function togglePasswordVisibility(password, toggle) {
    const showPassword = password.type === "password";

    password.type = showPassword ? "text" : "password";
    toggle.textContent = showPassword ? "Ocultar" : "Mostrar";
    toggle.setAttribute("aria-label", showPassword ? "Ocultar contraseña" : "Mostrar contraseña");
    toggle.setAttribute("aria-pressed", String(showPassword));
}

function setSubmitLoadingState(submit) {
    submit.disabled = true;
    submit.classList.add("is-loading");
    submit.querySelector(".button-label").textContent = "Ingresando...";
}

function handleFormSubmit(form, submit) {
    if (!form.checkValidity()) return;
    setSubmitLoadingState(submit);
}

function initializeLoginForm() {
    const form = document.getElementById("login-form");
    const password = document.getElementById("password");
    const toggle = document.querySelector(".password-toggle");
    const submit = document.getElementById("login-submit");

    if (!form || !password || !toggle || !submit) return;

    toggle.addEventListener("click", function () {
        togglePasswordVisibility(password, toggle);
    });

    form.addEventListener("submit", function () {
        handleFormSubmit(form, submit);
    });
}

initializeLoginForm();
