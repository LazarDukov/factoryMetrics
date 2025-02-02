// const form = document.getElementById('submitButton');
// const workerIdentity = document.querySelector('#workerIdentity');
// const password = document.querySelector('#password');
//
// function showValidationMessage(input, message) {
//     let messageElement = input.nextElementSibling;
//     if (!messageElement || !messageElement.classList.contains('validation-message')) {
//         messageElement = document.createElement('span');
//         messageElement.classList.add('validation-message');
//         messageElement.style.color = 'red';
//         input.parentNode.insertBefore(messageElement, input.nextElementSibling);
//     }
//     messageElement.textContent = message;
// }
//
// function clearValidationMessage(input) {
//     let messageElement = input.nextElementSibling;
//     if (messageElement && messageElement.classList.contains('validation-message')) {
//         messageElement.textContent = '';
//         messageElement.classList.remove('validation-message');
//     }
// }
//
// function validation() {
//     let isValid = true;
//
//     if (!workerIdentity.value) {
//         workerIdentity.style.border = "5px solid red";
//         showValidationMessage(workerIdentity, 'You should write your identity.');
//         isValid = false;
//     } else {
//         workerIdentity.style.border = "";
//         clearValidationMessage(workerIdentity);
//     }
//
//     if (!password.value) {
//         password.style.border = "5px solid red";
//         showValidationMessage(password, 'You should write your password.');
//         isValid = false;
//     } else {
//         password.style.border = "";
//         clearValidationMessage(password);
//     }
//
//     return isValid; // Return true if valid, false otherwise
//
// }
//
//
// form.addEventListener('submit', async e => {
//     e.preventDefault();
//     validation();
//
// });

document.addEventListener("DOMContentLoaded", function () {
    const urlParams = new URLSearchParams(window.location.search);

    function showPopup(message) {
        let popup = document.createElement("div");
        popup.innerHTML = `<div style="
                position: fixed;
                top: 30%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
                z-index: 1000;
                text-align: center;
            ">
            <p style="color: red; font-weight: bold;">${message}</p>
            <button id="closePopup" style="margin-top: 10px; padding: 5px 10px; border: none; background-color: #ff4d4d; color: white; border-radius: 5px; cursor: pointer;">OK</button>
        </div>
            `;
        document.body.appendChild(popup);

        document.getElementById("closePopup").addEventListener("click", function () {
            popup.remove();

            removeErrorParam(); // Remove ?error=true from the URL after closing
            function removeErrorParam() {
                const newUrl = window.location.origin + window.location.pathname;
                window.history.replaceState({}, document.title, newUrl);

            }
        });
    }

    if (urlParams.has("error")) {
        showPopup("Invalid username or password");
    }


})
