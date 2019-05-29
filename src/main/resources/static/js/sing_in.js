$(document).ready(function(){
    $("#sing_in_btn").click(function(){
        $.ajax({
            url: 'AAA/sing-in',
            dataType: 'json',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify( { "email": $('#email_sing-in').val(), "pass": $('#pass_sing-in').val() } ),
            processData: false,
            success: function( data, textStatus, jQxhr ){
                if (data.statuse==404)
                {

                    $("#singin-error").show();
                    $("#singin-error").html("");
                    $("#singin-error").append("<div class=\"alert alert-danger text-right\">\n" +
                        "                                        <b class='info-box'>ایمیل اشتباه است</b>\n" +
                        "                                    </div>");
                }
                else if(data.statuse==403)
                {

                    $("#singin-error").show();
                    $("#singin-error").html("");
                    $("#singin-error").append("<div class=\"alert alert-danger text-right\">\n" +
                        "                                        <b class='info-box'>رمز عبور اشتباه است</b>\n" +
                        "                                    </div>");
                }
                else
                {
                    location.replace("login");
                }
            },
            error: function( jqXhr, textStatus, errorThrown ){
                console.log( errorThrown );
            }
        });
    });
});