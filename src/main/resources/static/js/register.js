/**
 * Created by Administrator on 2017/10/16.
 */
$(document).ready(function(){
    $("#register").click(function(){
        var formData = new FormData($( "#uploadForm" )[0]);
        if($("#password").val()!=$("#repassword").val()){
            window.alert("请确保密码一致");
        }
        $.ajax({
            url:"/user/register",
            type:"post",
            // contentType:'application/json;',
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            data: formData,
            dataType:'json',
            success:function(date){
                if (date.code==200){
                    window.alert("注册成功");
                    var url = "/view/login";
                    window.location.href=url;
                }else {
                    window.alert(date.msg);
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });
});

function imgPreview(fileDom){
    //判断是否支持FileReader
    if (window.FileReader) {
        var reader = new FileReader();
    } else {
        alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
    }

    //将图片固定尺寸
    var tableWidth = $("#imgTable").width(); //表格宽度
    var img = new Image();
    img.src =$('#preview').attr("src") ;
    var imgWidth = img.width; //图片实际宽度
    if(imgWidth<tableWidth){
        $('#preview').attr("style","width: 500px");
    }else{
        $('#preview').attr("style","width: 500px");
    }

    //获取文件
    var file = fileDom.files[0];
    var imageType = /^image\//;
    //是否是图片
    if (!imageType.test(file.type)) {
        alert("请选择图片！");
        return;
    }
    //读取完成
    reader.onload = function(e) {
        //获取图片dom
        var img = document.getElementById("preview");
        //图片路径设置为读取的图片
        img.src = e.target.result;
    };
    reader.readAsDataURL(file);
}