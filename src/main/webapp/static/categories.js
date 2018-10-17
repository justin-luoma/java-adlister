"use strict";

function buildCategories(json) {
    const adsMenu = document.getElementById("adsMenu");
    json.forEach(category => {
        adsMenu.innerHTML += `
            <a class="dropdown-item" href="/ads?category=${category.id}">${category.name}</a>
        `;
    });
}

document.addEventListener("DOMContentLoaded", () => {
    fetch("/categories",{
        method: "post",
    }).then(response => response.ok ? response.json() : new Error(response.statusText))
        .then(json => buildCategories(json))
        .catch(error => console.error(error));
});