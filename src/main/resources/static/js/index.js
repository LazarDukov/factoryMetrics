document.addEventListener("DOMContentLoaded", function () {
    const loginButton = document.getElementById("loadLoginPage");
    loginButton.addEventListener("click", loadLoginPage);
});
function loadLoginPage() {
    window.location.href = "http://localhost:8080/user/login";
}