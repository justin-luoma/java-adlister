<div class="modal fade confirmDeleteModal" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmDeleteModalLabel">Are you sure?</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <h6>Delete ad with title:</h6>
                <p id="adTitle"></p>
                <label class="d-none" id="id"></label>
                <%--<form>--%>
                <%--<div class="form-group">--%>
                <%--<label for="recipient-name" class="col-form-label">Recipient:</label>--%>
                <%--<input type="text" class="form-control" id="recipient-name">--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                <%--<label for="message-text" class="col-form-label">Message:</label>--%>
                <%--<textarea class="form-control" id="message-text"></textarea>--%>
                <%--</div>--%>
                <%--</form>--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-danger" id="adDeleteBtn">Delete</button>
            </div>
        </div>
    </div>
</div>