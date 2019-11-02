$(function () {

    $('#login-form-link').click(function (e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $("#forgot-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function (e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $("#forgot-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#forgot-form-link').click(function (e) {
        $("#forgot-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $('#login-form-link').removeClass('active');
        e.preventDefault();
    });

    $("input[type='password']").change(function () {
        var value = $(this).val();
        var ok = $(this).parent().find('.ok');
        var error = $(this).parent().find('.error');
        var height = $(this).parent().css("height") / 2;
        if (value.length >= 8 && value.length < 20) {
            ok.show();
            ok.css("top", height + "px");
            error.hide();
        } else {
            error.show();
            error.css("top", height + "px");
            ok.hide();
        }
    });

    $("input[type='email']").change(function () {
        var value = $(this).val();
        var ok = $(this).parent().find('.ok');
        var error = $(this).parent().find('.error');
        var height = $(this).position().top + 13;
        if (value.contains('@') &&
            (value.contains('gmail.com' || value.contains('mail.ru') || value.contains('ukr.net')))
            && value.split('@')[0].length > 3) {
            ok.show();
            ok.css("top", height + "px");
            error.hide();
        } else {
            error.show();
            error.css("top", height + "px");
            ok.hide();
        }
    });

    $("input[type='date']").change(function () {
        var value = $(this).val();
        var ok = $(this).parent().find('.ok');
        var error = $(this).parent().find('.error');
        var height = $(this).position().top + 13;
        var date = new Date(value);
        if (date < new Date()) {
            ok.show();
            ok.css("top", height + "px");
            error.hide();
        } else {
            error.show();
            error.css("top", height + "px");
            ok.hide();
        }
    });

    $("input[type='text']").change(function () {
        var name = $(this).attr("name");
        var value = $(this).val();
        var ok = $(this).parent().find('.ok');
        var error = $(this).parent().find('.error');
        var height = $(this).css("top");
        if (name == 'username' || name == 'name' || name == 'surname') {
            if (value.length > 3 && value.length <= 20) {
                ok.show();
                ok.css("top", height + "px");
                error.hide();
            } else {
                error.show();
                error.css("top", height + "px");
                ok.hide();
            }
        } else if (name == 'phone') {
            if (value.length == 13 && value.startsWith('+380')) {
                ok.show();
                ok.css("top", height + "px");
                error.hide();
            } else {
                error.show();
                error.css("top", height + "px");
                ok.hide();
            }
        }
    });


});