/**
 * Created by Administrator on 2017/10/15.
 */
$(document).ready(function(){
    $("#login").click(function(){
        $.ajax({
            url:"/manager/login",
            type:"post",
            contentType:'application/x-www-form-urlencoded',
            data:{
                "userId":$("#username").val(),
                "password":$("#password").val()
            },
            success:function(date){
                if (date.code==200){
                    window.alert(date.msg);
                    var url = "/manager/getTypes?pageNum=1";
                    window.location.href=url;
                }else {
                    window.alert(date.msg);
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {

            }
        });
    });
    $("#register").click(function () {
        var url = "/view/register";window.location.href=url;
    })


});