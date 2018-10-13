"use strict";

const clickHandler = event => {
    const t = event.target;
    if(t.id === "submit") {
        const user = document.getElementById("username");
        const pass = document.getElementById("password");

        if (user.value !== "" && pass.value !== "") {
            document.getElementById("login-form").submit();
        }
    }
};

const keypressHandler = event => {
    const t = event.target;
    const key = event.key;
    if (t.classList.contains("form-control") && key === "Enter") {
        const user = document.getElementById("username");
        const pass = document.getElementById("password");
        if (user.value !== "" && pass.value !== "")
            document.getElementById("submit").click();
    }
};

document.addEventListener('click', clickHandler);
document.addEventListener('keypress', keypressHandler);

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById("username").focus();
});