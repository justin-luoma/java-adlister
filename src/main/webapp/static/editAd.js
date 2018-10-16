"use strict";

const toggleEditing = (type) => {
    const editingElems = document.getElementsByClassName("editing");
    const viewingElems = document.getElementsByClassName("viewing");
    switch (type) {
        case "editing":
            for(let elem of editingElems) {
                elem.classList.add("d-none");
            }
            for (let elem of viewingElems) {
                elem.classList.remove("d-none");
            }
            break;
        case "viewing":
            for(let elem of editingElems) {
                elem.classList.remove("d-none");
            }
            for (let elem of viewingElems) {
                elem.classList.add("d-none");
            }
            break;
    }
};

const editClickHandler = (event) => {
    const t = event.target;
    if (t.id === "editBtn") {
        event.preventDefault();
        toggleEditing("viewing");
    }
    if (t.id === "cancelBtn") {
        event.preventDefault();
        toggleEditing("editing");
    }
};

const editInputHandler = event => {
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
        $('#updateAd').popover();
        document.getElementById("updateAd").addEventListener("click", (e) => {
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

            let catArray = [];
            for (let cat of categories.selectedOptions) {
                catArray.push(cat.text);
            }

            if (title.value.trim().toLowerCase() === initialTitle.toLowerCase()
                && description.value.trim().toLowerCase() === initialDescription.toLowerCase()
                && catArray.join(",") === initialCategories) {
                $('#updateAd').popover('show');
                return;
            }

            let urlParams = new URLSearchParams();

            if (title.value.trim().toLowerCase() !== initialTitle.toLowerCase())
                urlParams.append("title", title.value.trim());
            if (description.value.trim().toLowerCase() !== initialDescription.toLowerCase())
                urlParams.append("description", description.value.trim());
            if (catArray.join(",") !== initialCategories) {
                for (let category of categories.selectedOptions) {
                    urlParams.append("categories", category.value)
                }
            }

            fetch(`/profile/ad/${adID}`,
                {
                    "method": "post",
                    "headers": {"Content-Type": "application/x-www-form-urlencoded"},
                    "body": urlParams,
                }).then(response => response.ok ? response.json(): new Error(response.statusCode))
                .then(json => {
                    console.log(json);
                    json.errors ? new Error(json) : window.location = `/profile/ad/${json.success}`
                })
                .catch(error => console.log(error));
        });
        const select = document.getElementById("categories");
        for (let cat of initialCategories.split(",")) {
            for (let option of select.options) {
                if (option.innerText === cat) {
                    option.selected = true;
                }
            }
        }
    }
);

document.addEventListener("input", editInputHandler);
document.addEventListener("click", editClickHandler);