const submitButton = document.getElementById("submitButton");
const workerIdentity = document.querySelector('#workerIdentity');
const password = document.querySelector('#password');

function showValidationMessageWorkerIdentity(workerIdentityInput, message) {
    let messageElement = workerIdentityInput.nextElementSibling;
    if (!messageElement || !messageElement.classList.contains('validation-message')) {
        messageElement = document.createElement('span');
        messageElement.classList.add('validation-message');
        messageElement.style.color = 'red';
        workerIdentityInput.parentNode.insertBefore(messageElement, workerIdentityInput.nextElementSibling);
    }

    messageElement.textContent = message;
}

function showValidationMessagePassword(passwordInput, message) {
    let messageElement = passwordInput.nextElementSibling;
    if (!messageElement || !messageElement.classList.contains('validation-message')) {
        messageElement = document.createElement('span');
        messageElement.classList.add('validation-message');
        messageElement.style.color = 'red';
        passwordInput.parentNode.insertBefore(messageElement, passwordInput.nextElementSibling);
    }

    messageElement.textContent = message;
}

function clearValidationMessage(workerIdentityInput, passwordInput) {
    let workerIdentity = workerIdentityInput.nextElementSibling;
    let password = passwordInput.nextElementSibling;
    if (workerIdentity.classList.contains('validation-message')) {
        workerIdentity.textContent = '';
        workerIdentity.classList.remove('validation-message');
    }
    if (password.classList.contains('validation-message')) {
        password.textContent = '';
        password.classList.remove('validation-message');
    }
}

function validation() {

    if (!workerIdentity.value) {
        workerIdentity.style.border = "5px solid";
        workerIdentity.style.borderColor = "red";
        showValidationMessageWorkerIdentity(workerIdentity, 'You should write your identity.');
    }
    if (!password.value) {
        password.style.border = "5px solid";
        password.style.borderColor = "red";
        showValidationMessagePassword(password, 'You should write your password.');
    }
    if (workerIdentity.value) {
        workerIdentity.style.border = "";
        clearValidationMessage(workerIdentity, password);
    }
    if (password.value) {
        password.style.border = "";
        clearValidationMessage(password);
    }
}


submitButton.addEventListener('click', e => {
    e.preventDefault();
    validation();
});