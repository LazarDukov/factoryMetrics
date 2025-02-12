document.addEventListener("DOMContentLoaded", function () {
    const loginButton = document.getElementById("loadLoginPage");
    const registerButton = document.getElementById("loadRegisterPage");

    // Event listeners for login and register buttons
    loginButton.addEventListener("click", loadLoginPage);
    registerButton.addEventListener("click", loadRegisterPage);

    // Check if the user has a valid token
});

function loadLoginPage() {
    console.log("Login button clicked");
    window.location.href = "http://localhost:8080/user/login";
}

function loadRegisterPage() {
    window.location.href = "http://localhost:8080/user/register";
}



