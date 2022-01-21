let replyMain = {
    init : function () {
    let _this = this;
    $('#btn-save').on('click', function () {
    _this.save();
    });
    $('#btn-update').on('click', function () {
        _this.update();
    });
    $('#btn-delete').on('click', function () {
        _this.delete();
    });
    },
    save: function() {
        let data = {
            writer: $('#reply-writer').val(),
            content: $('#reply-content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/reply/add',
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done()
        .fail(function (error) {
            alert(JSON.stringify(error))
        })
    },

    update: function() {
        let data = {
            content: $('#reply-content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/reply/update',
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done()
        .fail(function (error){
            alert(JSON.stringify(error))
        })
    },

    delete: function() {
        let id = $('#reply-id').val();

        $.ajax({
            type: 'POST',
            url: '/reply/delete',
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
        }).done()
        .fail(function (error) {
            alert(JSON.stringify(error))
        });
    }
};
