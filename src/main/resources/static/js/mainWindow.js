/**
 * Created by Administrator on 2017/10/17.
 */
$(document).ready(function(){
    $(".model").attr("style","border:0px solid #7E9DB9;");

    $(".model").mouseover(function () {
        $(".model").attr("style","border:0px solid #EDBB72;");
    });
    $(".model").mouseout(function () {   $(this).attr("style","border:0px solid #7E9DB9;");  });
    $("#sign01").click(function () {
        var url = "/manager/getTypes?condition=1";
        window.location.href=url;
    });
    $("#sign02").click(function () {
        var url = "/manager/getMessages?pageNum=1";
        window.location.href=url;
    });
    $("#sign03").click(function () {
        var url = "/manager/getArticles?pageNum=1";
        window.location.href=url;
    });
    $("#sign04").click(function () {
        var url = "/view/loginout";
        window.location.href=url;
    });

    $("#sex").change(function () {
        if($("#sex").text() == 0)  $("#sex").text("男");
        if($("#sex").text() == 1)  $("#sex").text("女");
    })
    // $("#photoFile").attr("src", "E:/ffm/users/sny.png");
});