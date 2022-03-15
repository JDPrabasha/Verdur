$(document).ready(function(){
    $("#loading").load("/Client/Manager/Loading.html #loading")
    $("#loading").on("loaded",function(){
        // console.log("123")
        $("#loading").attr("style","display:none")
    })
})