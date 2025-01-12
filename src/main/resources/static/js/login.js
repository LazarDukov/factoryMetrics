const form = document.getElementById('submitButton');
const workerIdentity = document.querySelector('#workerIdentity');
const password = document.querySelector('#password');

function showValidationMessage(input, message) {
    let messageElement = input.nextElementSibling;
    if (!messageElement || !messageElement.classList.contains('validation-message')) {
        messageElement = document.createElement('span');
        messageElement.classList.add('validation-message');
        messageElement.style.color = 'red';
        input.parentNode.insertBefore(messageElement, input.nextElementSibling);
    }
    messageElement.textContent = message;
}

function clearValidationMessage(input) {
    let messageElement = input.nextElementSibling;
    if (messageElement && messageElement.classList.contains('validation-message')) {
        messageElement.textContent = '';
        messageElement.classList.remove('validation-message');
    }
}

function validation() {
    let isValid = true;

    if (!workerIdentity.value) {
        workerIdentity.style.border = "5px solid red";
        showValidationMessage(workerIdentity, 'You should write your identity.');
        isValid = false;
    } else {
        workerIdentity.style.border = "";
        clearValidationMessage(workerIdentity);
    }

    if (!password.value) {
        password.style.border = "5px solid red";
        showValidationMessage(password, 'You should write your password.');
        isValid = false;
    } else {
        password.style.border = "";
        clearValidationMessage(password);
    }

    return isValid; // Return true if valid, false otherwise

}


form.addEventListener('submit', async e => {
    e.preventDefault();
    validation();

});