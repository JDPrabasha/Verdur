$(document).ready(function(){
    let name = window.localStorage.getItem("name"),
    photo = window.localStorage.getItem("photo")
    $("#profile-name").html(name);
    $("#profile-image").attr("src",photo)
})