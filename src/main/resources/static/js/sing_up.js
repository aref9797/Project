$(document).ready(function(){
    $("#sing_up_btn").click(function(){
        $.ajax({
            url: 'AAA/sing-up',
            dataType: 'json',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify( {"type":"", "codemelli":$("#singup_mellicode").val(),"family":$("#singup_family").val(),"name": $('#singup_name').val(), "email": $('#singup_email').val(), "pass": $('#singup_pass').val() } ),
            processData: false,
            success: function( data, textStatus, jQxhr ) {
                if (data.statuse == 404) {
                    $("#singup-error").show();
                    $("#singup-error").html("");
                    $("#singup-error").append("<div class=\"alert alert-danger text-right\">\n" +
                        "                                        <b class='info-box'>ایمیل تکراری است</b>\n" +
                        "                                    </div>");
                }
                if (data.statuse == 405)
                {
                    $("#singup-error").show();
                    $("#singup-error").html("");
                    $("#singup-error").append("<div class=\"alert alert-danger text-right\">\n" +
                        "                                        <b class='info-box'>کد ملی اشتباه است</b>\n" +
                        "                                    </div>");
                }
                else
                {
                    $("#singup").hide(1000,"swing",function () {

                        $("#info").hide(100,"swing",function () {
                            $("#singin").show(1000,"swing",function () {
                                $("#info").show(100,function () {
                                    $("#singin-error").show();
                                    $("#singin-error").html("");
                                    $("#singin-error").append("<div class=\"alert alert-success text-right\">\n" +
                                        "                                        <b class='info-box'>با موفقیت ثبت نام شدید لطفا وارد شوید</b>\n" +
                                        "                                    </div>");
                                    $("input").val("");
                                });
                            });
                        });

                    });
                }
            },
            error: function( jqXhr, textStatus, errorThrown ){
                console.log( errorThrown );
            }
        });
    });
});