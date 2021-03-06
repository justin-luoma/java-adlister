"use strict";

const generateResult = (data) => {
    const resultsDiv = document.getElementById("searchResults");
    let html = "";
    if (data === null) {
        resultsDiv.innerHTML = "";
        return;
    }
    data.forEach(item => {
        html += `
            <div class="col-12">
                <div class="card mt-1 hoverCursor" onclick="window.location='/ad/${item.id}'">
                    <div class="card-body">
                        <h5 class="card-title">${item.title}</h5>
                        <p class="card-text">${item.description}</p>
                    </div>
                </div>
            </div>
        `;
    });
    resultsDiv.innerHTML = html;
};

const searchHandler = event => {
    const t = event.target;
    const query = t.value;
    if (query.length > 2) {
        fetch("/search", {
            "method": "post",
            "headers": {"Content-Type": "application/x-www-form-urlencoded"},
            "body": new URLSearchParams(`search=${query}`),
        }).then(response => response.ok ? response.json() : new Error(response.statusCode))
            .then(json => generateResult(json))
            .catch(error => console.error(error));
        // generateResult(query, "test description");
    } else
        document.getElementById("searchResults").innerHTML = "";
};

document.addEventListener("DOMContentLoaded", () => {
    const navbarBgColor = tinycolor(document.getElementById("navbar").style.backgroundColor);
    const searchIcon = document.getElementById("searchIcon");
    if (navbarBgColor.isDark) {
        searchIcon.style.color = "white";
    } else {
        searchIcon.style.color = "#222";
    }
    document.getElementById("searchInput").addEventListener('input', searchHandler);
    $('#searchModal').on('shown.bs.modal', function (e) {
        $('#searchInput').trigger('focus');
    });

    document.getElementById('navbarSearch').addEventListener('focus', e => {
        $('#searchModal').modal('show');
    });

    $('.modal').on('show.bs.modal', function (e) {
        $('.modal .search-dialog').attr('class', 'modal-dialog  slideInRight  animated search-dialog');
    })
    $('.modal').on('hide.bs.modal', function (e) {
        $('.modal .search-dialog').attr('class', 'modal-dialog  slideOutRight  animated search-dialog');
        document.getElementById("navbarSearch").value = document.getElementById("searchInput").value;
    })
});
