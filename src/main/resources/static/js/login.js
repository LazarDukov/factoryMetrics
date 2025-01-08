const submitButton = document.getElementById("submitButton");
const workerIdentity = document.querySelector('#workerIdentity');

function showValidationMessage(input, message) {
    let messageElement = input.nextElementSibling;
    messageElement = document.createElement('span');
    messageElement.classList.add('validation-message');
    messageElement.style.color = 'red';
    input.parentNode.insertBefore(messageElement, input.nextElementSibling);
    messageElement.textContent = message;
}

function nameValidation() {
    if (!workerIdentity.value) {
        workerIdentity.style.border = "5px solid";
        workerIdentity.style.borderColor = "red";
        showValidationMessage(workerIdentity, 'This field should not be empty.');
    }
}

submitButton.addEventListener('click', e => {
    e.preventDefault();
    nameValidation();
});