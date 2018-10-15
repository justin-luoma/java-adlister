"use strict";

const createInputHandler = event => {
    const t = event.target;
    if (t.id === "title") {
        const counter = document.getElementById("titleLength");
        counter.innerText = 100 - t.value.trim().length;
    }
    if (t.id === "description") {
        const counter = document.getElementById("desLength");
        counter.innerText = 5000 - t.value.trim().length;
    }
};

document.addEventListener("DOMContentLoaded", () => {
        document.getElementById("createAd").addEventListener("click", (e) => {
            e.preventDefault();
            const title = document.getElementById("title");
            const titleInfo = document.getElementById("titleInfo");
            const description = document.getElementById("description");
            const descriptionInfo = document.getElementById("desInfo");
            const categories = document.getElementById("categories");
            const categoriesInfo = document.getElementById("catInfo");


            if (title.value.trim().length < 5) {
                titleInfo.innerText = "Enter a more descriptive title!";
                titleInfo.classList.remove("d-none");
                return;
            } else
                titleInfo.classList.add("d-none");
            if (description.value.trim().length < 15) {
                descriptionInfo.innerText = "Enter a more descriptive description!";
                descriptionInfo.classList.remove("d-none");
                return;
            } else
                descriptionInfo.classList.add("d-none");
            if (categories.selectedOptions.length < 1) {
                categoriesInfo.innerText = "You must select at least on category!";
                categoriesInfo.classList.remove("d-none");
                return;
            } else
                categoriesInfo.classList.add("d-none");
            let urlParams = new URLSearchParams();
            urlParams.append("title", title.value.trim());
            urlParams.append("description", description.value.trim());
            for (let category of categories.selectedOptions) {
                urlParams.append("categories", category.value)
            }
            fetch("/create",
                {
                    "method": "post",
                    "headers": {"Content-Type": "application/x-www-form-urlencoded"},
                    "body": urlParams,
                }).then(response => response.ok ? response.json(): new Error(response.statusCode))
                .then(json => {
                    console.log(json);
                    json.errors ? new Error(json) : window.location = `/ad/${json.success}`
                })
                .catch(error => console.log(error));
        });
    }
);

document.addEventListener("input", createInputHandler);