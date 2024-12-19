let logInButton = document.createElement('log-in');
logInButton.textContent = 'Login';
logInButton.addEventListener("mouseover", () => logInButton.style.color = 'gray');
logInButton.addEventListener("click", () => window.location.href = '/user/login');
document.getElementById('logInButton').appendChild(logInButton);

let registerButton = document.createElement('register');
registerButton.textContent = 'Register';
registerButton.addEventListener("click", () => window.location.href = '/api/register');
document.getElementById('registerButton').appendChild(registerButton);