"use strict";

const goToPage = (page) => {
    fetch(fetchURL, {
        "method": "post",
        "headers": {"Content-Type": "application/x-www-form-urlencoded"},
        "body": new URLSearchParams(`page=${page}`),
    }).then(response => response.ok ? window.location=fetchURL: new Error(response.statusCode))
        .catch(error => console.error(error));
};

$('#confirmDeleteModal').on('show.bs.modal', function (event) {
    const button = $(event.relatedTarget); // Button that triggered the modal
    const title = button.data('title');
    const adID = button.data('id');
    const modal = $(this);
    modal.find('.modal-body #adTitle').text(title);
    modal.find('#id').text(adID);
});

document.getElementById("adDeleteBtn").addEventListener("click", () => {
    const id = document.getElementById("id").innerText;
    fetch("/profile/ads", {
        "method": "post",
        "headers": {"Content-Type": "application/x-www-form-urlencoded"},
        "body": new URLSearchParams(`adID=${id}`),
    }).then(response => response.ok ? response.json() : new Error(response.statusCode))
        .then(json => console.log(json))
        .catch(error => console.error(error))
        .then(() => {
            $('#confirmDeleteModal').modal('hide');
            location.reload();
        });
});
