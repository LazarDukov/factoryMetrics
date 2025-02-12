document.addEventListener("DOMContentLoaded", function () {
    const urlParams = new URLSearchParams(window.location.search);

    function showPopup(message) {
        let popup = document.createElement("div");
        popup.innerHTML = `<div style="
                position: fixed;
                top: 30%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: gray;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
                z-index: 1000;
                text-align: center;
            ">
            <p style="color: red; font-weight: bold;">${message}</p>
            <button id="closePopup" style="margin-top: 10px; padding: 5px 10px; border: none; background-color: #ff4d4d; color: white; border-radius: 5px; cursor: pointer;">Try again</button>
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

