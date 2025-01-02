document.addEventListener("DOMContentLoaded", function () {
    const loginButton = document.getElementById("loadLoginPage");
    loginButton.addEventListener("click", loadLoginPage);
});

document.addEventListener("DOMContentLoaded", function () {
    const registerButton = document.getElementById("loadRegisterPage");
    registerButton.addEventListener("click", loadRegisterPage);
});

function loadLoginPage() {
    window.location.href = "http://localhost:8080/user/login";
}

function loadRegisterPage() {
    window.location.href = "http://localhost:8080/user/register";
}