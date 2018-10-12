"use strict";

const goToPage = (page) => {
    console.log(page);
    fetch("/ads", {
        "method": "post",
        "headers": {"Content-Type": "application/x-www-form-urlencoded"},
        "body": new URLSearchParams(`page=${page}`),
    }).then(response => response.ok ? window.location="/ads": new Error(response.statusCode))
        .catch(error => console.error(error));
};