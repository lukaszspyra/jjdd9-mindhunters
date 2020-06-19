$(document).ready(function () {
    $('#ID03').on('click', '#FAVOURITES', function () {
        let drinkId = $(this).siblings('p').text().trim().toString();

        let url = new URL(window.location);

        let newURL = url.origin + '/list?page=1';

        $.post(newURL, {drinkId: drinkId});

        $(this).toggleClass("color_toggle_on");
    });
});

$(document).ready(function () {
    $('#ID03').on('click', '#DELETE', function () {
        let drinkId = $(this).siblings('p').text().trim().toString();

        $.ajax({
            url: '/drink-management?id=' + drinkId,
            type: 'DELETE',
        });

    });
});

$(document).ready(function () {
    $('#ID03').on('click', '#EDIT', function () {
        let drinkId = $(this).siblings('p').text().trim().toString();

        window.location = '/drink-management?id=' + drinkId + '&action=edit';
        // let value = $(this).val();
        //
        // $.ajax({
        //     type: 'GET',
        //     url: '/drink-management?id=' + drinkId + '&action=edit',
        //     success: function() {
        //         $('#abc').html(value);
        //     }

        // });
    });
});
