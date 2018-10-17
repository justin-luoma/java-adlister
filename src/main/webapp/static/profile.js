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

function parseErrors(json, target) {
    const info = document.getElementById(`${target}Info`);
    info.classList.add("text-warning");
    info.innerText = json[target];
}

function updateUsername() {
    const oU = document.getElementById("staticUsername");
    const nU = document.getElementById("username");
    const usernameInfo = document.getElementById("usernameInfo");

    if (oU.value === nU.value) {
        usernameInfo.classList.add("text-warning");
        usernameInfo.innerText = "same as old username";
    } else if (nU.value === "") {
        usernameInfo.classList.add("text-warning");
        usernameInfo.innerText = "invalid username";
    } else {
        fetch("/profile", {
            "method": "post",
            "headers": {"Content-Type": "application/x-www-form-urlencoded"},
            "body": new URLSearchParams(`data=username&username=${nU.value}`),
        }).then(response => response.ok ? response.json() : new Error(response.statusText))
            .then(json => json.errors ? parseErrors(json, "username") : location.reload())
            .catch(error => console.error(error));
    }
}

function updateEmail() {
    const oE = document.getElementById("staticEmail");
    const nE = document.getElementById("email");
    const emailInfo = document.getElementById("emailInfo");

    if (oE.value === nE.value) {
        emailInfo.classList.add("text-warning");
        emailInfo.innerText = "same as old email";
    } else if (nE.value === "" || !validateEmail(nE.value)) {
        emailInfo.classList.add("text-warning");
        emailInfo.innerText = "invalid email";
    } else {
        fetch("/profile", {
            "method": "post",
            "headers": {"Content-Type": "application/x-www-form-urlencoded"},
            "body": new URLSearchParams(`data=email&email=${nE.value}`),
        }).then(response => response.ok ? response.json() : new Error(response.statusText))
            .then(json => json.errors ? parseErrors(json, "email") : location.reload())
            .catch(error => console.error(error));
    }
}

function updatePassword() {
    const cP = document.getElementById("passwordCurrent");
    const cPInfo = document.getElementById("passwordCurrentInfo");
    const pass = document.getElementById("password");
    const passVerify = document.getElementById("passwordVerify");
    const passwordInfo = document.getElementById("passwordInfo");
    const passwordVerifyInfo = document.getElementById("passwordVerifyInfo");

    if (cP.value === "") {
        cPInfo.classList.add("text-warning");
        cPInfo.innerText = "enter your current password";
    } else if (pass.value === "" || !passwordStrength(pass.value)) {
        passwordInfo.classList.add("text-warning");
        passwordInfo.innerText = "invalid password, must be > 8 characters, >= 1 uppercase letter, >= 1 lowercase, >= 1 number, >= 1 special";
    } else if (pass.value !== passVerify.value) {
        passwordVerifyInfo.classList.add("text-warning");
        passwordVerifyInfo.innerText = "passwords don't match"
    } else {
        fetch("/profile", {
            "method": "post",
            "headers": {"Content-Type": "application/x-www-form-urlencoded"},
            "body": new URLSearchParams(`data=password&password=${cP.value}&new=${pass.value}`),
        }).then(response => response.ok ? response.json() : new Error(response.statusText))
            .then(json => json.errors ? parseErrors(json, "password") : location.reload())
            .catch(error => console.error(error));
    }
}

document.addEventListener("DOMContentLoaded", event => {
    const username = document.getElementById("username");

    const email = document.getElementById("email");
    const emailInfo = document.getElementById("emailInfo");
    const passwordCurrent = document.getElementById("passwordCurrent");
    const passwordCurrentInfo = document.getElementById("passwordCurrentInfo");
    const password = document.getElementById("password");
    const passwordInfo = document.getElementById("passwordInfo");
    const passwordVerify = document.getElementById("passwordVerify");
    const passwordVerifyInfo = document.getElementById("passwordVerifyInfo");

    function clickEvent(event) {
        const uViewing = document.getElementsByClassName("viewing");
        const uEditing = document.getElementsByClassName("editing");

        if (event.target.classList.contains("editBtn")) {
            for (elem of uViewing) {
                if (elem.classList.contains(event.target.dataset.target))
                    elem.classList.add("d-none");
            }
            for (elem of uEditing) {
                if (elem.classList.contains(event.target.dataset.target))
                    elem.classList.remove("d-none");
            }
        }

        if (event.target.classList.contains("cancelBtn")) {
            for (elem of uViewing) {
                if (elem.classList.contains(event.target.dataset.target))
                    elem.classList.remove("d-none");
            }
            for (elem of uEditing) {
                if (elem.classList.contains(event.target.dataset.target))
                    elem.classList.add("d-none");
            }
        }

        if (event.target.classList.contains("updateBtn")) {
            switch (event.target.dataset.target) {
                case "username":
                    updateUsername();
                    break;
                case "email":
                    updateEmail();
                    break;
                case "password":
                    updatePassword();
                    break;
            }
        }
    }

    document.addEventListener("click", clickEvent);
});