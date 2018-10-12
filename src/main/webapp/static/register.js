"use strict";


function validateEmail(email) {
    const re = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    return re.test(email);
}

function passwordStrength(password) {
    const strongRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[;:'\"!@#$%^&*()_=+|?/.>,<`~\-{}\[\]\\ ])(?=.{8,})/;
    return strongRegex.test(password);
}

function validatePassword(password1, password2) {
    return password1 === password2;
}

let invalidUsername = "";
let invalidEmail = "";

let usernameValid = false;
let emailValid = false;
let passwordValid = false;

const inputHandler = event => {
    const t = event.target;
    if (t.id === "username") {
        const info = document.getElementById("usernameHelp");
        if (t.value === invalidUsername || t.value === "") {
            info.classList.remove("text-muted", "text-info");
            info.classList.add("text-warning");
            info.innerText = "Username invalid!";
            usernameValid = false;
        } else {
            info.classList.remove("text-muted", "text-warning");
            info.classList.add("text-info");
            info.innerText = "Username valid!";
            usernameValid = true;
        }
    }
    if (t.id === "email") {
        const info = document.getElementById("emailHelp");
        if (!validateEmail(t.value) || t.value === invalidEmail) {
            info.classList.remove("text-muted", "text-info");
            info.classList.add("text-warning");
            info.innerText = "Please enter a valid email address!";
            emailValid = false;
        } else {
            info.classList.remove("text-muted", "text-warning");
            info.classList.add("text-info");
            info.innerText = "Email valid!";
            emailValid = true;
        }
    }
    if (t.id === "password" || t.id === "passwordVerify") {
        const infoPassword = document.getElementById("passwordHelp");
        const infoPasswordVerify = document.getElementById("passwordVerifyHelp");
        const password = document.getElementById("password");
        const passwordVerify = document.getElementById("passwordVerify");
        if (!passwordStrength(password.value)) {
            infoPassword.classList.remove("text-muted", "text-info");
            infoPassword.classList.add("text-warning");
            infoPassword.innerText = "Password does not meet complexity requirements";
            passwordValid = false;
        } else {
            infoPassword.classList.remove("text-muted", "text-warning");
            infoPassword.classList.add("text-info");
            infoPassword.innerText = "Password valid!";
        }
        if (!validatePassword(password.value, passwordVerify.value)) {
            infoPasswordVerify.classList.remove("text-muted", "text-info");
            infoPasswordVerify.classList.add("text-warning");
            infoPasswordVerify.innerText = "Passwords do not match!";
            passwordValid = false;
        } else {
            infoPasswordVerify.classList.remove("text-muted", "text-warning");
            infoPasswordVerify.classList.add("text-info");
            infoPasswordVerify.innerText = "Passwords valid!";
            passwordValid = true;
        }
    }
    if (usernameValid && emailValid && passwordValid) {
        document.getElementById("submit").classList.remove("disabled");
    } else {
        document.getElementById("submit").classList.add("disabled");
    }
};

const clickHandler = event => {
    const t = event.target;
    if(t.id === "submit" && !t.classList.contains("disabled")) {
        console.log(t.id);
        document.getElementById("register-form").submit();
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

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
});

document.addEventListener('input', inputHandler);
document.addEventListener('click', clickHandler);
document.addEventListener('keypress', keypressHandler);


document.addEventListener("DOMContentLoaded", () => {
    if (typeof data_user !== "undefined") {
        const username = document.getElementById("username");
        const email = document.getElementById("email");
        if (data_user.username !== undefined) {
            username.value = data_user.username;
            usernameValid = true;
        }
        if (data_user.email !== undefined) {
            email.value = data_user.email;
            emailValid = true;
        }
    }
    if (typeof data_errors !== "undefined") {
        const usernameInfo = document.getElementById("usernameHelp");
        const emailInfo = document.getElementById("emailHelp");
        const passwordInfo = document.getElementById("passwordHelp");
        const passwordVerifyInfo = document.getElementById("passwordVerifyHelp");
        if (data_errors.username !== undefined) {
            usernameInfo.classList.remove("text-muted", "text-info");
            usernameInfo.classList.add("text-warning");
            usernameInfo.innerText = data_errors.username;
            invalidUsername = data_user.username;
            usernameValid = false;
        }
        if (data_errors.email !== undefined) {
            emailInfo.classList.remove("text-muted", "text-info");
            emailInfo.classList.add("text-warning");
            emailInfo.innerText = data_errors.email;
            invalidEmail = data_user.email;
            usernameValid = false;
        }
        if (data_errors.password !== undefined) {
            passwordInfo.classList.remove("text-muted", "text-info");
            passwordInfo.classList.add("text-warning");
            passwordInfo.innerText = data_errors.password;
            passwordVerifyInfo.classList.remove("text-muted", "text-info");
            passwordVerifyInfo.classList.add("text-warning");
            passwordVerifyInfo.innerText = data_errors.password;
            passwordValid = false;
        }
    }
});
